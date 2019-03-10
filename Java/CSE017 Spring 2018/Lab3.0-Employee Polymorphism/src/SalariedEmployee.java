// -------------------------------------------------------------------------
/**
 * Represents an employee who is paid a yearly salary.
 *
 * @author Tyler Hogue
 * @version 2018.2.2
 */
public class SalariedEmployee extends Employee {

    /**
     * @param name
     *            name of employee
     * @param payRate
     *            amount made epr hour
     */
    public SalariedEmployee(String name, double payRate) {
        super(name, payRate); // inherited from Employee

    }
    // ~ Instance/static fields ................................................

    /**
     * @return Amount of money each week
     * 
     */
    public double weeklyPay() {
        return this.getPayRate();
    }

    /**
     * @param otherParticipant
     *            Another employee in business.
     * @return Message telling who is meeting who in a conference
     */
    public String meetWith(SalariedEmployee otherParticipant) {
        return otherParticipant.getName() + " is joining "
                + this.getName() + " in a conference";

        // ~ Constructor .................

        // ~ Methods .....................

    }
}