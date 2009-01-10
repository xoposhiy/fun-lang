package ru.usu.cs.fun.engine;

import java.util.List;

/**
 * Узел абстрактного синтаксического дерева, представляющий определение новой
 * функции. У функции есть имя, тело и имена формальных аргументов, который
 * могут использоваться в теле функции.
 * 
 * @author pe
 */
public class FunDefNode implements ASTNode {

	private final String funName;
	private final List<String> args;
	private final FunCallNode body;

	public FunDefNode(String funName, List<String> args, FunCallNode body) {
		this.funName = funName;
		this.args = args;
		this.body = body;
	}

	/**
	 * На время конструирования тела функции создаём временный дочерний scope и
	 * кладём туда заглушки для каждого из аргументов. И не забываем заглушку
	 * для самой функции, чтобы функция могла быть рекурсивной.
	 */
	@Override
	public Object execute(Scope scope) {
		Scope funScope = new ChildScope(scope);
		for (String arg : args) {
			funScope.addFun(arg, new RuntimeFun(arg));
		}
		funScope.addFun(funName, new RuntimeFun(funName));
		scope.addFun(funName, new UserDefinedFun(funName, args, body
				.constructFun(funScope)));
		return true;
	}

	public FunCallNode getBody() {
		return body;
	}

	public String getFunName() {
		return funName;
	}

	public List<String> getParams() {
		return args;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("fun ");
		sb.append(funName);
		if (args.size() != 0) {
			sb.append("(");
			for (String arg : args) {
				sb.append(arg).append(" ");
			}
			sb.delete(sb.length() - 1, sb.length());
			sb.append(")");
		}
		sb.append(" ").append(body.toString());
		return sb.toString();
	}

}
