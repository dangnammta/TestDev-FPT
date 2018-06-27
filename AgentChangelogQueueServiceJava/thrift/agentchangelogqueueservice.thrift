include "agent_share.thrift"

namespace java crdhn.queue.thrift

service TAgentChangelogQueueService{
	bool addQueue(1: agent_share.TAgentChangelogQueue changelogQueue);
	agent_share.TAgentChangelogQueue deQueue();
}
