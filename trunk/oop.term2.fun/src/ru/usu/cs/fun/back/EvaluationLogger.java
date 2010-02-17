package ru.usu.cs.fun.back;

import java.util.HashMap;
import java.util.Map;

public class EvaluationLogger implements EvalObserver, TermsSubstitutor {
	private final Map<Term, Term> progress = new HashMap<Term, Term>();
	private final Term term;

	public EvaluationLogger(Term term) {
		super();
		this.term = term;
		System.out.println(term.toString(this));
	}

	public void onReduction(Term from, Term to) {
		if (from == to)
			return;
		progress.put(from, to);
		System.out.println(substitute(term).toString(this));
	}

	@Override
	public Term substitute(Term originalTerm) {
		Term result = originalTerm;
		while (progress.containsKey(result))
			result = progress.get(result);
		return result;
	}
}
