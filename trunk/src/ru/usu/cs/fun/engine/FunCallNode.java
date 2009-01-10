package ru.usu.cs.fun.engine;

import java.util.ArrayList;
import java.util.List;

/**
 * ”зел абстрактного синтаксического дерева, представл€ющий вызов функции.
 * 
 * @author pe
 */
public class FunCallNode implements ASTNode {

	private final String funName;
	private final List<FunCallNode> args;

	public FunCallNode(String funName) {
		this(funName, new ArrayList<FunCallNode>());
	}

	public FunCallNode(String funName, List<FunCallNode> actualParams) {
		this.funName = funName;
		this.args = actualParams;
	}

	public Fun constructFun(Scope scope) {
		Fun f = scope.findFun(funName);
		if (f == null)
			throw new RuntimeException("fun " + funName + " is undefined");
		for (FunCallNode node : args) {
			f = f.curry(0, node.constructFun(scope));
		}
		return f;
	}

	@Override
	public Object execute(Scope scope) {
		return constructFun(scope).execute(scope);
	}

	public List<FunCallNode> getArgs() {
		return args;
	}

	public String getFunName() {
		return funName;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(funName);
		if (args.size() != 0) {
			sb.append("(");
			for (ASTNode arg : args) {
				sb.append(arg.toString()).append(" ");
			}
			sb.delete(sb.length() - 1, sb.length());
			sb.append(")");
		}
		return sb.toString();
	}
}
