package com.mudkipboy7.alien;

/**
 * Runs code for testing purposes
 */
public class TestRun {
	public static void main(String[] args) {
		/*
		 * while (true) { Scanner reader = new Scanner(System.in);
		 * System.out.println("Input: "); Float n = reader.nextFloat(); if (n instanceof
		 * Float) System.out.println(getCurrentPhase(n)); }
		 */
		System.out.println(7.6D % 1.0D);

	}

	private static final double TWO_PI = Math.PI * 2.0D;

	public static double normalizeRadians(double value) {
		double ajustedPos = value % TWO_PI;
		return ajustedPos < 0.0D ? TWO_PI + ajustedPos : ajustedPos;
	}
}
