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
import java.util.Scanner;

public class ClientHandler extends Thread {

    private Socket client;
    private Scanner input;
    private PrintWriter output;
    private fileReader read = new fileReader();

    public ClientHandler(Socket socket) {
        client = socket;

        try {
            input = new Scanner(client.getInputStream());
            output = new PrintWriter(client.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        // String received;
        String word;
        boolean isWordGuessed;
        boolean playing = true;
        int tries;

        do {
            /*  // received = input.nextLine();
            word = read.readFile().toLowerCase();
            // output.println("ECHO: " + word);
            System.out.println(word);

            char[] guessWord = word.toCharArray();
            int totalTries = guessWord.length;
            char[] playerGuess = new char[totalTries];

            for (int i = 0; i < playerGuess.length; i++) {
                playerGuess[i] = '_';
            }

            isWordGuessed = false;
            tries = 0;
            while (!isWordGuessed && tries != totalTries) {
                output.print("Current state: ");
                printArray(playerGuess);
                output.printf("You have %d tries left.\n", totalTries - tries);
                output.printf("Enter a letter:\n");
                char letter = input.nextLine().charAt(0);
                tries++;

                if (letter == '-') {
                    playing = false;
                    isWordGuessed = true;
                } else {
                    for (int i = 0; i < guessWord.length; i++) {
                        if (guessWord[i] == letter) {
                            playerGuess[i] = letter;
                        }
                    }
                    if (isWordGuessed(playerGuess)) {
                        isWordGuessed = true;
                        output.println("Congratulations you won!");
                    }
                    if (tries == totalTries) {
                        isWordGuessed = true;
                    }
                }
            }
            if (isWordGuessed) {
                output.println("You ran out of guesses.");
                output.println("Word was " + word + ".");
            }*/
            output.println("Do you want to play another game? (yes/no)");
            if (input.nextLine().equals("no")) {
                playing = false;
                isWordGuessed = true;
                System.out.println("Closing down connection.");
                try {
                    client.close();
                } catch (IOException e) {
                    System.out.println("Unable to disconnect!");
                }
            }

        } while (/*!received.equals("QUIT") ||*/playing);

        try {
            if (client != null) {
                System.out.println("Closing down connection.");
                client.close();
            }
        } catch (IOException e) {
            System.out.println("Unable to disconnect!");
        }
    }

    public void printArray(char[] array) {
        for (int i = 0; i < array.length; i++) {
            output.print(array[i] + " ");
        }
        output.println();
    }

    public boolean isWordGuessed(char[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == '_') {
                return false;
            }
        }
        return true;
    }
}
