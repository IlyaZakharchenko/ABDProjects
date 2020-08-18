package parser;

import base.BaseEmployeeParser;
import base.Employee;
import exception.WrongFileFormatException;
import model.FulltimeEmployee;
import model.HourEmployee;

public class FileEmployeeParser extends BaseEmployeeParser {

    protected final String SEPARATOR = "|";
    protected final String HE_TAG = "HE";
    protected final String FE_TAG = "FE";

    @Override
    public Employee parseEmployee(String str) {
        String[] strSplit = str.split("\\" + SEPARATOR);
        if (strSplit.length < 4) {
            try {
                throw new WrongFileFormatException();
            } catch (WrongFileFormatException e) {
                e.printStackTrace();
                return null;
            }
        }
        if (strSplit[0].equals(HE_TAG)) {
            return new HourEmployee(Integer.parseInt(strSplit[1]), strSplit[2], Double.parseDouble(strSplit[3]));
        } else {
            return new FulltimeEmployee(Integer.parseInt(strSplit[1]), strSplit[2], Double.parseDouble(strSplit[3]));
        }
    }

    @Override
    public String employeeToString(Employee employee) {
        String str;
        if (employee instanceof HourEmployee) {
            str = HE_TAG + SEPARATOR;
        } else {
            str = FE_TAG + SEPARATOR;
        }
        str += employee.getId() + SEPARATOR + employee.getName() + SEPARATOR + employee.calculatePayment();
        return str;
    }
}
