package oop.StarBucksGithub;

public class Whip extends Condiment{
	private Beverage beverage;
	public Whip(Beverage beverage) {
		this.beverage = beverage;
		description = "Whip ";
	}
	
	@Override
	public String getDescription() {
		return this.beverage.getDescription() + description; 
	}
	@Override
	public float cost() {
		return 51f + beverage.cost();
	}
}
