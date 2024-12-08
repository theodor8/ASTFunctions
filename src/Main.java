
import java.io.IOException;
import java.util.Scanner;

import function.*;
import function.visitor.*;
import parser.Parser;

// TODO: acos, asin, atan ...

public class Main {

    public static void main(String[] args) {

        Parser parser = new Parser();
        System.out.println("Available functions: " + parser.getAvailableFunctions());
        System.out.println("Available constants: " + parser.getAvailableConstants());
        System.out.println();


        EvalVisitor ev = new EvalVisitor();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print(" < ");
            String input = sc.nextLine();
            if (input.equals("q")) {
                break;
            }
            Function parsed;
            try {
                parsed = parser.parse(input);
            } catch (IOException e) {
                System.out.println(e.getMessage());
                continue;
            }
            System.out.println(" > Parsed: " + parsed);
            Function evaluated = ev.eval(parsed);
            System.out.println(" > Evaluated: " + evaluated);
            System.out.println();
        }
        sc.close();

    }

}