<html>
<head>
    <title>BK Olsberg</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="./css/indexCss.css" />

    <!-- Bootstrap CDN, JS and JQuery -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

    <?php
        require_once($_SERVER["DOCUMENT_ROOT"]. "/utils/functions.php");

        //echo $_SERVER["DOCUMENT_ROOT"];

        session_start();
        //zeigeParameter();
    ?>
</head>
<body>
<form method="post" action="./utils/validateLogin.php">
    <!--<div id="loginArea">
        table class="invisibleTable">
            <tr>
                <td><span class="loginParameter">Benutzername</span></td>
                <td><input name="inputUsername" type="text" placeholder="Benutzername"></td>
            </tr>
            <tr>
                <td><span class="loginParamter">Passwort</span></td>
                <td><input name="inputPassword" type="password"></td>
            </tr>
            <tr>
                <td colspan="2"><input name="inputSubmit" type="submit" class="btn"></td>
            </tr>
        </table>-->
        <div class="container content-center" style="width: 500px">

            <div class="row">
                <div class="col"><img id="bkologo" src="./media/img/bko-logo-200x100.png" alt="BK Olsberg - Logo"></div>
            </div>

            <div class="row">
                <div class="col-sm">Benutzername</div>
                <div class="col-sm"><input type="text" name="inputUsername" placeholder="Benutzername"></div>
            </div>

            <div class="row">
                <div class="col-sm">Passwort</div>
                <div class="col-sm"><input type="password" name="inputPassword" placeholder="Passwort"/></div>
            </div>

            <div class="row">
                <button type=submit" class="btn btn-success">Einloggen</button>
            </div>
        <?php
            session_start();

            if(isset($_SESSION["errorMsg"]))
            {
                ?>
                <div class="container-fluid alert alert-warning">
                    <?php
                        echo $_SESSION["errorMsg"];

                        unset($_SESSION["errorMsg"]);
                    ?>
                </div>
            <?php
            }
        ?>
        </div>
    </div>
</form>
</body>
</html>