//
// Copyright (c) Microsoft. All rights reserved.
// Licensed under the MIT license. See LICENSE file in the project root for full license information.
//

using System;
// using System.Security;
// using Microsoft.SqlTools.ServiceLayer.TestDriver.Driver;
// using Microsoft.SqlTools.ServiceLayer.TestDriver.Utility;
// using Microsoft.SqlTools.Utility;
using Microsoft.SqlServer.Management.Smo;
using Microsoft.SqlServer.Management.Sdk.Sfc;
using Microsoft.SqlServer.Management.Common;
using System.Data.SqlClient;
using System.Collections.Generic;
using Newtonsoft.Json.Linq;

namespace Microsoft.SqlTools.ServiceLayer.PerfTests
{
    public class Program
    {
        internal static int Main(string[] args)
        {
            getScript();
            // testConnect();
            if (args.Length < 1)
            {
                // Console.WriteLine("Microsoft.SqlTools.ServiceLayer.PerfTests.exe [tests]" + Environment.NewLine +
                //                   "    [tests] is a space-separated list of tests to run." + Environment.NewLine +
                //                   "            They are qualified within the Microsoft.SqlTools.ServiceLayer.TestDriver.PerfTests namespace" + Environment.NewLine +
                //                   $"Be sure to set the environment variable {ServiceTestDriver.ServiceHostEnvironmentVariable} to the full path of the sqltoolsservice executable.");
                return 0;
            }

            // Logger.Initialize("testdriver", LogLevel.Verbose);
            return 0;
            // return TestRunner.RunTests(args, "Microsoft.SqlTools.ServiceLayer.PerfTests.").Result;
        }

        public static void getScript()
        {
            // Connect to the local, default instance of SQL Server.
            var databaseName = "TestImporter";
            // SqlConnectionInfo srvInfo = new SqlConnectionInfo();
            // srvInfo.UserName = "sa";
            // srvInfo.Password = "123456abcA";
            // srvInfo.DatabaseName = databaseName;
            // srvInfo.ServerName = "10.86.222.24";
            // srvInfo.UseIntegratedSecurity = true;

            ServerConnection svrConn = null;
            Server srv = null;
            try
            {
                string connString =
                  "Data Source=10.86.222.24,1444;" +
                  "Initial Catalog=TestImporter;" +
                  "User ID=sa;" +
                  "Password=123456abcA; MultipleActiveResultSets=True;";
                svrConn = new Microsoft.SqlServer.Management.Common.ServerConnection(new System.Data.SqlClient.SqlConnection(connString));
               
                srv = new Microsoft.SqlServer.Management.Smo.Server(svrConn);
                srv.ConnectionContext.Connect();

                 // Reference the database.    
                Database database = srv.Databases[databaseName];



                 Scripter scripter = new Scripter(srv);
                Scripter scripterTable = new Scripter(srv);

                ScriptingOptions options = new ScriptingOptions();
                options.ScriptData = false;

                options.Triggers = true;
                options.ClusteredIndexes = true;
                options.Default = true;
                options.DriAll = true;
                options.Indexes = true;
                options.IncludeHeaders = true;
                options.WithDependencies = true;

                scripter.Options = options;
                scripterTable.Options = options;

                List<Table> tableList = new List<Table>();
                //List<String> tableNameList = new List<String>();
                JArray tableNameList = new JArray();
                List<SqlSmoObject> objList = new List<SqlSmoObject>();

                Console.WriteLine("Table");
                for (int idx = 0; idx < database.Tables.Count; idx++)
                {
                    if (!database.Tables[idx].IsSystemObject)
                    {
                        Table obj = database.Tables[idx];
                        objList.Add(obj);
                        tableList.Add(obj);
                    }
                }
                //view
                // Console.WriteLine("View");
                // for (int idx = 0; idx < database.Views.Count; idx++)
                // {
                //     if (!database.Views[idx].IsSystemObject)
                //     {
                //         SqlSmoObject obj = database.Views[idx];
                //         objList.Add(obj);
                //     }
                // }

                // Console.WriteLine("UserDefinedDataTypes");
                // for (int idx = 0; idx < database.UserDefinedDataTypes.Count; idx++)
                // {
                //     UserDefinedDataType obj = database.UserDefinedDataTypes[idx];
                //     objList.Add(obj);
                // }
                // Console.WriteLine("UserDefinedTableTypes");
                // for (int idx = 0; idx < database.UserDefinedTableTypes.Count; idx++)
                // {
                //     UserDefinedTableType obj = database.UserDefinedTableTypes[idx];
                //     objList.Add(obj);
                // }
                // Console.WriteLine("UserDefinedTypes");
                // for (int idx = 0; idx < database.UserDefinedTypes.Count; idx++)
                // {
                //     UserDefinedType obj = database.UserDefinedTypes[idx];
                //     objList.Add(obj);
                // }
                // Console.WriteLine("UserDefinedFunctions");
                // for (int idx = 0; idx < database.UserDefinedFunctions.Count; idx++)
                // {
                //     UserDefinedFunction obj = database.UserDefinedFunctions[idx];
                //     if (!obj.IsSystemObject)
                //     {
                //         objList.Add(obj);
                //     }
                // }

                // Console.WriteLine("UserDefinedAggregates");
                // for (int idx = 0; idx < database.UserDefinedAggregates.Count; idx++)
                // {
                //     SqlSmoObject obj = database.UserDefinedAggregates[idx];
                //     objList.Add(obj);
                // }



                // Console.WriteLine("Sequences");
                // for (int idx = 0; idx < database.Sequences.Count; idx++)
                // {
                //     SqlSmoObject obj = database.Sequences[idx];
                //     objList.Add(obj);
                // }

                // Console.WriteLine("Synonyms");
                // for (int idx = 0; idx < database.Synonyms.Count; idx++)
                // {
                //     SqlSmoObject obj = database.Synonyms[idx];
                //     objList.Add(obj);
                // }

                // Console.WriteLine("StoredProcedures");
                // for (int idx = 0; idx < database.StoredProcedures.Count; idx++)
                // {
                //     if (!database.StoredProcedures[idx].IsSystemObject)
                //     {
                //         SqlSmoObject obj = database.StoredProcedures[idx];
                //         objList.Add(obj);
                //     }

                // }

                Console.WriteLine("Triggers");
                for (int idx = 0; idx < database.Triggers.Count; idx++)
                {
                    if (!database.Triggers[idx].IsSystemObject)
                    {
                        SqlSmoObject obj = database.Triggers[idx];
                        objList.Add(obj);
                    }

                }

                SqlSmoObject[] objs = objList.ToArray();

                //Get tableName order
                Table[] tbls = tableList.ToArray();
                scripter.Options.ScriptData = true;
                // scripter.Script(objs);

                // DependencyTree tree = scripter.DiscoverDependencies(tbls, true);
                // DependencyWalker depwalker = new Microsoft.SqlServer.Management.Smo.DependencyWalker();
                // DependencyCollection depcoll = depwalker.WalkDependencies(tree);


                // foreach (DependencyCollectionNode dep in depcoll)
                // {
                //     //Console.WriteLine("tableName: " + dep.Urn.GetAttribute("Name"));
                //     tableNameList.Add(dep.Urn.GetAttribute("Name"));
                //     //sb.AppendFormat("EXEC sp_generate_inserts @table_name='{0}', @owner='dbo'{1}", dep.Urn.GetAttribute("Name"), Environment.NewLine);
                // }

                string scrs = "";
                for(int i = 0; i < tbls.Length; i++){
                    Table table = tbls[i];
                    if( table.IsSystemObject == true ) continue;
                    foreach( string s in scripter.EnumScript( new Urn[] { table.Urn } ) )
                        scrs += s + "\n\n"; ;

                }
                ///////////////////////////////////////
                // Define a Scripter object and set the required scripting options.   
                // Scripter scripter = new Scripter(srv);
                scripter.Options.ScriptDrops = false;
                // To include indexes  
                scripter.Options.Indexes = true;
                // to include referential constraints in the script 
                scripter.Options.DriAllConstraints = true;
                // Iterate through the tables in database and script each one. Display the script.     
                foreach (Table tb in database.Tables)
                {
                    // check if the table is not a system table  
                    if (tb.IsSystemObject == false)
                    {
                        Console.WriteLine("-- Scripting for table " + tb.Name);
                        // Generating script for table tb  
                        System.Collections.Specialized.StringCollection sc = scripter.Script(new Urn[] { tb.Urn });
                        foreach (string st in sc)
                        {
                            Console.WriteLine(st);
                        }
                        Console.WriteLine("--");
                    }
                }
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.Message);
            }
            finally
            {
                if (svrConn != null)
                    svrConn.Disconnect();
            }

