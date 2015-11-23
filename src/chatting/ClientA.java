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
        
        
        new Thread(new Runnable() {
        	@Override
        	public void run() {
    		try {
    			BufferedReader reader = new BufferedReader( new InputStreamReader(inputStream));
    			
				//System.out.println("ClientA ->Reading Buffer");
				String info = reader.readLine();
				while(info != null)
				{
					//System.out.println("ClientA ->yes i was able to read buffer " + info);
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
            	sendRandomMessage(); //Working
            	//readMessage(); //testing
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
    	//if (random %2 ==0)
    	//	return;
    	
    	//System.out.println("Random=" + random);
    	
    	String message = "At '%s' Client A said: %s\n";
    	int result = (int)(Math.random()*10) % 6;
    	String randomWord = words[result];
    	String date = (new Date()).toString();
    	
    	String chatMessage = String.format(message, date  ,randomWord);
    	//String chatMessage = randomWord;
    	try {
			this.outputStream.write(chatMessage.getBytes());
			this.outputStream.flush();
			//Thread.sleep(2000);
			Thread.yield();
			this.outputStream.write("".getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    private void readMessage()
    {
		try {
			BufferedReader reader = new BufferedReader( new InputStreamReader(this.inputStream));
			
			//if (this.inputStream.available() > 0)
			//{
				System.out.println("ClientA ->Reading Buffer");
				String info = reader.readLine();
				System.out.println("ClientA ->yes i was able to read buffer");
				if (info != null)
				{
					System.out.println("ClientA -> " + info);
				}
			//}
			//else
			// System.out.println("ClientA -> has nothing to sayReading Buffer");

		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}