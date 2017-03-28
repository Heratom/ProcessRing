/**
 * 
 */
package main;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mflamcou
 *
 */
public class MessContainer {
	private Message mess;
	private List<Integer> sentTo;
	
	public MessContainer (Message M) {
		this.mess=M;
		sentTo = new ArrayList<>();
	}
	
	public void putSent(int P) {
		sentTo.add(P);
	}
	
	public void Acked(Integer P) {
		if(!sentTo.remove(P)) {
			throw new RuntimeException("[MessContainer " + mess.getID() + "] Received ack from unknown sender, id " + P + ". List of sender is : " + sentTo.toString());
		}
	}
	
	public boolean fullyAcked() {
		return(sentTo.isEmpty());
	}
	
	public Message getMessage() {
		return mess;
	}
}