            // ServerConnection conn = new ServerConnection(srvInfo);
            // Microsoft.SqlServer.Management.Smo.Server srv = new Microsoft.SqlServer.Management.Smo.Server(conn);
            // srv.ConnectionContext.LoginSecure = true;
            // srv.ConnectionContext.Connect();
            // database name
            // Console.WriteLine("Enter database name for scripting:");
            
            /*SqlConnectionStringBuilder myBuilder = new SqlConnectionStringBuilder();
                myBuilder.ConnectTimeout = 3000;

                
                myBuilder.UserID = "sa";
                myBuilder.Password = "123456abcA";
                myBuilder.InitialCatalog = databaseName;
                myBuilder.DataSource = "10.86.222.24,1444";
                //myBuilder.IntegratedSecurity = true;
                myBuilder.PersistSecurityInfo = true;
                
                

                string connectionString = myBuilder.ToString();

                Console.WriteLine("-- connectionString " + connectionString);

                ServerConnection src = new ServerConnection { ConnectionString = connectionString };
                
                var srv = new Server(src);
                srv.ConnectionContext.Connect();
                var db = srv.Databases[databaseName];*/

        }

        public static void testConnect()
        {
            Console.WriteLine("Relação de países e códigos telefônicos\n");

            string connectionString =
                "Data Source=10.86.222.24,1444;" +
                "Initial Catalog=TestImporter;" +
                "User ID=sa;" +
                "Password=123456abcA;";

            using (SqlConnection conn =
                new SqlConnection(connectionString))
            {
                SqlCommand cmd = conn.CreateCommand();
                cmd.CommandText =
                    "SELECT name " +
                    "FROM dbo.Account ";

                conn.Open();
                SqlDataReader reader = cmd.ExecuteReader();
                while (reader.Read())
                {
                    Console.WriteLine(
                        $"{reader["name"]}");
                }

                conn.Close();
            }

            Console.WriteLine("\nTérmino da aplicação TesteNETCoreSQL");
        }
    }
}
