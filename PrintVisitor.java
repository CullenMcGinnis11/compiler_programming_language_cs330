public class PrintVisitor implements ASTVisitor<Object> {

	 
	
	
	//an example for programvisitor
	@Override
	public Object visitProgram(ProgramNode node) {
		int statementNumber = 0;
		for (StatementNode stmt : node.statements) {
			statementNumber++;
			System.out.println("Visiting Statement " + statementNumber);
            stmt.visit(this);
        }
        return null;
	}
	
	//another example for variable declaration visitor
	@Override
	public Object visitVariableDeclaration(VariableDeclarationNode node) {
		
		
		String expression = (String) node.expression.visit(this);
		String output = "Create a new variable: " + node.name + " Type: " + node.type + " Value: " + expression;
		System.out.println(output);
		
		return output;
	}
	@Override
	public Object visitPokemonLoadStatement(PokemonLoadStatement node){
		String stringLiteral = (String) node.stringLiteral.visit(this);
		String output = "Pokemon created named: " + node.identifier + " built off of file: " + stringLiteral;
		return output;
	}

	public Object visitSpellDatabaseStatement(SpellDatabaseStatement node){
		String stringLiteral = (String) node.stringLiteral.visit(this);
		String output = "Spell Database grabbed from: " + stringLiteral;
		return output;
	}
	//....more visitors for different nodes....
	//about 250 lines of code here

}
