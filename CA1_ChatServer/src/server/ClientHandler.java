/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import shared.ProtocolStrings;

/**
 *
 * @author bulskov
 */
public class ClientHandler implements Runnable
{
    private final Socket socket;
    private final Scanner input;
    private final PrintWriter writer;

    public ClientHandler(Socket socket) throws IOException
    {
        this.socket = socket;
        input = new Scanner(socket.getInputStream());
        writer = new PrintWriter(socket.getOutputStream(), true);
    }

    @Override
    public void run()
    {
        try
        {
            String message = input.nextLine(); //IMPORTANT blocking call
            Logger.getLogger(ChatServer.class.getName()).log(Level.INFO, String.format("Received the message: %1$S ",message));
            while (!message.equals(ProtocolStrings.STOP)) {
                ChatServer.send(message);
                Logger.getLogger(ChatServer.class.getName()).log(Level.INFO, String.format("Received the message: %1$S ",message.toUpperCase()));
                message = input.nextLine(); //IMPORTANT blocking call
            }
            writer.println(ProtocolStrings.STOP);//Echo the stop message back to the client for a nice closedown
            socket.close();
            Logger.getLogger(ChatServer.class.getName()).log(Level.INFO, "Closed a Connection");
        } catch (IOException ex)
        {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void send(String msg) {
        writer.println(msg);
    }
    
}
