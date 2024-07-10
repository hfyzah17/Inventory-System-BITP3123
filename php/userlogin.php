<?php
    // Include the database connection file
    require_once("dbConnection.php");

    // Check if the POST request has been made
    if ($_SERVER['REQUEST_METHOD'] == 'POST') {
        // Retrieve  the POST request
        $Email = $_POST['Email'];
        $Password = $_POST['Password'];
        $access = $_POST['access'];
  
        // Check if email already exists
        $query = "SELECT * FROM useraccount WHERE Email = :Email";
        $stmt = $dbPDO->prepare($query);
        $stmt->execute(array(':Email' => $Email));
        $result = $stmt->fetch(PDO::FETCH_ASSOC);

        if ($result) {
            // Check password
            if ($result['Password'] == $Password) {
                // Password correct, check access
                if ($result['access'] == $access) {
                    // Fetch UserId from user table using email
                    $query = "SELECT UserId FROM user WHERE Email = :Email";
                    $stmt = $dbPDO->prepare($query);
                    $stmt->execute(array(':Email' => $Email));
                    $user = $stmt->fetch(PDO::FETCH_ASSOC);

                    if ($user) {
                        // Successful login
                        $response = array('status' => 'success', 'UserId' => $user['UserId']);
                    } else {
                        // No corresponding user found
                        $response = array('status' => 'error', 'message' => 'User ID not found');
                    }
                } else {
                    // Incorrect access
                    $response = array('status' => 'error', 'message' => 'Incorrect !!');
                }
            } else {
                // Incorrect password
                $response = array('status' => 'error', 'message' => 'Incorrect password !! Try Again');
            }
        } else {
            // Email not found
            $response = array('status' => 'error', 'message' => 'Email not found');
        }

        // Return the JSON response
        echo json_encode($response);
    } else {
        // If the request method is not POST, return an error
        $response = array('status' => 'error', 'message' => 'Invalid request method');
        echo json_encode($response);
    }
?>
