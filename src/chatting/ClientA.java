package chatting;

/*
 * Frederick Small 
 * CISC 3150
 *  
 */

import java.io.BufferedReader;
//import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
//import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Date;


public class ClientA implements Runnable
{
    private OutputStream outputStream = null;
    private InputStream inputStream = null;
    private String [] words = {"Blah!","Weh Blah","Zoom Zoom","Oh no","Meh","Glah","How's it going, eh","Say hello","Hey there","You got it working","Return of the Empire"}; 
 
    public ClientA(InputStream inputStream,OutputStream outputStream)
    {
        this.outputStream = outputStream;
        this.inputStream = inputStream;
        
        
        new Thread(new Runnable() {
        	@Override
        	public void run() {
    		try {
    			BufferedReader reader = new BufferedReader( new InputStreamReader(inputStream));
    			
				String info = reader.readLine();
				while(info != null)
				{
					if (info != null)
					{
						System.out.println("ClientA -> " + info);
					}
					Thread.yield();
					info = reader.readLine();
				}
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
       	}
        }).start();
    }
 
    @Override
    public void run()
    {
        try
        {
            for (int i=0;i <Server.SERVER_MAX_MESSAGES;i++)
            {
            	sendRandomMessage();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                outputStream.flush();
                outputStream.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
    
    private void sendRandomMessage()
    {
    	int random = (int)(Math.random()*10) % 20;
    	if (random %6 ==0)
    		return;
    	
    	String message = "At '%s' Client A said: %s\n";
    	int result = (int)(Math.random()*10) % words.length;
    	String randomWord = words[result];
    	String date = (new Date()).toString();
    	
    	String chatMessage = String.format(message, date  ,randomWord);
    	try {
			this.outputStream.write(chatMessage.getBytes());
			this.outputStream.flush();
			Thread.yield();
			this.outputStream.write("".getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}