package Tokenizer;
import java.io.BufferedReader;
import java.io.StringReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class Tokenizer {
	
	public static List<Token> tokenize(String code) {
	    	
		//init the array list for storing the tokens
	    List<Token> tokens = new ArrayList<>();
		System.out.println("Code: " + code);
	    char[] symbolList = new char[32]; //List to encapsulate all of non arabic numbers/not underscore as well
		symbolList[0] = (char)(33);
		symbolList[1] = (char)(34);
		for(int i = 0; i < 24; i++){ // 33, 34, [38, 62] // 26
			symbolList[i + 2] = (char)(i + 38);
		}for(int i = 0; i < 3; i++){ // 91, 92, 93, 123, 124, 125
			symbolList[i + 26] = (char)(i + 91);
		}for(int i = 0; i < 3; i++){ // 91, 92, 93, 123, 124, 125
			symbolList[i + 29] = (char)(i + 123);
		}
		System.out.println(Arrays.toString(symbolList));

		boolean isSymbol = false;
		
		
	    
		try{
			// FileReader Class used
            StringReader stringReader = new StringReader(code);

            System.out.println("Reading char by char : \n");
            int i;
			int line = 1;
            
            while ((i = stringReader.read()) != -1) {
				isSymbol = false;
				for(char character : symbolList){
					if(i == (int)character){
						isSymbol = true;
					}
				}
				if(i == '\n'){ // check new line
					line++;
				} else if(i == ';'){
					//Use this statement to insert the recognized token into the list
		    	tokens.add(new Token("Punctuator", ";"));
				
				} else if(isSymbol){
					System.out.println("Caught One!");
				}
            }
			System.out.println();
			System.out.println(line);
			stringReader.close();



        }
        catch (IOException e){
            System.out.println("Error occured while reading file: " + e.getMessage());
        }
	    // while () {
	    	
	    	
	    // }
	    
	    
	    
	    //return the tokens in the input code
	    return tokens;
		
	    
	}

}
