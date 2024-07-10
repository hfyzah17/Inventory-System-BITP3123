
 package ProjectDAD;
 
 import java.awt.EventQueue; 
 import java.util.ArrayList; 
 import java.util.Scanner;
 import javax.swing.JFrame; 
 import javax.swing.JPanel; 
 import javax.swing.border.EmptyBorder;
 
 public class InventoryController {
 
 private Scanner keyboard = new Scanner(System.in); 
 private DBHandler handler = new DBHandler();
 
 private void addProduct() { 
	 System.out.print("Enter Product ID : "); 
	 int ProductId = keyboard.nextInt(); 
	 keyboard.nextLine();
	 System.out.print("Enter Product Name : "); 
	 String ProductName = keyboard.nextLine();
	 System.out.print("Enter Product Quantity : ");
	 int quantity = keyboard.nextInt();
	 System.out.print("Enter Product Price : "); 
	 double Price = keyboard.nextDouble();
	 
	 Product product = new Product(ProductId, ProductName, quantity, Price);
	 if(handler.insertProduct(product)) {
		 System.out.print("Product has been saved into the database"); 
		 }else {
			 System.out.print("Failed to save the product"); 
			 } 
 }
 
 public boolean editProduct(Product product) {
	 return this.handler.updateProduct(product);
 }
 
 public boolean  deleteProduct(int id) {
	 return this.handler.deleteProduct(id);
 }
 
 private void updateProduct() {
 System.out.print("Please enter Product Id : "); 
 int id = keyboard.nextInt();
 
 Product product = handler.getProduct(id);
 
 if(product == null) { 
	 System.out.println("no such Product Exist with ID: "+id); 
	 return; 
 }
 
 System.out.println("\nProduct ID: " + product.getProductId());
 System.out.println("Product Name: " + product.getProductName());
 System.out.println("Quantity: " + product.getQuantity());
 System.out.println("Price: " + product.getPrice()); 
 System.out.println();
 
 System.out.print("Please enter new Quantity: "); 
 int quantity = keyboard.nextInt(); 
 System.out.print("Please enter new price: "); 
 double price = keyboard.nextDouble();
 
 product.setQuantity(quantity); 
 product.setPrice(price);
 
 if(handler.updateProduct(product)) {
	 System.out.println("Product has been updated !"); 
 }else {
	 System.out.println("Product has not been updated !"); 
	 } 
 }
  
 public void displayInventory() { 
	 ArrayList<Product> products = handler.getProducts();
 
     System.out.print(String.format("%-12s %-30s %-10s %s%n", "Product Id","Product Name", "Quantity", "Price"));
 
     for(Product p: products) { 
	 System.out.print(p); 
	 } 
 }
 
 public String getProductsAsString() {
	 ArrayList<Product> products = handler.getProducts();
	 
	 String output = String.format("%-12s %-30s %-10s %s%n", "Product Id","Product Name", "Quantity", "Price");
	 
	 for(Product p: products) { 
		 output += p.toString(); 
		 } 
	 return output;
 }
 
 private void deleteProduct() { 
	 System.out.print("Please enter Product Id: ");
	 int id = keyboard.nextInt();
 
 if(handler.deleteProduct(id)) {
	 System.out.print("Product with id " + id +"has been deleted !"); 
	 }else {
		 System.out.print("Product with id " + id +"has not been deleted !"); 
		 } 
 }
 
 private int menu() { 
	 System.out.println("Inventory Management System");
	 System.out.println("1. Add New Product");
	 System.out.println("2. Delete Product"); 
	 System.out.println("3. Update Product");
	 System.out.println("4. Display All products"); 
	 System.out.println("5. Quit");
	 
	 int choice; 
	 do { 
		 System.out.print("Enter option: "); 
		 choice =keyboard.nextInt(); 
		 } while (choice < 1 || choice > 5); 
	 return choice; 
	 }
 
 public void runApp() {
 
 int choice;
 
 do { 
	 choice = menu(); 
	 System.out.println();
	 
	 if(choice == 1) { 
		 addProduct(); 
	 }else if(choice == 2){ 
		 deleteProduct();
	 }else if(choice ==3){
		 updateProduct(); 
	 }else if(choice == 4){
		 displayInventory(); 
	 }
	 System.out.println(); 
	 }while(choice != 5); 
 }
 
 public ArrayList<Product>getProducts() { 
	 return this.handler.getProducts();
	 } 
 
 public boolean addProduct(Product product) {
	 return this.handler.insertProduct(product);
 }
 
 
 //get product by id
 public Product getProduct (int id) {
	 return this.handler.getProduct(id);
 }
 }
 
 