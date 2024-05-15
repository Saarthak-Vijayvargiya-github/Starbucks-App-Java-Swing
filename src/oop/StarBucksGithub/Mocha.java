package oop.StarBucksGithub;

public class Mocha extends Condiment{
	private Beverage beverage;
	public Mocha(Beverage beverage) {
		this.beverage = beverage;
		description = "Mocha ";
	}
	
	@Override
	public String getDescription() {
		return beverage.getDescription() + description;
	}
	@Override
	public float cost() {
		return 63f + beverage.cost();
	}
}
