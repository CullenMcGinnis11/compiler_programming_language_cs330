import java.util.ArrayList;
import java.util.List;

//    \****__              ____
//      |    *****\_      --/ *\-__
//      /_          (_    ./ ,/----'                             _
//        \__         (_./  /                                   /-|
//           \__           \___----^__                        ___  \
//            _/   _                  \        ______________|  /|--]
//     |    _/  __/ )\"\ _____         *\                    | / |__|
//     |\__/   /    ^ ^       \____      )                    \ /.\ |
//      \___--"                    \_____ )                    '|| ||
//                                       "                     <_'<_'
// Dragon By IronWing
// Cullen McGinnis's Dragon | October-November 2026

public class Parser {
    private int current = 0;
    List<Token> token = new ArrayList<>();
    Token nullToken = (new Token("null", "null"));
    // List of tokens, the output from tokenizer
    private final List<Token> tokens;

    // constructor, takes the list of the tokens as input
    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    private boolean atEnd() { // if youre at end the current will be larger than tokens.size
        return current >= tokens.size();
    }

    private Token peek() { // make sure you are not at end to check tokens
        if (atEnd()) {
            return nullToken;
        }
        return tokens.get(current);
    }

    private Token pop() {
        if (atEnd()) {
            return nullToken;
        }
        Token returnVal = tokens.get(current);
        current++;
        return returnVal;
    }

    private boolean checkTokenType(String expectType) { // TYPE examples, Keyword, String, Operator, Boolean
        return peek().getType().equals(expectType);
    }

    private boolean checkTokenValue(String expectValue) { // create a statment for what you expect out of a token
        return peek().getValue().equals(expectValue);
    }

    private void errorOccuredWrongToken(String expected) {
        throw new IllegalAccessError(
                "Expected " + expected + " instead " + peek().getValue() + " token #" + (current + 1));
    }

    private String identifierExpressionPop() {
        if (checkTokenType("Identifier")) {
            return pop().getValue();
        } else {
            errorOccuredWrongToken("Identifier");
            return "";
        }
    }

    private void match(String expected) {
        if (checkTokenValue(expected)) {
            pop();
        } else {
            errorOccuredWrongToken(expected);
        }
    }

    // Start Point. Parse the program from the first token
    public ProgramNode parseProgram() {

        // init the program node
        ProgramNode program = new ProgramNode();

        // parse the statements, hint: statements, but not a statement. But how?
        while (current < tokens.size()) {
            program.statements.add(parseStatement());
        }

        // return the program node which is also the root of the AST
        return program;
    }

    private List<StatementNode> parseStatements() {
        List<StatementNode> statements = new ArrayList<>();
        while (!atEnd() && !checkTokenValue("}")) {
            statements.add(parseStatement());
        }
        return statements;
    }

    // function to parse each statement
    private StatementNode parseStatement() {
        if (checkTokenValue("int")
                || checkTokenValue("String")
                || checkTokenValue("boolean")) {
            String type = pop().getValue();
            String name = identifierExpressionPop(); // Identifier is a special case where we need to check its an
                                                     // identifier then grab the string
            match("=");
            ExpressionNode expr = parseExpression(); // for expression need to grab the expression node
            match(";");
            return new VariableDeclarationNode(type, name, expr);
        } else if (checkTokenType("Identifier")) {
            String identifier = pop().getValue();
            ExpressionNode statementTailNode = parseStatementTail();
            match(";"); // TODO: THIS IS A HARD MAYBE!
            return new StatementNodeTail(identifier, statementTailNode);
        } else if (checkTokenValue("Pokemon")) {
            pop();
            String identifier = identifierExpressionPop();
            match("=");
            match("Pokemon");
            match("(");
            ExpressionNode stringLiteralNode = parseStringLiteral();
            match(")");
            match(";");
            return new PokemonLoadStatement(identifier, stringLiteralNode);
        } else if (checkTokenValue("spelldatabase")) {
            pop();
            ExpressionNode stringLiteralNode = parseStringLiteral();
            match(";");
            return new SpellDatabaseStatement(stringLiteralNode);

        } else if (checkTokenValue("show")) {
            pop();
            String identifier = identifierExpressionPop();
            match(";");
            return new ShowStatement(identifier);
        } else if (checkTokenValue("print")) {
            pop();
            ExpressionNode expression = parseExpression();
            match(";");
            return new PrintStatement(expression);
        } else if (checkTokenValue("if")) {
            match("if");
            match("(");
            ExpressionNode expression = parseExpression();
            match(")");
            List<StatementNode> block = parseBlock();
            List<StatementNode> elseIfListNode = parseElseIfList();
            StatementNode elseClauseNode = parseElseClause();
            return new IfStatement(expression, block, elseIfListNode, elseClauseNode);
        } else if (checkTokenValue("while")) {
            match("while");
            match("(");
            ExpressionNode expression = parseExpression();
            match(")");
            List<StatementNode> block = parseBlock();
            return new WhileStatement(expression, block);
        } else if (checkTokenValue("for")) {
            match("for");
            match("(");
            ExpressionNode forInitializeNode = parseForInit();
            match(";");
            ExpressionNode expression = parseExpression();
            match(";");
            ExpressionNode forUpdateNode = parseForUpdate();
            match(")");
            List<StatementNode> blockNode = parseBlock();
            return new ForStatement(forInitializeNode, expression, forUpdateNode, blockNode);
        } else if (checkTokenValue("function")) {
            match("function");
            ExpressionNode returnTypeNode = parseReturnType();
            String identifier = identifierExpressionPop();
            match("(");
            ExpressionNode paramaterListNode = parseParamaterList();
            match(")");
            List<StatementNode> block = parseBlock();
            return new FunctionStatement(returnTypeNode, identifier, paramaterListNode, block);
        } else if (checkTokenValue("break")) {
            match("break");
            match(";");
            return new BreakStatement();
        } else if (checkTokenValue("continue")) {
            match("continue");
            match(";");
            return new ContinueStatement();
        } else if (checkTokenValue("return")) {
            match("return");
            ExpressionNode expression = parseExpression();
            match(";");
            return new ReturnStatement(expression);
        }

        throw new IllegalAccessError("Something went wrong while grabbing token, token grabbed was " + peek().getValue()
                + " token was #" + (current + 1));
    }

