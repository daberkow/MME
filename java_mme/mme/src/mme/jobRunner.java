/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mme;

import mme.Jobs.*;

/**
 *
 * @author dan
 */
public class jobRunner implements Runnable{
    static Object locker = new Object();
    static String passing_string = new String();
    boolean told_quit = false;
    Thread[] WorkerBees = new Thread[1];
    /*
     * 0 Is for ID3 tagger and sorter
     */
    public jobRunner()
    {
      
    }
    
    @Override
    public void run() {
        mysqlWrapper mysqlHandler = new mysqlWrapper();
        synchronized(locker)
        {
            if (passing_string.equals("QUIT"))
            {
                told_quit = true;
            }
        }
        while(!told_quit)
        {
             JOB[] undoneJobs = mysqlHandler.return_jobs();
             for(int i = 0; i < undoneJobs.length; i++)
             {
                 switch(undoneJobs[i].get_type())
                 {
                     case 1:
                         if (WorkerBees[0] != null)
                         {
                            if (WorkerBees[0].isAlive())
                            {
                                break;
                            }
                         }
                         Runnable Id3Tagger = new ID3Tagger(undoneJobs[i].get_ID());
                         WorkerBees[0] = new Thread(Id3Tagger);
                         WorkerBees[0].setName("ID3 Tagger Thread");
                         WorkerBees[0].start();
                         break;
                 }
                 mysqlHandler.set_job_status(undoneJobs[i].get_ID(), 1);
             }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                System.err.println("Error 7: Cant sleep Job Agent");
            }
        }
       
        
    }
    
}
