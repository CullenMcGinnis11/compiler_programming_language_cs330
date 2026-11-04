public abstract class ASTNode {
	abstract <T> T visit(ASTVisitor<T> visitor);
}

