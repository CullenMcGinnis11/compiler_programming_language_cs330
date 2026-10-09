package Tokenizer;

import java.util.List;

public class Starter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//put the code test case in string code
		String code = """
				!=
	            Pokemon pokemon1 = Pokemon("./Pokemons/1.txt"); ==
	            String name = ""; // || == & &&
	            pokemon1.hp = pokemon1.hp + 150; ! !=
	            if(pokemon1.hp < 0) { print "Lost"; }
	            pokemon1 move1 pokemon2;
	            """;
		
		//init the arraylist for tokens and pass the code into the tokenizer
		//TODO try to look through the txtFile and find a specific word and have that flag something
		List<Token> tokens = Tokenizer.tokenize(code);

		
		//We use this number to record the number of tokens in the list
		int number = 1;
		
		//traverse the list and print the tokens' information
        for(Token t : tokens) 
        {
        	System.out.println("Token: " + t.getValue() + " Type: " + t.getType() + " Number: " + number);
        	number++;
        }

	}

}
