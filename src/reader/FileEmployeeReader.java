package reader;

import base.BaseEmployeeReader;
import base.Employee;
import exception.WrongFormatException;
import parser.EmployeeParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileEmployeeReader extends BaseEmployeeReader {

    private final EmployeeParser parser;

    public FileEmployeeReader(EmployeeParser parser) {
        this.parser = parser;
    }

    @Override
    public ArrayList<Employee> readEmployee(String filePath) throws IOException, WrongFormatException {
        ArrayList<Employee> employees = new ArrayList<>();
        File file = new File(filePath);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        while (true) {
            String line = reader.readLine();
            if (line == null) {
                break;
            }
            employees.add(parser.parseEmployee(line));
        }
        return employees;
    }
}
