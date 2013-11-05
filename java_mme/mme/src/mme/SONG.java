/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mme;

/**
 *
 * @author dan
 */
public class SONG {
    private String SongName;
    private String FileName;
    private int ID;
    
    
    
    SONG(String passedName, String passedFileName, int passedID)
    {
        SongName = passedName;
        FileName = passedFileName;
        ID = passedID;
    }
    
    SONG()
    {
        SongName = "";
        FileName = "";
        ID = 0;
    }
    
    public int get_id()
    {
        return ID;
    }
    
    public void set_id(int passedID)
    {
        ID = passedID;
    }
    
    public String get_name()
    {
        return SongName;
    }
    
    public void set_name(String passedName)
    {
        SongName = passedName;
    }
    
    public String get_filename()
    {
        return FileName;
    }
    
    public void set_filename(String passedFilename)
    {
        FileName = passedFilename;
    }
}
