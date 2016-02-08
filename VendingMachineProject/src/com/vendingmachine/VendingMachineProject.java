package com.vendingmachine;

import java.util.Scanner;

/**
 * Created by azegiest on 2/8/16.
 */
public class VendingMachineProject {

    //state variables
    public static int q0 = 0; //initial state
    public static int q1 = 5;
    public static int q2 = 10;
    public static int q3 = 15;
    public static int q4 = 20;
    public static int q5 = 25;
    public static int q6 = 30; //final state

    public static void main(String[] args) {
        System.out.println("***Welcome to the Vending Machine App***");

        //user input
        int changeInput = 0;
        int curState = q0;

        Scanner changeSlot = new Scanner(System.in);

        System.out.println("Please insert $0.30 to purchase a candy bar");
        System.out.println("Enter 5 (nickel), 10 (dime), or 25 (quarter)");

        while (curState != q6) {
            changeInput = changeSlot.nextInt();

            if (changeInput != 5 && changeInput != 10 && changeInput != 25) {
                System.out.println("Invalid input: please enter 5, 10, or 25 to proceed");
            }

            curState = calculateChange(curState, changeInput);
            System.out.println("Change entered: " + curState);
        }

        System.out.println("You have entered sufficieint funds!!!");
        System.out.println("Please select a candybar");
        System.out.println("Press 1 for Snickers, 2 for Twix, and 3 for Reeses");

        int candyChoice = changeSlot.nextInt();
        pickCandy(candyChoice);

    }

    public static int calculateChange(int state, int changeEntered) {
        //based on transition table
        switch (state) {
            case 0:
                if (changeEntered == 5) state = q1;
                else if (changeEntered == 10) state = q2;
                else if (changeEntered == 25) state = q5;
                break;
            case 5:
                if (changeEntered == 5) state = q2;
                else if (changeEntered == 10) state = q3;
                else if (changeEntered == 25) state = q6;
                break;

            case 10:
                if (changeEntered == 5) state = q3;
                else if (changeEntered == 10) state = q4;
                else if (changeEntered == 25) state = q6;
                break;

            case 15:
                if (changeEntered == 5) state = q4;
                else if (changeEntered == 10) state = q5;
                else if (changeEntered == 25) state = q6;
                break;

            case 20:
                if (changeEntered == 5) state = q5;
                else if (changeEntered == 10) state = q6;
                else if (changeEntered == 25) state = q6;
                break;

            case 25:
                if (changeEntered == 5) state = q6;
                else if (changeEntered == 10) state = q6;
                else if (changeEntered == 25) state = q6;
                break;

            case 30:
                if (changeEntered == 5) state = q6;
                else if (changeEntered == 10) state = q6;
                else if (changeEntered == 25) state = q6;
                break;

            default:
                System.out.println("Error");
                break;

        }
        return state;
    }

    public static void pickCandy(int choice) {
        if (choice == 1) System.out.println("Enjoy your Snickers!");
        else if (choice == 2) System.out.println("Enjoy your Twix!");
        else if (choice == 3) System.out.println("Enjoy your Reeses!");
        else {
            System.out.println("You recieve a mystery candy!!!");
        }
    }
}

/*
Sample Run
***Welcome to the Vending Machine App***
Please insert $0.30 to purchase a candy bar
Enter 5 (nickel), 10 (dime), or 25 (quarter)
25
Change entered: 25
1
Invalid input: please enter 5, 10, or 25 to proceed
Change entered: 25
10
Change entered: 30
You have entered sufficieint funds!!!
Please select a candybar
Press 1 for Snickers, 2 for Twix, and 3 for Reeses
3
Enjoy your Reeses!
 */
