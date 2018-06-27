//
// Copyright (c) Microsoft. All rights reserved.
// Licensed under the MIT license. See LICENSE file in the project root for full license information.
//

using System.Linq;
using Microsoft.SqlServer.Management.XEvent;
using Microsoft.SqlTools.ServiceLayer.Connection;
using Microsoft.SqlTools.ServiceLayer.Profiler.Contracts;

namespace Microsoft.SqlTools.ServiceLayer.Profiler
{
    public class XEventSession : IXEventSession
    {
        public Session Session { get; set; }

        public string GetTargetXml()
        {
            if (this.Session == null)
            {
                return string.Empty;
            }

            // try to read events from the first target
            Target defaultTarget = this.Session.Targets.FirstOrDefault();
            return defaultTarget != null ? defaultTarget.GetTargetData() : string.Empty;
        }
    }
}
