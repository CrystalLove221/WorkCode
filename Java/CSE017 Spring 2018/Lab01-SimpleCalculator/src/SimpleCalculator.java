
/**
 * @author Tyler Hogue
 * @version 2018.1.27
 *
 */
public class SimpleCalculator {

	private double operand1;
	private double operand2;
	private char operator;

	/**
	 * constructor for calculator
	 * @param operand1 first number in expression
	 * @param operand2 second number in expression
	 * @param operator arithmetic oeprator
	 */
	public SimpleCalculator(double operand1, double operand2, char operator) {

		this.operand1 = operand1;
		this.operand2 = operand2;
		this.operator = operator;
	}

	/**
	 * @return value of the first number in expression
	 */
	public double getOperand1() {
		return operand1;
	}

	/**
	 * @param operand1 first number in expression
	 */
	public void setOperand1(double operand1) {
		this.operand1 = operand1;
	}

	/**
	 * @return value of second number in expression
	 */
	public double getOperand2() {
		return operand2;
	}

	/**
	 * @param operand2 second number in expression
	 */
	public void setOperand2(double operand2) {
		this.operand2 = operand2;
	}

	/**
	 * @return aithmetic operator
	 */
	public char getOperator() {
		return operator;
	}

	/**
	 * @param operator aithmetic oepration char
	 */
	public void setOperator(char operator) {
		this.operator = operator;
	}

	/**
	 * @return result of arithmetic
	 */
	public double computeOperation() {
		double res = 0;

		if (operator == '+') {
			res = operand1 + operand2;
		}

		else if (operator == '-') {
			res = operand1 - operand2;
		}

		else if (operator == '*') {
			res = operand1 * operand2;
		}

		else if (operator == '/') {
			res = operand1 / operand2;
		}
		
		else {
			res = -1;
		}

		return res;
	}
}