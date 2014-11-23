/*
 * Created at 19/ 11/ 2014
 * by Karim Tarek
 *
 */

package speech.calculator;

import javax.swing.JTextArea;

import parsii.tokenizer.ParseException;
import translator.Translator;
import edu.cmu.sphinx.frontend.util.Microphone;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.util.props.ConfigurationManager;

public class SpeechCalculator extends Thread {
	private Recognizer recognizer;
	private boolean recording;
	private String parsedExpression;

	JTextArea resultArea, expressionArea;

	/**
	 * Parse the given expression and calculates it's value if possible else it
	 * will output the error
	 * 
	 * @param expression
	 *            the string to parse and calculate
	 * @return result of the calculation or syntax error
	 */
	public String evaluateText(String expression) {
		String result = "";
		Translator translator = new Translator();

		try {
			result = String.valueOf(translator.parseExpression(expression));
		} catch (ParseException e) {
			result = "Syntax Error";
		}

		parsedExpression = translator.getParsedExpression();
		System.out.println("You said: " + expression);
		System.out.println("Answer  : " + result);
		System.out.println("==========================================");
		return result;
	}

	public void run() {
		System.out.println("/*******  Welcome To Speech Calculator  *******/");
		while (recording) {
			System.out.println("Start speaking. Press Ctrl-C to quit.\n");
			Result result = recognizer.recognize();

			if (result != null) {
				String resultText = result.getBestFinalResultNoFiller();
				String answer = evaluateText(resultText);
				resultArea.setText(answer);
				expressionArea.setText(parsedExpression);
			} else {
				System.out.println("I can't hear what you said.\n");
			}
		}
	}

	public void stopRecording() {
		recording = false;
	}

	public void startRecording() {
		recording = true;
	}

	public void initialize(JTextArea txtrresult, JTextArea textarea) {
		System.out.println("Loading....");
		recording = false;
		resultArea = txtrresult;
		expressionArea = textarea;
		ConfigurationManager cm;
		cm = new ConfigurationManager(
				SpeechCalculator.class.getResource("calculator.config.xml"));

		recognizer = (Recognizer) cm.lookup("recognizer");
		recognizer.allocate();

		// start the microphone or exit if the program if this is not possible
		Microphone microphone = (Microphone) cm.lookup("microphone");
		if (!microphone.startRecording()) {
			System.out.println("Cannot start microphone.");
			recognizer.deallocate();
			System.exit(1);
		}

		System.out.println("Loaded");
	}

}
