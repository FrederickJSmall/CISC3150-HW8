package chatting;


import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class Main {
    public static void main(String[] args)
    {
        try
        {
            int BUFFER = 4096;
            System.out.println("***** Starting a conversation with two overly chatty clients\n\n");
 
            //Client A Connections
            PipedInputStream clientAInput = new PipedInputStream(BUFFER);
            PipedOutputStream clientAOutput = new PipedOutputStream(clientAInput);
            PipedInputStream serverClientAInput = new PipedInputStream(BUFFER);
            PipedOutputStream serverClientAOutput = new PipedOutputStream(serverClientAInput);
            
            
            //Client B Connections
            PipedInputStream clientBInput = new PipedInputStream(BUFFER);
            PipedOutputStream clientBOutput = new PipedOutputStream(clientBInput);
            PipedInputStream serverClientBInput = new PipedInputStream(BUFFER);
            PipedOutputStream serverClientBOutput = new PipedOutputStream(serverClientBInput);
            
            
            //ClientA clientA = new ClientA(clientAOutput); //working
            //ClientB clientB = new ClientB(serverClientAInput); //working
            ClientA clientA = new ClientA(serverClientBInput,clientAOutput); //testing
            ClientB clientB = new ClientB(serverClientAInput,clientBOutput); //testing
            Server server   = new Server(clientAInput,serverClientAOutput,clientBInput,serverClientBOutput);
            
            
            Thread clientAThread = new Thread(clientA);
            Thread serverThread  = new Thread(server);
            Thread clientBThread = new Thread(clientB);
 
            //System.out.println("Starting Conversation");
            clientAThread.start();
            clientBThread.start();
            serverThread.start(); // Works after clientA & B threads
            
            clientAThread.join();
            clientBThread.join();
            serverThread.join();
            Thread.sleep(2000);
            System.out.println("\n\n***** Overly chatty conversation has stopped");
            System.exit(0);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
