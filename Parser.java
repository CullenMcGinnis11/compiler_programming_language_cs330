import java.util.List;


    //    \****__              ____
    //      |    *****\_      --/ *\-__
    //      /_          (_    ./ ,/----'                             _
    //        \__         (_./  /                                   /-|
    //           \__           \___----^__                        ___  \
    //            _/   _                  \        ______________|  /|--]
    //     |    _/  __/ )\"\ _____         *\                    | / |__|
    //     |\__/   /    ^ ^       \____      )                    \ /.\ |
    //      \___--"                    \_____ )                    '|| ||
    //                                       "                     <_'<_'
    // Dragon By IronWing

public class Parser {
	private int current = 0;
	//List of tokens, the output from tokenizer
	private final List<Token> tokens;





	//constructor, takes the list of the tokens as input
    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }
    private boolean atEnd(){ //if youre at end the current will be larger than tokens.size
        return current >= tokens.size();
    }
    private Token peek(){ //make sure you are not at end to check tokens
        if(atEnd()){
            throw new IllegalArgumentException("End of Tokens");
        }
        return tokens.get(current);
    }
    private Token pop(){
        Token returnVal = tokens.get(current); 
        current++;
        return returnVal;
    }
    private boolean checkToken(String expectType, String expectValue){ //create a statment for what you expect out of a token
       boolean returnVal =  peek().getType().equals(expectType) 
        && peek().getValue().equals(expectValue);
       if(returnVal){
        nextToken();
        return returnVal;
       }
       else{
        return returnVal;
       }

    }
    private boolean checkTokenType(String expectType){ //TYPE examples, Keyword, String, Operator, Boolean
         boolean returnVal = peek().getValue().equals(expectType);
         if(returnVal){
        nextToken();
        return returnVal;
       }
       else{
        return returnVal;
       }
    }
    private boolean checkTokenValue(String expectValue){ //create a statment for what you expect out of a token
         boolean returnVal = peek().getType().equals(expectValue);
         if(returnVal){
        nextToken();
        return returnVal;
       }
       else{
        return returnVal;
       }
    }
    private void nextToken(){
        if(!atEnd()){
            current++;
        }
    }
    private void errorOccuredWrongToken(String expected) {
        throw new IllegalAccessError("Expected " + expected + " instead " + peek().getValue());
    }
    private String identifierExpression(){
        if(checkTokenType("Identifier")){
            return pop().getValue();
        } else {
            errorOccuredWrongToken("Identifier");
            return "";
        }
    }

    private void match(String expected){
        if(checkTokenValue(expected)){
            pop();
        } else {
            errorOccuredWrongToken(expected);
        }
    }









    //Start Point. Parse the program from the first token
    public ProgramNode parseProgram() {
    	
    	//init the program node
    	ProgramNode program = new ProgramNode();
        
    	
    	//parse the statements, hint: statements, but not a statement. But how?
        int index = 0;
        while(index < tokens.size()){
            program.statements.add(parseStatement());
        }
    	

    	
    	//return the program node which is also the root of the AST
    	return program;
    }

    //function to parse each statement
    private StatementNode parseStatement() {
        if(checkTokenValue("int") 
            || checkTokenValue("String") 
        || checkTokenValue("boolean") 
        || checkTokenValue("Pokemon")){
            return parseVariableDeclaration();
        }
        //TODO: Trying to work though how parsing statement would work, need to work through this part
    	//Parse other non-terminal here
    	//e.g., parseIfStatement, parseVariableDeclarationStatement, parseForStatement
    	//return the node based on different function
    	throw new IllegalAccessError("Nothing was found");
    }
    
    //Some examples on what other parsing functions look like
    private VariableDeclarationNode parseVariableDeclaration() {
        //TODO: need to declare the type, name, expression
        String type = pop().getValue();
        String name = identifierExpression(); //Identifier is a special case where we need to check its an identifier then grab the string
        match("=");
        ExpressionNode expr = parseExpression(); //for expression need to grab the expression node
        match(";");
        return new VariableDeclarationNode(type, name, expr); //TODO: confused as to what this is returning so when you are returning 
                                                              // a variable declaration node, what exactly are you returning?
                                                              // need to make these variables for passing to the varible decleration node
    }
    
    
    //function for parsing expression
    private ExpressionNode parseExpression() { 
        ExpressionNode logicOr = LogicalOr();
    	//more codes here!
    	return ExpressionNode; 
    }
    
    
    //more functions for parsing every non-terminal
    //...
    //... 500 lines of code :D
    //...
    
    
    
    
    
    
}
