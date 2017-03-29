package main;

import static main.ProcessManager.Println;
import static utils.State.Halted;
import static utils.State.Running;
import static utils.State.Stopped;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import interfaces.Contactable;
import utils.State;

public class Process implements Contactable, Runnable {
	private final int ID;
	private State state;
	private Set<Process> Group;
	private Map<Long, Message>fileAttente;	// Need ordre !!! par timestamp !!
	private Map<Long, AckVector>listVecs;
	
	//*** Constructors ***\\
	
	public Process(int i, Process P) {
		this.ID = i;
		this.setState(Running);
		this.fileAttente = new HashMap<>();
		this.Group = new HashSet<>();
		this.listVecs = new HashMap<>();
		Group.add(this);
		if(P!=null) {					// P = proxy fourni par l'admin pour rejoindre le group
			if(P.Handshake(this)) {
				Group.add(P);
			}
			else {
				this.Stop();			// sans groupe, ça ne sert à rien de tourner.
			}
		}
	}
	
	//*** Methods to run the process ***\\
	
	public void Halt() {
		if(this.getState()==Running) {
			setState(Halted);
		}
		else if(getState()==Stopped) {
			Println("Process " + this.getID() + " is stopped, can't halt.");
		}
		else {
			Println("Process " + this.getID() + " is already halted.");
		}
	}
	
	public void Restart() {
		if(getState()==Halted) {
			setState(Running);
		}
		else if(getState()==Stopped) {
			Println("Process " + this.getID() + " is stopped, can't restart");
		}
		else {
			Println("Process " + this.getID() + " is already running.");
		}
	}
	
	public void Stop() {
		if(getState()==Stopped) {
			Println("Process " + this.getID() + " is already stopped.");
		}
		else {
			setState(Stopped);
		}
	}
	
	@Override
	public String toString() {
		return ("I am process number " + this.getID() + " And I am " + this.getState() + ".");
	}

	/**
	 * @return the iD
	 */
	public int getID() {
		return ID;
	}
	
	/**
	 * Prints the whole group linked to this process
	 */
	public void printGroup() {
		for(Process P : Group) {
			Println(P.toString());
		}
	}
	
	//*** Thread methods ***\\
	
	public void run() {
		while(getState()==Running) {
			pingAll();
			broadcastMessage(new Message(this.ID, "Hello World ! I am process " + this.ID + "."));
			try {
				TimeUnit.SECONDS.sleep(2L);
			} catch (InterruptedException e) {
				throw new RuntimeException("[Process " + this.ID + "] Error while sleeping :" + e);
			}
		}
	}
	
	//*** Contact methods ***\\
	
	@Override
	public boolean pingMe() {
		return(this.getState()==Running);
	}

	/**
	 * A little pointless here, but this encapsulation is meant to ease the update if processes happened to be dispatched later
	 */
	@Override
	public boolean ping(Process P) {
		return P.pingMe();
	}
	
	public void pingAll() {
		for(Process P : Group) {
			if(!ping(P)) {
				Group.remove(P);
			}
		}
	}

	@Override
	public void receiveMessage(Message M, int IDS) {
		if(this.getState()==Running) {
			if(!this.fileAttente.containsKey(M.getID())) {
				this.fileAttente.put(M.getID(), M);
				this.listVecs.put(M.getID(), new AckVector());
				this.broadcastAck(M);
			}
			else {
				Println("[Process " + this.ID + "] Message reçu en double : " + M.getMessage());
			}
		}
	}

	@Override
public void receiveAck(Ack A) {
		if(this.getState()==Running) {
			AckVector B = this.listVecs.get(A.getIdMessage());
			if(!B.newAck(A.getIdSender())) {
				throw new RuntimeException("[Process " + this.getID() + "] Error : received Ack from unknown sender " + A.getIdSender() + ".");
			}
			if(B.fullyAcked()) {
				this.deliverMessage(A.getIdMessage());
			}
		}
	}
	
	private void deliverMessage(Long idMessage) {
		// TODO Auto-generated method stub
		
	}

	public void signalNewcomer(Process P) {
		if(this.Group.contains(P)) {
			throw new RuntimeException("[Process " + this.ID + "] Error while adding newcomer : already exists in this group.");
		}
		else {
			Group.add(P);
			P.secondaryHandshake(this);
		}
	}

	@Override
	public void broadcastMessage(Message M) {
		if(this.getState()==Running) {
			for(Process P:this.Group) {
				P.receiveMessage(M, this.ID);
			}
		}
	}

	@Override
	public void broadcastAck(Message M) {
		if(this.getState()==Running) {
			Ack A = new Ack(M.getID(), this.getID());
			for(Process P: this.Group) {
				this.listVecs.get(M.getID()).addTarget(P.getID());
				P.receiveAck(A);
			}
		}
	}
	
	public void broadcastNewcomer(Process P) {
		if(this.getState()==Running) {
			for(Process Q : Group) {
				Q.signalNewcomer(P);
			}
		}
	}
	
	/**
	 * Invoked by newcomer in the group, should be added
	 * @param ID of the newcomer
	 */
	public boolean Handshake(Process P) {
		if(this.getState()==Running) {
			if(this.Group.contains(P)) {
				return false;
			}
			broadcastNewcomer(P);
			return true;
		}
		return false;
	}
	
	public void secondaryHandshake(Process P) {
		if(this.getState()==Running) {
			this.Group.add(P);
		}
	}
	
	//*** Handy methods, autogenerated by eclipse ***\\

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ID;	// suppressed the involvment of group and state, since they have no point identifying the process
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Process other = (Process) obj;
		if (ID != other.ID)
			return false;
		if (getState() != other.getState())
			return false;
		return true;
	}

	/**
	 * @return the state
	 */
	public State getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(State state) {
		this.state = state;
	}

}