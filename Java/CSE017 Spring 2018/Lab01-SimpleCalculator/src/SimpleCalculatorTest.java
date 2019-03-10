
/**
 * @author Tyler Hogue
 * @version 2018.1.27
 *
 */
public class SimpleCalculatorTest
	extends student.TestCase {
	private SimpleCalculator calc;


	/**
     * Sets up the test fixture.
     * Called before every test case method.
     */
	public void setUp() {
		calc = new SimpleCalculator(2.1, 2.1, '+');
	}

	/**
	 * test return value of first number of arithmetic
	 */
	public void testGetOperand1() {
		assertEquals(2.1, calc.getOperand1(), 0.01);
	}

	/**
	 * test setting value of first number
	 */
	public void testSetOperand1() {
		calc.setOperand1(3);
		assertEquals(3, calc.getOperand1(), 0.01);
	}

	/**
	 * test return value of second number
	 */
	public void testGetOperand2() {
		assertEquals(2.1, calc.getOperand2(), 0.01);
	}

	/**
	 * test setting the second number
	 */
	public void testSetOperand2() {
		calc.setOperand2(41.5);
		assertEquals(41.5, calc.getOperand2(), 0.01);
	}

	/**
	 * test return of operator
	 */
	public void testGetOperator() {
		assertEquals('+', calc.getOperator());
	}

	/**
	 * test setting the arithmetic operator
	 */
	public void testSetOperator() {
		calc.setOperator('/');
		assertEquals('/', calc.getOperator());
	}

	/**
	 * test the value of arithmetic
	 */
	public void testComputeOperation() {
		calc.setOperator('+');
		assertEquals(4.2, calc.computeOperation(), 0.01);

		calc.setOperator('-');
		assertEquals(0, calc.computeOperation(), 0.01);

		calc.setOperator('*');
		assertEquals(4.41, calc.computeOperation(), 0.01);

		calc.setOperator('/');
		assertEquals(1, calc.computeOperation(), 0.01);
		
		calc.setOperator('[');
		assertEquals(-1, calc.computeOperation(), 0.01);
	}
}