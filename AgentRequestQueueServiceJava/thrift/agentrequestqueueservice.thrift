include "agent_response.thrift"
include "agent_share.thrift"

namespace java crdhn.queue.thrift

service TAgentRequestQueueService{
	bool addQueue(1: agent_share.TAgentRequestQueue agentRequestInfo);
	agent_share.TAgentRequestQueue deQueue();
}
