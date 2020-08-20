package reader;

import base.Employee;
import exception.WrongFormatException;

import java.io.IOException;
import java.util.ArrayList;

public interface EmployeeReader {
    ArrayList<Employee> readEmployee(String filePath) throws IOException, WrongFormatException;
}
