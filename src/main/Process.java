package main;

import static main.ProcessManager.Println;
import static utils.State.Halted;
import static utils.State.Running;
import static utils.State.Stopped;

import interfaces.Contactable;
import utils.State;

public class Process implements Contactable {
	private final int ID;
	private State state;
	
	//*** Constructors ***\\
	
	public Process(int i) {
		this.ID = i;
		this.state = Running;
	}
	
	//*** Methods to run the process ***\\
	
	public void Halt() {
		if(this.state==Running) {
			state=Halted;
		}
		else if(state==Stopped) {
			Println("Process " + this.getID() + " is stopped, can't halt.");
		}
		else {
			Println("Process " + this.getID() + " is already halted.");
		}
	}
	
	public void Restart() {
		if(state==Halted) {
			state=Running;
		}
		else if(state==Stopped) {
			Println("Process " + this.getID() + " is stopped, can't restart");
		}
		else {
			Println("Process " + this.getID() + " is already running.");
		}
	}
	
	public void Stop() {
		if(state==Stopped) {
			Println("Process " + this.getID() + " is already stopped.");
		}
		else {
			state=Stopped;
		}
	}
	
	@Override
	public String toString() {
		return ("I am process number " + this.getID() + " And I am " + this.state + ".");
	}

	/**
	 * @return the iD
	 */
	public int getID() {
		return ID;
	}
	
	
	//*** Contact methods ***\\
	
	@Override
	public boolean pingMe() {
		return false;
	}

	@Override
	public boolean ping(int ID) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public void receiveMessage() {
		
	}
	
	public void receiveAck() {
		
	}
	@Override
	public void receiveMessage(Message M, int IDS) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void receiveAck(Message M, int IDS) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void broadcastMessage(Message M) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void broadcastAck(Message M) {
		// TODO Auto-generated method stub
		
	}
	
}