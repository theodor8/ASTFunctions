package parser;

import java.io.StreamTokenizer;
import java.io.StringReader;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.io.IOException;


import function.*;


public class Parser {

    private StreamTokenizer st;

    private static HashMap<String, Class<? extends Unary>> builtinFuncs = new HashMap<>();
    private static HashMap<String, Double> namedCons = new HashMap<>();
    static {
        builtinFuncs.put("sin", Sin.class);
        builtinFuncs.put("cos", Cos.class);
        builtinFuncs.put("tan", Tan.class);
        builtinFuncs.put("exp", Exp.class);
        builtinFuncs.put("log", Log.class);
        builtinFuncs.put("der", Derivative.class);
        // builtinFuncs.put("int", Integral.class);

        namedCons.put("pi", Math.PI);
        namedCons.put("e", Math.E);
    }

    public String getAvailableFunctions() {
        return builtinFuncs.keySet().toString();
    }

    public String getAvailableConstants() {
        return namedCons.keySet().toString();
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

        result = evalVar();
        
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
        if (this.st.sval.equals("x")) {
            return new Var();
        }
        if (this.st.sval.equals("a")) {
            return new Ans();
        }
        if (namedCons.containsKey(this.st.sval)) {
            return new NamedCon(this.st.sval, namedCons.get(this.st.sval));
        }
        throw new SyntaxErrorException("Syntax Error: Unexpected " + this.st.sval);
    }

    private Function evalVar() throws IOException {
        Function result = expression();
        this.st.nextToken();
        while (this.st.ttype == '<' || this.st.ttype == '>') {
            int operation = st.ttype;
            this.st.nextToken();
            if (operation == '<') {
                result = new EvalVar(result, expression());
            } else {
                result = new EvalVar(expression(), result);
            }
            this.st.nextToken();
        }
        this.st.pushBack();
        return result;
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
            result = evalVar();
            if (this.st.nextToken() != ')') {
                throw new SyntaxErrorException("Syntax Error: Expected ')'");
            }
        } else if (this.st.ttype == '-') {
            result = unary();
        } else if (this.st.ttype == StreamTokenizer.TT_WORD) {
            if (builtinFuncs.containsKey(this.st.sval)) {
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
            Class<? extends Unary> funcClass = builtinFuncs.get(operation);
            Constructor<? extends Unary> funcClassCtor = funcClass.getConstructor(Function.class);
            return funcClassCtor.newInstance(primary());
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
