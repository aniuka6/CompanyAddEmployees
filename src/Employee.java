public class Employee extends Person{
    private double salary;

    public Employee(String  firstname, String lastname, double salary) {
        super(firstname, lastname);
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return super.toString() + "wypłata: " + salary + " zł";
    }
}
