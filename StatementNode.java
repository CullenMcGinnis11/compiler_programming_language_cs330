
abstract class StatementNode {
	
}

//VariableDeclarationNode for variable declaration statements
class VariableDeclarationNode extends StatementNode{
	
	//Attributes of the node
	public String type;
    public String name;
    public ExpressionNode expression;
    
    //Complete it, your tasks
	
}

//Make other classes for other nodes, each non-terminal should have a node
//But we will only include the nodes for statements here, Nodes for expression will be in ExpressionNode.java
//another example
class AssignmentNode extends StatementNode {
	public final LValueNode target ;
    public final String operator ; // =, +=, -=
    public final ExpressionNode value;
    
}


//...
//...200 lines of code here
//...

