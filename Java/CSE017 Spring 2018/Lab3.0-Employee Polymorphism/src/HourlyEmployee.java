// -------------------------------------------------------------------------
/**
 * Represents an employee who is paid an hourly wage.
 *
 * @author Tyler Hogue
 * @version 2018.2.2
 */
public class HourlyEmployee extends Employee {

    /**
     * @param name
     *            Name of employee
     * @param payRate
     *            Amount employee makes each hour
     */
    public HourlyEmployee(String name, double payRate) {
        super(name, payRate);
    }

    @Override
    public double weeklyPay() {
        // TODO Auto-generated method stub
        return 40 * this.getPayRate();
    }
    // ~ Instance/static fields ................................................

    // ~ Constructor ...........................................................

    // ~ Methods ...............................................................

}