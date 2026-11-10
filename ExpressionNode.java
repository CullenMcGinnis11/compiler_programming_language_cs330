import java.util.List;

abstract class ExpressionNode {
public <T> T visit(ASTVisitor<T> visitor){
    return null;
}
}

class expressionExpressionNode extends ExpressionNode {
    public final ExpressionNode or;

    public expressionExpressionNode(ExpressionNode or) {
        this.or = or;
    }
    @Override 
    public <T> T visit(ASTVisitor<T> visitor){
        return visitor.visitExpressionExpressionNode(this);
    }
}

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

class LogicalOr extends ExpressionNode {
    // parts of the expression
    public final ExpressionNode left;
    public final ExpressionNode right;

    public LogicalOr(ExpressionNode left, ExpressionNode right) {
        this.left = left;
        this.right = right;
    }
    public <T> T visit(ASTVisitor<T> visitor){
        return visitor.visitLogicalOr(this);
    }
}

class LogicalOrTail extends ExpressionNode {
    public final ExpressionNode left;
    public final ExpressionNode right;

    public LogicalOrTail(ExpressionNode left, ExpressionNode right) {
        this.left = left;
        this.right = right;
    }

    public LogicalOrTail() {
        this.left = null;
        this.right = null;
    }
    public <T> T visit(ASTVisitor<T> visitor) {
        return visitor.visitLogicalOrTail(this);
    }
}

class LogicalAnd extends ExpressionNode {
    public final ExpressionNode left;
    public final ExpressionNode right;

    public LogicalAnd(ExpressionNode left, ExpressionNode right) {
        this.left = left;
        this.right = right;
    }
    public <T> T visit(ASTVisitor<T> visitor) {
        return visitor.visitLogicalAnd(this);
    }
}

class LogicalAndTail extends ExpressionNode {
    public final ExpressionNode equalityExpressionNode;
    public final ExpressionNode logicalAndTail;

    public LogicalAndTail(ExpressionNode equalityExpressionNode, ExpressionNode logicalAndTail) {
        this.equalityExpressionNode = equalityExpressionNode;
        this.logicalAndTail = logicalAndTail;
    }

    public LogicalAndTail() {
        this.equalityExpressionNode = null;
        this.logicalAndTail = null;
    }
    public <T> T visit(ASTVisitor<T> visitor) {
        return visitor.visitLogicalAndTail(this);
    }
}

class EqualityExpression extends ExpressionNode {
    public final ExpressionNode relationalExpression;

    public EqualityExpression(ExpressionNode relationalExpression) {
        this.relationalExpression = relationalExpression;
    }
    public <T> T visit(ASTVisitor<T> visitor) {
        return visitor.visitEqualityExpression(this);
    }
}

class RelationalExpression extends ExpressionNode {
    public final ExpressionNode additiveExpression;
    public final ExpressionNode relationalExpressionTail;

    public RelationalExpression(ExpressionNode additiveExpression, ExpressionNode relationalExpressionTail) {
        this.additiveExpression = additiveExpression;
        this.relationalExpressionTail = relationalExpressionTail;
    }
    public <T> T visit(ASTVisitor<T> visitor) {
        return visitor.visitRelationalExpression(this);
    }
}

class RelationalExpressionTail extends ExpressionNode {
    public final String relation;
    public final ExpressionNode additiveExpression;

    public RelationalExpressionTail(String relation, ExpressionNode additiveExpression) {
        this.relation = relation;
        this.additiveExpression = additiveExpression;
    }

    public RelationalExpressionTail() {
        this.relation = null;
        this.additiveExpression = null;
    }
    public <T> T visit(ASTVisitor<T> visitor) {
        return visitor.visitRelationalExpressionTail(this);
    }
}
class BlockExpression extends ExpressionNode {
    public final ExpressionNode statements;
    public BlockExpression(ExpressionNode statements){
        this.statements = statements;
    }
    public <T> T visit(ASTVisitor<T> visitor) {
        return visitor.visitBlock(this);
    }
}
class Statements extends ExpressionNode {
    public final List<StatementNode> statements;
    public Statements(List<StatementNode> statements){
        this.statements = statements; // this could be named better
    }
    public <T> T visit(ASTVisitor<T> visitor) {
        return visitor.visitStatements(this);
    }
}

