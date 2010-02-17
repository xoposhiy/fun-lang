package ru.usu.cs.fun.front;

import java.util.List;

public class MuxRecognizer implements Recognizer {

	private final Recognizer[] recognizers;
	private RecognizerState state;
	private Recognizer recognizer;

	public MuxRecognizer(List<Recognizer> alive) {
		recognizers = new Recognizer[alive.size()];
		alive.toArray(recognizers);
	}

	@Override
	public RecognizerState currentState() {
		return state;
	}

	@Override
	public String getLexemeType() {
		return recognizer.getLexemeType();
	}

	@Override
	public void reset() {
		recognizer = null;
		state = RecognizerState.alive;
		for (Recognizer r : recognizers)
			r.reset();
	}

	@Override
	public RecognizerState takeChar(char ch) {
		state = RecognizerState.dead;
		for (Recognizer r : recognizers) {
			if (r.currentState() == RecognizerState.dead)
				continue;
			RecognizerState rstate = r.takeChar(ch);
			if (rstate == RecognizerState.finished) {
				recognizer = r;
				state = RecognizerState.finished;
				break;
			} else if (rstate == RecognizerState.alive)
				state = RecognizerState.alive;
		}
		return state;
	}

	@Override
	public boolean invisible() {
		return recognizer.invisible();
	}
}
