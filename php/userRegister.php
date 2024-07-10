<?php
// Database connection parameters
$server = "localhost";
$dbName = "inventorymanagement";
$username = "root";
$password = "";

try {
    // Create a PDO instance
    $dbPDO = new PDO("mysql:host=$server;dbname=$dbName", $username, $password);
    $dbPDO->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

    // Check if all required parameters are set
    if (isset($_GET['Username']) && isset($_GET['Phone']) && isset($_GET['Email']) && isset($_GET['Password'])) {
        $username = $_GET['Username'];
        $phone = $_GET['Phone'];
        $email = $_GET['Email'];
        $password = $_GET['Password'];

        // Prepare SQL statement to insert into user table
        $stmt = $dbPDO->prepare("INSERT INTO `user`(`Username`, `Phonenumber`, `Email`, `Password`) VALUES (:name, :phone, :email, :password)");
        
        // Bind parameters
        $stmt->bindParam(':name', $username);
        $stmt->bindParam(':phone', $phone);
        $stmt->bindParam(':email', $email);
        $stmt->bindParam(':password', $password);

        // Execute the query
        if ($stmt->execute()) {
            echo json_encode(array("message" => "Data inserted successfully"));
        } else {
            echo json_encode(array("error" => "Failed to insert data"));
        }
    } else {
        echo json_encode(array("error" => "Missing required parameters"));
    }
} catch(PDOException $e) {
    echo json_encode(array("error" => "Database error: " . $e->getMessage()));
}

// Close the connection (optional as it will be closed automatically when script ends)
$dbPDO = null;
?>
