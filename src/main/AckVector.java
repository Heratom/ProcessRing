/**
 * 
 */
package main;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Heratom
 *
 */
public class AckVector {
	private Map<Integer,Boolean>receivedAcks;
	
	public AckVector() {
		this.receivedAcks = new HashMap<>();
	}
	
	public boolean newAck(int IDS) {
		if(receivedAcks.containsKey(IDS)) {
			receivedAcks.replace(IDS, true);
			return true;
		}
		else {
			return false;
		}
	}
	
	public void addTarget(int IDS) {
		receivedAcks.put(IDS, false);
	}
	
	public boolean fullyAcked() {
		return(!receivedAcks.containsValue(false));
	}
}
