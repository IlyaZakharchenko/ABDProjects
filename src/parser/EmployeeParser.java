package parser;

import base.Employee;
import exception.WrongFormatException;

public interface EmployeeParser {
    Employee parseEmployee(String str) throws WrongFormatException;

    String employeeToString(Employee employee);
}
