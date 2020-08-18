package parser;

import base.Employee;
import exception.WrongFileFormatException;

public interface EmployeeParser {
    Employee parseEmployee(String str);
    String employeeToString(Employee employee);
}
