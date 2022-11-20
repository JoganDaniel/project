package Banking_Application.Controller;

import java.sql.SQLException;

import Banking_Application.Database.EmployeeDatabase;
import Banking_Application.Model.Employee;

public class EmployeeControl {
    EmployeeDatabase empDatabase=new EmployeeDatabase();
    public void addtoEmployeeTable(Employee employee) throws SQLException
    {
        empDatabase.addtoEmployeeTable(employee);
    }

    public boolean checkEmployeeexists(int empid) throws SQLException
    {
        return empDatabase.checkExists(empid);
    }

    public Employee getEmployee(int empid) throws SQLException
    {
        return empDatabase.getemployee(empid);
    }

    public void updateEmployee(Employee employee) throws SQLException
    {
        empDatabase.updateemployee(employee);
    }

    public void deleteEmployee(int empid) throws SQLException
    {
        empDatabase.deleteEmployee(empid);
    }
}
