package main;

import java.util.concurrent.TimeUnit;
import static main.ProcessManager.Println;

public class testMessage {

	public static void testTimeStamp() {
		Message M1, M2;
		M1 = new Message(1, "Premier message");
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			Println("Unexpected exception occured in testMessage, while waiting. Here is the error : " + e);
		}
		M2 = new Message(2, "Deuxième message");
		if(M2.getTimeStamp().compareTo(M1.getTimeStamp())==1) {	// compareTo renvoit 1 si l'opérande gauche est supérieure à la droite
			Println("Timestamp working correctly");
		}
	}
	
	public static void main (String[] args) {
		testTimeStamp();
	}
}
