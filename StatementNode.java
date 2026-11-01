import java.lang.Thread.State;
import java.util.ArrayList;
import java.util.List;
abstract class StatementNode {
}

//VariableDeclarationNode for variable declaration statements
class VariableDeclarationNode extends StatementNode{
	
	//Attributes of the node
	public String type;
    public String name;
    public ExpressionNode expression;
    
    //Complete it, your tasks
	public VariableDeclarationNode(
        String type,
        String name,
        ExpressionNode expression
    ) {
        this.type = type;
        this.name = name;
        this.expression = expression;
    }

}
class StatementNodeTail extends StatementNode{
    public final String identifier;
    public final ExpressionNode statementTail;
    public StatementNodeTail(String identifier, ExpressionNode statementTail){
        this.identifier = identifier;
        this.statementTail = statementTail;
    }

}
class PokemonLoadStatement extends StatementNode {
    public final String identifier;
    public final ExpressionNode stringLiteral;
    public PokemonLoadStatement(String identifier, ExpressionNode stringLiteral){
        this.identifier = identifier;
        this.stringLiteral = stringLiteral;
    }
}
class SpellDatabaseStatement extends StatementNode {
    public final ExpressionNode stringLiteral;
    public SpellDatabaseStatement(ExpressionNode stringLiteral){
        this.stringLiteral = stringLiteral;
    }
}
class ShowStatement extends StatementNode {
    public final String identifier;
    public ShowStatement(String identifier){
        this.identifier = identifier;
    }
}
class PrintStatement extends StatementNode {
    public final ExpressionNode expression;
    public PrintStatement(ExpressionNode expression){
        this.expression = expression;
    }
}

//Make other classes for other nodes, each non-terminal should have a node
//But we will only include the nodes for statements here, Nodes for expression will be in ExpressionNode.java
//another example

//TODO: Here I tried to make another if node, to extend statment node and the calling of that ifnode
class IfStatement extends StatementNode{
    public final ExpressionNode condition;
    public final List<StatementNode> block;
    public final List<StatementNode> thenStatments;
    public final StatementNode elseStatements;

    //initialization step
    public IfStatement(
        ExpressionNode condition,
        List<StatementNode> block,
        List<StatementNode> thenStatements,
        StatementNode elseStatements) 
        {
            this.condition = condition;
            this.block = new ArrayList<>(block);
            this.thenStatments = new ArrayList<>(thenStatements);
            this.elseStatements = elseStatements;
    }
}

class ElseIfStatement extends StatementNode {
    public final ExpressionNode expression;
    public final List<StatementNode> statements;

    public ElseIfStatement(ExpressionNode expression, List<StatementNode> statements){
        this.expression = expression;
        this.statements = statements;
    }
}
class ElseClause extends StatementNode {
    public final List<StatementNode> block;
    public ElseClause(List<StatementNode> block) {
        this.block = new ArrayList<>(block);
    }
    public ElseClause(){
        this.block = null;
    }
}
class WhileStatement extends StatementNode {
    public final List<StatementNode> block;
    public final ExpressionNode expression;
    public WhileStatement(ExpressionNode expression, List<StatementNode> block){
        this.expression = expression;
        this.block = block;
    }
}
class ForStatement extends StatementNode {
    public final ExpressionNode forInit;
    public final ExpressionNode expression;
    public final ExpressionNode forUpdate;
    public final List<StatementNode> block;
    public ForStatement(ExpressionNode forInit, ExpressionNode expression, ExpressionNode forUpdate, List<StatementNode> block){
        this.forInit = forInit;
        this.expression = expression;
        this.forUpdate = forUpdate;
        this.block = block;
    }
}
class FunctionStatement extends StatementNode {
    public final ExpressionNode returnType;
    public final String identifier;
    public final ExpressionNode parameterList;
    public final List<StatementNode> block;
    public FunctionStatement(ExpressionNode returnType, String identifier, ExpressionNode parameterList, List<StatementNode> block){
        this.returnType = returnType;
        this.parameterList = parameterList;
        this.identifier = identifier;
        this.block = block;
    }
}
class BreakStatement extends StatementNode {
    public BreakStatement(){};
}
class ContinueStatement extends StatementNode {
    public ContinueStatement(){};
}
class ReturnStatement extends StatementNode {
    public final ExpressionNode expression;
    public ReturnStatement(ExpressionNode expression) {
        this.expression = expression;
    }
}

//...
//...200 lines of code here
//...

