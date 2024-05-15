package oop.StarBucksGithub;

public class Espresso extends Beverage{
	public Espresso() {
		description = "Espresso ";
	}
	@Override
	public float cost() {
		return 65f;
	}
}
