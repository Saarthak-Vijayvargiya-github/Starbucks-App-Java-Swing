package oop.StarBucksGithub;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class StarBucksAppUI {

	static List<Beverage> list = new ArrayList<Beverage>();
	static Beverage currentBev = null;
	static JFrame frame = new JFrame();
	static String[] startOpts = {"Get Bill", "Place an Order", "Cart"};
	static String[] bevOpts = {"Exit", "DarkRoast", "Decafe","Espresso","HouseBlend"};
	static String[] CondiOpts = {"None", "Milk", "Mocha","Soy","Whip"};
	static ImageIcon mainLogo, bill_Icon, paySuccess, cancelOrder, cartImg, coffeeImg, condimentsImg;
	
	static {
		try {mainLogo = new ImageIcon(StarBucksAppUI.class.getResource("/MainLogo.png")); }
		catch (Exception e) {mainLogo = null;}
		
		try {bill_Icon = new ImageIcon(StarBucksAppUI.class.getResource("/bill_icon.png"));} 
		catch (Exception e) {bill_Icon = null;}
		
		try {paySuccess = new ImageIcon(StarBucksAppUI.class.getResource("/check_mark.png"));} 
		catch (Exception e) {paySuccess = null;}
		
		try {cancelOrder = new ImageIcon(StarBucksAppUI.class.getResource("/SadEmoji.png"));} 
		catch (Exception e) {cancelOrder = null;}
		
		try {coffeeImg = new ImageIcon(StarBucksAppUI.class.getResource("/coffee.png"));} 
		catch (Exception e) {coffeeImg = null;}
		
		try {condimentsImg = new ImageIcon(StarBucksAppUI.class.getResource("/condimentsImg.png"));} 
		catch (Exception e) {condimentsImg = null;}
	}
	
	public static int getChoiceMain() {
		int choice = JOptionPane.showOptionDialog(
                frame,
                "Welcome to StarBucks :D",
                "Main Menu",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                mainLogo,
                startOpts,
                "default");
		return choice;
	}
	
	public static int getBeverage() {;
		int choice = JOptionPane.showOptionDialog(
                frame,
                "Hey, what's up on your Mind :)",
                "Select Beverage",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                coffeeImg,
                bevOpts,
                "default");
		return choice;
	}
	
	public static int getCondiment() {
		int choice = JOptionPane.showOptionDialog(
                frame,
                "Addons? Select none when you are satified :P\nYour current order: \n" +
                currentBev.getDescription(),
                "Select Condiments",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                condimentsImg,
                CondiOpts,
                "default");
		return choice;
	}
	
	public static void showCart() {
		StringBuilder sb = new StringBuilder();
		float[] total = {0};
		int icon = 0;
		int[] index = {0};
		ArrayList<String> show = new ArrayList<String>();
		
		if(list.size() != 0) {
			show.add("Go Back");
			show.add("Remove Item");
			icon = JOptionPane.INFORMATION_MESSAGE;
			sb.append("Items in your cart: \n");
			list.forEach(beverage -> {
				sb.append((index[0]+1) + ". " + beverage.getDescription() + beverage.cost() + "\n");
				total[0] += beverage.cost();
				index[0]++;
			});
			sb.append("\nTotal: " + total[0] + "\n");
			try {cartImg = new ImageIcon(StarBucksAppUI.class.getResource("/shopping-cart.png"));} 
			catch (Exception e) {cartImg = null;}
		}
		else {
			show.add("Go back");
			icon = JOptionPane.WARNING_MESSAGE;
			sb.append("Your Cart is empty :| \n");
			cartImg = null;
		}
		
		int opt = JOptionPane.showOptionDialog(
                frame,
                sb,
                "Cart",
                JOptionPane.DEFAULT_OPTION,
                icon,
                cartImg,
                show.toArray(),
                "default");
		
		if(opt == 1) {
			removeItemDialog();
		}
	}
	
	public static int getBill() {
		StringBuffer sb = new StringBuffer();
		float[] total = {0};
		int icon = 0;
		ArrayList<String> show = new ArrayList<String>();
		
		sb.append("Your Bill: \n");
		list.forEach(beverage -> {
			sb.append(beverage.getDescription() + beverage.cost() + "\n");
			total[0] += beverage.cost();
		});
		sb.append("Grand Total: " + total[0] + "\n");
		sb.append("\nCross to exit the App\n");
		icon = JOptionPane.WARNING_MESSAGE;
		show.add("Pay");
		show.add("Cancel");
		
		int opt = JOptionPane.showOptionDialog(
                frame,
                sb,
                "Please review your order",
                JOptionPane.CLOSED_OPTION,
                icon,
                bill_Icon,
                show.toArray(),
                "default");
		
		show.set(0,"Close");
		show.remove(1);
		
		if(opt == -1) {
			icon = JOptionPane.ERROR_MESSAGE;						
			JOptionPane.showOptionDialog(
	                frame,
	                "We are sorry to let you go :( \n",
	                "Thanks and Visit Again",
	                JOptionPane.CLOSED_OPTION,
	                icon,
	                cancelOrder,
	                show.toArray(),
	                "default");
			return 0;
		} else if(opt == 0){
			icon = JOptionPane.INFORMATION_MESSAGE;						
			JOptionPane.showOptionDialog(
	                frame,
	                "Payment Successful of INR " + total[0] ,
	                "Thanks and Visit Again",
	                JOptionPane.CLOSED_OPTION,
	                icon,
	                paySuccess,
	                show.toArray(),
	                "default");
			return 0;
		} else {
			return opt;
		}
	}
	
	public static void removeItemDialog() {
		String index = null;
		index = JOptionPane.showInputDialog("Enter the index of the item: ");
		try {
			int ind = Integer.parseInt(index);
			list.remove(ind-1);
		} catch (Exception e){
			return;
		}
	}
	
	public static void main(String[] args) {
		int choice;
		
		while(true) {
			choice = getChoiceMain();
			if(choice == 1) {
				choice = getBeverage();
				
				if(choice <= 0) {
					continue;
				} else {
					currentBev = OrderBeverage.getInstance(choice);
					
					// Decorating a beverage with condiments
					while(currentBev != null) {
						choice = getCondiment();
						if(choice <= 0) {
							list.add(currentBev);
							break;
						}else {
							currentBev = CondimentDecorator.decorate(currentBev, choice);
						}
					}
				}
			}
			
			else if(choice == 2) {
				showCart();
			}
			else if(choice == -1) {
				System.exit(0);
			}
			
			else {				
				if(list.size() != 0) {
					choice = getBill();
					if(choice == 1) continue;
				}
				else {
					showCart();
					continue;
				}
				System.exit(0);
			}
		}
	}
}
