package writer;

import base.BaseEmployeeWriter;
import base.Employee;
import parser.EmployeeParser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileEmployeeWriter extends BaseEmployeeWriter {

    private final EmployeeParser parser;

    public FileEmployeeWriter(EmployeeParser parser) {
        this.parser = parser;
    }

    @Override
    public void writeEmployee(ArrayList<Employee> employeeList, String filePath) throws IOException {
        File file = new File(filePath);
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));

        for (Employee employee : employeeList) {
            writer.write(parser.employeeToString(employee));
            writer.newLine();
        }
        writer.flush();
    }
}
