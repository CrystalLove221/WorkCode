
/**
 * @author Tyler Hogue
 * @version 2018.4.23
 *
 */
public class Contact implements Comparable<Contact> {

    private String firstName;
    private String lastName;
    private String phone;
    
    /**
     * @param first first name of contact
     * @param last last name of contact
     * @param phone phone number of contact
     */
    public Contact(String first, String last, String phone) {
        firstName = first;
        lastName = last;
        this.phone = phone;
    }
    
    /**
     * @param c contact to compare to
     * @return integer representing comparison
     * (-1, this < c; +1: this > c; 0: this == c)
     * 
     */
    public int compareTo(Contact c) {
        
        if (this.lastName.compareTo(c.lastName) < 0) {
            return -1;
        }
        
        else if (this.lastName.compareTo(c.lastName) > 0) {
            return 1;
        }
        
        else {
            if (this.firstName.compareTo(c.firstName) < 0) {
                return -1;
            }
            
            if (this.lastName.compareTo(c.lastName) > 0) {
                return 1;
            }
            
            else {
                if (this.phone.compareTo(c.phone) < 0) {
                    return -1;
                }
                
                if (this.phone.compareTo(c.phone) > 0) {
                    return 1;
                }
            }
        }
        
        return 0;
    }
    
    /**
     * @return first name of contact
     */
    public String getFirst() {
        return firstName;
    }
    
    /**
     * @return last name of contact
     */
    public String getLast() {
        return lastName;
    }
    
    /**
     * @return phone number of contact
     */
    public String getPhone() {
        return phone;
    }
    
    /**
     * @param first first name
     */
    public void setFirst(String first) {
        firstName = first;
    }
    
    /**
     * @param last last name
     */
    public void setLast(String last) {
        lastName = last;
    }
    
    /**
     * @param phone phone number
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
}