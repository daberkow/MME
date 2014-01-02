/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mme;

import javazoom.jl.player.JavaSoundAudioDevice;
import jssc.SerialPort;
import jssc.SerialPortException;

/**
 *
 * @author dan
 */
public class radioMode implements Runnable{
    static Object locker = new Object();
    static int MasterVolume = 0;
    static int MasterChannel = 0;
    
    
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
                    HoldingBuffer = "";
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
    private void Check_Input (String passedData) 
    {
        String[] Chopped_Input = passedData.split("\\|");
        int volume = 0;
        int station = 0;
        for (int i = 0; i < Chopped_Input.length; i++)
        {
            if (Chopped_Input[i].contains("-"))
            {
                volume = Integer.parseInt(Chopped_Input[i+1]);
                station = Integer.parseInt(Chopped_Input[i+2]);
                break;
            }
        }
        if (MasterVolume != volume)
        {
            System.out.println("Changing Volume: " + volume);
            float NewVolume = -80F + (volume - 0) * (2F - -80F) / (100 - 0);
            if (mme.stream_player.audio instanceof JavaSoundAudioDevice)
            {
                JavaSoundAudioDevice jsAudio = (JavaSoundAudioDevice) mme.stream_player.audio;
                jsAudio.setLineGain(NewVolume);
            }
        }
        if (MasterChannel != station)
        {
            System.out.println("Channel Changed: " + station);
            //need to change entire playlist in mme.core_player.playlist, then reset stream_player
            
        }
        System.out.println(passedData);
    }
    
}