    private ExpressionNode parseParamaterList() {
        if (checkTokenValue("int")
                || checkTokenValue("String")
                || checkTokenValue("boolean")
                || checkTokenValue("Pokemon")) {
            ExpressionNode parameter = parseParamater();
            List<ExpressionNode> parameterListTailNode = parseParamaterListTail();
            return new ParamaterList(parameter, parameterListTailNode);
        } else {
            return new ParamaterList();
        }
    }

    private ExpressionNode parseParamater() {
        String type = pop().getValue();
        String identifier = identifierExpressionPop();
        return new Parameter(type, identifier);
    }

    private List<ExpressionNode> parseParamaterListTail() {
        List<ExpressionNode> paramaterListTail = new ArrayList<>();
        while (checkTokenValue(",")) {
            match(",");
            ExpressionNode parameter = parseParamater();
            paramaterListTail.add(parameter);
        }
        return paramaterListTail;
    }

    private ExpressionNode parseReturnType() {
        if (checkTokenValue("void")) {
            String voidString = pop().getValue();
            return new ReturnType(voidString);
        } else if (checkTokenValue("int")
                || checkTokenValue("String")
                || checkTokenValue("boolean")
                || checkTokenValue("Pokemon")) {
            String type = pop().getValue();
            return new ReturnType(type);
        } else {
            errorOccuredWrongToken("Void/int/String/boolean/Pokemon");
            return new ReturnType();
        }
    }

    private ExpressionNode parseForUpdate() {
        String identifier = identifierExpressionPop();
        match("++");
        return new ForUpdate(identifier);
    }

    private ExpressionNode parseForInit() {
        match("int");
        String identifier = identifierExpressionPop();
        match("=");
        ExpressionNode expression = parseExpression();
        return new ForInit(identifier, expression);
    }

    private List<StatementNode> parseBlock() {
        match("{");
        List<StatementNode> statements = parseStatements();
        match("}");
        return statements;
    }

    private List<StatementNode> parseElseIfList() {
        List<StatementNode> elseIfList = new ArrayList<>();
        while (checkTokenValue("elseif")) {
            pop();
            match("(");
            ExpressionNode expression = parseExpression();
            match(")");
            List<StatementNode> block = parseBlock();
            StatementNode elseIfStatementNode = new ElseIfStatement(expression, block);
            elseIfList.add(elseIfStatementNode);

        }
        return elseIfList;
    }

    private StatementNode parseElseClause() {
        if (checkTokenValue("else")) {
            pop();
            List<StatementNode> block = parseBlock();
            return new ElseClause(block);
        } else {
            return new ElseClause();
        }
    }

    private ExpressionNode parseStringLiteral() {
        if (checkTokenType("String")) {
            String string = pop().getValue();
            return new StringLiteral(string);
        } else {
            errorOccuredWrongToken(" String ");
            return new StringLiteral("ERROR");
        }

    }

