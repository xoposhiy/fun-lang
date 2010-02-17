package ru.usu.cs.fun.back;

public interface EvalObserver {
	void onReduction(Term from, Term to);

	public static EvalObserver nullObserver = new EvalObserver() {
		public void onReduction(Term from, Term to) {
		}
	};
}
