
// -------------------------------------------------------------------------
/**
 * Write a one-sentence summary of your class here. Follow it with additional
 * details about its purpose, what abstraction it represents, and how to use it.
 *
 * @author Tyler Hogue
 * @version 2018.2.4
 */
public class WeeklyNewspaper extends Newspaper
{
    // ----------------------------------------------------------
    /**
     * Create a new WeeklyNewspaper object.
     *
     * @param idNumber ID of weekly newspaper
     * @param title Title of weekly newspaper
     * @param numberCopies Number of copies sold
     * @param price Price of each newspaper ($)
     */
    public WeeklyNewspaper(
        int idNumber,
        String title,
        int numberCopies,
        double price)
    {
        super(idNumber, title, numberCopies, price);
    }
    
    /**
     * @param otherNews Newspaper to be bundled with 
     * Weekly newspaper
     * @return Message describing bundle
     */
    public String bundledWith(WeeklyNewspaper otherNews) {
        return otherNews.getTitle() + " subscription is bundled "
                + "with " + this.getTitle() + ".";
    }
    
    /**
     * @return Monthly cost of newspapers
     */
    public double monthlyCost() {
        return 4 * super.getPrice() * super.getNumberCopies();
    }

}
