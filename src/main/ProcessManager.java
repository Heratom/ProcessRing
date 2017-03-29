/**
 * 
 */
package main;

import java.util.LinkedList;
import java.util.List;

import utils.Rand;

/**
 * @author mflamcou
 *
 */
public class ProcessManager {
	private static int nbProcesses = 0;
	private static List<Process> processes = new LinkedList<Process>();
	private static Rand random = new Rand();
	private static Process proxy;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Process A = new Process (nbProcesses + 1, null);
		if(A.getState()!=utils.State.Running) {
			Println("[ProcessManager] initialization failed : proxy isn't running.");
		}
		else {
			processes.add(A);
			proxy=A;
			nbProcesses++;
			PrintRing();
			addProcess();
			PrintRing();
			Println("Group incoming");
			for(Process B:processes) {
				B.printGroup();
			}
		}
	}
	
	private static void addProcess() {
		processes.add(new Process(nbProcesses+1, proxy));
		nbProcesses++;
	}

	private static void PrintRing() {
		for(Process p: processes) {
			Println(p.toString());
		}
	}

	public static void Println(String S) {
		System.out.println(S);
	}
	
	public static void Println(int I) {
		System.out.println(Integer.toString(I));
	}
	
	public static Process getProcess(int ID) {
		for (Process P : processes) {
			if(P.getID()==ID) {
				return P;
			}
		}
		throw new IllegalArgumentException("[ProcessManager] Received Request for unknown process ID : " + ID);
	}
	
	public static void signalDead(int ID) {
		// stay tuned
	}
	
	//*** fonctions de génération d'aléatoire ***\\
	public static int rand() {
		return random.randInt();
	}
	
	public static int rand(int Max) {
		return random.randInt(Max);
	}
}
