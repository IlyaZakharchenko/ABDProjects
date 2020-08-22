package model;

import base.Employee;

public class FulltimeEmployee extends Employee {


    public FulltimeEmployee(int ID, String name, double payment) {
        super(ID, name, payment);
    }

    @Override
    public double calculatePayment() {
        return payment;
    }
}
