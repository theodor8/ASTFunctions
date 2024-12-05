package parser;

import java.io.StreamTokenizer;
import java.io.StringReader;


import function.*;


public class Parser {

    private StreamTokenizer st;

    public Function parse(String inputString) throws Exception {
        this.st = new StreamTokenizer(new StringReader(inputString)); // reads from inputString via stringreader.
        this.st.ordinaryChar('-');
        this.st.ordinaryChar('/');
        this.st.eolIsSignificant(true);
        Function result = statement(); // calls to statement
        return result; // the final result
    }

    private Function statement() throws Exception {
        Function result;

        this.st.nextToken();
        if (this.st.ttype == StreamTokenizer.TT_EOF) {
            throw new Exception("Error: Expected an expression");
        }

        result = expression();
        
        if (this.st.nextToken() != StreamTokenizer.TT_EOF) { // token should be an end of stream token if we are done
            if (this.st.ttype == StreamTokenizer.TT_WORD) {
                throw new Exception("Error: Unexpected '" + this.st.sval + "'");
            } else {
                throw new Exception("Error: Unexpected '" + String.valueOf((char) this.st.ttype) + "'");
            }
        }

        return result;
    }



    private Function identifier() throws Exception {
        switch (this.st.sval) {
            case "x":
                return new Var();
            
            case "pi":
                return new NamedCon("pi", Math.PI);

            case "e":
                return new NamedCon("e", Math.E);
        
            default:
                throw new Exception("Error: Unexpected " + this.st.sval);
        }
    }


    private Function expression() throws Exception {
        Function result = term();
        this.st.nextToken();
        while (this.st.ttype == '+' || this.st.ttype == '-') {
            int operation = st.ttype;
            this.st.nextToken();
            if (operation == '+') {
                result = new Add(result, term());
            } else {
                result = new Sub(result, term());
            }
            this.st.nextToken();
        }
        this.st.pushBack();
        return result;
    }

    private Function term() throws Exception {
        Function result = primary();
        this.st.nextToken();
        while (this.st.ttype == '*' || this.st.ttype == '/') {
            int operation = st.ttype;
            this.st.nextToken();
            if (operation == '*') {
                result = new Mul(result, primary());
            } else {
                result = new Div(result, primary());
            }
            this.st.nextToken();
        }
        this.st.pushBack();
        return result;
    }

    private Function primary() throws Exception {
        Function result;
        if (this.st.ttype == '(') {
            this.st.nextToken();
            result = expression();
            /// This captures unbalanced parentheses!
            if (this.st.nextToken() != ')') {
                throw new Exception("expected ')'");
            }
        } else if (this.st.ttype == '-') {
            result = unary();
        } else if (this.st.ttype == StreamTokenizer.TT_WORD) {
            if (st.sval.equals("sin") || st.sval.equals("cos") || st.sval.equals("exp") || st.sval.equals("log")) {
                result = unary();
            } else {
                result = identifier();
            }
        } else {
            this.st.pushBack();
            result = number();
        }
        return result;
    }

    private Function unary() throws Exception {
        Function result;
        int operationNeg = st.ttype;
        String operation = st.sval;
        this.st.nextToken();
        if (operationNeg == '-') {
            result = new Neg(primary());
        } else if (operation.equals("sin")) {
            result = new Sin(primary());
        } else if (operation.equals("cos")) {
            result = new Cos(primary());
        } else if (operation.equals("log")) {
            result = new Log(primary());
        } else {
            result = new Exp(primary());
        }
        return result;
    }

    private Function number() throws Exception {
        this.st.nextToken();
        if (this.st.ttype == StreamTokenizer.TT_NUMBER) {
            return new Con(this.st.nval);
        } else {
            throw new Exception("Error: Expected number");
        }
    }
}
