<?php
/**
 * Created by PhpStorm.
 * User: Christian
 * Date: 28.10.2018
 * Time: 13:21
 */


    $password = $_GET["pswd"];

    $hpswd = password_hash($password, PASSWORD_DEFAULT);

    echo $hpswd;
?>