package parser;

import java.io.StreamTokenizer;
import java.io.StringReader;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.io.IOException;


import function.*;


public class Parser {

    private StreamTokenizer st;

    private static HashMap<String, Class<? extends Unary>> builtInFunctions = new HashMap<>();
    static {
        builtInFunctions.put("sin", Sin.class);
        builtInFunctions.put("cos", Cos.class);
        builtInFunctions.put("exp", Exp.class);
        builtInFunctions.put("log", Log.class);
        builtInFunctions.put("der", Derivative.class);
    }

    public Function parse(String inputString) throws IOException {
        this.st = new StreamTokenizer(new StringReader(inputString)); // reads from inputString via stringreader.
        this.st.ordinaryChar('-');
        this.st.ordinaryChar('/');
        this.st.eolIsSignificant(true);
        Function result = statement(); // calls to statement
        return result; // the final result
    }

    private Function statement() throws IOException {
        Function result;

        this.st.nextToken();
        if (this.st.ttype == StreamTokenizer.TT_EOF) {
            throw new SyntaxErrorException("Syntax Error: Expected an expression");
        }

        result = expression();
        
        if (this.st.nextToken() != StreamTokenizer.TT_EOF) { // token should be an end of stream token if we are done
            if (this.st.ttype == StreamTokenizer.TT_WORD) {
                throw new SyntaxErrorException("Syntax Error: Unexpected '" + this.st.sval + "'");
            } else {
                throw new SyntaxErrorException("Syntax Error: Unexpected '" + String.valueOf((char) this.st.ttype) + "'");
            }
        }

        return result;
    }



    private Function identifier() throws IOException {
        switch (this.st.sval) {
            case "x":
                return new Var();
            
            case "pi":
                return new NamedCon("pi", Math.PI);

            case "e":
                return new NamedCon("e", Math.E);
        
            default:
                throw new SyntaxErrorException("Syntax Error: Unexpected " + this.st.sval);
        }
    }


    private Function expression() throws IOException {
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

    private Function term() throws IOException {
        Function result = pow();
        this.st.nextToken();
        while (this.st.ttype == '*' || this.st.ttype == '/') {
            int operation = st.ttype;
            this.st.nextToken();
            if (operation == '*') {
                result = new Mul(result, pow());
            } else {
                result = new Div(result, pow());
            }
            this.st.nextToken();
        }
        this.st.pushBack();
        return result;
    }


    private Function pow() throws IOException {
        Function result = primary();
        this.st.nextToken();
        while (this.st.ttype == '^') {
            this.st.nextToken();
            result = new Pow(result, primary());
            this.st.nextToken();
        }
        this.st.pushBack();
        return result;
    }


    private Function primary() throws IOException {
        Function result;
        if (this.st.ttype == '(') {
            this.st.nextToken();
            result = expression();
            if (this.st.nextToken() != ')') {
                throw new SyntaxErrorException("Syntax Error: Expected ')'");
            }
        } else if (this.st.ttype == '-') {
            result = unary();
        } else if (this.st.ttype == StreamTokenizer.TT_WORD) {
            if (builtInFunctions.containsKey(this.st.sval)) {
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

    private Function unary() throws IOException {
        int operationNeg = st.ttype;
        String operation = st.sval;
        this.st.nextToken();
        if (operationNeg == '-') {
            return new Neg(primary());
        }
        try {
            Class<? extends Unary> functionClass = builtInFunctions.get(operation);
            Constructor<? extends Unary> functionClassConstructor = functionClass.getConstructor(Function.class);
            return functionClassConstructor.newInstance(primary());
        } catch (IOException e) {
            throw e;
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private Function number() throws IOException {
        this.st.nextToken();
        if (this.st.ttype == StreamTokenizer.TT_NUMBER) {
            return new Con(this.st.nval);
        } else {
            throw new SyntaxErrorException("Syntax Error: Expected number");
        }
    }
}
