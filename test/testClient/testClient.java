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
package testClient;

import java.io.*;
import java.net.*;
import java.util.*;

public class testClient {

    private static InetAddress host;
    private static final int PORT = 8080;

    public static void main(String[] Args) {
        try {
            host = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        sendMessages();
    }

    private static void sendMessages() {
        Socket socket = null;
        ListenerThread earThread;
        try {
            socket = new Socket(host, PORT);
            Scanner input = new Scanner(socket.getInputStream());
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            Scanner userEntry = new Scanner(System.in);
            String message;

            earThread = new ListenerThread(socket);
            earThread.start();
            do {
                message = userEntry.nextLine();
                if ((message.equals("-") || message.equals("no"))) {
                    earThread.close();
                }
                output.println(message);
            } while (!(message.equals("-") || message.equals("no")));

        } catch (IOException e) {
            System.out.println("Something went wrong...");
        } finally {
            try {
                System.out.println("\nClosing connection...");
                socket.close();
            } catch (IOException e) {
                System.out.println("Unable to disconnect!");
            }
        }
    }
}
