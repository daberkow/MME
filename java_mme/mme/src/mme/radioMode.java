/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mme;

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
        SerialPort serialPort = new SerialPort("/dev/pts/1");
        /*
        try {
            System.out.println("Port opened: " + serialPort.openPort()); 
            System.out.println("Params setted: " + serialPort.setParams(9600, 8, 1, 0)); 
            System.out.println("\"Hello World!!!\" successfully writen to port: " + serialPort.writeBytes("Hello World!!!".getBytes())); 
            System.out.println("Port closed: " + serialPort.closePort());
        } catch (SerialPortException ex){
            System.out.println(ex);
        }*/
    }
    
}
