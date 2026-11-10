import java.util.List;

public class Starter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//put the code test case in string code
		String code = """
	            Pokemon pokemon1 = Pokemon("./Pokemons/1.txt");
	            String name = " ";
	            pokemon1.hp = pokemon1.hp + 150;
	            if(pokemon1.hp < 0) { print "Lost"; }
	            pokemon1 move1 pokemon2;
	            """;
		
		//init the arraylist for tokens and pass the code into the tokenizer
		List<Token> tokens = Tokenizer.tokenize(code);
        
		Parser parser = new Parser(tokens);
        ProgramNode program = parser.parseProgram();
        System.out.println("Parse SUCCESS: " + program.statements.size() + " statement(s)");

	    PrintVisitor pv = new PrintVisitor();
	    pv.visitProgram(program);
        
        
	}

}
