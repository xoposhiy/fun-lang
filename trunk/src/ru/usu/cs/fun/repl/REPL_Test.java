package ru.usu.cs.fun.repl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;

import junit.framework.TestCase;

import org.jmock.Expectations;
import org.jmock.Mockery;

public class REPL_Test extends TestCase {
	public void testSimple() throws Exception {
		checkExternalTestCase("simple");
	}

	private void checkExternalTestCase(String testssetName) throws Exception {
		InputStream stream = getClass().getResourceAsStream(
				"test-" + testssetName + ".txt");
		checkTestCase(new InputStreamReader(stream));
	}

	private void checkTestCase(String testcase) throws Exception {
		checkTestCase(new StringReader(testcase));
	}

	private void checkTestCase(Reader reader) throws IOException {
		BufferedReader r = new BufferedReader(reader);
		String line;
		Mockery m = new Mockery();
		final FunConsole console = m.mock(FunConsole.class);
		while ((line = r.readLine()) != null) {
			if (line.startsWith("\t")) {
				final String l = line.substring(1);
				m.checking(new Expectations() {
					{
						oneOf(console).readLine();
						will(returnValue(l));
					}
				});
			} else {
				if (line.endsWith("$"))
					line = line.substring(0, line.length() - 1) + "\r\n";
				final String l = line;
				m.checking(new Expectations() {
					{
						oneOf(console).write(l);
					}
				});
			}
		}
		m.checking(new Expectations() {
			{
				oneOf(console).readLine();
				will(returnValue(null));
			}
		});
		new REPL(console).run();
		m.assertIsSatisfied();
	}

	public void testEmptyInput() throws Exception {
		checkTestCase("fun>");
	}

	public void testMultilineInput() throws Exception {
		checkExternalTestCase("multilineInput");
	}

	public void testMultipleCalculations() throws Exception {
		checkExternalTestCase("multipleCalcs");
	}

}
