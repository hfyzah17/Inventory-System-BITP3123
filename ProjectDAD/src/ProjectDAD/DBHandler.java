package ProjectDAD;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.*;
import java.util.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class DBHandler {
	
	public boolean insertProduct(Product product) {
		
		boolean result = false;
		
		try {
			
			String sql = "Insert Into inventorymanagement.inventory VALUES (?, ?, ?, ?)";
			Connection connection = DBConnection.getConnection();
			
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setInt(1, product.getProductId());
			stmt.setString(2, product.getProductName());
			stmt.setInt(3, product.getQuantity());
			stmt.setDouble(4, product.getPrice());
			
			int inserted = stmt.executeUpdate();
			
			result = inserted >= 1;
			
			DBConnection.disconnect(connection);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean deleteProduct(int id) {
		boolean result = false;
		
		try {
			String sql = "Delete from inventorymanagement.inventory where ProductID=?";
			Connection conn = DBConnection.getConnection();
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1,  id);
			
			int delete = stmt.executeUpdate();
			
			result = delete >= 1;
			
			DBConnection.disconnect(conn);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	public boolean updateProduct(Product product) {
		boolean result = false;
		
		try {
			
			String sql = "UPDATE inventorymanagement.inventory set Quantity=?, Price=? where ProductID=?";
			Connection conn = DBConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, product.getQuantity());
			stmt.setDouble(2, product.getPrice());
			stmt.setInt(3, product.getProductId());
			
			int updated = stmt.executeUpdate();
			result = updated >= 1;
			
			DBConnection.disconnect(conn);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	public Product getProduct(int id) {
		Product product = null;
		
		try {
			
			String sql = "SELECT * FROM inventorymanagement.inventory WHERE ProductID=?";
			Connection conn = DBConnection.getConnection();
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				
				product = new Product(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getDouble(4));
				
			}
			
			DBConnection.disconnect(conn);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return product;
	}
	
	public ArrayList<Product> getProducts() {
		
		ArrayList<Product> products = new ArrayList<Product>();
		
Product product = null;
		
		try {
			
			String sql = "SELECT * FROM inventorymanagement.inventory";
			Connection conn = DBConnection.getConnection();
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				
				product = new Product(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getDouble(4));
				
				products.add(product);
				
			}
			
			DBConnection.disconnect(conn);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return products;
	}
}
