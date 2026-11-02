import java.util.List;

//We can merge the code in this file into the file statement node
//But to have a better logic, I separate the nodes for expressions into a different file (this one)
//Node for Statements are in StatementNode.java
abstract class ExpressionNode {

}
class expressionExpressionNode extends ExpressionNode {
    public final ExpressionNode or;
    public expressionExpressionNode(ExpressionNode or){
        this.or = or;
    }
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
    // parts of the expression
    public final ExpressionNode left;
    public final ExpressionNode right;
    

    public LogicalOr(ExpressionNode left, ExpressionNode right) {
        this.left = left;
        this.right = right;
    }
}

class LogicalOrTail extends ExpressionNode {
    public final ExpressionNode left;
    public final ExpressionNode right;
    public LogicalOrTail(ExpressionNode left, ExpressionNode right){
        this.left = left;
        this.right = right;
    }
    public LogicalOrTail(){
        this.left = null;
        this.right = null;
    }
}

class LogicalAnd extends ExpressionNode {
    public final ExpressionNode left;
    public final ExpressionNode right;

    public LogicalAnd(ExpressionNode left, ExpressionNode right) {
        this.left = left;
        this.right = right;
    }
}
class LogicalAndTail extends ExpressionNode {
    public final ExpressionNode equalityExpressionNode;
    public final ExpressionNode logicalAndTail;

    public LogicalAndTail(ExpressionNode equalityExpressionNode, ExpressionNode logicalAndTail){
        this.equalityExpressionNode = equalityExpressionNode;
        this.logicalAndTail = logicalAndTail;
    }
    public LogicalAndTail(){
        this.equalityExpressionNode = null;
        this.logicalAndTail = null;
    }
}
class EqualityExpression extends ExpressionNode {
    public final ExpressionNode relationalExpression;
    public EqualityExpression(ExpressionNode relationalExpression){
        this.relationalExpression = relationalExpression;
    }
}
class RelationalExpression extends ExpressionNode {
    public final ExpressionNode additiveExpression;
    public final ExpressionNode relationalExpressionTail;
    public RelationalExpression(ExpressionNode additiveExpression, ExpressionNode relationalExpressionTail){
        this.additiveExpression = additiveExpression;
        this.relationalExpressionTail = relationalExpressionTail;
    }
}
class RelationalExpressionTail extends ExpressionNode {
    public final String relation;
    public final ExpressionNode additiveExpression;
    public RelationalExpressionTail(String relation, ExpressionNode additiveExpression){
        this.relation = relation;
        this.additiveExpression = additiveExpression;
    }
    public RelationalExpressionTail(){
        this.relation = null;
        this.additiveExpression = null;
    }
    }
class AdditiveExpression extends ExpressionNode {
    public final ExpressionNode multiplicativeExpression;
    public final ExpressionNode additiveExpressionTail;
    public AdditiveExpression(ExpressionNode multiplicativeExpression, ExpressionNode additiveExpressionTail){
        this.multiplicativeExpression = multiplicativeExpression;
        this.additiveExpressionTail = additiveExpressionTail;
    }
} 
class AdditiveExpressionTail extends ExpressionNode {
    public final String symbol;
    public final ExpressionNode multiplicativeExpression;
    public final ExpressionNode additiveExpressionTail;
    public AdditiveExpressionTail(String symbol, ExpressionNode multiplicativeExpression, ExpressionNode additiveExpressionTail){
        this.symbol = symbol;
        this.multiplicativeExpression = multiplicativeExpression;
        this.additiveExpressionTail = additiveExpressionTail;
    }
    public AdditiveExpressionTail(){
        this.symbol = null;
        this.multiplicativeExpression = null;
        this.additiveExpressionTail = null;
    }
}
class MultiplicativeExpression extends ExpressionNode {
    public final ExpressionNode unaryExpression;
    public final ExpressionNode multiplicativeExpressionTail;
    public MultiplicativeExpression(ExpressionNode unaryExpression, ExpressionNode multiplicativeExpressionTail) {
        this.unaryExpression = unaryExpression;
        this.multiplicativeExpressionTail = multiplicativeExpressionTail;
    }
}
class MultiplicativeExpressionTail extends ExpressionNode {
    public final String symbol;
    public final ExpressionNode unaryExpression;
    public final ExpressionNode multiplicativeExpressionTail;

    public MultiplicativeExpressionTail(String symbol, ExpressionNode unaryExpression, ExpressionNode multiplicativeExpressionTail){
        this.symbol = symbol;
        this.unaryExpression = unaryExpression;
        this.multiplicativeExpressionTail = multiplicativeExpressionTail;
    }
    public MultiplicativeExpressionTail(){
        this.symbol = null;
        this.unaryExpression = null;
        this.multiplicativeExpressionTail = null;
    }
}
class UnaryExpression extends ExpressionNode {
    public final String negativeSymbol;
    public final ExpressionNode unaryExpression;
    public final ExpressionNode primaryExpression;
    public UnaryExpression(String negativeSymbol, ExpressionNode unaryExpression){
        this.negativeSymbol = negativeSymbol;
        this.unaryExpression = unaryExpression;
        this.primaryExpression = null;
    }
    public UnaryExpression(ExpressionNode primaryExpression){
        this.primaryExpression = primaryExpression;
        this.negativeSymbol = null;
        this.unaryExpression = null;
    }
}
class PrimaryExpression extends ExpressionNode {
    public final ExpressionNode literalExpression;
    public final ExpressionNode primaryExpressionSuffix;
    public final String identifier;
    public final ExpressionNode expressionNode;

