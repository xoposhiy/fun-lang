package ru.usu.cs.fun.back;

// ����� �������������� ������ ���������.
public abstract class Term {

	// �������� �������� ��������� �� ���������� �����.
	// ������� ������� ��������� ���������.
	// � ������ ���� �������� ���������� observer-�.
	public Term eval(Scope scope, EvalObserver observer) {
		return this;
	}

	public Term eval(Scope scope) {
		return eval(scope, EvalObserver.nullObserver);
	}

	// ����������� ������ ���� ��������� ���������
	// ���������� name ��������� value.
	public Term substitute(String name, Term value) {
		return this;
	}

	// substitutor ����� ��������� ���������� �� ��� ����������� ���������.
	// toString ������ ��� ���������� ���������.
	public abstract String toString(TermsSubstitutor substitutor);

	@Override
	public final String toString() {
		return toString(TermsSubstitutor.empty);
	}

	// ���� �� ��� �������� � ������ ��������� ������ ���� � ������,
	// ���� �� ���������� � �������� ������� (�.�. ����� ����� Application-�)
	// ��������, ������������ "fun(x) y" ����� ��������� � ������
	// � ��������� "(fun(x) y) 5"
	// ����� ��� ����� ����� ������������������� ��� "fun(x) (y 5)".
	public boolean parenthesizeAsFun() {
		return false;
	}

	// ���� �� ��� �������� � ������ ��������� ������ ���� � ������,
	// ���� �� ���������� � �������� ��������� (�.�. ������ ����� Application-�)
	// ��������, ������������ "y z" ����� ��������� � ������
	// � ��������� "x (y z)"
	// ����� ��� ����� ����� ������������������� ��� "(x y) z".
	public boolean parenthesizeAsArg() {
		return false;
	}

	// ������� ������� ������� ���� ��� �������, ������� � ��� �������� arg.
	// ���� ���������� �� �������� �� � ����� ��������,
	// �� ����� ���������� null, ��� �������� "���������� ����������".
	public Term apply(Term arg, Scope scope, EvalObserver observer) {
		return null;
	}

	// ������ �������� _|_ � ��������������� �� ������.
	public static Term bottom = new Bottom();
}
