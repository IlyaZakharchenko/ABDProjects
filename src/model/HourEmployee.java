package model;

import base.Employee;

public class HourEmployee extends Employee {

    private static final double PAYMENT_FACTOR = 20.8 * 8;

    public HourEmployee(int ID, String name, double payment) {
        super(ID, name, payment);
    }

    @Override
    public double calculatePayment() {
        return PAYMENT_FACTOR * payment;
    }
}
