<?PHP
    class database_helper {
        protected $connected; //actuall connection varible
        
        //Opens a connection to MYSQL
        public static function db_connect()
        {
            if (isset($connected) == false)
            {
                $connected = mysql_connect("localhost", "MME_web", "UcbtBQFHyDWZx83v") or die("Could Not Connect To MYSQL");
                mysql_select_db("mme") or die ("Could Not Connect to DATABASE");
            }
        }
        
        //close the database connection
        public static function db_disconnect()
        {
            if(isset($connected))
            {
                mysql_close($connected);
                $connected = null;
            }
        }
        
        //This is a wrapped query statement that returns a array of anything passed to it
        //Theis and the nexdt comamnd werent added till later, so some code uses some does not
        public static function db_return_array($passed_query)
        {
            database_helper::db_connect();
            $return_array = array();
            //echo $query;
            $main_result = mysql_query($passed_query);
            if (mysql_num_rows($main_result) == 0)
            {
                return $return_array;
            }
            if ($main_result)
            {
                while($main_row = mysql_fetch_array($main_result))
                {
                    array_push($return_array, $main_row);
                }
                return $return_array;
            }else{
                echo "Error with " . $passed_query;
                return $return_array;
            }
        }
        
        //Another wrapper but for a insert comamnd
        public static function db_insert_query($passed_query)
        {
            database_helper::db_connect();
            $result = mysql_query($passed_query);
            if ($result)
            {
                return mysql_insert_id();
            }else{
                echo "Error on inset " . $passed_query;
                return false;
            }
        }
        
        public static function db_Music_Cat()
        {
            database_helper::db_connect();
            $songs_return[0] = database_helper::db_return_array("SELECT * FROM `songs` WHERE `status`>=1 ORDER BY id");
            $songs_return[1] = database_helper::db_return_array("SELECT * FROM `artists`");
            $songs_return[2] = database_helper::db_return_array("SELECT * FROM `album`");
            return $songs_return;
        }
        
        public static function db_Radio_Cat()
        {
            database_helper::db_connect();
            $songs_return[0] = database_helper::db_return_array("SELECT * FROM `radio` WHERE `status`>0");
            return $songs_return;
        }
    }
?>
