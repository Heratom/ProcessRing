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
	private final static int nbProcesses = 10;
	private static List<Process> processes = new LinkedList<Process>();
	private static Rand random = new Rand();
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		for(int i=0; i < nbProcesses; i++) {
			processes.add(new Process(i));
		}
		Println(processes.size());
		PrintRing(processes);
		int var1 = rand(processes.size());
		int var2 = rand(processes.size());
		processes.get(var1).Halt();
		processes.get(var2).Halt();
		PrintRing(processes);
		processes.get(var1).Stop();
		processes.get(var1).Restart();
		processes.get(var2).Restart();
		PrintRing(processes);
	}

	private static void PrintRing(List<Process> processes) {
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
