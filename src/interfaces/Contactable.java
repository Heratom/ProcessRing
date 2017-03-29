package interfaces;

import main.Ack;
import main.Message;
import main.Process;

/**
 * Should be implemented by each process that wants to communicate.
 * @author mflamcou
 *
 */
public interface Contactable {
	public boolean pingMe();
	public boolean ping(Process P);
	public void receiveMessage(Message M, int IDS);
	public void receiveAck(Ack A);
	public void broadcastMessage(Message M);
	public void broadcastAck(Message M);
}
