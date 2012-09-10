/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mme;

import sun.misc.Queue;
import java.io.*;
import java.io.FileInputStream;
import java.util.LinkedList;
import java.applet.AudioClip;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javazoom.jl.player.Player;
import sun.audio.*;


/**
 *
 * @author dan
 */
public class core_player implements Runnable{
    LinkedList<String> commands = new LinkedList<String>();
    LinkedList<String> playlist = new LinkedList<String>();
    Player player;
    boolean told_quit;

    
    public core_player()
    {
      
    }
    
    public void run() {
        told_quit = false;
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
    
    public int start(Object passedLock, String passedCommand)
    {
        told_quit = false;
        while(!told_quit)
        {
            synchronized(passedLock)
            {
                if (!passedCommand.isEmpty())
                {
                    commands.add(passedCommand);
                    passedCommand = "READ";
                    process_command();
                }
            }
            
            try{
                Thread.sleep(500);
            }catch(Exception e)
            {
                System.err.println("Error 1: Player Thread Sleep failure");
            }
        }
        return 0;
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
                playlist.add(commands.get(0).split(" ")[1]);
                break;
            case "echo":
                System.out.println(commands.get(0) + "from thread");
                break;
            case "play":
                play();
                
                break;
            case "quit":
                told_quit = true;
                break;
        }
        commands.remove();
    }
    
    private void play()
    {
        if(playlist.size() == 0)
        {
            System.err.println("Error 2: System told to play with no songs in playlist");
            return;
        }
        
        try{
            FileInputStream file = new FileInputStream(playlist.get(0));
            BufferedInputStream buff = new BufferedInputStream(file);
        
            player = new Player(buff);
            player.play();
            playlist.remove();
            //This will stay here until song finishes
        }catch (Exception e)
        {
            System.err.println("Error 3: Attempted to get file and play, failure");
        }
        
        
    }
    
    /*
     * 
     */
    
    
}
