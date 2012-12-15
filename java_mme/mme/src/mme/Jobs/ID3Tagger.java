/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mme.Jobs;

import java.util.LinkedList;
import mme.SONG;
import mme.mysqlWrapper;
import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.ID3v1Tag;
import com.mpatric.mp3agic.Mp3File;
import java.io.File;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import javax.swing.plaf.metal.MetalIconFactory;
import org.omg.CORBA.Environment;

/**
 *
 * @author dan
 */
public class ID3Tagger implements Runnable{
    static Object locker = new Object();
    static String passing_string = new String();
    boolean told_quit = false;
    int JobID = 0;
    
    public ID3Tagger(int passedJob)
    {
       JobID = passedJob;
    }
    
    @Override
    public void run() {
        LinkedList<SONG> Songs = new LinkedList<>();
        
        mysqlWrapper MySQL = new mysqlWrapper();
        SONG[] Songs_to_process = MySQL.return_songs(0);
        for (int i = 0; i < Songs_to_process.length; i++)
        {
            File MP3 = new File(Songs_to_process[i].get_filename());
            
            if (MP3.exists())
            {
                try {
                    Mp3File WorkingFile = new Mp3File(Songs_to_process[i].get_filename());
                    ID3v1 TAG = WorkingFile.getId3v1Tag();
                    //tag file data
                    if (!TAG.getTitle().isEmpty())
                    {
                        MySQL.song_set_name(Songs_to_process[i].get_id(), TAG.getTitle());
                    }
                    MySQL.song_set_time(Songs_to_process[i].get_id(), String.valueOf(WorkingFile.getLengthInSeconds()));
                    MySQL.song_set_album(Songs_to_process[i].get_id(), TAG.getAlbum(), TAG.getArtist());
                    //move file
                    String Storage = MySQL.return_storage();
                    File StorageFolder = new File(Storage);
                    if (StorageFolder.isDirectory())
                    {
                        File[] Files = StorageFolder.listFiles();
                        if (contains(Files, TAG.getArtist()))
                        {
                            //folder exists
                            String newFolder = StorageFolder.getAbsolutePath() + System.getProperty("file.separator") + TAG.getArtist();
                            StorageFolder = new File(newFolder);
                            
                        }else{
                            String newFolder = StorageFolder.getAbsolutePath() + System.getProperty("file.separator") + TAG.getArtist();
                            StorageFolder = new File(newFolder);
                            StorageFolder.mkdir();
                        }
                        Files = StorageFolder.listFiles();
                        if (!TAG.getAlbum().isEmpty())
                        {
                            if (contains(Files, TAG.getAlbum()))
                            {
                                //folder exists
                                String newFolder = StorageFolder.getAbsolutePath() + System.getProperty("file.separator") + TAG.getAlbum();
                                StorageFolder = new File(newFolder);
                            }else{
                                String newFolder = StorageFolder.getAbsolutePath() + System.getProperty("file.separator") + TAG.getAlbum();
                                StorageFolder = new File(newFolder);
                                StorageFolder.mkdir();
                            }
                        }
                        String newLocation = StorageFolder.getAbsoluteFile() + System.getProperty("file.separator") + MP3.getName();
                        MP3.renameTo(new File(newLocation));
                        MySQL.song_set_filename(Songs_to_process[i].get_id(), newLocation);
                        MySQL.song_set_status(Songs_to_process[i].get_id(), 1);
                    }
                }catch(Throwable e){
                    System.err.println("Error 9: Reading ID3 Tag");
                }
            }            
        }
        
        //we have just marked all the songs
        MySQL.set_job_status(JobID, 2);
        MySQL.set_job_end(JobID);
    }
    
    private boolean contains(File[] passedFiles, String passedFolder)
    {
        for(int i = 0; i < passedFiles.length; i++)
        {
            if ( passedFiles[i].getName().equals(passedFolder) )
            {
                return true;
            }
        }
        return false;
    }
}
