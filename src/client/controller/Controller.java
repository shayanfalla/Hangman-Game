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

import client.net.ListenerThread;
import client.net.TalkThread;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public final class Controller {

    private ListenerThread inputThread;
    private TalkThread outputThread;
    private final Socket clientSocket;
    private static InetAddress host;
    private static final int PORT = 8080;

    public Controller() throws IOException {
        try {
            host = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
        }

        clientSocket = new Socket(host, PORT);
        StartCommunication();
        StartListening();
    }

    void StartCommunication() throws IOException {
        outputThread = new TalkThread(clientSocket);
        outputThread.start();
    }

    void StartListening() throws IOException {
        inputThread = new ListenerThread(clientSocket);
        inputThread.start();
    }
}
