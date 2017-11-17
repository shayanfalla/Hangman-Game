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
package server.net;

import java.io.*;
import java.net.*;
import java.util.Scanner;
import server.controller.Controller;

public class ClientHandler extends Thread {

    private Socket client;
    private Scanner input;
    private PrintWriter output;
    private Controller control = new Controller();

    public ClientHandler(Socket socket) throws IOException {
        client = socket;
        input = new Scanner(client.getInputStream());
        output = new PrintWriter(client.getOutputStream(), true);
    }

    @Override
    public void run() {
        String word;
        boolean isWordGuessed;
        boolean playing = true;
        int tries;
        int score = 0;

        do {
            word = control.getWord().toLowerCase();

            char[] guessWord = word.toCharArray();
            int totalTries = guessWord.length;
            char[] playerGuess = new char[totalTries];

            for (int i = 0; i < playerGuess.length; i++) {
                playerGuess[i] = '_';
            }

            isWordGuessed = false;
            tries = 0;
            output.println("\nWelcome (again) to the Hangman game made by Shayan Fallahian! (shayanf@kth.se)\n");
            output.printf("Your total score so far is: %d\n\n", score);
            output.printf("The word as %d letters.\n\n", totalTries);
            output.print("Lets begin!\n");
            while (!isWordGuessed && tries != totalTries) {
                output.print("\nCurrent state: ");
                printArray(playerGuess);
                output.printf("You have %d tries left.\n", totalTries - tries);
                output.printf("Enter a letter or word! ('-' to quit)\n");
                String guessedWord = input.nextLine().toLowerCase();

                try {
                    char letter = guessedWord.charAt(0);
                    tries++;

                    if (letter == '-') {
                        try {
                            playing = false;
                            isWordGuessed = true;
                            tries = totalTries;
                            client.close();
                        } catch (IOException e) {
                        }
                    } else {
                        for (int i = 0; i < guessWord.length; i++) {
                            if (guessWord[i] == letter && guessedWord.length() == 1) {
                                playerGuess[i] = letter;
                            }
                        }
                    }
                } catch (java.lang.StringIndexOutOfBoundsException e) {
                    output.println("\nYou have to enter something!\n");
                }
                if (isWordGuessed(playerGuess) || word.equals(guessedWord)) {
                    isWordGuessed = true;
                    output.println("Congratulations you won!");
                    score++;
                }
            }

            if (!isWordGuessed) {
                output.println("\nYou ran out of guesses.");
                output.println("Word was " + word + ".");
            }
            output.println("Do you want to play another game? (yes/no)");
            try {
                String temp = input.nextLine();
                if (temp.equals("no") || temp.equals("-")) {
                    playing = false;
                    isWordGuessed = true;
                    System.out.println("Client with " + client.toString() + " ended the connection.");
                    try {
                        client.close();
                    } catch (IOException e) {
                        System.out.println("Unable to disconnect!");
                    }
                }
            } catch (java.util.NoSuchElementException e) {
                System.out.println("Client with " + client.toString() + " ended the connection.\n");
            }
        } while (playing);
    }

    public void printArray(char[] array) {
        for (int i = 0; i < array.length; i++) {
            output.print(array[i] + " ");
        }
        output.print("\n\n");
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
