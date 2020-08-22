import base.Employee;
import exception.WrongFormatException;
import model.FulltimeEmployee;
import model.HourEmployee;
import parser.FileEmployeeParser;
import parser.JsonEmployeeParser;
import reader.EmployeeReader;
import reader.FileEmployeeReader;
import reader.HttpEmployeeReader;
import utils.EmployeePaymentComparator;
import writer.EmployeeWriter;
import writer.FileEmployeeWriter;
import writer.HttpEmployeeWriter;

import java.io.IOException;
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
        readFromHttp("https://raw.githubusercontent.com/IlyaZakharchenko/ABDProjects/HW_13.08/employee.json");
    }

    private static void readFromHttp(String url) {
        EmployeeReader reader = new HttpEmployeeReader(new JsonEmployeeParser());
        try {
            ArrayList<Employee> employees = reader.readEmployee(url);
            for (Employee employee : employees) {
                System.out.println(employee.toString());
            }
        } catch (WrongFormatException e) {
            System.err.println("Exception while reading " + url + ": " + e.getLocalizedMessage());
        } catch (IOException e) {
            System.err.println(e.getLocalizedMessage());
        }
    }

    private static void writeToHttp(String url, ArrayList<Employee> employeeList) {
        EmployeeWriter writer = new HttpEmployeeWriter();
        try {
            writer.writeEmployee(employeeList, url);
        } catch (IOException e) {
            System.err.println(e.getLocalizedMessage());
        }
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
        employeeList.sort(new EmployeePaymentComparator());
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
        EmployeeWriter writer = new FileEmployeeWriter(new FileEmployeeParser());
        try {
            writer.writeEmployee(employeeList, filePath);
        } catch (IOException e) {
            System.err.println(e.getLocalizedMessage());
        }
    }

    private static void readFromFile(String filePath) {
        EmployeeReader reader = new FileEmployeeReader(new FileEmployeeParser());
        try {
            for (Employee employee : reader.readEmployee(filePath)) {
                System.out.println(employee.toString());
            }
        } catch (WrongFormatException e) {
            System.err.println("Exception while reading " + filePath + ": " + e.getLocalizedMessage());
        } catch (IOException e) {
            System.err.println(e.getLocalizedMessage());
        }
    }

    private static void readFromWrongFile(String filePath) {
        EmployeeReader reader = new FileEmployeeReader(new FileEmployeeParser());
        try {
            reader.readEmployee(filePath);
        } catch (WrongFormatException e) {
            System.err.println("Exception while reading " + filePath + ": " + e.getLocalizedMessage());
        } catch (IOException e) {
            System.err.println(e.getLocalizedMessage());
        }
    }
}
