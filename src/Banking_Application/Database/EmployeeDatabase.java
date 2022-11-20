package Banking_Application.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Banking_Application.Model.Employee;

public class EmployeeDatabase {
    private DBconnection dbconnection=new DBconnection();
    private Connection conn=dbconnection.getConnection();

    public void addtoEmployeeTable(Employee employee) throws SQLException
    {
        String query="insert into Employee(Name,Employee_id,Address,Mobile,Pan,Username,Password) values(?,?,?,?,?,?,?)";
        PreparedStatement ps=conn.prepareStatement(query);
        ps.setString(1,employee.getName());
        ps.setInt(2,employee.getEmployee_id());
        ps.setString(3,employee.getAddress());
        ps.setLong(4,employee.getMobile());
        ps.setString(5,employee.getPan());
        ps.setString(6,employee.getUsername());
        ps.setString(7,employee.getPassword());

        ps.executeUpdate();
    }

    public boolean checkExists(int empid) throws SQLException
    {
        String query="select * from Employee where Employee_id =(?)";
        PreparedStatement ps=conn.prepareStatement(query);
        ps.setInt(1, empid);
        ResultSet rs=ps.executeQuery();
        return rs.next();
    }

    public Employee getemployee(int empid) throws SQLException
    {
        Employee employee=new Employee();
        String query="select * from employee where Employee_id =(?)";
        PreparedStatement ps=conn.prepareStatement(query);
        ps.setInt(1, empid);
        ResultSet rs=ps.executeQuery();
        while(rs.next())
        {
            employee.setName(rs.getString("Name"));
            employee.setEmployee_id(rs.getInt("Employee_id"));
            employee.setAddress(rs.getString("Address"));
            employee.setMobile(rs.getLong("Mobile"));
            employee.setPan(rs.getString("Pan"));
            employee.setUsername(rs.getString("Username"));
            employee.setPassword(rs.getString("Password"));
        }
        return employee;
    }

    public void updateemployee(Employee employee) throws SQLException
    {
        String query="update employee set Name=?,Employee_id=?, Address=? ,Mobile=? ,Pan=?, Username=?, Password=? where Employee_id = ?";
        PreparedStatement ps=conn.prepareStatement(query);
        ps.setString(1,employee.getName());
        ps.setInt(2,employee.getEmployee_id());
        ps.setString(3,employee.getAddress());
        ps.setLong(4,employee.getMobile());
        ps.setString(5,employee.getPan());
        ps.setString(6,employee.getUsername());
        ps.setString(7,employee.getPassword());
        ps.setInt(8, employee.getEmployee_id());
        
        ps.executeUpdate();
    }

    
    public void deleteEmployee(int empid) throws SQLException
    {
        String query="delete from Employee where Employee_id=?";
        PreparedStatement ps=conn.prepareStatement(query);
        ps.setInt(1, empid);
        ps.executeUpdate();
    }

}
