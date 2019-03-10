
// -------------------------------------------------------------------------
/**
 * Write a one-sentence summary of your class here. Follow it with additional
 * details about its purpose, what abstraction it represents, and how to use it.
 *
 * @author Tyler Hogue
 * @version 2018.2.4
 */
public abstract class Newspaper implements BundledSubscription
{
    private int    idNumber;
    private String title;
    private int    numberCopies;
    private double price;



    
    /**
     * @param idNumber ID of newspaper
     * @param title Title of newspaper
     * @param numberCopies Number of copies sold
     * @param price Price of newspaper ($)
     */
    public Newspaper(int idNumber, String title,
            int numberCopies, double price) {
        this.idNumber = idNumber;
        this.title = title;
        this.numberCopies = numberCopies;
        this.price = price;
    }

    /**
     * @param otherNews Newpaper to be bundled with another
     * @return Message describing news bundle
     * 
     */
    public String bundledWith(BundledSubscription otherNews) {
        return this.getTitle() + " and " + otherNews.getTitle()
                + " subscriptions are bundled.";
    }
    
    /**
     * Abstract method for implementation in other
     * classes.
     * 
     * @return monthly cost of newspapers
     */
    public abstract double monthlyCost();

    // ----------------------------------------------------------
    /**
     * @return the idNumber of the Newspaper
     */
    public int getIdNumber()
    {
        return idNumber;
    }


    // ----------------------------------------------------------
    /**
     * @return the title of the Newspaper
     */
    public String getTitle()
    {
        return title;
    }


    // ----------------------------------------------------------
    /**
     * @return the number of copies of the Newspaper
     */
    public int getNumberCopies()
    {
        return numberCopies;
    }


    // ----------------------------------------------------------
    /**
     * @return the price ($)
     */
    public double getPrice()
    {
        return price;
    }
}
