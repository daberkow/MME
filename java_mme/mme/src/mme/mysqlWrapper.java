package mme;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author dan
 */
public class mysqlWrapper {
    private Connection con = null;
    private boolean connect()
    {
        if (con != null)
        {
            try {
                if (!con.isClosed())
                {
                    return true;
                }
            }catch(Throwable e){
                System.err.println("Error 10: Connecting to database");
            }
        }
        String url = "jdbc:mysql://localhost:3306/mme?zeroDateTimeBehavior=convertToNull";
        String user = "app_user";
        String password = "Zwu26UFE7VsKpNLY";
        try {
            con = DriverManager.getConnection(url, user, password);
            return true;
        } catch (Exception e) {
            return false;
        }
        
    }
    
    private void disconnect()
    {
        try {
            if (!con.isClosed())
            {
                con.close();
            }
        } catch (SQLException ex) {
            System.err.println("Error 8: Mysql Disconnect Error");
        }
        con = null;
        
    }
    
    public String return_mode()
    {
        connect();
        Statement st = null;
        ResultSet rs = null;
        String returnString = ""; 
        try {
            
            st = con.createStatement();
            rs = st.executeQuery("SELECT `value` FROM settings WHERE `setting`='mode'");
            

            try{
                rs.next();
                returnString = rs.getString("value");
            }catch(Exception e)
            {
                System.err.println("Error 6: Error getting mode to enter");
            }
            
        } catch (SQLException ex) {
            System.err.println("Error 5: Error in sql statement");

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                System.err.println("Error 6: Cleaning sql varibles");
            }
            return returnString;
        }
    }
    
    public String return_storage()
    {
        connect();
        Statement st = null;
        ResultSet rs = null;
        String returnString = ""; 
        try {
            
            st = con.createStatement();
            rs = st.executeQuery("SELECT `value` FROM settings WHERE `setting`='storage'");
            

            try{
                rs.next();
                returnString = rs.getString("value");
            }catch(Exception e)
            {
                System.err.println("Error 6: Error getting mode to enter");
            }
            
        } catch (SQLException ex) {
            System.err.println("Error 5: Error in sql statement");

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                System.err.println("Error 6: Cleaning sql varibles");
            }
            return returnString;
        }
    }
    
    public JOB[] return_jobs()
    {
        connect();
        Statement st = null;
        ResultSet rs = null;
        LinkedList<JOB> returnArray = new LinkedList<JOB>();
        try {
            
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM `jobs` WHERE `status`=0;");
            
            try{
                while(rs.next())
                {
                    JOB tempjob = new JOB();
                    tempjob.set_ID(rs.getInt("id"));
                    tempjob.set_type(rs.getInt("type"));
                    tempjob.set_args(rs.getString("arguments"));
                    
                    returnArray.add(tempjob);
                }
            }catch(Exception e)
            {
                System.err.println("Error 6: Error getting mode to enter");
            }
            
        } catch (SQLException ex) {
            System.err.println("Error 5: Error in sql statement");
            disconnect();

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                System.err.println("Error 6: Cleaning sql varibles");
            }
            return returnArray.toArray(new JOB[0]);
        }
    }
    
    public SONG[] return_songs(int Mode) //0 is unnamed
    {
        connect();
        Statement st = null;
        ResultSet rs = null;
        LinkedList<SONG> returnArray = new LinkedList<SONG>();
        try {
            
            st = con.createStatement();
            switch(Mode)
            {
                case 0:
                    rs = st.executeQuery("SELECT * FROM `songs` WHERE `status`=0;");
                    break;
                case 1:
                    rs = st.executeQuery("SELECT * FROM `songs` WHERE `status`=1;");
                    break;
                default:
                    rs = st.executeQuery("SELECT * FROM `songs`;");
                    break;
            }
            
            
            try{
                while(rs.next())
                {
                    SONG tempsong = new SONG(rs.getString("song"), rs.getString("file"), rs.getInt("id"));
                    
                    returnArray.add(tempsong);
                }
            }catch(Exception e)
            {
                System.err.println("Error 6: Error getting mode to enter");
            }
            
        } catch (SQLException ex) {
            System.err.println("Error 5: Error in sql statement");
            disconnect();

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                System.err.println("Error 6: Cleaning sql varibles");
            }
            return returnArray.toArray(new SONG[0]);
        }
    }
    
    public String return_song(int id) //0 is unnamed
    {
        connect();
        Statement st = null;
        ResultSet rs = null;
        String finalAnswer = "";
        try {
            
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM `songs` WHERE `id`=" + id + ";");

            try{
                while(rs.next())
                {
                    finalAnswer = rs.getString("file");
                }
            }catch(Exception e)
            {
                System.err.println("Error 6: Error getting mode to enter");
            }
            
        } catch (SQLException ex) {
            System.err.println("Error 5: Error in sql statement");
            disconnect();

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                System.err.println("Error 6: Cleaning sql varibles");
            }
            return finalAnswer;
        }
    }
    
    public int return_find_album(String passedAlbum, int passedArtist) //0 is unnamed
    {
        connect();
        Statement st = null;
        ResultSet rs = null;
        int returnInfo = 0;
        try {
            
            st = con.createStatement();
            
            rs = st.executeQuery("SELECT * FROM `album` WHERE `name`='" + passedAlbum + "' AND `artist`=" + passedArtist + ";");
                    
            try{
                while(rs.next())
                {//if exists should be one return
                    returnInfo = rs.getInt("id");
                }
            }catch(Exception e)
            {
                System.err.println("Error 6: Error getting mode to enter");
            }
            
        } catch (SQLException ex) {
            System.err.println("Error 5: Error in sql statement");
            disconnect();

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                System.err.println("Error 6: Cleaning sql varibles");
            }
            return returnInfo;
        }
    }
    
    public int return_find_artist(String passedArtist) //0 is unnamed
    {
        connect();
        Statement st = null;
        ResultSet rs = null;
        int returnInfo = 0;
        try {
            
            st = con.createStatement();
            
            rs = st.executeQuery("SELECT * FROM `artists` WHERE `artist`='" + passedArtist + "';");
                    
            try{
                while(rs.next())
                {//if exists should be one return
                    returnInfo = rs.getInt("id");
                }
            }catch(Exception e)
            {
                System.err.println("Error 6: Error getting mode to enter");
            }
            
        } catch (SQLException ex) {
            System.err.println("Error 5: Error in sql statement");
            disconnect();

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                System.err.println("Error 6: Cleaning sql varibles");
            }
            return returnInfo;
        }
    }
    
    /*public int job_started(int job)
    {
        connect();
        Statement st = null;
        int rs = 0;
        
        try {
            String Query = "UPDATE `jobs` SET `status`='1' WHERE `id`='" + job + "' ;";
            st = con.createStatement();
            rs = st.executeUpdate(Query);
            
        } catch (SQLException ex) {
            System.err.println("Error 5: Error in sql statement");
            disconnect();

        } finally {
            try {
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                System.err.println("Error 6: Cleaning sql varibles");
            }
        }
        return rs;
    }*/
    
    public int song_set_name(int database_id, String passedName)
    {
        connect();
        Statement st = null;
        int rs = 0;
        
        try {
            String Query = "UPDATE `songs` SET `song`='" + passedName + "' WHERE `id`='" + database_id + "' ;";
            st = con.createStatement();
            rs = st.executeUpdate(Query);
            
        } catch (SQLException ex) {
            System.err.println("Error 5: Error in sql statement");
            disconnect();

        } finally {
            try {
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                System.err.println("Error 6: Cleaning sql varibles");
            }
        }
        return rs;
    }
    
    public int song_set_filename(int database_id, String passedName)
    {
        connect();
        Statement st = null;
        int rs = 0;
        
        try {
            String Query = "UPDATE `songs` SET `file`='" + passedName + "' WHERE `id`='" + database_id + "' ;";
            st = con.createStatement();
            rs = st.executeUpdate(Query);
            
        } catch (SQLException ex) {
            System.err.println("Error 5: Error in sql statement");
            disconnect();

        } finally {
            try {
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                System.err.println("Error 6: Cleaning sql varibles");
            }
        }
        return rs;
    }
    
    public int song_set_time(int database_id, String passedName)
    {
        connect();
        Statement st = null;
        int rs = 0;
        
        try {
            String Query = "UPDATE `songs` SET `length`='" + passedName + "' WHERE `id`='" + database_id + "' ;";
            st = con.createStatement();
            rs = st.executeUpdate(Query);
            
        } catch (SQLException ex) {
            System.err.println("Error 5: Error in sql statement");
            disconnect();

        } finally {
            try {
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                System.err.println("Error 6: Cleaning sql varibles");
            }
        }
        return rs;
    }
    
    public int song_make_album(String passedName, int passedArtist)
    {
        connect();
        Statement st = null;
        int rs = 0;
        
        try {
            String Query = "INSERT INTO `mme`.`album` (`id`, `name`, `artist`) VALUES (NULL, '" + passedName + "', " + passedArtist + ");";
            st = con.createStatement();
            rs = st.executeUpdate(Query);
            
        } catch (SQLException ex) {
            System.err.println("Error 5: Error in sql statement");
            disconnect();

        } finally {
            try {
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                System.err.println("Error 6: Cleaning sql varibles");
            }
        }
        return rs;
    }
    
    public int song_make_artist(String passedArtist)
    {
        connect();
        Statement st = null;
        int rs = 0;
        
        try {
            String Query = "INSERT INTO `mme`.`artists` (`id`, `artist`) VALUES (NULL, '" + passedArtist + "');";
            st = con.createStatement();
            rs = st.executeUpdate(Query);
            
        } catch (SQLException ex) {
            System.err.println("Error 5: Error in sql statement");
            disconnect();

        } finally {
            try {
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                System.err.println("Error 6: Cleaning sql varibles");
            }
        }
        return rs;
    }
    
    public int song_set_album(int database_id, String passedName, String passedArtist)
    {
        int ArtistID = return_find_artist(passedArtist);
        if (ArtistID == 0)
        {
            ArtistID = song_make_artist(passedArtist);
        }
        
        int AlbumID = return_find_album(passedName, ArtistID);
        if (AlbumID == 0)
        {
            AlbumID = song_make_album(passedName, ArtistID);
        }
        
        connect();
        Statement st = null;
        int rs = 0;
        
        try {
            String Query = "UPDATE `songs` SET `album`=" + AlbumID + " WHERE `id`='" + database_id + "' ;";
            st = con.createStatement();
            rs = st.executeUpdate(Query);
            
        } catch (SQLException ex) {
            System.err.println("Error 5: Error in sql statement");
            disconnect();

        } finally {
            try {
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                System.err.println("Error 6: Cleaning sql varibles");
            }
        }
        return rs;
    }
    
    public int song_set_status(int database_id, int passedStatus)
    {
        connect();
        Statement st = null;
        int rs = 0;
        
        try {
            String Query = "UPDATE `songs` SET `status`=" + passedStatus + " WHERE `id`='" + database_id + "' ;";
            st = con.createStatement();
            rs = st.executeUpdate(Query);
            
        } catch (SQLException ex) {
            System.err.println("Error 5: Error in sql statement");
            disconnect();

        } finally {
            try {
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                System.err.println("Error 6: Cleaning sql varibles");
            }
        }
        return rs;
    }
    
    public int set_job_status(int database_id, int passedStatus)
    {
        connect();
        Statement st = null;
        int rs = 0;
        
        try {
            String Query = "UPDATE `jobs` SET `status`=" + passedStatus + " WHERE `id`='" + database_id + "' ;";
            st = con.createStatement();
            rs = st.executeUpdate(Query);
            
        } catch (SQLException ex) {
            System.err.println("Error 5: Error in sql statement");
            disconnect();

        } finally {
            try {
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                System.err.println("Error 6: Cleaning sql varibles");
            }
        }
        return rs;
    }
    public int set_job_end(int database_id)
    {
        connect();
        Statement st = null;
        int rs = 0;
        
        try {
            String Query = "UPDATE `jobs` SET `end`=CURRENT_TIMESTAMP WHERE `id`='" + database_id + "' ;";
            st = con.createStatement();
            rs = st.executeUpdate(Query);
            
        } catch (SQLException ex) {
            System.err.println("Error 5: Error in sql statement");
            disconnect();

        } finally {
            try {
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                System.err.println("Error 6: Cleaning sql varibles");
            }
        }
        return rs;
    }
}
