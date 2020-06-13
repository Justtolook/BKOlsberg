<?php
/**
 * Created by PhpStorm.
 * User: Christian
 * Date: 15.10.2018
 * Time: 11:55
 */

    require_once($_SERVER["DOCUMENT_ROOT"]. "/classes/DatabaseHandler.php");

    session_start();

    $input_Password = $_POST["inputPassword"];
    $input_Username = $_POST["inputUsername"];

    //$hashedPassword = password_hash($input_Password, PASSWORD_DEFAULT);

    $sql = "SELECT * FROM Users WHERE Username = ?";

    $databasehandler = new DatabaseHandler();

    $dbh = $databasehandler->dbh;

    if ($sqlPassword = $dbh->prepare($sql))
    {
        $sqlPassword->bind_param("s",$input_Username);
        $sqlPassword->execute();

        $result = $sqlPassword->get_result();

        while ($row = $result->fetch_array()) {
            $databasePassword = $row['Password'];
            $databaseUserID = $row['ID_User'];
        }

        if (password_verify($input_Password, $databasePassword))
        {
            $_SESSION["loginValidate"] = true;
            $_SESSION["userID"] = $databaseUserID;
            header("Location: ../main.php");
            exit();
        }
        else
        {
            $_SESSION["loginValidate"] = false;
            $_SESSION["userID"] = null;
            $_SESSION["errorMsg"] = "Die eingegebenen Login-Daten sind ungültig!";
            header("Location: ../index.php");
            exit();
        }

    }
    else
    {
        $error = $dbh->errno . " " . $dbh->error;
        echo $error;
    }


?>