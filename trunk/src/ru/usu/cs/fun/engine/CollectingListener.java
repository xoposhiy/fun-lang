package ru.usu.cs.fun.engine;

import java.util.ArrayList;
import java.util.List;

public class CollectingListener implements ParsingListener {
	private final List<ASTNode> nodes = new ArrayList<ASTNode>();

	public List<ASTNode> getNodes() {
		return nodes;
	}

	@Override
	public void parsedNode(ASTNode node) {
		nodes.add(node);
	}

}
