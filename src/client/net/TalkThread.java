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
package client.net;

import java.io.*;
import java.net.*;
import java.util.*;

public class TalkThread extends Thread {

    private final PrintWriter output;
    private final Scanner userEntry;

    public TalkThread(Socket socket) throws IOException {
        this.userEntry = new Scanner(System.in);
        output = new PrintWriter(socket.getOutputStream(), true);
    }

    @Override
    public void run() {
        String message;

        do {
            message = userEntry.nextLine();
            output.println(message);
        } while (!(message.equals("-") || message.equals("no")));

        System.out.println("\nClosing connection...");
        System.exit(0);
    }
}
