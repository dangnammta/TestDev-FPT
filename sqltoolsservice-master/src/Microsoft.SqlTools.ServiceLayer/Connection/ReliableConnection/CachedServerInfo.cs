﻿//
// Copyright (c) Microsoft. All rights reserved.
// Licensed under the MIT license. See LICENSE file in the project root for full license information.
//

using System;
using System.Collections.Concurrent;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using Microsoft.SqlTools.Utility;

namespace Microsoft.SqlTools.ServiceLayer.Connection.ReliableConnection
{

    /// <summary>
    /// This class caches server information for subsequent use
    /// </summary>
    internal class CachedServerInfo
    {
        /// <summary>
        /// Singleton service instance
        /// </summary>
        private static readonly Lazy<CachedServerInfo> instance
            = new Lazy<CachedServerInfo>(() => new CachedServerInfo());

        /// <summary>
        /// Gets the singleton instance
        /// </summary>
        public static CachedServerInfo Instance
        {
            get
            {
                return instance.Value;
            }
        }

        public enum CacheVariable {
            IsSqlDw,
            IsAzure,
            IsCloud
        }

        #region CacheKey implementation
        internal class CacheKey : IEquatable<CacheKey>
        {
            private string dataSource;
            private string dbName;

            public CacheKey(SqlConnectionStringBuilder builder)
            {
                Validate.IsNotNull(nameof(builder), builder);
                dataSource = builder.DataSource;
                dbName = GetDatabaseName(builder);
            }

            internal static string GetDatabaseName(SqlConnectionStringBuilder builder)
            {
                string dbName = string.Empty;
                if (!string.IsNullOrEmpty((builder.InitialCatalog)))
                {
                    dbName = builder.InitialCatalog;
                }
                else if (!string.IsNullOrEmpty((builder.AttachDBFilename)))
                {
                    dbName = builder.AttachDBFilename;
                }
                return dbName;
            }

            public override bool Equals(object obj)
            {
                if (obj == null) { return false; }

                CacheKey keyObj = obj as CacheKey;
                if (keyObj == null) { return false; }
                else { return Equals(keyObj); }
            }

            public override int GetHashCode()
            {
                unchecked // Overflow is fine, just wrap
                {
                    int hash = 17;
                    hash = (hash * 23) + (dataSource != null ? dataSource.GetHashCode() : 0);
                    hash = (hash * 23) + (dbName != null ? dbName.GetHashCode() : 0);
                    return hash;
                }
            }

            public bool Equals(CacheKey other)
            {
                return string.Equals(dataSource, other.dataSource, StringComparison.OrdinalIgnoreCase)
                    && string.Equals(dbName, other.dbName, StringComparison.OrdinalIgnoreCase);
            }
        }
        #endregion

        private struct CachedInfo
        {
            public bool IsAzure;
            public DateTime LastUpdate;
            public bool IsSqlDw;
        }

        private const int _maxCacheSize = 1024;
        private const int _deleteBatchSize = 512;
        private const int MinimalQueryTimeoutSecondsForAzure = 300;

        private ConcurrentDictionary<CacheKey, CachedInfo> _cache;
        private object _cacheLock;

        /// <summary>
        /// Internal constructor for testing purposes. For all code use, please use the <see cref="CachedServerInfo.Instance"/>
        /// default instance.
        /// </summary>
        internal CachedServerInfo()
        {
            _cache = new ConcurrentDictionary<CacheKey, CachedInfo>();
            _cacheLock = new object();
        }

        public int GetQueryTimeoutSeconds(IDbConnection connection)
        {
            SqlConnectionStringBuilder connStringBuilder = SafeGetConnectionStringFromConnection(connection);
            return GetQueryTimeoutSeconds(connStringBuilder);
        }

        public int GetQueryTimeoutSeconds(SqlConnectionStringBuilder builder)
        {
            //keep existing behavior and return the default ambient settings
            //if the provided data source is null or whitespace, or the original
            //setting is already 0 which means no limit.
            int originalValue = AmbientSettings.QueryTimeoutSeconds;
            if (builder == null || string.IsNullOrWhiteSpace(builder.DataSource)
                || (originalValue == 0))
            {
                return originalValue;
            }

            CachedInfo info;
            bool hasFound = TryGetCacheValue(builder, out info);

            if (hasFound && info.IsAzure
                && originalValue < MinimalQueryTimeoutSecondsForAzure)
            {
                return MinimalQueryTimeoutSecondsForAzure;
            }
            else
            {
                return originalValue;
            }
        }

