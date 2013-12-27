package mme;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author dan
 */
public class Mme {
    static Object locker = new Object();
    static String passing_string = new String();
    
    public static void main(String[] args) {
        Thread.currentThread().setName("Main Thread");
        Runnable player_run = new core_player();
        Thread Player = new Thread(player_run);
        Player.setName("Core Player");
        Player.start();
        
        Runnable jobRunnable = new jobRunner();
        Thread JobController = new Thread(jobRunnable);
        JobController.setName("Job Controller");
        JobController.start();
        
        boolean told_quit = false;
        while(!told_quit && Player.isAlive())
        {
            try {
                System.out.print(">>");
                
                InputStreamReader inputStreamReader = new InputStreamReader(System.in);
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String handled_input;
                try {
                    handled_input = reader.readLine();
                } catch (Throwable ex) {
                    System.err.println("Error 4: Error with input");
                    handled_input = "";
                }
                //PipedInputStream input = new PipedInputStream("test");
                if (handled_input.split(" ").length == 0)
                    break;
                passed_command(handled_input);
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                System.err.println("Error 7: Idling in main thread");
            }
        }
        synchronized(mme.jobRunner.locker)
        {
            mme.jobRunner.passing_string = "QUIT";
        }
        System.out.println("All threads closed, exiting...");
    }
    
    private static void passed_command(String passed_line)
    {
        synchronized(locker)
        {
            passing_string = passed_line;
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
                Thread.sleep(20);
            }catch (Exception e)
            {
                System.err.println("Error 1: Player Thread Sleep failure");
            }
        }
    }
}
