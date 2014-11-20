/*
 * Created at 19/ 11/ 2014
 * by Karim Tarek
 *
 */

package speech.calculator;

import parsii.tokenizer.ParseException;
import translator.Translator;
import edu.cmu.sphinx.frontend.util.Microphone;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.util.props.ConfigurationManager;

public class SpeechCalculator {

	/**
	 * Parse the given expression and calculates it's value if possible else it
	 * will output the error
	 * 
	 * @param expression
	 *            the string to parse and calculate
	 * @return result of the calculation or syntax error
	 */
	public static String evaluateText(String expression) {
		String result = "";
		Translator translator = new Translator();

		try {
			result = String.valueOf(translator.parseExpression(expression));
		} catch (ParseException e) {
			result = "Syntax Error";
		}

		System.out.println("You said: " + translator.getParsedExpression());
		System.out.println("Answer  : " + result);
		System.out.println("==========================================");

		/*
		 * TESTS
		 */
		// String[] testText = {
		// "7 thousand 3 hundred and 23 plus 3 hundred times 2squared - log 4 hundred 34 to the base 7thousand hundred",
		// "2 * 3 - 1", "store last result", "retrieve last result",
		// "define variable x", "store x 10", " 2 * x + 1 + pi/2",
		// "2 *3 + pi - 1", "pi/2", "4pi+4", "4pi squared", "log 12" };
		//
		// for (String test : testText) {
		// try {
		// result = String.valueOf(translator.parseExpression(test));
		// } catch (ParseException e) {
		// result = "Syntax Error";
		// e.printStackTrace();
		// }
		//
		// System.out.println("You said: " + translator.getParsedExpression());
		// System.out.println("Answer  : " + result);
		// System.out.println("==========================================");
		// }

		return result;
	}

	/**
	 * Keep recognizing and evaluating the users speech until the program is
	 * closed
	 * 
	 */
	public static void main(String[] args) {
		ConfigurationManager cm;

		if (args.length > 0)
			cm = new ConfigurationManager(args[0]);
		else
			cm = new ConfigurationManager(
					SpeechCalculator.class.getResource("calculator.config.xml"));

		Recognizer recognizer = (Recognizer) cm.lookup("recognizer");
		recognizer.allocate();

		// start the microphone or exit if the program if this is not possible
		Microphone microphone = (Microphone) cm.lookup("microphone");
		if (!microphone.startRecording()) {
			System.out.println("Cannot start microphone.");
			recognizer.deallocate();
			System.exit(1);
		}

		System.out.println("/*******  Welcome To Speech Calculator  *******/");
		// loop the recognition until the program exits.
		while (true) {
			System.out.println("Start speaking. Press Ctrl-C to quit.\n");
			Result result = recognizer.recognize();

			if (result != null) {
				String resultText = result.getBestFinalResultNoFiller();
				String answer = evaluateText(resultText);
			} else {
				System.out.println("I can't hear what you said.\n");
			}
		}

	}
}
