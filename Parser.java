import java.util.List;

public class Parser {
	
	//List of tokens, the output from tokenizer
	private final List<Token> tokens;

	//constructor, takes the list of the tokens as input
    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }
    
    
    //Start Point. Parse the program from the first token
    public ProgramNode parseProgram() {
    	
    	//init the program node
    	ProgramNode program = new ProgramNode();
    	
    	//parse the statements, hint: statements, but not a statement. But how?
    	program.statements.add(parseStatement());
    	
    	//return the program node which is also the root of the AST
    	return program;
    }

    //function to parse each statement
    private StatementNode parseStatement() {
    	
    	//Parse other non-terminal here
    	//e.g., parseIfStatement, parseVariableDeclarationStatement, parseForStatement
    	//return the node based on different function
    	return;
    }
    
    //Some examples on what other parsing functions look like
    private VariableDeclarationNode parseVariableDeclaration() {
    	
    	//Store type and name for the variable
    	String type;
        String name;
        
        //match with =
        
        //parse expression
        ExpressionNode expr = parseExpression();

        //match with ;
        
        //return the node created based on the information we have
        return new VariableDeclarationNode(type, name, expr);
    	
    }
    
    //function for parsing expression
    private ExpressionNode parseExpression() { 
    	//more codes here!
    	return ExpressionNode; 
    }
    
    
    //more functions for parsing every non-terminal
    //...
    //...about 500 lines of code here...Yes, 500 lines of code :D
    //...
    
    
    
    
    
    
}
