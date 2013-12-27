/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mme;

import java.sql.Types;
import jssc.SerialPort;
import jssc.SerialPortException;

/**
 *
 * @author dan
 */
public class radioMode implements Runnable{
    static Object locker = new Object();
    
    public radioMode()
    {
        
    }
    
    public void run()
    {
        //SerialPort serialPort = new SerialPort("/dev/ttyACM0");
        SerialPort serialPort = new SerialPort("/dev/pts/3");
        boolean noError = false;
        try {
            System.out.println("Port opened: " + serialPort.openPort()); 
            System.out.println("Params setted: " + serialPort.setParams(9600, 8, 1, 0)); 
            noError = true;
        } catch (SerialPortException ex){
            System.out.println(ex);
        }
        String HoldingBuffer = "";
        while(noError)
        {
            String temp = "";
            try {
                temp = serialPort.readString();
            
            }catch (Throwable e ){}
            
            if (temp != null)
            {
                HoldingBuffer += temp;
                
                if (HoldingBuffer.length() > 19)
                {
                    Check_Input(HoldingBuffer);
                }                
            }
            
            synchronized(mme.core_player.locker)
            {
                if (mme.core_player.told_quit)
                {
                    noError = false;
                }
            }
            
            try{
                Thread.sleep(250);
            }catch (Throwable e) {}
            
        }
        try {
            System.out.println("Port closed: " + serialPort.closePort());
        }catch (Throwable e) {}
    }
    private void Check_Input(String passedData)
    {
        

        System.out.println(passedData);
    }
    
}
