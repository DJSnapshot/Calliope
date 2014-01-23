package net.baystation12.calliope.network;

import java.util.Map;

import com.artemis.Component;
import com.artemis.utils.Bag;

public class MultiEntityDelta extends NetworkMessage {
	public Map<Long, Bag<Component>> AtomIDs;
}
