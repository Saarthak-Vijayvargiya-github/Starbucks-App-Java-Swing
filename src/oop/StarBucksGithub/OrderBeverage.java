package oop.StarBucksGithub;

public class OrderBeverage {
	/*
	 * System.out.println("1. DarkRoast");
		System.out.println("2. Decafe");
		System.out.println("3. Espresso");
		System.out.println("4. HouseBlend");
	 */
	public static Beverage getInstance(int choice) {
		switch(choice) {
		case 1:
			return new DarkRoast();
		case 2:
			return new Decafe();
		case 3:
			return new Espresso();
		case 4:
			return new HouseBlend();
		default:
			return null;
		}
	}
}
