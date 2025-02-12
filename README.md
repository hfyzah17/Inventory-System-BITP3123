# Inventory Management System
BITP 3123 DISTRIBUTION DEVELOPMENT


# How many apps are involved?


# Apps involved: 
1)Eclipse(.java/etc) 2)Xampp(phpmyadmin) 3)Mysql workbench 4)Visualcode(.php)

# Brief explanation of each apps:

# 1) Eclipse: This app do design ui and functions  using Java  to make inventory mangement system in java

   Function of Eclipse: - Action Listener: These are bit code that                                                        react to user actions, like clicking the                                                        "login" button.
                         -Error Handling:  This ensures users enter                                                       valid information and handles any problems                                                      communicating with the server.
                         -Integrate Database : To connect application                                                     to database to store and retrieve the data                                                      to run SQL queries and manage data.
                         -Testing and debugging: Test application                                                         thoroughly to make sure everything  work                                                        correctly.
                                                  
                                                  
# 2)XAMPP(PHPMYADMIN): 
To let run web server on computer .It include Apache (webserver) and MYSQL (database server).phpMyAdmin  is web tool for manging MYSQL database,included with XAMPP.The function is to create ,modify and delete database (database management),Table Mangement to create table manage table , Data mangement to insert,update , delete the data and User mangement  to manage databse user account.

# 3)MYSQL Workbench:
# Functions of MySQL Workbench

MySQL Workbench allows you to create and visualize database schemas, making database design intuitive. It supports SQL development, enabling you to write and run SQL queries efficiently. With data modeling tools, you can design database structures visually. The tool also provides features for database administration, helping you manage databases, users, and backups. Additionally, MySQL Workbench offers performance tuning capabilities to optimize and analyze database performance, ensuring your system runs smoothly.



# 4)VisualCode(.PHP)
# Functions of Visual Studio Code for PHP Development

Visual Studio Code makes writing and editing PHP code straightforward with syntax highlighting and code completion. Its functionality is enhanced by extensions such as PHP Intelephense, which adds advanced PHP features. Debugging PHP code is simple with built-in tools and extensions. The integrated terminal allows running PHP scripts and commands directly within the editor. Additionally, it integrates seamlessly with Git for managing source code versions efficiently.

# Architecture/Layer diagram for each of the apps including the middleware
![architecture layer diagram](https://github.com/user-attachments/assets/33bacfca-498a-4196-be86-cbb2c4ab1adc)


# RESTful API (Common for modern inventory systems):

Endpoints:
/products (GET): Retrieve a list of products
/products/{id} (GET): Get details of a specific product (using product ID)
/products (POST): Create a new product
/products/{id} (PUT): Update an existing product
/products/{id} (DELETE): Delete a product
/inventory (GET): Get overall inventory status
/inventory/{location} (GET): Get inventory status for a specific location
/orders (POST): Create a new order
/orders/{id} (GET): Get details of a specific order
/suppliers (GET): Retrieve a list of suppliers
(and similar endpoints for managing suppliers)


# Middleware: Common uses include:
Authentication and authorization (ensuring users have access)
Input validation (verifying product data integrity)
Logging and auditing inventory changes
Data transformation (preparing data for the application)


# Protocol: HTTP verbs (GET, POST, PUT, DELETE) are used to interact with resources.

socket : Two-way communication with mobile inventory scanners



# *Functions/Features in the middleware*

# 1. Authentication and Authorization

User Registration:
- New User Registration: Allows new users to create an account by providing a username, password, IC, and mobile number.
  
User Authentication:
- Login: Allows users to securely access the system by verifying their credentials (username and password).
- Logout: Ends the user session, ensuring no unauthorized access after the user leaves.

Role-Based Access Control (RBAC):
- Admin: Users with full access to all system features, including user management.

  
# 2. Inventory Management
CRUD Operations:

- Create new inventory items: Allows adding new items to the inventory database, specifying details such as product name product ID, quantity, and price.
- Read inventory item details: Retrieves detailed information about specific inventory items.
- Update inventory item information: Modifies existing inventory item details, such as quantity adjustments.
- Delete inventory items: Removes items from the inventory database when they are no longer needed.


# *The database and tables involve in the projects*

# Database: inventorymanagement xampp

- TABLE: inventory 
 -  ProductID` int NOT NULL,
  ProductName` varchar(45) NOT NULL,
  Quantity` int NOT NULL,
  Price` float NOT NULL,

# Database: inventorysystem

- TABLE: user 
 -  UserId int(11) NOT NULL,
  Username varchar(50) NOT NULL,
  Phonenumber varchar(15) NOT NULL,
  Email varchar(100) NOT NULL,
  Password varchar(50) NOT NULL
  
# *GROUP MEMBER*

SITI NURUL NURHAFIZAH BINTI ROSZAIMI (B032210073)
NUR ALIFAH ILYANA BINTI NORAZLAN (B032210109) 
NOOR FARAH ELLYANA BINTI MOHD JASMER (B032210059)
NURLIYANA ATHIRAH BINTI ROSLI (B032310136)
