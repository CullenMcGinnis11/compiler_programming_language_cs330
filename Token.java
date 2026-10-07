package Tokenizer;
public class Token {
    private String type; // String, Integer
    private String value;

    public Token(String type, String value) {
    	//Complete the constructor
        this.type = type;
        this.value = value;// This could be bogus so be careful about this
    }

    public String getType() { return type; }
    public String getValue() { return value; }

    // @Override
    // public String toString() {
    // 	//Complete the toString method
        
    // }
}