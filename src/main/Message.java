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
	private int clock;
	private int senderID;
	private String message;
	private LocalDateTime timeStamp;
	
	public Message(int Sender, String Mess, int clock) {
		this.senderID = Sender;
		this.message = Mess;
		this.setClock(clock);
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

	/**
	 * @return the clock
	 */
	public int getClock() {
		return clock;
	}

	/**
	 * @param clock the clock to set
	 */
	private void setClock(int clock) {
		this.clock = clock;
	}
}
