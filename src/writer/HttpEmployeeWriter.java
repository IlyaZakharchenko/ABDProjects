package writer;

import base.BaseEmployeeWriter;
import base.Employee;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class HttpEmployeeWriter extends BaseEmployeeWriter {
    private final String BASE_URL = "https://raw.githubusercontent.com/IlyaZakharchenko/ABDProjects/master/employee.json";

    @Override
    public void writeEmployee(ArrayList<Employee> employees, String filePath) {
        try {
            URL url = new URL(BASE_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
            writer.write("{\"name\": \"Upendra\", \"job\": \"Programmer\"}");
            writer.flush();
            try(BufferedReader br = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
                StringBuilder response = new StringBuilder();
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println(response.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
