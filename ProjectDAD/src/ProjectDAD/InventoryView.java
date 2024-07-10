package ProjectDAD;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class InventoryView extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private InventoryController controller; 
	//component
	private JLabel lbSelect;
	private JLabel lblProductId;
	private JLabel lblProductName;
	private JLabel lblQuantity;
	private JLabel lblPrice;
	
	//textfiled
	private JTextField txtProductId;
	private JTextField txtProductName;
	private JTextField txtProductQuantity;
	private JTextField txtProductPrice;
	
	//combobox
	private JComboBox cboProduct;
	
	//button
	private JButton btndisplay;
	private JButton btnadd;
	private JButton btnedit;
	private JButton btndelete;
	private JButton btnclose;
	
	//display product
	private JTextArea txtDisplay;
	private JScrollPane pane;
	
	//panel
	private JPanel paneSelect;
	private JPanel paneInputs;
	
	public InventoryView() {
		getContentPane().setBackground(new Color(255, 182, 193));
		//frame
		this.setTitle("Inventory Management System");
		this.setSize(700,500);
		this.setPreferredSize(new Dimension(700,500));
		this.setLocation(200,300);
		this.setBackground(new Color(255, 182, 193));
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		//set panel
		setupSelectPanel();
		
		//Input Panel
		setupInputPanel();
		
		//output area
		this.txtDisplay = new JTextArea();
		Font font = new Font("Courier New", Font.PLAIN, 14);
		this.txtDisplay.setFont(font);
		this.pane = new JScrollPane(this.txtDisplay);
		this.pane.setPreferredSize(new Dimension(670, 200));
		this.pane.setAutoscrolls(true);
		getContentPane().add(this.pane);
		
		this.controller = new InventoryController(); 
		 this.loadProduct(); 
		
		//pack the GUI
		this.pack();
	}
	
	public void setupSelectPanel() {
		this.paneSelect = new JPanel();
		paneSelect.setForeground(new Color(255, 255, 255));
		paneSelect.setBackground(new Color(221, 160, 221));
		this.paneSelect.setPreferredSize(new Dimension(670, 90));
		this.paneSelect.setBorder(BorderFactory.createTitledBorder("Select Product"));
		getContentPane().add(this.paneSelect);
		
		this.lbSelect = new JLabel("Select a Product ID: ");
		lbSelect.setForeground(new Color(255, 255, 255));
		this.lbSelect.setPreferredSize(new Dimension(200,30));
		this.paneSelect.add(this.lbSelect);
		
		//JComboBox
		this.cboProduct = new JComboBox<Integer>();
		this.cboProduct.setPreferredSize(new Dimension(200, 30));
		this.cboProduct.addActionListener(this);
		this.paneSelect.add(this.cboProduct);
	}
	
	private void editProduct() {
		
		int idx = this.cboProduct.getSelectedIndex();
		
		if(idx >= 0) {
			
			try {
				int id = Integer.parseInt(this.txtProductId.getText());
				String name = this.txtProductName.getText();
				int qty = Integer.parseInt(this.txtProductQuantity.getText());
				double price = Double.parseDouble(this.txtProductPrice.getText());
				
				//product object
				Product product = new Product(id, name, qty, price);
				
				//calling controller
				if(controller.editProduct(product)) {
					displayInfo("Product has been updated");
				}else {
					displayError("Product not been updated");
				}
				
				this.loadProduct();
				resetForm();
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			
		}else {
			this.displayError("Please select product !");
		}
	}
	
	private void addProduct() {
		
		try {
			int id = Integer.parseInt(this.txtProductId.getText());
			String name = this.txtProductName.getText();
			int qty = Integer.parseInt(this.txtProductQuantity.getText());
			double price = Double.parseDouble(this.txtProductPrice.getText());
			
			//product object
			Product product = new Product(id, name, qty, price);
			
			//calling controller
			if(controller.addProduct(product)) {
				displayInfo("Product has been added");
			}else {
				displayError("Product is not added");
			}
			
			this.loadProduct();
			resetForm();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//Alert message
	public void displayInfo(String message) {
		JOptionPane.showMessageDialog(this, message, "Info", JOptionPane.INFORMATION_MESSAGE);
	}
	
	//Alert message
	public void displayError(String message) {
		JOptionPane.showMessageDialog(this, message, "Info", JOptionPane.ERROR_MESSAGE);
	}

	 private void loadProduct() { 
		 this.cboProduct.removeAllItems();
		 ArrayList<Product> products = this.controller.getProducts(); 
		 for(Product p:products) { 
			 this.cboProduct.addItem(p.getProductId()); 
		 }
				/* this.cboProduct.setSelectedIndex(-1); */
	 }
	 
	 private void resetForm() {
		 this.txtDisplay.setText("");
		 this.txtProductPrice.setText("");
		 this.txtProductId.setText("");
		 this.txtProductName.setText("");
		 this.txtProductQuantity.setText("");
	 }
	 

	//input product
	public void setupInputPanel() {
		this.paneInputs = new JPanel();
		paneInputs.setForeground(new Color(255, 255, 255));
		paneInputs.setBackground(new Color(221, 160, 221));
		this.paneInputs.setPreferredSize(new Dimension(670, 120));
		this.paneInputs.setBorder(BorderFactory.createTitledBorder("Manage Product"));
		getContentPane().add(this.paneInputs);
		
		this.lblProductId = new JLabel("Product ID : ");
		lblProductId.setForeground(new Color(255, 255, 255));
		this.lblProductId.setPreferredSize(new Dimension(150,25));
		this.paneInputs.add(this.lblProductId);
		
		this.txtProductId = new JTextField();
		this.txtProductId.setPreferredSize(new Dimension(150,25));
		this.paneInputs.add(this.txtProductId);
		
		this.lblProductName = new JLabel("Product Name : ");
		lblProductName.setForeground(new Color(255, 255, 255));
		this.lblProductName.setPreferredSize(new Dimension(150,25));
		this.paneInputs.add(this.lblProductName);
		
		this.txtProductName = new JTextField();
		this.txtProductName.setPreferredSize(new Dimension(150,25));
		this.paneInputs.add(this.txtProductName);
		
		this.lblQuantity = new JLabel("Quantity : ");
		lblQuantity.setForeground(new Color(255, 255, 255));
		this.lblQuantity.setPreferredSize(new Dimension(150,25));
		this.paneInputs.add(this.lblQuantity);
		
		this.txtProductQuantity = new JTextField();
		this.txtProductQuantity.setPreferredSize(new Dimension(150,25));
		this.paneInputs.add(this.txtProductQuantity);
		
		this.lblPrice = new JLabel("Price : ");
		lblPrice.setForeground(new Color(255, 255, 255));
		this.lblPrice.setPreferredSize(new Dimension(150,25));
		this.paneInputs.add(this.lblPrice);
		
		this.txtProductPrice = new JTextField();
		this.txtProductPrice.setPreferredSize(new Dimension(150,25));
		this.paneInputs.add(this.txtProductPrice);
		
		this.btnadd = new JButton("Add Product");
		this.btnadd.setPreferredSize(new Dimension(120, 25));
		this.btnadd.addActionListener(this);
		this.paneInputs.add(this.btnadd);
		
		this.btnedit = new JButton("Edit Product");
		this.btnedit.setPreferredSize(new Dimension(120, 25));
		this.btnedit.addActionListener(this);
		this.paneInputs.add(this.btnedit);
		
		this.btndelete = new JButton("Delete Product");
		this.btndelete.setPreferredSize(new Dimension(120, 25));
		this.btndelete.addActionListener(this);
		this.paneInputs.add(this.btndelete);
		
		this.btndisplay = new JButton("Display All");
		this.btndisplay.setPreferredSize(new Dimension(120, 25));
		this.btndisplay.addActionListener(this);
		this.paneInputs.add(this.btndisplay);
		
		this.btnclose = new JButton("Close");
		this.btnclose.setPreferredSize(new Dimension(120, 25));
		this.btnclose.addActionListener(this);
		this.paneInputs.add(this.btnclose);
	}
	
	private void selectProduct() {
		int idx = this.cboProduct.getSelectedIndex();
		
		if(idx >= 0) {
			int id = Integer.parseInt(this.cboProduct.getSelectedItem().toString());
			Product product = controller.getProduct(id);
			
			if(product != null) {
				this.txtProductId.setText(String.valueOf(product.getProductId()));
				this.txtProductName.setText(String.valueOf(product.getProductName()));
				this.txtProductQuantity.setText(String.valueOf(product.getQuantity()));
				this.txtProductPrice.setText(String.valueOf(product.getPrice()));
			}
		}
	}
	
	private void deleteProduct() {
	
		int idx = this.cboProduct.getSelectedIndex();
		
		if(idx >= 0) {
			
			int id = Integer.parseInt(this.cboProduct.getSelectedItem().toString());
			
			if(controller.deleteProduct(id)) {
				displayInfo("Product has been deleted");
			}else {
				displayError("Product is not deleted !");
			}
			
			this.loadProduct();
			this.resetForm();
		}else {
			this.displayError("Please select a product !");
		}
	}
	
	private void displayProduct() {
		this.txtDisplay.setText(controller.getProductsAsString());
	}
	
	 @Override 
	public void actionPerformed(ActionEvent event) {
		
		Object src = event.getSource();
		
		if(src.equals(this.cboProduct)) {
			selectProduct();
		}else if(src.equals(this.btnadd)) {
			addProduct();
		}else if(src.equals(this.btnedit)) {
			this.editProduct();
		}else if(src.equals(this.btndelete)) {
			this.deleteProduct();
		}else if(src.equals(this.btnclose)) {
			System.exit(0);
		}else if(src.equals(this.btndisplay)) {
			displayProduct();
		}
	}

}
