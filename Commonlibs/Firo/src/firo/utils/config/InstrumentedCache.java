package firo.utils.config;

import com.reardencommerce.kernel.collections.shared.evictable.ConcurrentLinkedHashMap;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

public class InstrumentedCache<K, V> {

	private int capacity;
	private final ConcurrentLinkedHashMap<K, V> map;
	private final AtomicLong requests = new AtomicLong(0L);
	private final AtomicLong hits = new AtomicLong(0L);
	private final AtomicLong lastRequests = new AtomicLong(0L);
	private final AtomicLong lastHits = new AtomicLong(0L);
	private volatile boolean capacitySetManually;
	private String name = "";

	public InstrumentedCache(int capacity) {
		this.capacity = capacity;
		this.map = ConcurrentLinkedHashMap.create(ConcurrentLinkedHashMap.EvictionPolicy.SECOND_CHANCE, capacity);
	}

	public InstrumentedCache(int capacity, ConcurrentLinkedHashMap.EvictionPolicy evictionPolicty) {
		this.capacity = capacity;
		this.map = ConcurrentLinkedHashMap.create(evictionPolicty, capacity);
	}

	public InstrumentedCache(String name, int capacity, ConcurrentLinkedHashMap.EvictionPolicy evictionPolicty) {
		this.name = name;
		this.capacity = capacity;
		this.map = ConcurrentLinkedHashMap.create(evictionPolicty, capacity);
	}

	public void put(K key, V value) {
		this.map.put(key, value);
	}

	public V get(K key) {
		V v = this.map.get(key);
		this.requests.incrementAndGet();
		if (v != null) {
			this.hits.incrementAndGet();
		}
		return v;
	}

	public V getInternal(K key) {
		return this.map.get(key);
	}

	public void remove(K key) {
		this.map.remove(key);
	}

	public int getCapacity() {
		return this.capacity;
	}

	public boolean isCapacitySetManually() {
		return this.capacitySetManually;
	}

	public void updateCapacity(int capacity) {
		this.map.setCapacity(capacity);
		this.capacity = capacity;
	}

	public void setCapacity(int capacity) {
		updateCapacity(capacity);
		this.capacitySetManually = true;
	}

	public int getSize() {
		return this.map.size();
	}

	public long getHits() {
		return this.hits.get();
	}

	public long getRequests() {
		return this.requests.get();
	}

	public double getRecentHitRate() {
		long r = this.requests.get();
		long h = this.hits.get();
		try {
			return (h - this.lastHits.get()) / (r - this.lastRequests.get());
		}
		finally {
			this.lastRequests.set(r);
			this.lastHits.set(h);
		}
	}

	public void clear() {
		this.map.clear();
		this.requests.set(0L);
		this.hits.set(0L);
	}

	public Set<K> getKeySet() {
		return this.map.keySet();
	}

	public String dumpProfilerConsole() {
		String content = "";

		content = "LocalCache-LRU '" + this.name + "' profiler";

		NumberFormat formatter = new DecimalFormat("#0.00");
		long req = getRequests();
		long _hits = getHits();
		long totalitems = getSize();
		long size = getCapacity();
		double ratioHits = _hits * 100.0D / req;
		double ratioUsage = totalitems * 100.0D / size;

		content = content + "total req:\t\t" + getRequests() + "\n";
		content = content + "total hits:\t\t" + getHits() + "\n";
		content = content + "% hits:\t\t" + formatter.format(ratioHits) + "%\n";
		content = content + "cur items:\t\t" + totalitems + "\n";
		content = content + "max items:\t\t" + size + "\n";

		return content;
	}

	public String dumpProfilerHtml() {
		String content = "";

		content = "<table border=1><tr><td colspan=2>LocalCache-LRU '" + this.name + "' profiler" + "</td></tr>";

		NumberFormat formatter = new DecimalFormat("#0.00");
		long req = getRequests();
		long _hits = getHits();
		long totalitems = getSize();
		long size = getCapacity();
		double ratioHits = _hits * 100.0D / req;
		double ratioUsage = totalitems * 100.0D / size;

		content = content + "<tr><td>total req:</td><td>" + getRequests() + "</td></tr>";
		content = content + "<tr><td>total hits:</td><td>" + getHits() + "</td></tr>";
		content = content + "<tr><td>% hits:</td><td>" + formatter.format(ratioHits) + "</td></tr>";
		content = content + "<tr><td>cur items:</td><td>" + getSize() + "</td></tr>";
		content = content + "<tr><td>max items:</td><td>" + getCapacity() + "</td></tr>";
		return content;
	}
}
