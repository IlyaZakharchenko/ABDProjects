package model;

import base.Employee;

public class HourEmployee extends Employee {

    public HourEmployee(int ID, String name, double payment) {
        super(ID, name, payment);
        type = "HE";
    }

    @Override
    public double calculatePayment() {
        return 20.8 * 8 * payment;
    }
}
