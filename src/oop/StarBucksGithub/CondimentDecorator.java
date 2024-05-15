package oop.StarBucksGithub;

public class CondimentDecorator{
//	Beverage bev;
//	int choice;
	
	public static Beverage decorate(Beverage bev, int choice) {
		switch(choice) {
		case 1:
			return new Milk(bev);
		case 2:
			return new Mocha(bev);
		case 3:
			return new Soy(bev);
		case 4:
			return new Whip(bev);
		default:
			return bev;
		}
	}
}
