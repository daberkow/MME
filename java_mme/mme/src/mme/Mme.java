package mme;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PipedInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author dan
 */
public class Mme {
    static Object locker = new Object();
    static String passing_string = new String();
    
    public static void main(String[] args) {
        
        Runnable player_run = new core_player();
        new Thread(player_run).start();
        
        boolean told_quit = false;
        while(!told_quit)
        {
            System.out.print(">>");
            
            InputStreamReader inputStreamReader = new InputStreamReader(System.in);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String handled_input;
            try {
                handled_input = reader.readLine();
            } catch (IOException ex) {
                Logger.getLogger(Mme.class.getName()).log(Level.SEVERE, null, ex);
                handled_input = "";
            }
            //PipedInputStream input = new PipedInputStream("test");
            if (handled_input.split(" ").length == 0)
                break;
            switch(handled_input.split(" ")[0])
            {
                case "add":
                    //add_song(handled_input);
                        synchronized(locker)
                        {
                            passing_string = handled_input;
                        }
                        boolean read_done = false;
                        while(!read_done)
                        {
                            synchronized(locker)
                            {
                                if (passing_string == "READ")
                                {
                                    read_done = true;
                                }
                            }
                            try{
                                Thread.sleep(300);
                            }catch (Exception e)
                            {
                                System.err.println("Error 1: Player Thread Sleep failure");
                            }
                        }
                    break;
                case "echo":
                    synchronized(locker)
                    {
                        passing_string = "echo hello";
                    }
                    break;
                case "play":
                    synchronized(locker)
                    {
                        passing_string = "play hello";
                    }
                    break;
            }
        }
    }
    
    private static void add_song(String passed_line)
    {
        
    }
}