class AdditiveExpression extends ExpressionNode {
    public final ExpressionNode multiplicativeExpression;
    public final ExpressionNode additiveExpressionTail;

    public AdditiveExpression(ExpressionNode multiplicativeExpression, ExpressionNode additiveExpressionTail) {
        this.multiplicativeExpression = multiplicativeExpression;
        this.additiveExpressionTail = additiveExpressionTail;
    }
    public <T> T visit(ASTVisitor<T> visitor) {
        return visitor.visitAdditiveExpression(this);
    }
}

class AdditiveExpressionTail extends ExpressionNode {
    public final String symbol;
    public final ExpressionNode multiplicativeExpression;
    public final ExpressionNode additiveExpressionTail;

    public AdditiveExpressionTail(String symbol, ExpressionNode multiplicativeExpression,
            ExpressionNode additiveExpressionTail) {
        this.symbol = symbol;
        this.multiplicativeExpression = multiplicativeExpression;
        this.additiveExpressionTail = additiveExpressionTail;
    }

    public AdditiveExpressionTail() {
        this.symbol = null;
        this.multiplicativeExpression = null;
        this.additiveExpressionTail = null;
    }
    public <T> T visit(ASTVisitor<T> visitor) {
        return visitor.visitAdditiveExpressionTail(this);
    }
}

class MultiplicativeExpression extends ExpressionNode {
    public final ExpressionNode unaryExpression;
    public final ExpressionNode multiplicativeExpressionTail;

    public MultiplicativeExpression(ExpressionNode unaryExpression, ExpressionNode multiplicativeExpressionTail) {
        this.unaryExpression = unaryExpression;
        this.multiplicativeExpressionTail = multiplicativeExpressionTail;
    }
    public <T> T visit(ASTVisitor<T> visitor) {
        return visitor.visitMultiplicativeExpression(this);
    }
}

class MultiplicativeExpressionTail extends ExpressionNode {
    public final String symbol;
    public final ExpressionNode unaryExpression;
    public final ExpressionNode multiplicativeExpressionTail;

    public MultiplicativeExpressionTail(String symbol, ExpressionNode unaryExpression,
            ExpressionNode multiplicativeExpressionTail) {
        this.symbol = symbol;
        this.unaryExpression = unaryExpression;
        this.multiplicativeExpressionTail = multiplicativeExpressionTail;
    }

    public MultiplicativeExpressionTail() {
        this.symbol = null;
        this.unaryExpression = null;
        this.multiplicativeExpressionTail = null;
    }
    public <T> T visit(ASTVisitor<T> visitor) {
        return visitor.visitMultiplicativeExpressionTail(this);
    }
}

class UnaryExpression extends ExpressionNode {
    public final String negativeSymbol;
    public final ExpressionNode unaryExpression;
    public final ExpressionNode primaryExpression;

    public UnaryExpression(String negativeSymbol, ExpressionNode unaryExpression) {
        this.negativeSymbol = negativeSymbol;
        this.unaryExpression = unaryExpression;
        this.primaryExpression = null;
    }

    public UnaryExpression(ExpressionNode primaryExpression) {
        this.primaryExpression = primaryExpression;
        this.negativeSymbol = null;
        this.unaryExpression = null;
    }
    public <T> T visit(ASTVisitor<T> visitor) {
        return visitor.visitUnaryExpression(this);
    }
}

class PrimaryExpression extends ExpressionNode {
    public final ExpressionNode literalExpression;
    public final ExpressionNode primaryExpressionSuffix;
    public final String identifier;
    public final ExpressionNode expressionNode;

