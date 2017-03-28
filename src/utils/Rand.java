package utils;

import java.util.Random;

public class Rand {
	private Random Generator = new Random(42L);
	
	public int randInt(int Max) {
		return Generator.nextInt(Max);
	}
	
	public int randInt() {
		return Generator.nextInt();
	}
}
