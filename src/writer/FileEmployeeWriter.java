package writer;

import base.BaseEmployeeWriter;
import base.Employee;
import parser.FileEmployeeParser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileEmployeeWriter extends BaseEmployeeWriter {

    private final FileEmployeeParser PARSER = new FileEmployeeParser();

    @Override
    public void writeEmployee(ArrayList<Employee> employeeList, String filePath) {
        try {
            File file = new File(filePath);
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));

            for (Employee employee : employeeList) {
                writer.write(PARSER.employeeToString(employee));
                writer.newLine();
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
