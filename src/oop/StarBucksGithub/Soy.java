package oop.StarBucksGithub;

public class Soy extends Condiment{
	private Beverage beverage;
	public Soy(Beverage beverage) {
		this.beverage = beverage;
		description = "Soy ";
	}
	
	@Override
	public String getDescription() {
		return this.beverage.getDescription() + description; 
	}
	@Override
	public float cost() {
		return 47f + beverage.cost();
	}
}
