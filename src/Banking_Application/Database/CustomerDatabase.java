package Banking_Application.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Banking_Application.Model.Customer;

public class CustomerDatabase {
    private DBconnection dbconnection=new DBconnection();
    private Connection conn=dbconnection.getConnection();

    public void addToCustomerTable(Customer customer) throws SQLException
    {
        String query="insert into Customer(Name,Address,Mobile,Aadhar,Pan,Balance,Account_number,Username,Password,Added_by,Approver_Designation) values(?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps=conn.prepareStatement(query);
        ps.setString(1,customer.getName());
        ps.setString(2,customer.getAddress());
        ps.setLong(3,customer.getMobile());
        ps.setLong(4,customer.getAadhar());
        ps.setString(5,customer.getPan());
        ps.setDouble(6,customer.getBalance());
        ps.setString(7,customer.getAccnum());
        ps.setString(8,customer.getUsername());
        ps.setString(9,customer.getPassword());
        ps.setString(10,customer.getAddedby());
        ps.setString(11,customer.getDesignation());

        ps.executeUpdate();
    }

    public boolean checkExists(String user) throws SQLException
    {
        String query="select * from Customer where Username =(?)";
        PreparedStatement ps=conn.prepareStatement(query);
        ps.setString(1, user);
        ResultSet rs=ps.executeQuery();
        return rs.next();
    }

    public boolean checkAccnum(String accnum) throws SQLException
    {
        String query="select * from Customer where Account_number =(?)";
        PreparedStatement ps=conn.prepareStatement(query);
        ps.setString(1, accnum);
        ResultSet rs=ps.executeQuery();
        return rs.next();
    }

    public Customer getCustomer(String user) throws SQLException
    {
        Customer customer=new Customer();
        String query="select * from Customer where Username =(?)";
        PreparedStatement ps=conn.prepareStatement(query);
        ps.setString(1, user);
        ResultSet rs=ps.executeQuery();
        while(rs.next())
        {
            customer.setName(rs.getString("Name"));
            customer.setAccnum(rs.getString("Account_number"));
            customer.setAddress(rs.getString("Address"));
            customer.setMobile(rs.getLong("Mobile"));
            customer.setBalance(rs.getDouble("Balance"));
            customer.setUsername(rs.getString("Username"));
            customer.setPassword(rs.getString("Password"));
            customer.setAadhar(rs.getLong("Aadhar"));
            customer.setPan(rs.getString("Pan"));
            customer.setAddedby(rs.getString("Added_by"));
            customer.setDesignation(rs.getString("Approver_Designation"));
        }
        return customer;
    }

    public Customer getCustomerAc(String accnum) throws SQLException
    {
        Customer customer=new Customer();
        String query="select * from Customer where Account_number =(?)";
        PreparedStatement ps=conn.prepareStatement(query);
        ps.setString(1, accnum);
        ResultSet rs=ps.executeQuery();
        while(rs.next())
        {
            customer.setName(rs.getString("Name"));
            customer.setAccnum(rs.getString("Account_number"));
            customer.setAddress(rs.getString("Address"));
            customer.setMobile(rs.getLong("Mobile"));
            customer.setBalance(rs.getDouble("Balance"));
            customer.setUsername(rs.getString("Username"));
            customer.setPassword(rs.getString("Password"));
            customer.setAadhar(rs.getLong("Aadhar"));
            customer.setPan(rs.getString("Pan"));
            customer.setAddedby(rs.getString("Added_by"));
            customer.setDesignation(rs.getString("Approver_Designation"));
        }
        return customer;
    }

    public void updateCustomer(Customer customer) throws SQLException
    {
        String query="update Customer set Name=?, Address=? ,Mobile=? ,Aadhar=? ,Pan=? , Balance =? ,Account_number=?, Username=?, Password=?, Added_by=?, Approver_Designation=? where Account_number = ?";
        PreparedStatement ps=conn.prepareStatement(query);
        ps.setString(1,customer.getName());
        ps.setString(2,customer.getAddress());
        ps.setLong(3,customer.getMobile());
        ps.setLong(4,customer.getAadhar());
        ps.setString(5,customer.getPan());
        ps.setDouble(6,customer.getBalance());
        ps.setString(7, customer.getAccnum());
        ps.setString(8,customer.getUsername());
        ps.setString(9,customer.getPassword());
        ps.setString(10,customer.getAddedby());
        ps.setString(11,customer.getDesignation());
        ps.setString(12,customer.getAccnum());
        
        ps.executeUpdate();
    }
    public void deleteCustomer(String user) throws SQLException
    {
        String query="delete from Customer where Username = ?";
        PreparedStatement ps=conn.prepareStatement(query);
        ps.setString(1, user);
        ps.executeUpdate();
    }
}
