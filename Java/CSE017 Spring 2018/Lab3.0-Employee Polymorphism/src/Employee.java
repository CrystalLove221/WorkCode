// -------------------------------------------------------------------------
/**
 * Represents an employee in a company or business.
 *
 * @author Tyler Hogue
 * @version 2018.2.2
 */
public abstract class Employee implements MeetingParticipant {
    // ~ Instance/static fields ................................................

    private String name; // The employee's name.
    private double payRate; // The employee's pay rate.

    // ~ Constructor ...........................................................
    /**
     * @param name Name of employee
     * @param payRate Hourly rate
     */
    public Employee(String name, double payRate) {
        this.name = name;
        this.payRate = payRate;
    }

    // ~ Methods ...............................................................
    /**
     * @return amount of money made per week
     */
    public abstract double weeklyPay(); //abstract method implemented elsewhere

    // ----------------------------------------------------------
    /**
     * Gets the employee's name.
     * 
     * @return the employee's name
     */
    public String getName() {
        return name;
    }

    // ----------------------------------------------------------
    /**
     * Gets the pay rate.
     * 
     * @return the pay rate
     */
    public double getPayRate() {
        return payRate;
    }

    /**
     * @param otherParticipant
     *            employee that will be meeting other employee
     * @return Message telling who is meeting who
     * 
     */
    public String meetWith(MeetingParticipant otherParticipant) {
        return this.getName() + " is meeting with "
                + otherParticipant.getName();
    }
}
