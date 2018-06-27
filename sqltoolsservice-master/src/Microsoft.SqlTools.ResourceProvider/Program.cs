//
// Copyright (c) Microsoft. All rights reserved.
// Licensed under the MIT license. See LICENSE file in the project root for full license information.

using System;
using System.IO;
using Microsoft.SqlTools.Hosting.Utility;
using Microsoft.SqlTools.ServiceLayer.SqlContext;
using Microsoft.SqlTools.Utility;

namespace Microsoft.SqlTools.ResourceProvider
{
    /// <summary>
    /// Main application class for the executable that supports the resource provider and identity services
    /// </summary>
    internal class Program
    {
        private const string ServiceName = "SqlToolsResourceProviderService.exe";

        /// <summary>
        /// Main entry point into the Credentials Service Host
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

                string logFilePath = "SqlToolsResourceProviderService";
                if (!string.IsNullOrWhiteSpace(commandOptions.LoggingDirectory))
                {
                    logFilePath = Path.Combine(commandOptions.LoggingDirectory, logFilePath);
                }

                // turn on Verbose logging during early development
                // we need to switch to Normal when preparing for public preview
                Logger.Initialize(logFilePath: logFilePath, minimumLogLevel: LogLevel.Verbose, isEnabled: commandOptions.EnableLogging);
                Logger.Write(LogLevel.Normal, "Starting SqlTools Resource Provider");

                // set up the host details and profile paths 
                var hostDetails = new HostDetails(
                    name: "SqlTools Resource Provider",
                    profileId: "Microsoft.SqlTools.ResourceProvider",
                    version: new Version(1, 0));

                SqlToolsContext sqlToolsContext = new SqlToolsContext(hostDetails);
                UtilityServiceHost serviceHost = ResourceProviderHostLoader.CreateAndStartServiceHost(sqlToolsContext);

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
