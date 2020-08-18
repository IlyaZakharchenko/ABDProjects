package reader;

import base.BaseEmployeeReader;
import base.Employee;
import parser.FileEmployeeParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileEmployeeReader extends BaseEmployeeReader {

    private final FileEmployeeParser PARSER = new FileEmployeeParser();

    @Override
    public ArrayList<Employee> readEmployee(String filePath) {
        ArrayList<Employee> employees = new ArrayList<>();
        try {
            File file = new File(filePath);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                employees.add(PARSER.parseEmployee(line));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return employees;
    }
}
