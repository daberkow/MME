package mme;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.LinkedList;
import javazoom.jl.player.Player;

/**
 *
 * @author dan
 */
public class stream_player implements Runnable{
    LinkedList<String> playlist;
    static Object locker = new Object();
    static int blocker; //0 is not blocking, 1 is blocking, 2 is just resumed play
    static Player player;
    
    public stream_player(){
        
    }
    
    @Override
    public void run()
    {
        blocker = 1;
        boolean please_quit = false;
        while(!please_quit){
            check_blocked();
            String top_playlist = new String();
            
            synchronized(mme.core_player.locker)
            {
                if (blocker == 2)
                {
                    blocker = 0;
                }
                if (mme.core_player.playlist.size() != 0)
                {
                    top_playlist = mme.core_player.playlist.getFirst();
                    System.out.println("Got " + top_playlist);
                }/*else{
                    System.out.println("waiting for songs");
                }*/
                
            }
            check_blocked();
            if (!top_playlist.isEmpty())
            {
                play(top_playlist);
            }
            check_blocked();
            synchronized(mme.core_player.locker)
            {
                if (blocker == 2)
                {
                    blocker = 0;
                }else{
                    if (mme.core_player.playlist.size() != 0)
                    {
                        if (mme.core_player.playlist.get(0).equals(top_playlist))
                        {//THEORY BUG:if two same songs after each other and first all ready deleted weirdness
                            System.out.println("Removing " + top_playlist);
                            mme.core_player.playlist.pop();
                        }
                    }
                }
            }
            try {
                Thread.sleep(60);
            } catch (InterruptedException ex) {
                System.err.println("Error 1: Player Thread Sleep failure");
            }
        }
    }
         
    private void play(String passed_song)
    {
        try{
            FileInputStream file = new FileInputStream(passed_song);
            BufferedInputStream buff = new BufferedInputStream(file);
            player = new Player(buff);
            player.play();
            
            //This will stay here until song finishes
        }catch (Exception e)
        {
            System.err.println("Error 3: Attempted to get file and play, failure");
        }

    }
    
    private void check_blocked()
    {
        int local_blocker = 1;//blocking first run
        while(local_blocker == 1)
        {
            synchronized(locker)
            {
                local_blocker = blocker;
            }
        
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                System.err.println("Error 1: Player Thread Sleep failure");
            }
        }
    }
}
