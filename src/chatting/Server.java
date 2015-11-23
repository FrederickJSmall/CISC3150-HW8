package chatting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
//import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
//import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

//import javax.xml.stream.XMLInputFactory;
//import javax.xml.stream.XMLStreamConstants;
//import javax.xml.stream.XMLStreamReader;
 
public class Server implements Runnable
{
    //private InputStream inputStream;
    //private OutputStream outputStream;
    private PipedInputStream inputStreamClientA;
    private PipedOutputStream outputStreamClientA;

    private PipedInputStream inputStreamClientB;
    private PipedOutputStream outputStreamClientB;
    
    public static final int SERVER_MAX_MESSAGES = 50;
 
    public Server(PipedInputStream inputStreamClientA, PipedOutputStream outputStreamClientA
    						,PipedInputStream inputStreamClientB, PipedOutputStream outputStreamClientB)
    //public Server(PipedInputStream inputStreamClientA, PipedOutputStream outputStreamClientA)
    {
        this.inputStreamClientA = inputStreamClientA;
        this.outputStreamClientA = outputStreamClientA;
        
        this.inputStreamClientB = inputStreamClientB;
        this.outputStreamClientB = outputStreamClientB;
    }
 
    @Override
    public void run()
    {
        try
        {
        	int infoClientA;
        	int infoClientB;

        	//System.out.println("Reading inputStream A Available=" + inputStreamClientA.available());
        	//System.out.println("Reading inputStream B Available=" + inputStreamClientB.available());
        	//if (inputStreamClientA.available() > 0)
        	//{
	            while ((infoClientA = inputStreamClientA.read()) != -1)
	            {
	            	//infoClientB = inputStreamClientB.read();
	            	try
	            	{
	            		if (infoClientA != -1)
	            			outputStreamClientA.write(infoClientA);
	            	}
	            	catch (Exception ex)
	            	{}
	            	//if (infoClientB != -1)
	            	//	outputStreamClientB.write(infoClientB);
	            }
        	//}
  
	        //System.out.println("Reading inputStream B Available=" + inputStreamClientB.available());
        	//if (inputStreamClientB.available() > 0)
        	//{
	            while ((infoClientB = inputStreamClientB.read()) != -1)
	            {
	            	//infoClientB = inputStreamClientB.read();
	            	try
	            	{
	            		if (infoClientB != -1)
	            			outputStreamClientB.write(infoClientB);
	            	}
	            	catch (Exception ex)
	            	{}
	            	//if (infoClientB != -1)
	            	//	outputStreamClientB.write(infoClientB);
	            }
        	//}
            
            //System.out.println("Reading inputStream A");
            //while ((infoClientA = inputStreamClientA.read()) != -1)
            //{
            //	//infoClientB = inputStreamClientB.read();
            //	if (infoClientA != -1)
            //		outputStreamClientA.write(infoClientA);
            //	//if (infoClientB != -1)
            //	//	outputStreamClientB.write(infoClientB);
            //}

            
            
            	//{
            	
            	//outputStreamClientA.write(infoClientA);
            	//System.out.print((char)info);
            	//System.out.println("Server sending="+(char)info);
            //}
            //while ((infoClientB = inputStreamClientB.read()) != -1)
            //{
            	
            	//outputStreamClientB.write(infoClientB);
            	//System.out.print((char)info);
            	//System.out.println("Server sending="+(char)info);
            //}            
            //while (((infoClientA = inputStreamClientA.read()) != -1) || ((infoClientB = inputStreamClientB.read()) != -1))
            

        }
        catch (Exception e)
        {
            //e.printStackTrace();
        }
        finally
        {
            try
            {
                outputStreamClientA.flush();
                outputStreamClientA.close();
                
                outputStreamClientB.flush();
                outputStreamClientB.close();
            }
            catch (Exception e)
            {
                //e.printStackTrace();
            }
        }
    }
}