    public PrimaryExpression(ExpressionNode literalExpression) {
        this.literalExpression = literalExpression;
        this.primaryExpressionSuffix = null;
        this.identifier = null;
        this.expressionNode = null;
    }

    public PrimaryExpression(String identifier, ExpressionNode primaryExpressionSuffix) {
        this.identifier = identifier;
        this.primaryExpressionSuffix = primaryExpressionSuffix;
        this.literalExpression = null;
        this.expressionNode = null;
    }

    public PrimaryExpression(ExpressionNode expressionNode, String dummyString) {
        this.expressionNode = expressionNode;
        this.primaryExpressionSuffix = null;
        this.literalExpression = null;
        this.identifier = null;
    }

    public PrimaryExpression() {
        this.expressionNode = null;
        this.primaryExpressionSuffix = null;
        this.literalExpression = null;
        this.identifier = null;
    }
    public <T> T visit(ASTVisitor<T> visitor) {
        return visitor.visitPrimaryExpression(this);
    }
}

class LiteralNode extends ExpressionNode {
    public final String value;
    public final ExpressionNode expression;

    public LiteralNode(String value) {
        this.value = value;
        this.expression = null;
    }

    public LiteralNode(ExpressionNode expression) {
        this.value = null;
        this.expression = expression;
    }
    public <T> T visit(ASTVisitor<T> visitor) {
        return visitor.visitLiteralNode(this);
    }
}

class IntegerNode extends ExpressionNode {
    public final String string;
    public final ExpressionNode expression;

    public IntegerNode(String sign, ExpressionNode expressionNode) {
        this.string = sign;
        this.expression = expressionNode;
    }

    public IntegerNode(String string) {
        this.string = string;
        this.expression = null; // this
    }
    public <T> T visit(ASTVisitor<T> visitor) {
        return visitor.visitIntegerNode(this);
    }
}

class PrimaryExpressionSuffix extends ExpressionNode {
    public final ExpressionNode node;

    public PrimaryExpressionSuffix(ExpressionNode node) {
        this.node = node;
    }

    public PrimaryExpressionSuffix() {
        this.node = null;
    }
    public <T> T visit(ASTVisitor<T> visitor) {
        return visitor.visitPrimaryExpressionSuffix(this);
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
    public <T> T visit(ASTVisitor<T> visitor) {
        return visitor.visitArgumentList(this);
    }
}

class ArgumentListTail extends ExpressionNode {
    public final ExpressionNode expression;
    public final ExpressionNode argumentListTail;

    public ArgumentListTail(ExpressionNode expression, ExpressionNode argumentListTail) {
        this.expression = expression;
        this.argumentListTail = argumentListTail;
    }

    public ArgumentListTail() {
        this.argumentListTail = null;
        this.expression = null;
    }
    public <T> T visit(ASTVisitor<T> visitor) {
        return visitor.visitArgumentListTail(this);
    }
}

class LValueTail extends ExpressionNode {
    public final ExpressionNode pokemonField;

    public LValueTail(ExpressionNode pokemonField) {
        this.pokemonField = pokemonField;
    }

    public LValueTail() {
        this.pokemonField = null;
    }
    public <T> T visit(ASTVisitor<T> visitor) {
        return visitor.visitLValueTail(this);
    }
}

class PokemonField extends ExpressionNode {
    public final String pokemonField;

    public PokemonField(String pokemonField) {
        this.pokemonField = pokemonField;
    }
    public <T> T visit(ASTVisitor<T> visitor) {
        return visitor.visitPokemonField(this);
    }
}


class ElseIfList extends ExpressionNode {
    public final List<StatementNode> elseList;
    public ElseIfList(List<StatementNode> elseList) {
        this.elseList = elseList;
    }
    public <T> T visit(ASTVisitor<T> visitor) {
        return visitor.visitElseIfList(this);
    }
}

class StatementTail extends ExpressionNode {
    public final ExpressionNode node1;
    public final ExpressionNode node2;
    public final String identifier;
    public final ExpressionNode node3;

