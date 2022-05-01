package it.twenfir.antlr.ast;

import java.util.Iterator;

public abstract class BaseAstVisitor<ValueT> implements AstVisitor<ValueT> {


	public BaseAstVisitor() {
	}

	@Override
	public ValueT defaultValue() {
		return null;
	}

	@Override
	public ValueT aggregate(ValueT accumulator, ValueT value) {
		return value;
	}

	@Override
	public ValueT visit(AstNode node) {
		return visitChildren(node);
	}

	@Override
	public ValueT visitChildren(AstNode node) {
		ValueT result = defaultValue();
		Iterator<AstNode> i = node.getChildren();
		while ( i.hasNext() ) {
			AstNode c = i.next();
			result = aggregate(result, c.accept(this));
		}
		return result;
	}

}
