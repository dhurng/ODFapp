<?php
    $conn = mysqli_connect("localhost", "my_user", "my_password", "my_db");


    $name = $_POST["name"];
    $age = $_POST["age"];
    $username = $_POST["username"];
    $password = $_POST["password"];

    
    //avoid sql injection
    $statement = mysqli_prepare($conn, "INSERT INTO User(name, age, username, password)
                                        VALUES (?, ?, ?, ?)");
    
    //siss = string int string string
    mysqli_stmt_bind_param($statement, "siss", $name, $age, $username, $password);
    mysqli_stmt_execute($statement);

    mysqli_stmt_close($statement);
    mysqli_close($conn);
?>