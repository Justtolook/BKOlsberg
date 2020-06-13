<?php
/**
 * Created by PhpStorm.
 * User: Christian
 * Date: 27.10.2018
 * Time: 23:49
 */

    session_start();

    foreach ($_SESSION as $schluessel => $wert)
    {
        unset($schluessel);
    }

    foreach ($_POST as $schluessel => $wert)
    {
        unset($schluessel);
    }

    session_destroy();
    session_start();

    $_SESSION["errorMsg"] = "Sie wurden erfolgreich ausgeloggt!";
    $_SESSION["loginValidate"] = false;

    header("Location: ../index.php");
    exit();
?>
