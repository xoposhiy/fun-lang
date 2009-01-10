package ru.usu.cs.fun.engine;

import java.util.List;

/**
 * ���� ������������ ��������������� ������, �������������� ����������� �����
 * �������. � ������� ���� ���, ���� � ����� ���������� ����������, �������
 * ����� �������������� � ���� �������.
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
	 * �� ����� ��������������� ���� ������� ������ ��������� �������� scope �
	 * ����� ���� �������� ��� ������� �� ����������. � �� �������� ��������
	 * ��� ����� �������, ����� ������� ����� ���� �����������.
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
