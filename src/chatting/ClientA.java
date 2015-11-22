package chatting;

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
    private String [] words = {"Blah!","Oh no","Meh","Glah","How's it going, eh","Say hello"}; 
 
    public ClientA(InputStream inputStream,OutputStream outputStream)
    //public ClientA(OutputStream outputStream) //test
    {
        this.outputStream = outputStream;
        this.inputStream = inputStream; //test
    }
 
    @Override
    public void run()
    {
        try
        {
            for (int i=0;i <Server.SERVER_MAX_MESSAGES;i++)
            {
            	readMessage(); //testing
            	sendRandomMessage(); //Working
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
    	if (random %2 ==0)
    		return;
    	
    	//System.out.println("Random=" + random);
    	
    	String message = "At '%s' Client A said: %s\n";
    	int result = (int)(Math.random()*10) % 6;
    	String randomWord = words[result];
    	String date = (new Date()).toString();
    	
    	String chatMessage = String.format(message, date  ,randomWord);
    	//String chatMessage = randomWord;
    	try {
			this.outputStream.write(chatMessage.getBytes());
			Thread.sleep(3000);
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    private void readMessage()
    {
		try {
			//System.out.println(this.inputStream.read());
			BufferedReader reader = new BufferedReader( new InputStreamReader(this.inputStream));
			
			//System.out.println("ClientB ->");
			//int info;
			String info = reader.readLine();
			if (info != null)
			{
				System.out.println("ClientA -> " + info);
				//System.out.println("\nClientB -> ***** ");
			}
            //while ((info = inputStream.read()) != -1)
            //{
            	//outputStream.write(info);
            	//System.out.print((char)info);
            	//Thread.yield();
            //}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}