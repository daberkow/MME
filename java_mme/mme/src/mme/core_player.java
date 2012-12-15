package mme;

import java.util.LinkedList;
import java.sql.ResultSet;

/**
 *
 * @author dan
 */
public class core_player implements Runnable{
    static LinkedList<String> commands = new LinkedList<String>();
    static LinkedList<String> playlist = new LinkedList<String>();
    static Object locker = new Object();
    Thread streamplayer;
    boolean told_quit;

    
    public core_player()
    {
      
    }
    
    @Override
    public void run() {
        told_quit = false;
        Runnable player_stream = new stream_player();
        streamplayer = new Thread(player_stream);
        streamplayer.setName("MP3 Player Thread");
        streamplayer.start();
        
        Runnable modeRun = null;
        Thread modeThread = null;
        mysqlWrapper MySQLcom = new mysqlWrapper();
        String returnValue = MySQLcom.return_mode();        
        switch(returnValue)
        {
            case "radio":
                modeRun = new radioMode();
                modeThread = new Thread(modeRun);
                modeThread.setName("Radio Thread");
                modeThread.start();
                break;
                
        }
        while(!told_quit)
        {
            synchronized(mme.Mme.locker)
            {
                if (mme.Mme.passing_string != "READ" && mme.Mme.passing_string != "" && !mme.Mme.passing_string.isEmpty())
                {
                    commands.add(mme.Mme.passing_string);
                    mme.Mme.passing_string = "READ";
                }
            }
            process_command();
            try{
                Thread.sleep(500);
            }catch(Exception e)
            {
                System.err.println("Error 1: Player Thread Sleep failure");
            }
        }
    }
    
    private void process_command()
    {
        if (commands.size() == 0)
        {
            return;
        }
        switch(commands.get(0).split(" ")[0])
        {
            case "add":
                synchronized(locker)
                {
                    playlist.add(commands.get(0).split(" ")[1]);
                }
                break;
            case "echo":
                System.out.println(commands.get(0));
                break;
            case "play":
                synchronized(mme.stream_player.locker)
                {
                    mme.stream_player.blocker = 2;
                }
                break;
            case "stop":
                //streamplayer.interrupt();
                synchronized(mme.stream_player.locker)
                {
                    mme.stream_player.blocker = 0;
                }
                mme.stream_player.player.close();
                break;
            case "next":
                synchronized(mme.stream_player.locker)
                {
                    mme.stream_player.blocker = 1;
                }
                mme.stream_player.player.close();
                synchronized(locker)
                {
                    playlist.remove();
                }
                synchronized(mme.stream_player.locker)
                {
                    mme.stream_player.blocker = 0;
                }
                break;
            case "status":
                synchronized(mme.stream_player.locker)
                {
                    if(mme.stream_player.blocker != 1)
                    {
                        System.out.println();
                        System.out.print("Playing:");
                    }else{
                        System.out.println();
                        System.out.print("Paused:");
                    }
                }
                synchronized(locker)
                {
                    if(playlist.size() > 0)
                    {
                        System.out.println(playlist.get(0));
                    }else{
                        System.out.println();
                    }
                }
                break;
            case "quit":
                told_quit = true;
                break;
        }
        commands.remove();
    }
    
    
}
