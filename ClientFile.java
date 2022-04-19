import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientFile {
     private static final int SERVER_PORT = 7656;

    public static void main(String[] args) {
        
        DataOutputStream toServer;
        DataInputStream fromServer;
        Scanner input = 
                new Scanner(System.in);
        String message;
        
        //attempt to connect to the server
        try {
            Socket socket = 
                    new Socket("localhost", SERVER_PORT);
            
            //create input stream to receive data
            //from the server
            fromServer = 
                    new DataInputStream(socket.getInputStream());
            
            toServer =
                    new DataOutputStream(socket.getOutputStream());
            
             
             while(true) {
                 //providing options for the user
                System.out.print("Send command to server:\nLOGIN, SOLVE, LIST, MESSAGE, SHUTDOWN, LOGOUT\nSome commands may only be used when logged in\n");
                message = input.nextLine();
                toServer.writeUTF(message);
                if(message.equalsIgnoreCase("SHUTDOWN")) {
                    break;
                }
                
                //received message:
                message = fromServer.readUTF();
                 System.out.println("Server: " + message);
             }
             
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }//end try-catch
        
        
    }//end main
}
