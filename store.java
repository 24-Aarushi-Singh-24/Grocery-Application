package lab;

import java.util.*;

class Item{
	protected int itemCode;
	protected float price;
	protected int availableQty;
	
	
	Scanner sc = new Scanner(System.in);
	
	public void setDetails() {
		System.out.println("Item Code: ");
		int itemCode = sc.nextInt();
		sc.nextLine();
		
		System.out.println("Price: ");
		float price = sc.nextFloat();
		sc.nextLine();
		
		System.out.println("Available Quantity: ");
		int availableQty = sc.nextInt();
		sc.nextLine();
		
		this.itemCode = itemCode;
		this.price = price;
		this.availableQty = availableQty;
	}
	
	public int getItemCode(){
		return this.itemCode;
	}
	
	public void getDetails() {
		System.out.println("Price: "+this.price);
		System.out.println("Available Quantity: "+this.availableQty);
	}
}

class FoodItem extends Item{
	private String dateOfExpiry;
	
	@Override
	public void getDetails() {
		super.getDetails();
		System.out.println("Date of Expiry: "+dateOfExpiry);
	}
}

class NonFoodItem extends Item{
	private int returnCount;
}

class Sell{
	protected String dateOfSell;
	protected int itemCode;
	protected int quantity;
	protected int totalAmount;
	
	
	Sell(int itemCode, int quantity){
		this.itemCode = itemCode;
		this.quantity = quantity;
	}
	
	public int getItemCode() {
		return this.itemCode;
	}
}

class Return{
	protected String dateOfReturn;
	protected int itemCode;
	protected int quantity;
	protected int returnAmount;
	
	Return(int itemCode, int quantity) {
		this.itemCode = itemCode;
		this.quantity = quantity;
	}
}


class User{
	protected int userID;
	protected String name;
	protected String dateOfBirth;
	protected String address;
	protected String pan;
	public ArrayList<Sell> sell;
	public ArrayList<Item> items;
	public ArrayList<Return> returnitem;
	
	Scanner sc = new Scanner(System.in);
	
	public void modifyStock(int itemCode) {
		for(int j = 0; j<items.size();j++) {
			if(items.get(j).getItemCode() == itemCode) {
				
				int choice,flag;
				
				while(true) {
					
					System.out.println("Choose what you want to update: ");
					System.out.println("1) Price \n2) Available Quantity \n3) Exit");
					choice = sc.nextInt();
					
					switch(choice) {
					   
					case 1:
						System.out.println("Enter the new price: ");
						float price = sc.nextFloat();
						items.get(j).price = price;
						break;
						
					case 2:
						System.out.println("Enter the new quantity available: ");
						int qty = sc.nextInt();
						items.get(j).availableQty = qty;
						break;
						
					case 3:
						return;
						
					default:
						System.out.println("Enter a correct choice.");
						break;
					}
					
				}
				
				
			}
		}
	}

	
	
}

class Admin extends User{
	private String dateOfJoining;
	private float salary;
	private String permissibleOperations;
	
	Scanner sc = new Scanner(System.in);
	
	public void addNewStock(int index,Item i) {
		i.setDetails();
		items.add(i);
		index++;
	}
	
	public void deleteStock(int index,int itemCode) {
		for(int j = 0; j<items.size();j++) {
			if(items.get(j).getItemCode() == itemCode) {
				items.remove(j);
				index--;
			}
		}
	}
	
	
}

class General extends User{
	private String dateOfJoining;
	private float salary;
	private float dutyHourperDay;
	
	
	@Override
	public void modifyStock(int itemCode) {
		for(int j = 0; j<sell.size();j++) {
			if(sell.get(j).getItemCode() == itemCode) {
				
				int choice,flag;
				
				while(true) {
					
					System.out.println("Choose what you want to update: ");
					System.out.println("1) Total Amount \n2) Available Quantity \n3) Exit");
					choice = sc.nextInt();
					
					switch(choice) {
					   
					case 1:
						System.out.println("Enter the new total amount: ");
						int amount = sc.nextInt();
						sell.get(j).totalAmount = amount;
						break;
						
					case 2:
						System.out.println("Enter the new quantity: ");
						int qty = sc.nextInt();
						sell.get(j).quantity = qty;
						break;
						
					case 3:
						return;
						
					default:
						System.out.println("Enter a correct choice.");
						break;
					}
					
				}
				
				
			}
		}
		
	}
	
