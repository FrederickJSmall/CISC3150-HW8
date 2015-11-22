package chatting;


import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class Main {
    public static void main(String... args)
    {
        try
        {
            int BUFFER = 4096;
            System.out.println("starting a chat with two overly chatty clients");
 
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
            
            
            //System.out.println("Streams opened");
            //System.out.println("Creating client->ClientA");
            ClientA clientA = new ClientA(clientAOutput);
            ClientB clientB = new ClientB(serverClientAInput);
            Server server = new Server(clientAInput,serverClientAOutput,clientBInput,serverClientBOutput);
            
            
            Thread clientAThread = new Thread(clientA);
            Thread serverThread = new Thread(server);
            Thread clientBThread = new Thread(clientB);
 

            //System.out.println("Starting Thread->ClientA");
            //Thread.sleep(3000);
            clientAThread.start();
            
            //System.out.println("Starting Thread->Server");
            //Thread.sleep(3000);
            serverThread.start();
            
            //System.out.println("Starting Thread->ClientB");
            clientBThread.start();
            //Thread.sleep(3000);
            
            serverThread.join();
            System.out.println("Overly chatty conversation has stopped");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
