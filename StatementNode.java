import java.util.ArrayList;
import java.util.List;

abstract class StatementNode {
    public <T> T visit(ASTVisitor<T> visitor){
        return null;
    };
}

class VariableDeclarationNode extends StatementNode {

    public String type;
    public String name;
    public ExpressionNode expression;

    public VariableDeclarationNode(
            String type,
            String name,
            ExpressionNode expression) {
        this.type = type;
        this.name = name;
        this.expression = expression;
    }

    public <T> T visit(ASTVisitor<T> visitor){
        return visitor.visitVariableDeclaration(this);
    }

}

class StatementNodeTail extends StatementNode {
    public final String identifier;
    public final ExpressionNode statementTail;

    public StatementNodeTail(String identifier, ExpressionNode statementTail) {
        this.identifier = identifier;
        this.statementTail = statementTail;
    }
    public <T> T visit(ASTVisitor<T> visitor) {
        return visitor.visitStatementNodeTail(this);
    }

}

class PokemonLoadStatement extends StatementNode {
    public final String identifier;
    public final ExpressionNode stringLiteral;

    public PokemonLoadStatement(String identifier, ExpressionNode stringLiteral) {
        this.identifier = identifier;
        this.stringLiteral = stringLiteral;
    }
     public <T> T visit(ASTVisitor<T> visitor) {
        return visitor.visitPokemonLoadStatement(this);
    }
}


class SpellDatabaseStatement extends StatementNode {
    public final ExpressionNode stringLiteral;

    public SpellDatabaseStatement(ExpressionNode stringLiteral) {
        this.stringLiteral = stringLiteral;
    }
     public <T> T visit(ASTVisitor<T> visitor) {
        return visitor.visitSpellDatabaseStatement(this);
    }
}

class ShowStatement extends StatementNode {
    public final String identifier;

    public ShowStatement(String identifier) {
        this.identifier = identifier;
    }
     public <T> T visit(ASTVisitor<T> visitor) {
        return visitor.visitShowStatement(this);
    }
}

class PrintStatement extends StatementNode {
    public final ExpressionNode expression;

    public PrintStatement(ExpressionNode expression) {
        this.expression = expression;
    }
     public <T> T visit(ASTVisitor<T> visitor) {
        return visitor.visitPrintStatement(this);
    }
}

class IfStatement extends StatementNode {
    public final ExpressionNode condition;
    public final ExpressionNode block;
    public final ExpressionNode elseIfStatements;
    public final StatementNode elseStatements;

    // initialization step
    public IfStatement(
            ExpressionNode condition,
            ExpressionNode block,
            ExpressionNode thenStatements,
            StatementNode elseStatements) {
        this.condition = condition;
        this.block = block;
        this.elseIfStatements = thenStatements;
        this.elseStatements = elseStatements;
    }
    public <T> T visit(ASTVisitor<T> visitor) {
        return visitor.visitIfStatement(this);
    }
}

class ElseIfStatement extends StatementNode {
    public final ExpressionNode expression;
    public final ExpressionNode statements;

    public ElseIfStatement(ExpressionNode expression, ExpressionNode statements) {
        this.expression = expression;
        this.statements = statements;
    }
    public <T> T visit(ASTVisitor<T> visitor) {
        return visitor.visitElseIfStatement(this);
    }
}

class ElseClause extends StatementNode {
    public final ExpressionNode block;

    public ElseClause(ExpressionNode block) {
        this.block = block;
    }

    public ElseClause() {
        this.block = null;
    }
    public <T> T visit(ASTVisitor<T> visitor){
        return visitor.visitElseClause(this);
    }
}

class WhileStatement extends StatementNode {
    public final ExpressionNode block;
    public final ExpressionNode expression;

    public WhileStatement(ExpressionNode expression, ExpressionNode block) {
        this.expression = expression;
        this.block = block;
    }
    public <T> T visit(ASTVisitor<T> visitor) {
        return visitor.visitWhileStatement(this); 
    }
}

class ForStatement extends StatementNode {
    public final ExpressionNode forInit;
    public final ExpressionNode expression;
    public final ExpressionNode forUpdate;
    public final ExpressionNode block;

    public ForStatement(ExpressionNode forInit, ExpressionNode expression, ExpressionNode forUpdate,
            ExpressionNode block) {
        this.forInit = forInit;
        this.expression = expression;
        this.forUpdate = forUpdate;
        this.block = block;
    }
    public <T> T visit(ASTVisitor<T> visitor) {
        return visitor.visitForStatement(this);
    }
}

class FunctionStatement extends StatementNode {
    public final ExpressionNode returnType;
    public final String identifier;
    public final ExpressionNode parameterList;
    public final ExpressionNode block;

    public FunctionStatement(ExpressionNode returnType, String identifier, ExpressionNode parameterList,
            ExpressionNode block) {
        this.returnType = returnType;
        this.parameterList = parameterList;
        this.identifier = identifier;
        this.block = block;
    }
    public <T> T visit(ASTVisitor<T> visitor){
        return visitor.visitFunctionStatement(this);
    }
}

class BreakStatement extends StatementNode {
    public BreakStatement() {
    }
     public <T> T visit(ASTVisitor<T> visitor) {
        return visitor.visitBreakStatement(this);
    }
}

class ContinueStatement extends StatementNode {
    public ContinueStatement() {
    };
     public <T> T visit(ASTVisitor<T> visitor) {
        return visitor.visitContinueStatement(this);
    }
}

class ReturnStatement extends StatementNode {
    public final ExpressionNode expression;

    public ReturnStatement(ExpressionNode expression) {
        this.expression = expression;
    }
     public <T> T visit(ASTVisitor<T> visitor) {
        return visitor.visitReturnStatement(this);
    }
}
