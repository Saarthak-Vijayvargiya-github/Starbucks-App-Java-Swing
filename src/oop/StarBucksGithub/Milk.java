package oop.StarBucksGithub;

public class Milk extends Condiment{
	private Beverage beverage;
	public Milk(Beverage beverage) {
		this.beverage = beverage;
		description = "Milk ";
	}
	
	@Override
	public String getDescription() {
		return this.beverage.getDescription() + description; 
	}
	@Override
	public float cost() {
		return 33f + beverage.cost();
	}

}
