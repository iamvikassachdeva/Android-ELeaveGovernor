package com.example.sachdeva.eleavegovernor;

/**
 * Created by Sachdeva on 02-Oct-17.
 */

public class EmployeeData {

    private static String Emp_id, Emp_name, Gender, department, email, designation, password;

    public EmployeeData(String emp_id, String emp_name, String gender, String department, String email, String designation, String Password) {
        Emp_id = emp_id;
        Emp_name = emp_name;
        Gender = gender;
        this.department = department;
        this.email = email;
        this.designation = designation;
        this.password = Password;
    }

    public static String getEmp_id() {
        return Emp_id;
    }

    public static String getEmp_name() {
        return Emp_name;
    }

    public static String getGender() {
        return Gender;
    }

    public static String getDepartment() {
        return department;
    }

    public static String getEmail() {
        return email;
    }

    public static String getDesignation() {
        return designation;
    }

    public static String getPassword() {
        return password;
    }
}
