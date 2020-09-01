package parser;

import base.BaseEmployeeParser;
import base.Employee;
import exception.WrongFormatException;
import model.FulltimeEmployee;
import model.HourEmployee;

public class FileEmployeeParser extends BaseEmployeeParser {

    protected final String SEPARATOR = "|";

    @Override
    public Employee parseEmployee(String str) throws WrongFormatException {
        String[] strSplit = str.split("\\" + SEPARATOR);
        if (strSplit.length < 4) {
            throw new WrongFormatException();
        }
        if (strSplit[0].equals(HourEmployee.class.getSimpleName())) {
            return new HourEmployee(Integer.parseInt(strSplit[1]), strSplit[2], Double.parseDouble(strSplit[3]));
        } else {
            return new FulltimeEmployee(Integer.parseInt(strSplit[1]), strSplit[2], Double.parseDouble(strSplit[3]));
        }
    }

    @Override
    public String employeeToString(Employee employee) {
        return employee.getClass().getSimpleName() + SEPARATOR + employee.getId() + SEPARATOR
                + employee.getName() + SEPARATOR + employee.getPayment();
    }
}