    private ExpressionNode parseStatementTail() {
        if (checkTokenValue("(")) {
            pop();
            ExpressionNode argumentList = parseArgumentList();
            match(")");
            return new StatementTail(argumentList);
        } else if (checkTokenValue("move1") || checkTokenValue("move2")
                || checkTokenValue("move3") || checkTokenValue("move4")) {
            ExpressionNode moveKeywordNode = parseMoveKeyword();
            String identifier = identifierExpressionPop();
            // match(";");
            return new StatementTail(moveKeywordNode, identifier);
        } else {
            ExpressionNode lValueTailNode = parseLValueTail();
            ExpressionNode assignmentOperatorNode = parseAssignmentOperator();
            ExpressionNode expression = parseExpression();
            return new StatementTail(lValueTailNode, assignmentOperatorNode, expression);
        }
    }

    private ExpressionNode parseMoveKeyword() {
        String moveKeyword = pop().getValue();
        return new MoveKeyword(moveKeyword);
    }

    private ExpressionNode parseAssignmentOperator() {
        if (checkTokenValue("=") || checkTokenValue("+=") || checkTokenValue("-=")) {
            String assignmentOperator = pop().getValue();
            return new MoveKeyword(assignmentOperator);
        } else {
            errorOccuredWrongToken("=, +=, -+");
            return new MoveKeyword();
        }
    }

    // function for parsing expression
    private ExpressionNode parseExpression() {
        ExpressionNode logicOr = parseLogicalOr();
        // more codes here!
        return new expressionExpressionNode(logicOr);
    }

    private ExpressionNode parseLogicalOr() {
        ExpressionNode and = parseLogicalAnd();
        ExpressionNode orTail = parseLogicalOrTail();
        return new LogicalOr(and, orTail);
    }

    private ExpressionNode parseLogicalOrTail() {
        if (peek().getValue().equals("||")) {
            pop();
            ExpressionNode and = parseLogicalAnd();
            ExpressionNode orTail = parseLogicalOrTail();
            return new LogicalOrTail(and, orTail);
        } else {
            return new LogicalOrTail();
        }
    }

    private ExpressionNode parseLogicalAnd() {
        ExpressionNode equalityExpression = parseEqualityExpression();
        ExpressionNode andTail = parseLogicalAndTail();
        return new LogicalAnd(equalityExpression, andTail);
    }

    private ExpressionNode parseLogicalAndTail() {
        if (peek().getValue().equals("&&")) {
            pop();
            ExpressionNode equalityExpression = parseEqualityExpression();
            ExpressionNode andTail = parseLogicalAndTail();
            return new LogicalAndTail(equalityExpression, andTail);
        } else {
            return new LogicalAndTail();
        }
    }

    private ExpressionNode parseEqualityExpression() {
        ExpressionNode relationalExpressionNode = parseRelationalExpression();
        return new EqualityExpression(relationalExpressionNode);
    }

    private ExpressionNode parseRelationalExpression() {
        ExpressionNode additiveExpressionNode = parseAdditiveExpression();
        ExpressionNode relationalExpressionTailNode = parseRelationalExpressionTail();
        return new RelationalExpression(additiveExpressionNode, relationalExpressionTailNode);
    }

    private ExpressionNode parseRelationalExpressionTail() {
        if (peek().getValue().equals(">") || peek().getValue().equals("<")) {
            String relation = pop().getValue();
            ExpressionNode additiveExpressionNode = parseAdditiveExpression();
            return new RelationalExpressionTail(relation, additiveExpressionNode);
        } else {
            return new RelationalExpressionTail();
        }
    }

    private ExpressionNode parseAdditiveExpression() {
        ExpressionNode mulitplicativeExpressionNode = parseMultiplicativeExpression();
        ExpressionNode additiveExpressionTailNode = parseAdditiveExpressionTail();
        return new AdditiveExpression(mulitplicativeExpressionNode, additiveExpressionTailNode);
    }

    private ExpressionNode parseAdditiveExpressionTail() {
        if (peek().getValue().equals("+") || peek().getValue().equals("-")) {
            String symbol = pop().getValue();
            ExpressionNode multiplicativeExpressionNode = parseMultiplicativeExpression();
            ExpressionNode additiveExpressionTailNode = parseAdditiveExpressionTail();

            return new AdditiveExpressionTail(symbol, multiplicativeExpressionNode, additiveExpressionTailNode);
        } else {
            return new AdditiveExpressionTail();
        }
    }

    private ExpressionNode parseMultiplicativeExpression() {
        ExpressionNode unaryExpressionNode = parseUnaryExpression();
        ExpressionNode multiplicativeExpressionTailNode = parseMultiplicativeExpressionTail();
        return new MultiplicativeExpression(unaryExpressionNode, multiplicativeExpressionTailNode);
    }

