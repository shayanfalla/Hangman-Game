/*
 * Copyright (C) 2017 Shayan
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

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Shayan
 */
public class ListenerThread extends Thread {

    private Socket client;
    private Scanner input;
    boolean running = true;

    public ListenerThread(Socket socket) throws IOException {
        client = socket;
        input = new Scanner(client.getInputStream());
    }

    @Override
    public void run() {
        while (running) {
            try {
                System.out.println(input.nextLine());
            } catch (java.util.NoSuchElementException e) {
                System.out.println("Connection Closed.");
                System.exit(0);
            }
        }
    }

    public void close() {
        running = false;
    }
}