        public void AddOrUpdateIsCloud(IDbConnection connection, bool isCloud)
        {
            AddOrUpdateCache(connection, isCloud, CacheVariable.IsCloud);
        }
        
        public void AddOrUpdateIsAzure(IDbConnection connection, bool isAzure)
        {
            AddOrUpdateCache(connection, isAzure, CacheVariable.IsAzure);
        }

        public void AddOrUpdateIsSqlDw(IDbConnection connection, bool isSqlDw)
        {
            AddOrUpdateCache(connection, isSqlDw, CacheVariable.IsSqlDw);
        }

        private void AddOrUpdateCache(IDbConnection connection, bool newState, CacheVariable cacheVar)
        {
            Validate.IsNotNull(nameof(connection), connection);
            SqlConnectionStringBuilder builder = new SqlConnectionStringBuilder(connection.ConnectionString);
            AddOrUpdateCache(builder, newState, cacheVar);
        }

        internal void AddOrUpdateCache(SqlConnectionStringBuilder builder, bool newState, CacheVariable cacheVar)
        {
            Validate.IsNotNull(nameof(builder), builder);
            Validate.IsNotNullOrWhitespaceString(nameof(builder) + ".DataSource", builder.DataSource);
            CachedInfo info;
            bool hasFound = TryGetCacheValue(builder, out info);

            if ((cacheVar == CacheVariable.IsSqlDw && hasFound && info.IsSqlDw == newState) || 
                (cacheVar == CacheVariable.IsAzure && hasFound && info.IsAzure == newState)) 
            {
                // No change needed
                return;
            }
            else
            {
                lock (_cacheLock)
                {
                    // Clean older keys, update info, and add this back into the cache
                    CacheKey key = new CacheKey(builder);
                    CleanupCache(key);

                    if (cacheVar == CacheVariable.IsSqlDw)
                    {
                        info.IsSqlDw = newState;
                    }
                    else if (cacheVar == CacheVariable.IsAzure)
                    {
                        info.IsAzure = newState;
                    }
                    info.LastUpdate = DateTime.UtcNow;
                    _cache.AddOrUpdate(key, info, (k, oldValue) => info);
                }
            }
        }

        private void CleanupCache(CacheKey newKey)
        {
            if (!_cache.ContainsKey(newKey))
            {
                //delete a batch of old elements when we try to add a new one and
                //the capacity limitation is hit
                if (_cache.Keys.Count > _maxCacheSize - 1)
                {
                    var keysToDelete = _cache
                        .OrderBy(x => x.Value.LastUpdate)
                        .Take(_deleteBatchSize)
                        .Select(pair => pair.Key);

                    foreach (CacheKey key in keysToDelete)
                    {
                        CachedInfo info;
                        _cache.TryRemove(key, out info);
                    }
                }
            }
        }

        public bool TryGetIsSqlDw(IDbConnection connection, out bool isSqlDw)
        {
            Validate.IsNotNull(nameof(connection), connection);

            SqlConnectionStringBuilder builder = new SqlConnectionStringBuilder(connection.ConnectionString);
            return TryGetIsSqlDw(builder, out isSqlDw);
        }

        public bool TryGetIsSqlDw(SqlConnectionStringBuilder builder, out bool isSqlDw)
        {
            Validate.IsNotNull(nameof(builder), builder);
            Validate.IsNotNullOrWhitespaceString(nameof(builder) + ".DataSource", builder.DataSource);
            CachedInfo info;
            bool hasFound = TryGetCacheValue(builder, out info);

            if(hasFound)
            {
                isSqlDw = info.IsSqlDw;
                return true;
            }
            
            isSqlDw = false;
            return false;
        }

        private static SqlConnectionStringBuilder SafeGetConnectionStringFromConnection(IDbConnection connection)
        {
            if (connection == null)
            {
                return null;
            }

            try
            {
                SqlConnectionStringBuilder builder = new SqlConnectionStringBuilder(connection.ConnectionString);
                return builder;
            }
            catch
            {
                Logger.Write(LogLevel.Error,  String.Format(Resources.FailedToParseConnectionString, connection.ConnectionString));
                return null;
            }
        }

        private bool TryGetCacheValue(SqlConnectionStringBuilder builder, out CachedInfo value)
        {
            CacheKey key = new CacheKey(builder);
            return _cache.TryGetValue(key, out value);
        }
    }
}
