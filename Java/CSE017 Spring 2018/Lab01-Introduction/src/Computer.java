
/**
 * @author Tyler Hogue
 * @version 2018.1.26
 *
 */
public class Computer {

	private String processor;
	private int numCores;
	private double processorSpeed;
	
	/**
	 * @param processor name of processor
	 * @param numCores number of cores
	 * @param processorSpeed speed of processor
	 */
	public Computer(String processor, int numCores, double processorSpeed) {
	
		
		this.setProcessor(processor);
		this.setNumCores(numCores);
		this.setProcessorSpeed(processorSpeed);
		
	}

	/**
	 * @return processor name
	 */
	public String getProcessor() {
		
		return processor;
	}

	/**
	 * @param processor set the name of the processor
	 */
	public void setProcessor(String processor) {
		this.processor = processor;
	}

	/**
	 * @return number of cores
	 */
	public int getNumCores() {
		return numCores;
	}

	/**
	 * @param numCores the number of cores
	 */
	public void setNumCores(int numCores) {
		this.numCores = numCores;
	}

	/**
	 * @return processor speed in GHz
	 */
	public double getProcessorSpeed() {
		return processorSpeed;
	}

	/**
	 * @param processorSpeed processor speed in GHz
	 */
	public void setProcessorSpeed(double processorSpeed) {
		this.processorSpeed = processorSpeed;
	}
	
	/**
	 * @return total power of processor
	 */
	public double computePower() {
		return numCores * processorSpeed;
	}

	/**
	 * @return string based on data given by methods
	 */
	public String toString() {
		String res = "";
		res = processor + ", " + numCores; 
		res += " cores at " + processorSpeed + " GHz";
		
		return res;
	}
}
