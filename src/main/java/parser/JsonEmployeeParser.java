package parser;

import base.Employee;
import com.google.gson.Gson;
import model.FulltimeEmployee;
import model.HourEmployee;

public class JsonEmployeeParser implements EmployeeParser {

    private static final Gson GSON = new Gson();

    @Override
    public Employee parseEmployee(String str) {
        if (str.matches(".*\"type\": \"" + HourEmployee.class.getSimpleName() + "\"")) {
            return GSON.fromJson(str, HourEmployee.class);
        }
        return GSON.fromJson(str, FulltimeEmployee.class);
    }

    @Override
    public String employeeToString(Employee employee) {
        return GSON.toJson(employee);
    }
}
