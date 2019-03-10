import java.util.Comparator;

/**
 * @author Tyler Hogue
 * @version 3.26.2018
 *
 */
public class Student 
    implements Comparable<Student> {

    
    private String name;
    private int id;
    private double gpa;
    
    /**
     * @param name name of student
     * @param id student id
     * @param gpa atudent gpa
     */
    public Student(String name, int id, double gpa) {
        this.name = name;
        this.id = id;
        this.gpa = gpa;
    }
    
    @Override
    public int compareTo(Student s) {
        
        if (this == s) {
            return 0;
        }
        
        if (this.getGPA() == s.getGPA()) {
            if (this.getID() == s.getID()) {
               return this.getName().compareTo(s.getName());
            }
            
            else if (this.getID() < s.getID()) {
                return -1;
            }
            
            else {
                return 1;
            }
        }
        
        else if (this.getGPA() < s.getGPA()) {
            return -1;
        }
        
        return 1;
    }
    
    /**
     * @return name of student
     */
    public String getName() {
        return name;
    }
    
    /**
     * @return id of student
     */
    public int getID() {
        return id;
    }
    
    /**
     * @return gpa of student
     */
    public double getGPA() {
        return gpa;
    }
    
    /**
     * @param name new name for student
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * @param id new id for student
     */
    public void setID(int id) {
        this.id = id;
    }
    
    /**
     * @param gpa new gpa for student
     */
    public void setGPA(double gpa) {
        this.gpa = gpa;
    }
    
    /**
     * @return whether the student is placed
     * on Dean's List
     */
    public boolean isInDeansList() {
        if (this.getGPA() >= 3.5) {
            return true;
        }
        
        else if (this.getGPA() > 2.0 && this.getGPA() < 3.5) {
            return false;
        }
        
        throw new NotInDeansListException("Not in Dean's List");
    }
    
    /**
     * @return Whteher the student
     * is on academic probation
     */
    public boolean isInAcademicProbation() {
        if (this.getGPA() <= 2.0) {
            return true;
        }
        
        else if (this.getGPA() > 2.0 && this.getGPA() < 3.5) {
            return false;
        }
        
        throw new NotInAcademicProbationException("Not in Probation");
    }
    
 // ---------------------------------

    /**

     * Comparator for students objects.

     * @return comparator

     */


    public static Comparator<Student> studentOrder() {

        return new Comparator<Student>() {

            @Override

            public int compare(Student o1, Student o2) {

                return o1.compareTo(o2);

            }
        };
    }
}