import java.util.List;

//We can merge the code in this file into the file statement node
//But to have a better logic, I separate the nodes for expressions into a different file (this one)
//Node for Statements are in StatementNode.java
abstract class ExpressionNode {

}

//An example (this one is completed, happy? :D)
class BinaryExprNode extends ExpressionNode {
    public final ExpressionNode left;
    public final String op;
    public final ExpressionNode right;

    public BinaryExprNode(ExpressionNode left, String op, ExpressionNode right) {
        this.left = left;
        this.op = op;
        this.right = right;
    }
}

//Another example, ( this one is not completed, sad :( )
class LValueNode extends ExpressionNode {
    public final String base;
    
    //this could be empty
    public final String field; // nullable
    
    //more code here...

}

//You may also want to know this...
class FunctionCallNode extends ExpressionNode {
    public final String name;
    public final List<ExpressionNode> args;
    
    //more code here...
}

//...
//More code here... about 100 lines 
//...
