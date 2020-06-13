<?php
/**
 * Created by PhpStorm.
 * User: Christian
 * Date: 16.10.2018
 * Time: 13:07
 */

class DatabaseHandler
{
    private $ldbName = "bkoapp";
    private $ldbHost = "127.0.0.1";
    private $ldbPort = "3306";
    private $ldbUser = "bkoapp_admin";
    private $ldbPassword = "KP955JeFAeGmHZmZckesdPyEbSFgMeDn";

    private $localMode = true;

    private $dbName = "bkoapp";
    private $dbHost = "bkoapp.hlammert.de";
    private $dbPort = "3306";
    private $dbUser = "bkoapp_admin";
    private $dbPassword = "KP955JeFAeGmHZmZckesdPyEbSFgMeDn";

    public $dbh;

    function __construct()
    {
        if ($this->localMode)
            $this->dbh = new mysqli($this->ldbHost, $this->ldbUser, $this->ldbPassword, $this->ldbName, $this->ldbPort);
        else
            $this->dbh = new mysqli($this->dbHost, $this->dbUser, $this->dbPassword, $this->dbName, $this->dbPort);

        if($this->dbh->connect_error)
        {
            echo "Error mit der Datenbank Verbindung!";
            echo "Error: " . $this->dbh->connect_error;
            echo "Errno: " . $this->dbh->connect_errno;
        }

        return $this->dbh;
    }
}
