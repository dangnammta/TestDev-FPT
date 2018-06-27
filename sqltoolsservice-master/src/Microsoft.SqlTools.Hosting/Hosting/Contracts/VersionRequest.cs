﻿//
// Copyright (c) Microsoft. All rights reserved.
// Licensed under the MIT license. See LICENSE file in the project root for full license information.
//

using Microsoft.SqlTools.Hosting.Protocol.Contracts;

namespace Microsoft.SqlTools.Hosting.Contracts
{
    /// <summary>
    /// Defines a message that is sent from the client to request
    /// the version of the server.
    /// </summary>
    public class VersionRequest
    {
        public static readonly
           RequestType<object, string> Type =
           RequestType<object, string>.Create("version");
    }
}
