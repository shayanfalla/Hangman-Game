/*
 * Copyright (C) 2017 Shayan Fallahian shayanf@kth.se
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package client.controller;

import client.net.InitSocket;
import client.net.ListenerThread;
import client.net.TalkThread;
import java.io.IOException;
import java.net.Socket;

public final class Controller {

    private InitSocket init;
    private ListenerThread inputThread;
    private TalkThread outputThread;
    private final Socket clientSocket;

    public Controller() throws IOException {
        
        clientSocket = getSocket();
        StartCommunication();
        StartListening();
    }
    
    //Establish a connection to the server.
    Socket getSocket() throws IOException{
       init = new InitSocket();
        return  init.getSocket();
    }

    // Starts the thread that writes messages to the server
    void StartCommunication() throws IOException {
        outputThread = new TalkThread(clientSocket);
        outputThread.start();
    }

    // Start the thread that waits for messages from the server
    void StartListening() throws IOException {
        inputThread = new ListenerThread(clientSocket);
        inputThread.start();
    }
}