    public StatementTail(ExpressionNode argumentList) {
        this.node1 = argumentList;
        this.node2 = null;
        this.identifier = null;
        this.node3 = null;
    }

    public StatementTail(ExpressionNode keywordNode, String identifier) {
        this.identifier = identifier;
        this.node1 = keywordNode;
        this.node2 = null;
        this.node3 = null;
    }

    public StatementTail(ExpressionNode lValue, ExpressionNode assignemntOperator, ExpressionNode expression) {
        this.node1 = lValue;
        this.node2 = assignemntOperator;
        this.node3 = expression;
        this.identifier = null;
    }
    public <T> T visit(ASTVisitor<T> visitor) {
        return visitor.visitStatementTail(this);
    }
}

class MoveKeyword extends ExpressionNode {
    public final String moveKeyword;

    public MoveKeyword(String moveKeyword) {
        this.moveKeyword = moveKeyword;
    }

    public MoveKeyword() {
        this.moveKeyword = null;
    }
    public <T> T visit(ASTVisitor<T> visitor) {
        return visitor.visitMoveKeyword(this);
    }
}

// Another example, ( this one is not completed, sad :( )
class LValueNode extends ExpressionNode {
    public final String base;

    // this could be empty
    public final String field; // nullable

    // more code here...
    public LValueNode(
            String field,
            String base) {
        this.field = field;
        this.base = base;
    }

    public LValueNode(String base) { // if field is null ...
                                     // May not need to use this as field would already be set to null in other
                                     // constructor statment
        this.base = base;
        this.field = null;
    }
    public <T> T visit(ASTVisitor<T> visitor) {
        return visitor.visitLValue(this);
    }
}

class StringLiteral extends ExpressionNode {
    public final String string;

    public StringLiteral(String string) {
        this.string = string;
    }
    public <T> T visit(ASTVisitor<T> visitor) {
        return visitor.visitStringLiteral(this);
    }
}

class ForInit extends ExpressionNode {
    public final String identifier;
    public final ExpressionNode expression;

    public ForInit(String identifier, ExpressionNode expression) {
        this.identifier = identifier;
        this.expression = expression;
    }
    public <T> T visit(ASTVisitor<T> visitor){
        return visitor.visitForInit(this);
    }
}

class ForUpdate extends ExpressionNode {
    public final String identifier;

    public ForUpdate(String identifier) {
        this.identifier = identifier;
    }
    public <T> T visit(ASTVisitor<T> visitor) {
        return visitor.visitForUpdate(this);
    }
}

class ReturnType extends ExpressionNode {
    public final String type;

    public ReturnType(String type) {
        this.type = type;
    }

    public ReturnType() {
        this.type = null;
    }
    public <T> T visit(ASTVisitor<T> visitor) {
        return visitor.visitReturnType(this);
    }
}

class ParamaterList extends ExpressionNode {
    public final ExpressionNode type;
    public final ExpressionNode expressionListTail;

    public ParamaterList(ExpressionNode type, ExpressionNode expressionListTail) {
        this.type = type;
        this.expressionListTail = expressionListTail;
    }

    public ParamaterList() {
        this.type = null;
        this.expressionListTail = null;
    }
    public <T> T visit(ASTVisitor<T> visitor) {
        return visitor.visitParameterList(this);
    }
}

class ExpressionListTail extends ExpressionNode {
    public final List<ExpressionNode> expressionList;
    public ExpressionListTail(List<ExpressionNode> expressionList) {
        this.expressionList = expressionList;
    }
    public <T> T visit(ASTVisitor<T> visitor) {
        return visitor.visitExpressionListTail(this);
    }
}

class Parameter extends ExpressionNode {
    public final String type;
    public final String identifier;

    public Parameter(String type, String identifier) {
        this.type = type;
        this.identifier = identifier;
    }
    public <T> T visit(ASTVisitor<T> visitor) {
        return visitor.visitParameter(this);
    }
}
