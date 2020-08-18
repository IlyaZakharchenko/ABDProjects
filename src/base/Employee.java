package base;

public abstract class Employee {
    protected String type;
    protected double payment;
    private int id;
    private String name;

    public Employee(int id, String name, double payment) {
        this.id = id;
        this.name = name;
        this.payment = payment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", payment=" + payment +
                '}';
    }

    public abstract double calculatePayment();
}