	public void sellItem(int itemCode, int quantity) {
		sell.add(new Sell(itemCode,quantity));
		for(int j = 0; j<items.size();j++) {
			if(items.get(j).getItemCode() == itemCode) {
				this.modifyStock(itemCode);
			}
		}
	}
	
	public void returnItem(int itemCode, int quantity) {
		Return obj = new Return(itemCode,quantity);
		returnitem.add(obj);
		for(int j = 0; j<items.size();j++) {
			if(items.get(j).getItemCode() == itemCode) {
				this.modifyStock(itemCode);
			}
		}
		
	}
	
	public void displayStock(int itemCode) {
		for(int j = 0; j<items.size(); j++) {
			if(items.get(j).getItemCode() == itemCode) {
				items.get(j).getDetails();
			}
		}
	}
		
}

public class store {
	public static void main(String args[]) {
		
		Admin a = new Admin();
		General g = new General();
		
		ArrayList<Item> itemsAdmin = new ArrayList<Item>();
		ArrayList<Sell> sellAdmin = new ArrayList<Sell>();
		ArrayList<Return> returnItemsAdmin = new ArrayList<Return>();
		a.items = itemsAdmin;
		a.sell = sellAdmin;
		a.returnitem = returnItemsAdmin;
		
		ArrayList<Item> itemsGeneral = new ArrayList<Item>();
		ArrayList<Sell> sellGeneral = new ArrayList<Sell>();
		ArrayList<Return> returnItemsGeneral = new ArrayList<Return>();
		g.items = itemsGeneral;
		g.sell = sellGeneral;
		g.returnitem = returnItemsGeneral;
		
		Scanner sc = new Scanner(System.in);
		
		int choice1;
		int choice2;
		
		int indexItem = 0;
		int code;
		
		while(true)
		{
			System.out.println("Choose: ");
			System.out.println("1) Admin \n2)General");
			choice1 = sc.nextInt();
			sc.nextLine();
			
			switch(choice1) {
			case 1:
				System.out.println("Choose: ");
				System.out.println("1) Add New Stock \n2)Delete Stock \n3) Modify Stock \n4)Exit");
				choice2 = sc.nextInt();
				sc.nextLine();
				
				switch(choice2) {
				case 1:
					Item item = new Item();
					a.addNewStock(indexItem, item);
					break;
					
				case 2:
					System.out.println("Enter the item code: ");
					code = sc.nextInt();
					sc.nextLine();
					a.deleteStock(indexItem, code);
					break;
					
				case 3:
					System.out.println("Enter the item code: ");
					code = sc.nextInt();
					sc.nextLine();
					a.modifyStock(code);
					break;
					
				case 4: 
					return;
					
				default:
					System.out.println("Invalid");
					break;
				}
				break;
				
			case 2:
				System.out.println("Choose: ");
				System.out.println("1) Sell Item \n2)Return Item \n3) Display Stock \n4)Exit");
				choice2 = sc.nextInt();
				sc.nextLine();
				
				switch(choice2) {
				case 1:
					System.out.println("Enter item code: ");
					code = sc.nextInt();
					sc.nextLine();
					System.out.println("Enter Quantity: ");
					int quantity = sc.nextInt();
					sc.nextLine();
					g.sellItem(code, quantity);
					break;
					
				case 2:
					System.out.println("Enter item code: ");
					code = sc.nextInt();
					sc.nextLine();
					System.out.println("Enter Quantity: ");
					int quantity1 = sc.nextInt();
					sc.nextLine();
					g.returnItem(code, quantity1);
					break;
					
				case 3:
					System.out.println("Enter item code: ");
					code = sc.nextInt();
					sc.nextLine();
					g.displayStock(code);
				}
				break;
				
			case 3:
				return;
				
			default:
				System.out.println("Invalid");
				break;
			}
		}
	}
}
