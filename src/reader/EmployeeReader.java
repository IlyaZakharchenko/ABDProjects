package reader;

import base.Employee;

import java.util.ArrayList;

public interface EmployeeReader {
    ArrayList<Employee> readEmployee(String filePath);
}
