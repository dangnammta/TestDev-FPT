﻿//
// Copyright (c) Microsoft. All rights reserved.
// Licensed under the MIT license. See LICENSE file in the project root for full license information.

using System;
using System.IO;
using Microsoft.SqlTools.Hosting.Utility;
using Microsoft.SqlTools.ServiceLayer.SqlContext;
using Microsoft.SqlTools.Utility;

namespace Microsoft.SqlTools.Serialization
{
    /// <summary>
    /// Main application class for Serialization Service Host executable
    /// </summary>
    internal class Program
    {
        private const string ServiceName = "SqlSerializationService.exe";

        /// <summary>
        /// Main entry point into the Serialization Service Host
        /// </summary>
        internal static void Main(string[] args)
        {
            try
            {
                // read command-line arguments
                CommandOptions commandOptions = new CommandOptions(args, ServiceName);
                if (commandOptions.ShouldExit)
                {
                    return;
                }

                string logFilePath = "serialization";
                if (!string.IsNullOrWhiteSpace(commandOptions.LoggingDirectory))
                {
                    logFilePath = Path.Combine(commandOptions.LoggingDirectory, logFilePath);
                }

                // turn on Verbose logging during early development
                // we need to switch to Normal when preparing for public preview
                Logger.Initialize(logFilePath: logFilePath, minimumLogLevel: LogLevel.Verbose, isEnabled: commandOptions.EnableLogging);
                Logger.Write(LogLevel.Normal, "Starting SqlTools Serialization Provider");

                // set up the host details and profile paths 
                var hostDetails = new HostDetails(
                    name: "SqlTools Serialization Provider",
                    profileId: "Microsoft.SqlTools.Serialization",
                    version: new Version(1, 0));

                SqlToolsContext sqlToolsContext = new SqlToolsContext(hostDetails);
                UtilityServiceHost serviceHost = HostLoader.CreateAndStartServiceHost(sqlToolsContext);

                serviceHost.WaitForExit();
            }
            catch (Exception e)
            {
                Logger.Write(LogLevel.Error, string.Format("An unhandled exception occurred: {0}", e));
                Environment.Exit(1);
            }
        }
    }
}