    private ExpressionNode parseMultiplicativeExpressionTail() {
        if (peek().getValue().equals("*") || peek().getValue().equals("/")) {
            String symbol = pop().getValue();
            ExpressionNode unaryExpressionNode = parseUnaryExpression();
            ExpressionNode multiplicativeExpressionTailNode = parseMultiplicativeExpressionTail();

            return new MultiplicativeExpressionTail(symbol, unaryExpressionNode, multiplicativeExpressionTailNode);
        } else {
            return new MultiplicativeExpressionTail();
        }
    }

    private ExpressionNode parseUnaryExpression() {
        if (peek().getValue().equals("!")) {
            String negativeSymbol = pop().getValue();
            ExpressionNode unaryExpressionNode = parseUnaryExpression();
            return new UnaryExpression(negativeSymbol, unaryExpressionNode);
        } else {
            ExpressionNode primaryExpressionNode = parsePrimaryExpression();
            return new UnaryExpression(primaryExpressionNode);
        }
    }

    private ExpressionNode parsePrimaryExpression() {
        if (peek().getValue().equals("false") || peek().getValue().equals("true")
                || peek().getType().equals("String") || peek().getType().equals("Integer")
                || peek().getValue().equals("-")) {
            ExpressionNode literalNode = parseLiteralNode();
            // TODO: POSSIBLE PLACE TO PUT match(";");
            return new PrimaryExpression(literalNode);
        } else if (checkTokenType("Identifier")) { // Other way of checking token type
            String identifier = pop().getValue();
            ExpressionNode primaryExpressionSuffixNode = parsePrimaryExpressionSuffix();
            return new PrimaryExpression(identifier, primaryExpressionSuffixNode);
        } else if (checkTokenValue("(")) {
            pop();
            ExpressionNode expressionNode = parseExpression();
            match(")");
            return new PrimaryExpression(expressionNode, "ExpressionString");
        } else {
            errorOccuredWrongToken("ParsePrimaryExpression");
            return new PrimaryExpression();
        }
    }

    private ExpressionNode parsePrimaryExpressionSuffix() {
        if (checkTokenValue("(")) {
            pop();
            ExpressionNode argumentListNode = parseArgumentList();
            match(")");
            return new PrimaryExpressionSuffix(argumentListNode);
        } else if (checkTokenValue(".")) {
            ExpressionNode lvalueTailNode = parseLValueTail();
            return new PrimaryExpressionSuffix(lvalueTailNode);
        } else {
            return new PrimaryExpressionSuffix();
        }
    }

    private ExpressionNode parseLiteralNode() {
        if (peek().getValue().equals("false") || peek().getValue().equals("true")) {
            String booleanValue = pop().getValue();
            return new LiteralNode(booleanValue);
        } else if (peek().getType().equals("String")) {
            String stringValue = pop().getValue();
            return new LiteralNode(stringValue);
        } else if (peek().getType().equals("Integer") || checkTokenValue("-")) {
            ExpressionNode integer = parseInteger();
            return new LiteralNode(integer);
        } else {
            errorOccuredWrongToken("Integer/String/Boolean");
            return new LiteralNode("Error");
        }
    }

    private ExpressionNode parseInteger() {
        if (checkTokenValue("-")) {
            String sign = pop().getValue();
            ExpressionNode integer = parseInteger();
            return new IntegerNode(sign, integer);
        } else if (checkTokenType("Integer")) {
            String integer = pop().getValue();
            return new IntegerNode(integer);
        } else {
            errorOccuredWrongToken("Integer/-");
            return new IntegerNode("Error");
        }
    }

    private ExpressionNode parseArgumentList() {
        if (checkTokenValue(")")) {
            return new ArgumentList();
        } else {
            ExpressionNode expressionExpressionNode = parseExpression();
            ExpressionNode argumentListTailNode = parseArgumentListTail();
            return new ArgumentList(expressionExpressionNode, argumentListTailNode);
        }
    }

    private ExpressionNode parseArgumentListTail() {
        if (checkTokenValue(",")) {
            pop();
            ExpressionNode expressionNode = parseExpression();
            ExpressionNode argumentListTailNode = parseArgumentListTail();
            return new ArgumentListTail(expressionNode, argumentListTailNode);
        } else {
            return new ArgumentListTail();
        }
    }

    private ExpressionNode parseLValueTail() {
        if (checkTokenValue(".")) {
            pop();
            ExpressionNode pokemonFieldNode = parsePokemonField();
            return new LValueTail(pokemonFieldNode);
        } else {
            return new LValueTail();
        }
    }

    private ExpressionNode parsePokemonField() {
        if (checkTokenValue("hp") || checkTokenValue("attack")
                || checkTokenValue("defend") || checkTokenValue("name")
                || checkTokenValue("type")) {
            String pokemonField = pop().getValue();
            return new PokemonField(pokemonField);
        } else {
            errorOccuredWrongToken(" Pokemon Field Token ");
            return new PokemonField("Error");
        }
    }
}
