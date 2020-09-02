package base;

public abstract class Employee {
    protected double payment;
    private int id;
    private String name;
    private final String type = getClass().getSimpleName();

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

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    @Override
    public String toString() {
        return type + "{" +
                "payment=" + payment +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public abstract double calculatePayment();
}
