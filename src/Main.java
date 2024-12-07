
import java.io.IOException;
import java.util.Scanner;

import function.*;
import function.visitor.EvalVisitor;
import parser.Parser;


public class Main {

    public static void main(String[] args) {

        System.out.println("Hello world :)");

        Parser parser = new Parser();

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
            Function evaluated = parsed.accept(new EvalVisitor());
            System.out.println(" > Evaluated: " + evaluated);
        }
        sc.close();

    }

}