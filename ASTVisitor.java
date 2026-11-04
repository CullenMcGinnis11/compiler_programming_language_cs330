public interface ASTVisitor<T> {

    // Program
    T visitProgram(ProgramNode node);
    
    // Statements
    T visitVariableDeclaration(VariableDeclarationNode node);
    T visitStatementNodeTail(StatementNodeTail node);
    T visitPokemonLoadStatement(PokemonLoadStatement node);
    T visitSpellDatabaseStatement(SpellDatabaseStatement node);
    T visitShowStatement(ShowStatement node);
    T visitPrintStatement(PrintStatement node);
    T visitIfStatement(IfStatement node);
    T visitElseIfStatement(ElseIfStatement node);
    T visitElseClause(ElseClause node);
    T visitWhileStatement(WhileStatement node);
    T visitForStatement(ForStatement node);
    T visitFunctionStatement(FunctionStatement node);
    T visitBreakStatement(BreakStatement node);
    T visitContinueStatement(ContinueStatement node);
    T visitReturnStatement(ReturnStatement node);


    // Expressions
    T visitBinaryExpression(BinaryExprNode node);
    T visitLValue(LValueNode node);
    T visitLogicalOr(LogicalOr node);
    T visitExpressionExpressionNode(expressionExpressionNode node);
    T visitLogicalOrTail(LogicalOrTail node);
    T visitLogicalAnd(LogicalAnd node);
    T visitLogicalAndTail(LogicalAndTail node);
    T visitEqualityExpression(EqualityExpression node);
    T visitRelationalExpression(RelationalExpression node);
    T visitRelationalExpressionTail(RelationalExpressionTail node);
    T visitAdditiveExpression(AdditiveExpression node);
    T visitAdditiveExpressionTail(AdditiveExpressionTail node);
    T visitMultiplicativeExpression(MultiplicativeExpression node);
    T visitMultiplicativeExpressionTail(MultiplicativeExpressionTail node);
    T visitUnaryExpression(UnaryExpression node);
    T visitPrimaryExpression(PrimaryExpression node);
    T visitLiteralNode(LiteralNode node);
    T visitIntegerNode(IntegerNode node);
    T visitPrimaryExpressionSuffix(PrimaryExpressionSuffix node);
    T visitArgumentList(ArgumentList node);
    T visitArgumentListTail(ArgumentListTail node);
    T visitLValueTail(LValueTail node);
    T visitPokemonField(PokemonField node);
    T visitStatementTail(StatementTail node);
    T visitMoveKeyword(MoveKeyword node);
    T visitStringLiteral(StringLiteral node);
    T visitForInit(ForInit node);
    T visitForUpdate(ForUpdate node);
    T visitReturnType(ReturnType node);
    T visitParamaterList(ParamaterList node);
    T visitParameter(Parameter node);
    
}
