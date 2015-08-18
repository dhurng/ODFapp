<?php
    $conn = mysqli_connect("localhost", "my_user", "my_password", "my_db");

    $username = $_POST["username"];
    $password = $_POST["password"];
    
    $statement = mysqli_prepare($conn, "SELECT * FROM User
                                        WHERE username = ?
                                        AND password = ?");
    mysqli_stmt_bind_param($statement, "ss", $username, $password);
    mysqli_execute($statement);

    //store result and name them
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $userID, $name, $age, $username, $password);

    //array to hold data
    $user = array();
    
//get all records
while(mysqli_stmt_fetch($statement)) {
    $user[name] = $name;
    $user[age] = $age;
    $user[username] = $username;
    $user[password] = $password;
}

    echo json_encode($user);

    mysqli_stmt_close($statement);
    mysqli_close($conn);

?>