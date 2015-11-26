package chatting;

/*
 * Frederick Small 
 * CISC 3150
 *  
 */


import java.io.PipedInputStream;
import java.io.PipedOutputStream;
 
public class Server implements Runnable
{

    private PipedInputStream inputStreamClientA;
    private PipedOutputStream outputStreamClientA;

    private PipedInputStream inputStreamClientB;
    private PipedOutputStream outputStreamClientB;
    
    public static final int SERVER_MAX_MESSAGES = 75;
 
    public Server(PipedInputStream inputStreamClientA, PipedOutputStream outputStreamClientA
    						,PipedInputStream inputStreamClientB, PipedOutputStream outputStreamClientB)
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

	            while ((infoClientA = inputStreamClientA.read()) != -1)
	            {
	            	try
	            	{
	            		if (infoClientA != -1)
	            			outputStreamClientA.write(infoClientA);
	            	}
	            	catch (Exception ex)
	            	{}
	            }
	            while ((infoClientB = inputStreamClientB.read()) != -1)
	            {
	            	try
	            	{
	            		if (infoClientB != -1)
	            			outputStreamClientB.write(infoClientB);
	            	}
	            	catch (Exception ex)
	            	{}
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
                e.printStackTrace();
            }
        }
    }
}
