
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Scanner;

public class ServerFile {

    private static final int SERVER_PORT = 7656;
    private static HashSet<PrintWriter> clients = new HashSet<PrintWriter>();

    
    public static void main(String[] args) {
        createMultithreadCommunicationLoop();
    }//end main
    
    public static void createMultithreadCommunicationLoop() {
        int clientNumber = 0;
     
        try {
            ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
            System.out.println("Server started on " + new Date() + ".");
            //listen for new connection request
            while(true) {
                Socket socket = serverSocket.accept();
                clientNumber++;  //increment client num
            
                //Find client's host name 
                //and IP address
                InetAddress inetAddress = socket.getInetAddress();
                System.out.println("Connection from client " + 
                        clientNumber);
                System.out.println("\tHost name: " + 
                        inetAddress.getHostName());
                System.out.println("\tHost IP address: "+
                        inetAddress.getHostAddress());
                
                //create and start new thread for the connection
                Thread clientThread = new Thread(
                        new ClientHandler(clientNumber, socket, serverSocket));
                clientThread.start();  
            }//end while           
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
        
    }//end createMultithreadCommunicationLoop

    public static void createCommunicationLoop() {
        try {
            //create server socket
            ServerSocket serverSocket
                    = new ServerSocket(SERVER_PORT);

            System.out.println("Server started at "
                    + new Date() + "\n");
            //listen for a connection
            //using a regular *client* socket
            
            Socket socket = serverSocket.accept();

            //now, prepare to send and receive data
            //on output streams
            DataInputStream inputFromClient
                    = new DataInputStream(socket.getInputStream());

            DataOutputStream outputToClient
                    = new DataOutputStream(socket.getOutputStream());

            //server loop listening for the client 
            //and responding
            File file = new File("logins.txt");
            Scanner inputFile = new Scanner(file);

            String loginString1 = inputFile.nextLine(); //taking the login data from the input file
            String loginString2 = inputFile.nextLine();
            String loginString3 = inputFile.nextLine();
            String loginString4 = inputFile.nextLine();
            inputFile.close();
            
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);


            while (true) {
                String strReceived = inputFromClient.readUTF();

                if (strReceived.equalsIgnoreCase("LOGIN " + loginString1) || strReceived.equalsIgnoreCase("LOGIN " + loginString2) //checking for correct login
                        || strReceived.equalsIgnoreCase("LOGIN " + loginString3) || strReceived.equalsIgnoreCase("LOGIN " + loginString4)) {
                    System.out.println("Client Message: " + strReceived);
                    outputToClient.writeUTF("SUCCESS");
                    
                     if (strReceived.equalsIgnoreCase("LOGIN " + loginString1)){
                        clients.add(out);
                    }
                    else if (strReceived.equalsIgnoreCase("LOGIN " + loginString2)){
                        clients.add(out);
                    }
                    else if (strReceived.equalsIgnoreCase("LOGIN " + loginString3)){
                        clients.add(out);
                    }
                    else if (strReceived.equalsIgnoreCase("LOGIN " + loginString4)){
                        clients.add(out);
                    }
                    
                    while (true) { //infinite loop for listening to what the client sends to server
                        String strReceived2 = inputFromClient.readUTF();

                        if (strReceived2.contains("SOLVE")) { //checking for solve command
                            
                            String[]vals = strReceived2.split(" "); //splitting up the values by spaces
                            
                            if (strReceived.equalsIgnoreCase("LOGIN " + loginString1)) { // everything within this statement is for the root

                                FileWriter filewriter = new FileWriter("root_solutions.txt", true);
                                PrintWriter printwriter = new PrintWriter(filewriter);

                                if (strReceived2.contains("-c")) {
                                    
                                    int i = Integer.parseInt(strReceived2.replaceAll("[^0-9]", "")); // converting number string to integers
                                    double circumference = Math.PI * (2 * i);
                                    double area = Math.PI * (i ^ 2);
                                    printwriter.println("Radius " + i + ": " + "Circle's circumference is " + circumference + " and area is " + area);
                                    printwriter.close(); //closing the text file that the function is writing the data in
                                    System.out.println("Client Message: " + strReceived2);
                                    outputToClient.writeUTF("Success: Circumference = " + circumference + " Area = " + area);
                                } 
                                else if (strReceived2.contains("-r") && strReceived2.length() == 10) {
                                    
                                    int i = Integer.parseInt(strReceived2.replaceAll("[^0-9]", ""));
                                    double perimeter = 2 * (i + i);
                                    double area = i * i;
                                    printwriter.println("Sides " + i + " " + i + ": Rectangle's perimeter is " + perimeter + " and area is " + area);
                                    printwriter.close();
                                    System.out.println("Client Message: " + strReceived2);
                                    outputToClient.writeUTF("SUCCESS: Perimeter = " + perimeter + " Area = " + area);
                                } 
                                else if (strReceived2.contains("-r") && strReceived2.length() == 12){
                                    
                                    
                                     int l = Integer.parseInt(vals[2]);
                                     int w = Integer.parseInt(vals[3]);
                                     double perimeter = 2 * (l + w);
                                     double area = l * w;
                                     printwriter.println("Sides " + l + " " + w + ": Rectangle's perimeter is " + perimeter + " and area is " + area);
                                     printwriter.close();
                                     System.out.println("Client Message: " + strReceived2);
                                     outputToClient.writeUTF("SUCCESS: Perimeter = " + perimeter + " Area = " + area);
                                }
                                else {
                                     printwriter.println("Error"); //crash prevention
                                     printwriter.close();
                                    System.out.println("Client Message: " + strReceived2);
                                    outputToClient.writeUTF("FAILURE: Please provide correct format. Try again.");
                                }
                            } 
                            else if (strReceived.equalsIgnoreCase("LOGIN " + loginString2)) { // solve for john

                                FileWriter filewriter = new FileWriter("john_solutions.txt", true);
                                PrintWriter printwriter = new PrintWriter(filewriter);

                                if (strReceived2.contains("-c")) {
                                    
                                    int i = Integer.parseInt(strReceived2.replaceAll("[^0-9]", ""));
                                    double circumference = Math.PI * (2 * i);
                                    double area = Math.PI * (i ^ 2);
                                    printwriter.println("Radius " + i + ": " + "Circle's circumference is " + circumference + " and area is " + area);
                                    printwriter.close();
                                    System.out.println("Client Message: " + strReceived2);
                                    outputToClient.writeUTF("Success: Circumference = " + circumference + " Area = " + area);
                                } 
                                else if (strReceived2.contains("-r") && strReceived2.length() == 10) {
                                    
                                    int i = Integer.parseInt(strReceived2.replaceAll("[^0-9]", ""));
                                    double perimeter = 2 * (i + i);
                                    double area = i * i;
                                    printwriter.println("Sides " + i + " " + i + ": Rectangle's perimeter is " + perimeter + " and area is " + area);
                                    printwriter.close();
                                    System.out.println("Client Message: " + strReceived2);
                                    outputToClient.writeUTF("SUCCESS: Perimeter = " + perimeter + " Area = " + area);
                                } 
                                else if (strReceived2.contains("-r") && strReceived2.length() == 12){
                                    
                                    
                                     int l = Integer.parseInt(vals[2]);
                                     int w = Integer.parseInt(vals[3]);
                                     double perimeter = 2 * (l + w);
                                     double area = l * w;
                                     printwriter.println("Sides " + l + " " + w + ": Rectangle's perimeter is " + perimeter + " and area is " + area);
                                     printwriter.close();
                                     System.out.println("Client Message: " + strReceived2);
                                     outputToClient.writeUTF("SUCCESS: Perimeter = " + perimeter + " Area = " + area);
                                }
                                else {
                                     printwriter.println("Error");
                                     printwriter.close();
                                    System.out.println("Client Message: " + strReceived2);
                                    outputToClient.writeUTF("FAILURE: Please provide correct format. Try again.");
                                }
                            } 
                            else if (strReceived.equalsIgnoreCase("LOGIN " + loginString3)) { //solve for sally

                                FileWriter filewriter = new FileWriter("sally_solutions.txt", true);
                                PrintWriter printwriter = new PrintWriter(filewriter);

                                if (strReceived2.contains("-c")) {
                                    
                                    int i = Integer.parseInt(strReceived2.replaceAll("[^0-9]", ""));
                                    double circumference = Math.PI * (2 * i);
                                    double area = Math.PI * (i ^ 2);
                                    printwriter.println("Radius " + i + ": " + "Circle's circumference is " + circumference + " and area is " + area);
                                    printwriter.close();
                                    System.out.println("Client Message: " + strReceived2);
                                    outputToClient.writeUTF("Success: Circumference = " + circumference + " Area = " + area);
                                } 
                                else if (strReceived2.contains("-r") && strReceived2.length() == 10) {
                                    
                                    int i = Integer.parseInt(strReceived2.replaceAll("[^0-9]", ""));
                                    double perimeter = 2 * (i + i);
                                    double area = i * i;
                                    printwriter.println("Sides " + i + " " + i + ": Rectangle's perimeter is " + perimeter + " and area is " + area);
                                    printwriter.close();
                                    System.out.println("Client Message: " + strReceived2);
                                    outputToClient.writeUTF("SUCCESS: Perimeter = " + perimeter + " Area = " + area);
                                } 
                                else if (strReceived2.contains("-r") && strReceived2.length() == 12){
                                    
                                    
                                     int l = Integer.parseInt(vals[2]);
                                     int w = Integer.parseInt(vals[3]);
                                     double perimeter = 2 * (l + w);
                                     double area = l * w;
                                     printwriter.println("Sides " + l + " " + w + ": Rectangle's perimeter is " + perimeter + " and area is " + area);
                                     printwriter.close();
                                     System.out.println("Client Message: " + strReceived2);
                                     outputToClient.writeUTF("SUCCESS: Perimeter = " + perimeter + " Area = " + area);
                                }
                                else {
                                     printwriter.println("Error");
                                     printwriter.close();
                                    System.out.println("Client Message: " + strReceived2);
                                    outputToClient.writeUTF("FAILURE: Please provide correct format. Try again.");
                                }
                            } 
                            else { // solve for qiang

                                FileWriter filewriter = new FileWriter("qiang_solutions.txt", true);
                                PrintWriter printwriter = new PrintWriter(filewriter);

                                if (strReceived2.contains("-c")) {
                                    
                                    int i = Integer.parseInt(strReceived2.replaceAll("[^0-9]", ""));
                                    double circumference = Math.PI * (2 * i);
                                    double area = Math.PI * (i ^ 2);
                                    printwriter.println("Radius " + i + ": " + "Circle's circumference is " + circumference + " and area is " + area);
                                    printwriter.close();
                                    System.out.println("Client Message: " + strReceived2);
                                    outputToClient.writeUTF("Success: Circumference = " + circumference + " Area = " + area);
                                } 
                                else if (strReceived2.contains("-r") && strReceived2.length() == 10) {
                                    
                                    int i = Integer.parseInt(strReceived2.replaceAll("[^0-9]", ""));
                                    double perimeter = 2 * (i + i);
                                    double area = i * i;
                                    printwriter.println("Sides " + i + " " + i + ": Rectangle's perimeter is " + perimeter + " and area is " + area);
                                    printwriter.close();
                                    System.out.println("Client Message: " + strReceived2);
                                    outputToClient.writeUTF("SUCCESS: Perimeter = " + perimeter + " Area = " + area);
                                } 
                                else if (strReceived2.contains("-r") && strReceived2.length() == 12){
                                    
                                    
                                     int l = Integer.parseInt(vals[2]);
                                     int w = Integer.parseInt(vals[3]);
                                     double perimeter = 2 * (l + w);
                                     double area = l * w;
                                     printwriter.println("Sides " + l + " " + w + ": Rectangle's perimeter is " + perimeter + " and area is " + area);
                                     printwriter.close();
                                     System.out.println("Client Message: " + strReceived2);
                                     outputToClient.writeUTF("SUCCESS: Perimeter = " + perimeter + " Area = " + area);
                                }
                                else {
                                     printwriter.println("Error");
                                     printwriter.close();
                                    System.out.println("Client Message: " + strReceived2);
                                    outputToClient.writeUTF("FAILURE: Please provide correct format. Try again.");
                                }
                            }
                        } 
                        else if (strReceived2.equalsIgnoreCase("LIST -all") && strReceived.equalsIgnoreCase("LOGIN " + loginString1)){ //checking for root login
                            
                            System.out.println("Client Message: " + strReceived2);
                            String input = "Root:\n";
                            Scanner infile = new Scanner(new File("root_solutions.txt"));
                            while(infile.hasNext()) { //loops through each line of the input file
                                input = input + "\n" + infile.nextLine();
                            }
                            input = input + "\nJohn:\n";
                            Scanner infile1 = new Scanner(new File("john_solutions.txt"));
                            while(infile1.hasNext()) { 
                                input = input + "\n" + infile1.nextLine();
                            }
                            input = input + "\nSally:\n";
                            Scanner infile2 = new Scanner(new File("sally_solutions.txt"));
                            while(infile2.hasNext()) { 
                                input = input + "\n" + infile2.nextLine(); //formatting the message to send to client
                            }
                            input = input + "\nQiang:\n";
                            Scanner infile3 = new Scanner(new File("qiang_solutions.txt"));
                            while(infile3.hasNext()) { 
                                input = input + "\n" + infile3.nextLine();
                            }
                            outputToClient.writeUTF(input); //sending full message to client at once
                        }
                        else if (strReceived2.equalsIgnoreCase("LIST")){
                            System.out.println("Client Message: " + strReceived2);
                            
                            if(strReceived.contains(loginString1)){ //checking who is logged in to see what file needs to be read
                           Scanner infile = new Scanner(new File("root_solutions.txt"));
                           String input = "";
                            while(infile.hasNext()) { //loops through each line of the input file
                                input = input + "\n" + infile.nextLine();
                            }
                            
                            outputToClient.writeUTF("Root:\n" + input);
                            }
                            else if (strReceived.contains(loginString2)){
                                
                                Scanner infile = new Scanner(new File("john_solutions.txt"));
                           String input = "";
                            while(infile.hasNext()) { //loops through each line of the input file
                                input = input + "\n" + infile.nextLine();
                            }
                            
                            outputToClient.writeUTF("John:\n" + input);
                            }
                            else if (strReceived.contains(loginString3)){
                                
                                Scanner infile = new Scanner(new File("sally_solutions.txt"));
                           String input = "";
                            while(infile.hasNext()) { //loops through each line of the input file
                                input = input + "\n" + infile.nextLine();
                            }
                            
                            outputToClient.writeUTF("Sally:\n" + input);
                            }
                            else if (strReceived.contains(loginString4)){
                                
                                Scanner infile = new Scanner(new File("qiang_solutions.txt"));
                           String input = "";
                            while(infile.hasNext()) { //loops through each line of the input file
                                input = input + "\n" + infile.nextLine();
                            }
                            
                            outputToClient.writeUTF("Qiang:\n" + input);
                            }
                            else {
                                
                                outputToClient.writeUTF("Error");
                            }
                        }
                       else if (strReceived2.contains("MESSAGE")){
                            System.out.println("Client Message: " + strReceived2);
                            String[]vals = strReceived2.split(" ");
                            System.out.println("Sending message to " + vals[1]);
                            outputToClient.writeUTF("Message Sent");
                        /*   if (vals[1].equalsIgnoreCase("-all") && strReceived.equalsIgnoreCase("LOGIN " + loginString1)){
                               String message = "";
                               for(int i=2; i <= vals.length - 1; i++){ //loops until the array is empty
                                  message += vals[i] + " ";
                               }
                               for (PrintWriter client: clients){ 
                                   client.println("Message from root:" + message); //accessing the array of connections and broadcasting the message
                               }
                           }*/
                        }
                        else if (strReceived2.equalsIgnoreCase("LOGOUT")) { 
                            System.out.println("Client Message: " + strReceived2);
                            outputToClient.writeUTF("200 Ok");
                            break; // when this function runs, it leaves the inner infinite loop but stays connected to server
                        } 
                        else {
                            System.out.println("Client Message: " + strReceived);
                            outputToClient.writeUTF("300 Invalid Command");
                        }
                    }
                }
                else if (strReceived.contains("LOGIN")) { //if above login attempt did not meet requriments, it was invalid login
                    System.out.println("Client Message: " + strReceived);
                    outputToClient.writeUTF("FAILURE: Please provide correct username and password. Try again.");
                } 
                else if (strReceived.equalsIgnoreCase("SHUTDOWN")) {
                    System.out.println("Shutting down server...");
                    outputToClient.writeUTF("Shutting down server...");
                    serverSocket.close();
                    socket.close();
                    break;  //leave connection with server
                } 
                else {
                    System.out.println("Client Message: " + strReceived);
                    outputToClient.writeUTF("300 Invalid Command");

                }
            }//end server loop
        } 
        catch (IOException ex) {
            ex.printStackTrace();
        }//end try-catch
    }//end createCommunicationLoop
}
