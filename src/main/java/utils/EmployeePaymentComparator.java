package utils;

import base.Employee;

import java.util.Comparator;

public class EmployeePaymentComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee o1, Employee o2) {
        if (o1.calculatePayment() > o2.calculatePayment()) {
            return -1;
        } else if (o1.calculatePayment() < o2.calculatePayment()) {
            return 1;
        } else {
            return o1.getName().compareTo(o2.getName());
        }
    }
}
