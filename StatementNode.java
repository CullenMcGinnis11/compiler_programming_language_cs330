import java.beans.Expression;
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


//Make other classes for other nodes, each non-terminal should have a node
//But we will only include the nodes for statements here, Nodes for expression will be in ExpressionNode.java
//another example
class AssignmentNode extends StatementNode {
	public final LValueNode target ;
    public final String operator ; // =, +=, -=
    public final ExpressionNode value;

}

//TODO: Here I tried to make another if node, to extend statment node and the calling of that ifnode
class IfNode extends StatementNode{
    public final ExpressionNode condition;
    public final List<StatementNode> thenStatments;
    public final List<StatementNode> elseStatements;

    //initialization step
    public IfNode(
        ExpressionNode condition,
        List<StatementNode> thenStatements,
        List<StatementNode> elseStatements) 
        {
            this.condition = condition;
            this.thenStatments = new ArrayList<>(thenStatements);
            this.elseStatements = new ArrayList<>(elseStatements);
    }
}


//...
//...200 lines of code here
//...

