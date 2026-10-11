package Tokenizer;

import java.util.List;
import java.lang.StringBuilder;

public class Token {
    private String type; // String, Integer
    private String value;

    public Token(String type, String value) {
    	//Complete the constructor
        this.type = type;
        this.value = value;
    }

    public String getType() { return type; }
    public String getValue() { return value; }

    public static String toString(List<Token> tokens) {
        StringBuilder tokenString = new StringBuilder();
        int number = 1;
    	//Complete the toString method
        for(Token t : tokens) 
        {
            tokenString.append("Token: " + t.getValue() + " Type: " + t.getType() + " Number: " + number + "\n");
        	number++;
        }
        return tokenString.toString();
    }
}