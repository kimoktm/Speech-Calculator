/*
 * Created at 19/ 11/ 2014
 * by Karim Tarek
 *
 */

package translator;

import java.util.HashMap;
import java.util.Map;
import parsii.eval.Expression;
import parsii.eval.Parser;
import parsii.eval.Scope;
import parsii.eval.Variable;
import parsii.tokenizer.ParseException;

public class Translator {

	/*
	 * General Separators
	 */
	private String andSeparator = " and";
	private String decimalSeparator = "point";
	private String logBaseSeparator = "to the base";

	/*
	 * Math Separators
	 */
	private String addSeparator = "plus";
	private String subSeparator = "minus";
	private String multSeparator = "times";
	private String dvdSeparator = "over";
	private String logSeparator = "log";
	private String powerSeparator = "power";
	private String squaredSeparator = "squared";
	private String cubedSeparator = "cubed";

	/*
	 * Variables Separators
	 */
	private String defineVariable = "define variable";
	private String assignVariable = "store";
	private String msResult = "last result";

	/*
	 * Delimiters and Number Bases
	 */
	private final String WITH_DELIMITER = "((?<=%1$s)|(?=%1$s))";
	private final String[] NUMBER_BASES = { "tens", "hundred", "thousand" };

	/*
	 * Global Variables
	 */
	private double LastResult;
	private String parsedExpression;
	private Scope scope;
	private Map<String, Variable> currentVariables;

	/**
	 * Creates a new translator
	 * 
	 * The constructor initializes the result, Math parser and variables Hash
	 * map
	 * 
	 */
	public Translator() {
		LastResult = 0.0;
		parsedExpression = "";
		scope = Scope.create();
		currentVariables = new HashMap<String, Variable>();
	}

	/**
	 * Get the last value calculated
	 * 
	 * @return result of the last calculation
	 */
	public double getLastValue() {
		return LastResult;
	}

	/**
	 * Get the last value calculated
	 * 
	 * @return result of the last calculation
	 */
	public String getParsedExpression() {
		return parsedExpression;
	}

	/**
	 * Parse a given input into a mathematical expression or define a new
	 * variable or assign value to a variable
	 * 
	 * @param expression
	 *            the string to check
	 * @return result of the induced calculation
	 */
	public double parseExpression(String expression) throws ParseException {
		if (expression.contains(msResult)) {
			parsedExpression = expression;
			return LastResult;
		} else if (expression.contains(defineVariable)) {
			defineVariable(expression);
		} else if (expression.contains(assignVariable)) {
			assignVariable(expression);
		} else {
			LastResult = calculateExpression(expression);
		}
		return LastResult;
	}

	/**
	 * Evaluate a mathematical expression and calculates its value
	 * 
	 * @param expression
	 *            the string to evaluate
	 * @return result of the calculation
	 */
	private double calculateExpression(String expression) throws ParseException {
		expression = numberFormat(expression);
		String[] terms = expression.split(String.format(WITH_DELIMITER,
				"-|\\+|\\*|\\/"));
		parsedExpression = termsParser(terms, NUMBER_BASES);
		Expression expr = Parser.parse(parsedExpression, scope);
		LastResult = expr.evaluate();
		return LastResult;
	}

	/**
	 * Define a variable with name
	 * 
	 * @param expression
	 *            the string to evaluate containing the variable name
	 */
	private void defineVariable(String expression) {
		String varName = expression.replace(defineVariable, "").replaceAll(
				"\\s+", "");
		currentVariables.put(varName, scope.getVariable(varName));
		parsedExpression = expression;
	}

	/**
	 * Assign a given value to a variable with name
	 * 
	 * @param expression
	 *            the string to evaluate containing the variable name and the
	 *            value
	 */
	private void assignVariable(String expression) {
		String[] terms = expression.split("\\s");
		if (terms.length >= 3) {
			double varValue = Double.parseDouble(terms[2]);
			currentVariables.get(terms[1]).setValue(varValue);
		}
		parsedExpression = expression;
	}

	/**
	 * Format string from words to numbers and symbols
	 * 
	 * @param expression
	 *            the string to format
	 * @return result of the formating
	 */
	public String numberFormat(String expression) {
		expression = expression.toLowerCase();
		expression = expression.replace(addSeparator, "+");
		expression = expression.replace(subSeparator, "-");
		expression = expression.replace(multSeparator, "*");
		expression = expression.replace(dvdSeparator, "/");
		expression = expression.replace(powerSeparator, "^");
		expression = expression.replace(squaredSeparator, "^2");
		expression = expression.replace(cubedSeparator, "^3");
		expression = expression.replace(logBaseSeparator, ",");
		expression = expression.replace(decimalSeparator, ".");
		expression = expression.replaceAll(andSeparator, "");
		expression = expression.replaceAll("\\s+", "");
		return expression;
	}

	/**
	 * Split an expression into terms, formating each based on the base
	 * 
	 * @param terms
	 *            the list of terms
	 * @param bases
	 *            the list of bases
	 * @return result of parsing
	 */
	public String termsParser(String[] terms, String[] bases) {
		String expression = "";
		for (String term : terms) {
			if (term.contains(logSeparator)) {
				term = term.replace(logSeparator, "");
				term = logSeparator
						+ "("
						+ termsParser(
								term.split(String.format(WITH_DELIMITER, ",")),
								bases) + ")";
			}
			expression = expression + " " + baseParser(term, bases);
		}
		return expression;
	}

	/**
	 * Parse a term based on it's base
	 * 
	 * @param term
	 *            the string to evaluate
	 * @param bases
	 *            the list of bases
	 * @return result of parsing
	 */
	public String baseParser(String term, String[] bases) {
		for (int j = 0; j < bases.length; j++)
			if (term.endsWith(bases[j]))
				term = term.replace(bases[j],
						new String(new char[j + 1]).replace("\0", "0"));
			else
				term = term.replace(bases[j], "");
		return term;
	}
}
