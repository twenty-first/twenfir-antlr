package io.github.twenty_first.antlr.ast;

public interface AstVisitor<ValueT> {

	ValueT defaultValue();
	ValueT aggregate(ValueT accumulator, ValueT value);

	ValueT visit(AstNode node);
	ValueT visitChildren(AstNode node);

}
