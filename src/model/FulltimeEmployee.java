package model;

import base.Employee;

public class FulltimeEmployee extends Employee {


    public FulltimeEmployee(int ID, String name, double payment) {
        super(ID, name, payment);
        type = "FE";
    }

    @Override
    public double calculatePayment() {
        return payment;
    }
}
