package com.example.yatzygui;

import java.util.Scanner;

public class DiceLogic {

    Scanner sc = new Scanner(System.in);
    // Method to throw the dice. Returns an Integer array containing values from 1-6.
    public static Integer[] diceThrow() {
        Integer[] dice = new Integer[5];
        for (int i = 0; i < dice.length; i++) {
            dice[i] = (int) (Math.random() * 6 + 1);
        }
        return dice;
    }

    // Reroll mechanism.
    public Integer[] reRoll(Integer[] dice, boolean[] diceKept) {

        for (int i = 0; i < diceKept.length; i++)  {
            if (!diceKept[i]) {
                dice[i] = (int) (Math.random() * 6 + 1);
            }
        }
        return dice;
    }

    // Method to validate user input for reroll method.
    public boolean[] validateInput() {

        boolean isValid = true;
        boolean[] diceToKeep = new boolean[5]; // Initialize a boolean array.

        String input = sc.nextLine();
        String[] positions = input.split(" ");

        while (isValid) {

            for (String s : positions) {
                int position = Integer.parseInt(s);
                if (position <= 0 || position > 6) {
                    System.out.println("Not a valid command. Enter dice positions 1-5");
                    isValid = false;
                    break;
                }
            }

            for (String dye : positions) {
                int position = Integer.parseInt(dye);
                switch (position) {
                    case 1:
                        diceToKeep[0] = true;
                        break;
                    case 2:
                        diceToKeep[1] = true;
                        break;
                    case 3:
                        diceToKeep[2] = true;
                        break;
                    case 4:
                        diceToKeep[3] = true;
                        break;
                    case 5:
                        diceToKeep[4] = true;
                        break;
                }
            }

            if (isValid) {
                break;
            }
        }
        return diceToKeep;
    }
}
