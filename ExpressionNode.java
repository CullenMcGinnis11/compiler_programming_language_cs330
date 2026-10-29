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

//TODO: Here i started to make more expression nodes as an extension of the binary expr node you made before, how would I implement into the tree?
class LogicalOr extends ExpressionNode {
    public final LogicalAnd left;
    public final String or;
    public final LogicalOr right;
    

    public LogicalOr(LogicalAnd left, String or, LogicalOr right) {
        this.left = left;
        this.or = or;
        this.right = right;
    }
    public LogicalOr(LogicalAnd left){
        this.left = left;
        this.or = null;
        this.right = null;
    }
}

class LogicalAnd extends ExpressionNode {
    public final LogicEquality left;
    public final String and;
    public final LogicalAnd right;

    public LogicalAnd(LogicEquality left, String and, LogicalAnd right) {
        this.left = left;
        this.and = and;
        this.right = right;
    }
    public LogicalAnd(LogicEquality left) {
        this.left = left;
        this.and = null;
        this.right = null;
    }
}
class LogicEquality extends ExpressionNode {
    public final relationalExpression right;// not going to finish this until 
                                            // I have a better understand of what I am 
                                            // doing but for future me just keepp extending this according to bnf grammar
}

//Another example, ( this one is not completed, sad :( )
class LValueNode extends ExpressionNode {
    public final String base;
    
    //this could be empty
    public final String field; // nullable
    
    //more code here...
    public LValueNode(
        String field,
        String base
    ) {
        this.field = field;
        this.base = base;
    }
    public LValueNode(String base){ //if field is null ... 
                                    // May not need to use this as field would already be set to null in other constructor statment
        this.base = base;
        this.field = null;
    }
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
