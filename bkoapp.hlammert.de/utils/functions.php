<?php
    function zeigeParameter()
    {
        session_start();

        echo "<h3>Parameter:</h3>";
        // Session Parameter
        echo "<h4>Sitzungsparameter SESSION</h4>";

        foreach ($_SESSION as $schluessel => $wert)
        {
            echo "<br> Schlüssel: $schluessel, Wert: $wert";
        }
        // Übergabeparameter
        echo "<h4>Formularparameter POST</h4>";

        foreach ($_POST as $schluessel => $wert)
        {
            if (gettype($wert) == "array")
            {
                foreach ($wert as $temp => $key)
                {
                    echo "<br>".$schluessel."    Wert: ".$key." Schlüssel: ".$temp;
                }
            }
            else
                echo "<br> Schlüssel: $schluessel, Wert: $wert";

        }
        echo "<br><hr><br>";
    }

    function checkAccess()
    {
        session_start();
        require_once($_SERVER["DOCUMENT_ROOT"]. "/classes/DatabaseHandler.php");
        require_once($_SERVER["DOCUMENT_ROOT"]. "/utils/functions.php");

        //zeigeParameter();

        if (isset($_SESSION["loginValidate"]))
        {
            if ($_SESSION["loginValidate"] == null || $_SESSION["loginValidate"] == false)
            {
                //Login is incorrect
                $_SESSION["errorMsg"] = "Sie müssen sich zuerst einloggen!";
                header("Location: ./index.php");
                exit();
            }
        }
        else
        {
            $_SESSION["errorMsg"] = "Sie müssen sich zuerst einloggen!";
            header("Location: ./index.php");
            exit();
        }
    }

    function countSQL($SQL)
    {
        require_once($_SERVER["DOCUMENT_ROOT"]. "/classes/DatabaseHandler.php");

        $temp = new DatabaseHandler();

        $dbh = $temp->dbh;

        $result = $dbh->query($SQL);

        while($row = $result->fetch_array())
        {
            return $row[0];
        }
    }

    function getLastElement($idName, $tableName)
    {
        require_once($_SERVER["DOCUMENT_ROOT"]. "/classes/DatabaseHandler.php");

        $sql = "SELECT MAX(".$idName.") as 'MAX' FROM ".$tableName."";

        $temp = new DatabaseHandler();

        echo "[Functions.php - GetLastElement] " .$sql;

        $dbh = $temp->dbh;

        $result = $dbh->query($sql);

        while($row = $result->fetch_array())
        {
            return $row['MAX'];
        }
    }

    function getIDofELement($idName, $tableName, $key, $value)
    {
        require_once($_SERVER["DOCUMENT_ROOT"]. "/classes/DatabaseHandler.php");

        $sql = "SELECT " .$idName. " as 'SEARCHED' FROM " .$tableName. "WHERE " .$key. " = '" .$value. "'";

        echo "[Functions.php - getIDofElement] ".$sql;

        $temp = new DatabaseHandler();

        $dbh = $temp->dbh;

        $result = $dbh->query($sql);

        while($row = $result->fetch_array())
        {
            return $row['SEARCHED'];
        }
    }

    function increaseVersionCounter() //new
    {
        require_once($_SERVER["DOCUMENT_ROOT"]. "/classes/DatabaseHandler.php");
        $sql = "SELECT Wert FROM updat";
        $temp = new DatabaseHandler();
        $dbh = $temp->dbh;
        $result = $dbh->query($sql);
        while($row = $result->fetch_array()) 
        {
            $Version = $row["Wert"];
            $newVersion = $Version + 1;
        }

        $sql = "UPDATE `updat` SET `Wert`=" .$newVersion. " WHERE  " .$Version. "";
        $result = $dbh->query($sql);
    }
?>