    public PrimaryExpression(ExpressionNode literalExpression){
        this.literalExpression = literalExpression;
        this.primaryExpressionSuffix = null;
        this.identifier = null;
        this.expressionNode = null;
    }
    public PrimaryExpression(String identifier, ExpressionNode primaryExpressionSuffix){
        this.identifier = identifier;
        this.primaryExpressionSuffix = primaryExpressionSuffix;
        this.literalExpression = null;
        this.expressionNode = null;
    }
    public PrimaryExpression(ExpressionNode expressionNode, String dummyString){
        this.expressionNode = expressionNode;
        this.primaryExpressionSuffix = null;
        this.literalExpression = null;
        this.identifier = null;
    }
     public PrimaryExpression(){
        this.expressionNode = null;
        this.primaryExpressionSuffix = null;
        this.literalExpression = null;
        this.identifier = null;
    }
}
class LiteralNode extends ExpressionNode {
    public final String value;
    public final ExpressionNode expression;
    public LiteralNode(String value){
        this.value = value;
        this.expression = null;
    }
    public LiteralNode(ExpressionNode expression) {
        this.value = null;
        this.expression = expression;
    }
}
class IntegerNode extends ExpressionNode {
    public final String string;
    public final ExpressionNode expression;
    public IntegerNode(String sign, ExpressionNode expressionNode){
        this.string = sign;
        this.expression = expressionNode;
    }
    public IntegerNode(String string){
        this.string = string;
        this.expression = null; //this
    }
}
class PrimaryExpressionSuffix extends ExpressionNode {
    public final ExpressionNode node;
    public PrimaryExpressionSuffix(ExpressionNode node){
        this.node = node;
    }
    public PrimaryExpressionSuffix(){
        this.node = null;
    }
}
class ArgumentList extends ExpressionNode {
    public final ExpressionNode expressionNode;
    public final ExpressionNode argumentListTail;
    public ArgumentList() {
        this.argumentListTail = null;
        this.expressionNode = null;
    }
    public ArgumentList(ExpressionNode expressionNode, ExpressionNode argumentListTail) {
        this.expressionNode = expressionNode;
        this.argumentListTail = argumentListTail;
    }
}
class ArgumentListTail extends ExpressionNode {
    public final ExpressionNode expression;
    public final ExpressionNode argumentListTail;
    public ArgumentListTail(ExpressionNode expression, ExpressionNode argumentListTail){
        this.expression = expression;
        this.argumentListTail = argumentListTail;
    }
    public ArgumentListTail(){
        this.argumentListTail = null;
        this.expression = null;
    }
}
class LValueTail extends ExpressionNode {
    public final ExpressionNode pokemonField;
    public LValueTail(ExpressionNode pokemonField){
        this.pokemonField = pokemonField;
    }
    public LValueTail(){
        this.pokemonField = null;
    }
}
class PokemonField extends ExpressionNode {
    public final String pokemonField;
    public PokemonField(String pokemonField){
        this.pokemonField = pokemonField;
    }
}

class StatementTail extends ExpressionNode {
    public final ExpressionNode node1;
    public final ExpressionNode node2;
    public final String identifier;
    public final ExpressionNode node3;
    
    public StatementTail(ExpressionNode argumentList){
        this.node1 = argumentList;
        this.node2 = null;
        this.identifier = null;
        this.node3 = null;
    }
    public StatementTail(ExpressionNode keywordNode, String identifier){
        this.identifier = identifier;
        this.node1 = keywordNode;
        this.node2 = null;
        this.node3 = null;
    }
    public StatementTail(ExpressionNode lValue, ExpressionNode assignemntOperator, ExpressionNode expression){
        this.node1 = lValue;
        this.node2 = assignemntOperator;
        this.node3 = expression;
        this.identifier = null;
    }
}

class MoveKeyword extends ExpressionNode {
    public final String moveKeyword;
    public MoveKeyword(String moveKeyword) {
        this.moveKeyword = moveKeyword;
    }
    public MoveKeyword(){
        this.moveKeyword = null;
    }
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



class StringLiteral extends ExpressionNode {
    public final String string;
    public StringLiteral(String string) {
        this.string = string;
    }
}

class ForInit extends ExpressionNode {
    public final String identifier;
    public final ExpressionNode expression;
    public ForInit(String identifier, ExpressionNode expression) {
        this.identifier = identifier;
        this.expression = expression;
    }
}
class ForUpdate extends ExpressionNode {
    public final String identifier;
    public ForUpdate(String identifier) {
        this.identifier = identifier;
    }
}

class ReturnType extends ExpressionNode {
    public final String type;
    public ReturnType(String type) {
        this.type = type;
    }
    public ReturnType(){
        this.type = null;
    }
}
class ParamaterList extends ExpressionNode {
    public final ExpressionNode type;
    public final List<ExpressionNode> expressionListTail;
    public ParamaterList(ExpressionNode type, List<ExpressionNode> expressionListTail) {
        this.type = type;
        this.expressionListTail = expressionListTail;
    }
    public ParamaterList(){
        this.type = null;
        this.expressionListTail = null;
    }
}

class Parameter extends ExpressionNode {
    public final String type;
    public final String identifier;
    public Parameter(String type, String identifier) {
        this.type = type;
        this.identifier = identifier;
    }
}


//...
//More code here... about 100 lines 
//...
