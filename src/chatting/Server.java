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
    
    public static final int SERVER_MAX_MESSAGES = 20;
 
    public Server(PipedInputStream inputStreamClientA, PipedOutputStream outputStreamClientA
    						,PipedInputStream inputStreamClientB, PipedOutputStream outputStreamClientB)
    //public Server(PipedInputStream inputStreamClientA, PipedOutputStream outputStreamClientA)
    {
        this.inputStreamClientA = inputStreamClientA;
        this.outputStreamClientA = outputStreamClientA;
        
        //this.inputStreamClientB = inputStreamClientB;
        //this.outputStreamClientB = outputStreamClientB;
    }
 
    @Override
    public void run()
    {
        try
        {
        	int infoClientA;
        	int infoClientB;
        	//System.out.println("Server start send");
            while ((infoClientA = inputStreamClientA.read()) != -1)
            {
            	
            	outputStreamClientA.write(infoClientA);
            	//System.out.print((char)info);
            	//System.out.println("Server sending="+(char)info);
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
