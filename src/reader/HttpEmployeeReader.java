package reader;

import base.BaseEmployeeReader;
import base.Employee;
import com.google.gson.*;
import model.FulltimeEmployee;
import model.HourEmployee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class HttpEmployeeReader extends BaseEmployeeReader {

    @Override
    public ArrayList<Employee> readEmployee(String filePath) {
        ArrayList<Employee> employees = new ArrayList<>();
        try {
            URL url = new URL(filePath);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoInput(true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            Gson gson = new Gson();
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                } else {
                    if (line.matches(".*\"type\":\"HE\"")) {
                        employees.add(gson.fromJson(line, HourEmployee.class));
                    }
                    else {
                        employees.add(gson.fromJson(line, FulltimeEmployee.class));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return employees;
    }
}
