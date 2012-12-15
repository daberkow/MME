<?PHP

    include_once("./core.php");
    database_helper::db_insert_query("insert into `songs` (`file`,`status`) values ('dan',0)");
?>