public class PrintVisitor implements ASTVisitor<Object> {
	// an example for programvisitor
	public boolean instanceOfNull(ExpressionNode node) {
		return node instanceof NullValue;
	}

	// TODO: issues with parenthesis in expression statements
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

	// another example for variable declaration visitor
	@Override
	public Object visitVariableDeclaration(VariableDeclarationNode node) {
		System.out.print("Create a new variable named " + node.name + " type: " + node.type + " value: ");
		node.expression.visit(this);
		System.out.println();
		return null;
	}

	@Override
	public Object visitPokemonLoadStatement(PokemonLoadStatement node) {
		System.out.print("Pokemon created named: " + node.identifier + " built off of file: ");
		node.stringLiteral.visit(this);
		System.out.println();
		return null;
	}

	public Object visitStatementNodeTail(StatementNodeTail node) {
		System.out.print(node.identifier + " has this happen: ");
		node.statementTail.visit(this);
		System.out.println();
		return null;
	}

	public Object visitStatementTail(StatementTail node) {
		node.node1.visit(this);
		node.node2.visit(this);
		// System.out.print(" identifier: " + node.identifier);
		System.out.print(" " + node.identifier);
		node.node3.visit(this);
		return null;
	}

	public Object visitMoveKeyword(MoveKeyword node) {
		System.out.print(" " + node.moveKeyword + " ");
		return null;
	}

	public Object visitLValue(LValueNode node) {
		System.out.print(" " + node.base + " " + node.field);
		return null;
	}

	public Object visitArgumentList(ArgumentList node) {
		node.expressionNode.visit(this);
		node.argumentListTail.visit(this);
		return null;
	}

	public Object visitArgumentListTail(ArgumentListTail node) {
		if (!instanceOfNull(node.expression)) {
			System.out.print(",");
			node.expression.visit(this);
			node.argumentListTail.visit(this);
		}
		return null;
	}

	public Object visitSpellDatabaseStatement(SpellDatabaseStatement node) {
		System.out.print("Spell Database grabbed from:");
		node.stringLiteral.visit(this);
		System.out.println();
		return null;
	}

	public Object visitStringLiteral(StringLiteral node) {
		System.out.print(" " + node.string);
		return null;
	}

	public Object visitExpressionExpressionNode(expressionExpressionNode node) {
		String expression = (String) node.or.visit(this);
		return expression;
	}

	public Object visitLogicalOr(LogicalOr node) {
		node.left.visit(this);
		node.right.visit(this);
		return null;
	}

	public Object visitLogicalOrTail(LogicalOrTail node) {
		if (!instanceOfNull(node.left)) {
			System.out.print(" or");
			node.left.visit(this);
			node.right.visit(this);
		}
		return null;
	}

	public Object visitLogicalAnd(LogicalAnd node) {
		node.left.visit(this);
		node.right.visit(this);
		return null;
	}

	public Object visitLogicalAndTail(LogicalAndTail node) {
		if (!instanceOfNull(node.equalityExpressionNode)) {
			System.out.print(" and");
			node.equalityExpressionNode.visit(this);
			node.logicalAndTail.visit(this);
		}
		return null;
	}

	public Object visitEqualityExpression(EqualityExpression node) {
		node.relationalExpression.visit(this);
		return null;
	}

	public Object visitRelationalExpression(RelationalExpression node) {
		node.additiveExpression.visit(this);
		node.relationalExpressionTail.visit(this);
		return null;
	}

	public Object visitRelationalExpressionTail(RelationalExpressionTail node) {
		System.out.print(" " + node.relation);
		node.additiveExpression.visit(this);
		return null;
	}

	public Object visitAdditiveExpression(AdditiveExpression node) {
		node.multiplicativeExpression.visit(this);
		node.additiveExpressionTail.visit(this);
		return null;
	}

	public Object visitAdditiveExpressionTail(AdditiveExpressionTail node) {
		if (!instanceOfNull(node.multiplicativeExpression)) {
			System.out.print(" +");
			node.multiplicativeExpression.visit(this);
		}
		node.additiveExpressionTail.visit(this);
		return null;
	}

	public Object visitMultiplicativeExpression(MultiplicativeExpression node) {
		node.unaryExpression.visit(this);
		node.multiplicativeExpressionTail.visit(this);
		return null;
	}

	// TODO: these are being tripped even if they are null values, this is because
	// they are still being created in the parse but their values are being assigned
	// null values
	// could either fix this in print visitor by checking instance of, or in parser
	// by changing what an empty tail gets assigned to
	public Object visitMultiplicativeExpressionTail(MultiplicativeExpressionTail node) {
		if (!instanceOfNull(node.unaryExpression)) {
			System.out.print(" " + node.symbol);
			node.unaryExpression.visit(this);
			node.multiplicativeExpressionTail.visit(this);
		}
		return null;
	}

