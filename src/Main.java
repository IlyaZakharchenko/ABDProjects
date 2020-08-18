import base.Employee;
import reader.FileEmployeeReader;
import model.FulltimeEmployee;
import model.HourEmployee;
import reader.HttpEmployeeReader;
import writer.FileEmployeeWriter;
import writer.HttpEmployeeWriter;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<Employee> employeeList = generateData();
        sortEmployee(employeeList);
        printTop5EmployeeNames(employeeList);
        printBottom3EmployeeIDs(employeeList);
        writeToFile(employeeList, "res\\employee");
        readFromFile("res\\employee");
        readFromWrongFile("res\\wrong_employee");
        readFromHttp("https://raw.githubusercontent.com/IlyaZakharchenko/ABDProjects/master/employee.json");
    }

    private static void readFromHttp(String url) {
        HttpEmployeeReader reader = new HttpEmployeeReader();
        ArrayList<Employee> employees = reader.readEmployee(url);
        for (Employee employee : employees) {
            System.out.println(employee.toString());
        }
    }

    private static void writeToHttp(String url, ArrayList<Employee> employeeList) {
        HttpEmployeeWriter writer = new HttpEmployeeWriter();
        writer.writeEmployee(employeeList, url);
    }

    public static ArrayList<Employee> generateData() {
        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(new HourEmployee(0, "Ivan", 10));
        employees.add(new FulltimeEmployee(1, "Mariya", 500));
        employees.add(new HourEmployee(2, "Kirill", 7));
        employees.add(new HourEmployee(3, "Alex", 3));
        employees.add(new HourEmployee(4, "Gennady", 5));
        employees.add(new FulltimeEmployee(5, "Bogdan", 350));
        employees.add(new FulltimeEmployee(6, "Ilya", 200));
        employees.add(new FulltimeEmployee(7, "Nikita", 1000));
        return employees;
    }

    private static void sortEmployee(ArrayList<Employee> employeeList) {
        employeeList.sort((o1, o2) -> {
            if (o1.calculatePayment() > o2.calculatePayment()) {
                return -1;
            } else if (o1.calculatePayment() < o2.calculatePayment()) {
                return 1;
            } else {
                return o1.getName().compareTo(o2.getName());
            }
        });
    }

    private static void printTop5EmployeeNames(ArrayList<Employee> employeeList) {
        for (int i = 0; i < 5; i++) {
            System.out.println(employeeList.get(i).getName());
        }
    }

    private static void printBottom3EmployeeIDs(ArrayList<Employee> employeeList) {
        for (int i = employeeList.size() - 1; i > employeeList.size() - 4; i--) {
            System.out.println(employeeList.get(i).getId());
        }
    }

    private static void writeToFile(ArrayList<Employee> employeeList, String filePath) {
        FileEmployeeWriter writer = new FileEmployeeWriter();
        writer.writeEmployee(employeeList, filePath);
    }

    private static void readFromFile(String filePath) {
        FileEmployeeReader reader = new FileEmployeeReader();
        for(Employee employee : reader.readEmployee(filePath)) {
            System.out.println(employee.toString());
        }
    }

    private static void readFromWrongFile(String filePath) {
        FileEmployeeReader reader = new FileEmployeeReader();
        reader.readEmployee(filePath);
    }
}
