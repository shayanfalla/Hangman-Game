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

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class InitSocket {

    private Scanner write = new Scanner(System.in);
    private Socket socket;
    private final int PORT = 8080;
    private String HOST = null;

    // Establishes a connection to the server using either the desire IP address or the localhost
    public InitSocket() throws IOException {
        System.out.println("Hi, before we start, enter the IP address! (press enter if localhost)");
        Scanner hostis = new Scanner(System.in);
        HOST = hostis.nextLine();
        if (HOST == null) {
            HOST = InetAddress.getLocalHost().toString();
        }
        socket = new Socket(HOST, PORT);
    }

    public Socket getSocket() {
        return this.socket;
    }

}
