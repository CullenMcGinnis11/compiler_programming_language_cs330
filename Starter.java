package Tokenizer;

import java.util.List;


public class Starter {

	public static void main(String[] args) {
		//put the code test case in string code
		String code = """
				// ; ; - - 19invalid
	            Pokemon pokemon1 = Pokemon("./Pokemons/1.txt"); ==
	            String name = ""; || == & &&  //32invalid
	            pokemon1.hp = pokemon1.hp + 150; ! != int
	            if(pokemon1.hp < 0) { print "Lost"; }
	            pokemon1 move1 pokemon2;
				int movie = 100;
				String somebodyToldMe = "String Example";
	            """;
		
		//init the arraylist for tokens and pass the code into the tokenizer
		List<Token> tokens = Tokenizer.tokenize(code);

		


		
		//traverse the list and print the tokens' information
		System.out.println(Token.toString(tokens));
        

	}
	List<Token> grabTokens(List<Token> tokens){
		return tokens;
	}

}
