
// -------------------------------------------------------------------------
/**
 * Write a one-sentence summary of your class here. Follow it with additional
 * details about its purpose, what abstraction it represents, and how to use it.
 *
 * @author Tyler Hogue
 * @version 2018.2.4
 */
public class DailyNewspaper extends Newspaper

{
    // ----------------------------------------------------------
    /**
     * Create a new DailyNewspaper object.
     *
     * @param idNumber
     *            ID of newspaper
     * @param title
     *            Title of newspaper
     * @param numberCopies
     *            number of copies sold
     * @param price
     *            Price of each copy ($)
     */
    public DailyNewspaper(int idNumber, String title,
            int numberCopies, double price) {
        super(idNumber, title, numberCopies, price);
    }

    
    /**
     * @return Cost of newspapers per month ($)
     * 
     */
    public double monthlyCost() {
        return 30 * super.getPrice() * super.getNumberCopies();
    }
}
