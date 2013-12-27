<?PHP
    include './core.php';

    //Inserting a log into the log table
    if(isset($_REQUEST['Task_ID']))
    {
        switch($_REQUEST['Task_ID'])
        {
            case "addtoRadio":
                //Song_ID: SongID, Station_ID: $("#stationID").val(), Adding: added
                echo "dan: " . $_REQUEST["Adding"];
                if ($_REQUEST["Adding"] == "-1")
                {
                    //remove from station, set status 0
                    echo "removing";
                    $result = database_helper::db_insert_query("UPDATE  `mme`.`radio` SET  `status` =  '0' WHERE  `radio`.`songID` ='" . $_REQUEST[Song_ID] . "';");
                }else{
                    //add to station
                    $result = database_helper::db_insert_query("INSERT INTO `mme`.`radio` (`id`, `songID`, `channel`, `order`, `status`) VALUES (NULL, '" . $_REQUEST[Song_ID] . "', '" . $_REQUEST[Station_ID] . "', '" . $_REQUEST[Adding] . "', '1');");
                    if ($result == false)
                    {
                        echo "error";
                    }
                }
                break;
        }

    }else{
        echo "Error with request";
    }
        
?>