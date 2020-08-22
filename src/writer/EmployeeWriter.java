package writer;

import base.Employee;

import java.io.IOException;
import java.util.ArrayList;

public interface EmployeeWriter {
    void writeEmployee(ArrayList<Employee> employees, String filePath) throws IOException;
}
