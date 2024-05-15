package oop.StarBucksGithub;
import java.util.*;
import java.util.Scanner;

public class StarBucksApp {
	static Scanner sc = new Scanner(System.in);
	
	public static int getChoiceMain() {
		System.out.println("\n***Welcome to Starbucks***");
		System.out.println("0. Exit");
		System.out.println("1. Place Order");
		System.out.print("\nEnter your choice: ");
		return sc.nextInt();
	}
	
	public static int getBeverage() {
		System.out.println("\n***Select your beverage***");
		System.out.println("0. Exit");
		System.out.println("1. DarkRoast");
		System.out.println("2. Decafe");
		System.out.println("3. Espresso");
		System.out.println("4. HouseBlend");
		System.out.print("\nEnter your choice: ");
		int ch = sc.nextInt();
		return ch > 4 ? -1 : ch;	// Check Condition
	}
	
	public static int getCondiment() {
		System.out.println("\n***Select your Condiment***");
		System.out.println("0. Exit");
		System.out.println("1. Milk");
		System.out.println("2. Mocha");
		System.out.println("3. Soy");
		System.out.println("4. Whip");
		System.out.print("\nEnter your choice: ");
		int ch = sc.nextInt();
		return ch > 4 ? -1 : ch;		// Check Condition
	}
	
	public static void main(String[] args) {
		int choice;
		Beverage bev = null;
		List<Beverage> list = new ArrayList<Beverage>();
		
		while(true) {
			choice = getChoiceMain();
			if(choice == 1) {
				choice = getBeverage();
				
				if(choice <= 0) {
					continue;
				} else {
					bev = OrderBeverage.getInstance(choice);
					
					// Decorating a beverage with condiments
					while(bev != null) {
						choice = getCondiment();
						if(choice <= 0) {
							break;
						}else {
							bev = CondimentDecorator.decorate(bev, choice);
						}
					}
					list.add(bev);
				}
			} 
			
			else {
				float[] total = {0};
				if(list.size() != 0) {
					System.out.println("\nYour Bill: ");
					list.forEach(beverage -> {
						System.out.println(beverage.getDescription() + beverage.cost());
						total[0] += beverage.cost();
					});
					System.out.println("Grand Total: " + total[0]);
				}
				else {
					System.out.println("We are sorry to let you go :(");
				}
				break;
			}
		}
	}
}
