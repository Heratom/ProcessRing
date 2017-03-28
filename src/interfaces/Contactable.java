package interfaces;

import main.Message;

/**
 * Should be implemented by each process that wants to communicate.
 * @author mflamcou
 *
 */
public interface Contactable {
	public boolean pingMe();
	public boolean ping(int ID);
	public void receiveMessage(Message M, int IDS);
	public void receiveAck(Message M, int IDS);
	public void broadcastMessage(Message M);
	public void broadcastAck(Message M);
}
