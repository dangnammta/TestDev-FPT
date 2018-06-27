include "dip_shared.thrift"

namespace java crdhn.dip.thrift
namespace cpp CRDHN.DIP

service TDIPExporterService {
	bool ping(1: dip_shared.TExportRequest request);
	bool stop(1: dip_shared.TExportRequest request);
}
