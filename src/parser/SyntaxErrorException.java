package parser;

import java.io.IOException;

public class SyntaxErrorException extends IOException {
    public SyntaxErrorException(String message) {
        super(message);
    }
}