	public Object visitUnaryExpression(UnaryExpression node) {
		System.out.print(" " + node.negativeSymbol);
		node.unaryExpression.visit(this);
		node.primaryExpression.visit(this);
		return null;
	}

	public Object visitPrimaryExpression(PrimaryExpression node) {
		node.expressionNode.visit(this);
		node.literalExpression.visit(this);
		System.out.print(node.identifier);
		node.primaryExpressionSuffix.visit(this);
		return null;
	}

	public Object visitPrimaryExpressionSuffix(PrimaryExpressionSuffix node) {
		node.node.visit(this);
		return null;
	}

	public Object visitLValueTail(LValueTail node) {
		node.pokemonField.visit(this);
		return null;
	}

	public Object visitPokemonField(PokemonField node) {
		System.out.print(node.pokemonField + " "); // TODO: hello look at me!
		return null;
	}

	public Object visitLiteralNode(LiteralNode node) {
		// HELO HELO HELO
		// System.out.print("****Tripped literal Node ****");
		System.out.print(node.value);

		node.expression.visit(this);
		return null;
	}

	public Object visitIntegerNode(IntegerNode node) {
		System.out.print(node.string);
		node.expression.visit(this);
		return null;
	}

	public Object visitPrintStatement(PrintStatement node) {
		System.out.print(" Printing statement: ");
		node.expression.visit(this);
		System.out.println();
		return null;
	}

	public Object visitIfStatement(IfStatement node) {
		System.out.print("if (");
		node.condition.visit(this);
		System.out.print(" then:");
		node.block.visit(this);
		node.elseIfStatements.visit(this);
		node.elseStatements.visit(this);
		System.out.println();
		return null;
	}

	public Object visitElseIfStatement(ElseIfStatement node) {
		System.out.print(" Else if:");
		node.expression.visit(this);
		System.out.print(" Then:");
		node.statements.visit(this);
		return null;
	}

	public Object visitElseClause(ElseClause node) {
		if (!instanceOfNull(node.block)) {
			System.out.print("Else:");
			node.block.visit(this);
		}

		return null;
	}

	public Object visitWhileStatement(WhileStatement node) {
		System.out.print("While: ");
		node.expression.visit(this);
		System.out.print(" Do:");
		node.block.visit(this);
		System.out.println();
		return null;
	}

	public Object visitForStatement(ForStatement node) {
		System.out.print("For: ");
		node.forInit.visit(this);
		System.out.print(" for argument: ");
		node.expression.visit(this);
		System.out.print(" for update ");
		node.forUpdate.visit(this);
		node.block.visit(this);
		return null;
	}

	public Object visitForInit(ForInit node) {
		System.out.print(" int: " + node.identifier + " =");
		node.expression.visit(this);
		return null;
	}

	public Object visitForUpdate(ForUpdate node) {
		System.out.print(node.identifier + "++");
		return null;
	}

	public Object visitBlock(BlockExpression node) {
		System.out.print("");
		node.statements.visit(this);
		return null;
	}

	public Object visitStatements(Statements node) {
		for (StatementNode stmt : node.statements) {
			stmt.visit(this);
		}
		return null;
	}

	public Object visitElseIfList(ElseIfList node) {
		for (StatementNode stmt : node.elseList) {
			stmt.visit(this);
		}
		return null;
	}

	public Object visitFunctionStatement(FunctionStatement node) {
		System.out.print("Function Statement: ");
		node.returnType.visit(this);
		System.out.print(" identifier: " + node.identifier);
		node.parameterList.visit(this);
		node.block.visit(this);
		System.out.println();
		return null;
	}

	public Object visitReturnType(ReturnType node) {
		System.out.print(" " + node.type + " will be returned");
		return null;
	}

	public Object visitParameterList(ParamaterList node) {
		node.type.visit(this);
		node.expressionListTail.visit(this);
		return null;
	}

	public Object visitParameter(Parameter node) {
		System.out.print(" " + node.identifier + " of type: " + node.type);
		return null;
	}

	public Object visitExpressionListTail(ExpressionListTail node) {
		for (ExpressionNode expr : node.expressionList) {
			expr.visit(this);
		}
		return null;
	}

	public Object visitShowStatement(ShowStatement node) {
		System.out.println("Show pokemon named: " + node.identifier);
		return null;
	}

	public Object visitBreakStatement(BreakStatement node) {
		System.out.println("Break Statement Called");
		return null;
	}

	public Object visitContinueStatement(ContinueStatement node) {
		System.out.println("Continue Statement Called");
		return null;
	}

	public Object visitReturnStatement(ReturnStatement node) {
		System.out.print("Return: ");
		node.expression.visit(this);
		System.out.println();
		return null;
	}

	public Object visitNullExpression(NullValue node) {
		return null;
	}

	// ....more visitors for different nodes....
	// about 250 lines of code here

}
