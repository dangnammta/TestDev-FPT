//
// Copyright (c) Microsoft. All rights reserved.
// Licensed under the MIT license. See LICENSE file in the project root for full license information.
//

using System;

namespace Microsoft.SqlTools.ServiceLayer.SqlContext
{
    /// <summary>
    /// Context for SQL Tools
    /// </summary>
    public class SqlToolsContext
    {
        /// <summary>
        /// Gets the PowerShell version of the current runspace.
        /// </summary>
        public Version SqlToolsVersion
        {
            get; private set;
        }

        /// <summary>
        /// Initalizes the SQL Tools context instance
        /// </summary>
        /// <param name="hostDetails"></param>
        public SqlToolsContext(HostDetails hostDetails)
        {
            this.SqlToolsVersion = hostDetails.Version;
        }
    }
}

