<?php
	$server = "localhost";
	$dbName = "inventorymanagement";
	$username = "root";
	$password = "";

	try {
		$dbPDO = new PDO("mysql:host=$server;dbname=$dbName",$username, $password);
		$dbPDO->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
	} catch (PDOException $e) {
		echo "Connection failed: " . $e->getMessage();
		die();
	}
?>