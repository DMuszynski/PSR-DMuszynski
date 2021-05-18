import java.io.Serializable;

public class Driver implements Serializable {

    private static final long serialVersionUID = 1L;

    private String firstName;
    private String lastName;
    private int age;
    private float salary;

    public Driver(String firstName, String lastName, int age, float salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.salary = salary;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public float getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Driver: " + firstName + " " + lastName + " age: " + age + " salary: " + salary;
    }
}
