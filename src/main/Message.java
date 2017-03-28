/**
 * 
 */
package main;

import static main.ProcessManager.rand;

import java.time.LocalDateTime;

/** 
 * @author mflamcou
 *
 */
public class Message {
	private long id;
	private int senderID;
	private String message;
	private LocalDateTime timeStamp;
	
	public Message(int Sender, String Mess) {
		this.senderID = Sender;
		this.message = Mess;
		setTimeStamp(LocalDateTime.now());
		this.id=rand();
	}
	
	public int getSID() {
		return this.senderID;
	}
	
	public long getID() {
		return this.id;
	}
	
	public String getMessage() {
		return this.message;
	}

	/**
	 * @return the timeStamp
	 */
	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}

	/**
	 * @param timeStamp the timeStamp to set
	 */
	private void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}
}
