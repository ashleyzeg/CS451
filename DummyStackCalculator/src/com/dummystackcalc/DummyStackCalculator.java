package com.dummystackcalc;

import java.util.Scanner;
import java.util.Stack;
import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

/*
Ashley Zegiestowsky
CS451 Theory of Computation (Chen)
April 1, 2016
Description: Dummy Stack Calculator program that simulates a basic stack calculator. If the input string
is valid, the correct output will be displayed. If the input string is invalid, 'ERROR' will be displayed.
*/

public class DummyStackCalculator {
    private static final int q0 = 0;
    private static final int q1 = 1;
    private static final int q2 = 2;
    private static final int q3 = 3;
    private static final int q4 = 4;
    private static final int q5 = 5;
    private static final int q6 = 6;
    private static final int q7 = 7;

    //use javascript engine to parse and evaluate mathematical expression
    private static final ScriptEngineManager mgr = new ScriptEngineManager();
    private static final ScriptEngine engine = mgr.getEngineByName("JavaScript");

    public static void main(String[] args) throws ScriptException {
        Stack st = new Stack();
        Scanner scanner = new Scanner(System.in);
        int state = q0;

        System.out.println("*** Welcome to the Dummy Stack Calculator");
        System.out.println("Please enter an input to be calculated");
        String in = scanner.nextLine();

        scan(in, st, state);
    }

    private static void scan(String input, Stack stack, int q) throws ScriptException {
        stack.push('#');
        q = q1;
        input = input.trim();
        String infix = input.substring(0, input.length()-1);
        for (int i=0; i<input.length(); i++) {
            char c = input.charAt(i);

            if (c == '(') {
                stack.push('(');
                q = q1;
            } else if (c >= '0' && c <= '9') {
                if (q == q1 || q == q2) {
                    q = q2;
                } else if (q == q3 || q == q4 || q == q5) {
                    q = q5;
                }
            } else if ((c == '+' || c == '-' || c == '*') && (q == q2 || q == q5 || q == q6)) {
                q = q3;
            } else if (c == '/' && (q == q2 || q == q5 || q == q6) ) {
                q = q4;
            } else if (c == ')' && (q == q5 || q == q6)) {
                if (stack.empty()) {
                    error();
                } else {
                    stack.pop();
                    q = q6;
                }
            } else if (c == '=' && (q == q5 || q == q6)) {
                char end = (char) stack.pop();
                q = q7;
                if (end == '#' && stack.empty()) {
                    System.out.println(engine.eval(infix));
                } else {
                    error();
                }
            } else if (c == ' ' || c == '\t') {
                continue;
            } else
                error();
        }
    }

    private static void error() {
        System.out.println("ERROR!");
        System.exit(0);
    }
}

/*
Sample Run:

*** Welcome to the Dummy Stack Calculator
Please enter an input to be calculated
(((((2+1)*4)+2)))+1=
15

*** Welcome to the Dummy Stack Calculator
Please enter an input to be calculated
(((((2+1)*4)+2)+1=
ERROR!

*/
