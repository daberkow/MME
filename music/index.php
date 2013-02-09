<html>
    <head>
        <title>MME: Home</title>
        <link rel="stylesheet" type="text/css" href="./static/bootstrap/css/bootstrap.css"/>
        <link rel="stylesheet" type="text/css" href="./style.css"/>
        <script src="./static/jquery.js"></script>
        <script src="./static/bootstrap/js/bootstrap.js"></script>
    </head>
    <body>
        <div id="main_body">
            <?PHP include("header.php"); ?>
            <div id="work_area" style='margin-top: 10px;'>
                <?PHP
                    include('core.php');
                    $Mode = database_helper::db_return_array("SELECT `value` FROM `settings` WHERE `setting`='mode';");
                    switch($Mode[0][0])
                    {
                        case "radio":
                            include("radio.php");
                            break;
                    }
                ?>
            </div>
        </div>
    </body>
</html>