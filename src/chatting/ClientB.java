package chatting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Date;
 
public class ClientB implements Runnable 
{
    private InputStream inputStream=null;
    private OutputStream outputStream = null;
    private String [] words = {"Blah!","Oh no","Meh","Glah","How's it going, eh","Say hello"}; 
    
    public ClientB(InputStream inputStream,OutputStream outputStream)
    //public ClientB(InputStream inputStream) // test
    {
        this.outputStream = outputStream; //test
        this.inputStream = inputStream; //working
    }
 
    @Override
    public void run()
    {
        try
        {
            for (int i=0;i< Server.SERVER_MAX_MESSAGES;i++)
            {
            	sendRandomMessage(); //Testing
            	//readMessage(); // Working
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
                //outputStream.flush();
                //outputStream.close();
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
    	if (random %3 ==1)
    		return;
  	
    	
    	String message = "At '%s' Client B said: %s\n";
    	int result = (int)(Math.random()*10) % 6;
    	String randomWord = words[result];
    	String date = (new Date()).toString();
    	
    	String chatMessage = String.format(message, date  ,randomWord);

    	try {
			this.outputStream.write(chatMessage.getBytes());
			Thread.sleep(2000);
			this.outputStream.write("".getBytes());
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
    }
    private void readMessage()
    {
		try {
			BufferedReader reader = new BufferedReader( new InputStreamReader(this.inputStream));
			
			System.out.println("ClientB ->Reading Buffer");
			String info = reader.readLine();
			if (info != null)
			{
				System.out.println("\t\t\t\t\tClientB -> " + info);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}