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
package TestServer;

import java.io.*;
import java.net.*;

public class testServer {

    private static ServerSocket serverSocket;
    private static final int PORT = 8080;

    public static void main(String[] Args) throws IOException {
        System.out.println("Starting server test server!...");
        serverSocket = new ServerSocket(PORT);
        System.out.println("\nRunning!");
        do {
            Socket client = serverSocket.accept();
            System.out.println("\nClient with " + client.toString() + " accepted. \n");
            ClientHandler handler = new ClientHandler(client);
            handler.start();
        } while (true);
    }
}
