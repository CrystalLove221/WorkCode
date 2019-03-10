
/**
 * @author Tyler Hogue
 * @version 2018.2.4
 *
 */
public interface BundledSubscription {

    /**
     * @return Title of newspaper
     */
    public String getTitle();
    
    /**
     * @param otherNews the newspaper that will be bundles
     * another
     * @return message describing bundle of two newspapers
     */
    public String bundledWith(BundledSubscription otherNews);
}
