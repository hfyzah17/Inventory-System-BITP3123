package ProjectDAD;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Product{

	private int productId;
	private String productName;
	private int quantity;
	private double price;
	
	public Product() {
	}
	
	public Product(int productId, String productName, int quantity, double price) {
		this.productId = productId;
		this.productName = productName;
		this.quantity = quantity;
		this.price = price;
	}
	
	public int getProductId() {
		return productId;
	}
	
	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	public String getProductName() {
		return productName;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return String.format("%-12d %-30s %-10d %.2f%n",productId, productName, quantity, price);
	}
}
