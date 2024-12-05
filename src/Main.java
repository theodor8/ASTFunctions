
import java.util.Scanner;

import function.*;
import function.visitor.DerivativeVisitor;
import function.visitor.EvalVisitor;
import parser.Parser;


public class Main {

    public static void main(String[] args) {

        System.out.println("Hello world :)");

        Parser p = new Parser();
        EvalVisitor ev = new EvalVisitor();
        DerivativeVisitor dv = new DerivativeVisitor();

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("> ");
            String input = sc.nextLine();
            if (input.equals("q")) {
                break;
            }
            Function result;
            try {
                result = p.parse(input);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                continue;
            }
            System.out.println("Parsed: " + result);
            result = result.accept(ev);
            System.out.println("Evaluated: " + result);
            System.out.println("Derivative: " + result.accept(dv).accept(ev));
        }
        sc.close();

    }

}