package reader;

import base.BaseEmployeeReader;
import base.Employee;
import exception.WrongFormatException;
import parser.EmployeeParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class HttpEmployeeReader extends BaseEmployeeReader {

    private final EmployeeParser parser;

    public HttpEmployeeReader(EmployeeParser parser) {
        this.parser = parser;
    }

    @Override
    public ArrayList<Employee> readEmployee(String filePath) throws IOException, WrongFormatException {
        ArrayList<Employee> employees = new ArrayList<>();
        URL url = new URL(filePath);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoInput(true);
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
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
