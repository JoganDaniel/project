package Banking_Application.Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Banking_Application.Database.DBconnection;
import Banking_Application.Model.Customer;
import Banking_Application.Model.Employee;
import Banking_Application.Model.TransactionModel;
import Banking_Application.View.DatabaseView;

public class DatabaseRetrieve {
    Scanner s=new Scanner(System.in);
    private DBconnection dbconnection=new DBconnection();
    private Connection conn=dbconnection.getConnection();
    DatabaseView dbview=new DatabaseView();
    Customer customer;
    Employee employee;

    public void displayCustomer(String user) throws SQLException
    {
        customer=new Customer();
        String query="select * from Customer where Username=(?)";
        PreparedStatement ps=conn.prepareStatement(query);
        ps.setString(1, user);
        ResultSet rs=ps.executeQuery();
        while(rs.next())
        {
            customer.setName(rs.getString("Name"));
            customer.setAddress(rs.getString("Address"));
            customer.setMobile(rs.getLong("Mobile"));
            customer.setAadhar(rs.getLong("Aadhar"));
            customer.setPan(rs.getString("Pan"));
            customer.setBalance(rs.getDouble("Balance"));
            customer.setAccnum(rs.getString("Account_number"));
            customer.setUsername(rs.getString("Username"));
        }
            dbview.displayCustomerDetails(customer);
    }

    public void displayCustomer(String user,int empid) throws SQLException
    {
        String query="select * from Customer where Username=(?)";
        PreparedStatement ps=conn.prepareStatement(query);
        ps.setString(1, user);
        ResultSet rs=ps.executeQuery();
        customer=new Customer();
        while(rs.next())
        {
            customer.setName(rs.getString("Name"));
            customer.setAddress(rs.getString("Address"));
            customer.setMobile(rs.getLong("Mobile"));
            customer.setAadhar(rs.getLong("Aadhar"));
            customer.setPan(rs.getString("Pan"));
            customer.setBalance(rs.getDouble("Balance"));
            customer.setAccnum(rs.getString("Account_number"));
            customer.setUsername(rs.getString("Username"));
            customer.setAddedby(rs.getString("Added_by"));
            customer.setDesignation(rs.getString("Approver_Designation"));
        }
        dbview.displayCustomerDetails(customer, empid);
    }

    public void displayAllCustomers(int empid) throws SQLException
    {
        List<Customer> customerList=new ArrayList<Customer>();
        String query="select * from Customer";
        PreparedStatement ps=conn.prepareStatement(query);
        ResultSet rs=ps.executeQuery();
         while(rs.next())
        {
            customer=new Customer();
            customer.setName(rs.getString("Name"));
            customer.setAddress(rs.getString("Address"));
            customer.setMobile(rs.getLong("Mobile"));
            customer.setAadhar(rs.getLong("Aadhar"));
            customer.setPan(rs.getString("Pan"));
            customer.setBalance(rs.getDouble("Balance"));
            customer.setAccnum(rs.getString("Account_number"));
            customer.setUsername(rs.getString("Username"));
            customer.setAddedby(rs.getString("Added_by"));
            customer.setDesignation(rs.getString("Approver_Designation"));
            customerList.add(customer);
        }
        dbview.displayAllCustomers(customerList, empid);
    }

    public boolean checkUnapprovedCustomers() throws SQLException
    {
        
        String query="select * from Customer where Account_number = '-'";
        PreparedStatement ps=conn.prepareStatement(query);
        ResultSet rs=ps.executeQuery();
        if(rs.next()==false){
            return false;
        }
        return true;
    }
    public void UnapprovedCustomers() throws SQLException
    {
        
        String query="select * from Customer where Account_number = '-'";
        PreparedStatement ps=conn.prepareStatement(query);
        ResultSet rs=ps.executeQuery();
        List<Customer> customerList=new ArrayList<Customer>();
        while(rs.next())
        {
            customer=new Customer();
            customer.setName(rs.getString("Name"));
            customer.setAddress(rs.getString("Address"));
            customer.setMobile(rs.getLong("Mobile"));
            customer.setAadhar(rs.getLong("Aadhar"));
            customer.setPan(rs.getString("Pan"));
            customer.setBalance(rs.getDouble("Balance"));
            //customer.setAccnum(rs.getString("Account_number"));
            customer.setUsername(rs.getString("Username"));
            customerList.add(customer);
        }
        dbview.displayUnapprovedCustomers(customerList);
    }
    TransactionModel transaction;
    public void displayTransaction(String accnum) throws SQLException
    {
        List<TransactionModel> transactionList=new ArrayList<TransactionModel>();
        String query="select * from Transactions where Accountnumber =? or Receiver_Accountnumber =?";
        PreparedStatement ps=conn.prepareStatement(query);
        ps.setString(1,accnum);
        ps.setString(2, accnum);
        ResultSet rs=ps.executeQuery();
        while(rs.next())
        {
            transaction=new TransactionModel();
            transaction.setSender_acc(rs.getString("Accountnumber"));
            transaction.setTransactionid(rs.getInt("Transaction_id"));
            transaction.setAmount(rs.getDouble("Amount"));
            transaction.setType(rs.getString("Type"));
            transaction.setDate(rs.getDate("Date"));
            transaction.setReceiver_acc(rs.getString("Receiver_Accountnumber"));
            transactionList.add(transaction);
        }
        dbview.displayAccountTransactions(transactionList);
    }

    public void displayAllTransaction() throws SQLException
    {
        List<TransactionModel> transactionList=new ArrayList<TransactionModel>();
        String query="select * from Transactions";
        PreparedStatement ps=conn.prepareStatement(query);
        ResultSet rs=ps.executeQuery();
        while(rs.next())
        {
            transaction=new TransactionModel();
            transaction.setSender_acc(rs.getString("Accountnumber"));
            transaction.setTransactionid(rs.getInt("Transaction_id"));
            transaction.setAmount(rs.getDouble("Amount"));
            transaction.setType(rs.getString("Type"));
            transaction.setDate(rs.getDate("Date"));
            transaction.setReceiver_acc(rs.getString("Receiver_Accountnumber"));
            transactionList.add(transaction);
        }
        dbview.displayAllTransactions(transactionList);
    }

    public void displayEmployee(int empid) throws SQLException
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
        dbview.displayEmployee(employee);
    }
    public void displayAllEmployees() throws SQLException
    {
        Employee employee;
        String query="select * from employee";
        List<Employee> emplist=new ArrayList<>();
        PreparedStatement ps=conn.prepareStatement(query);
        ResultSet rs=ps.executeQuery();
        while(rs.next())
        {
            employee=new Employee();
            employee.setName(rs.getString("Name"));
            employee.setEmployee_id(rs.getInt("Employee_id"));
            employee.setAddress(rs.getString("Address"));
            employee.setMobile(rs.getLong("Mobile"));
            employee.setPan(rs.getString("Pan"));
            employee.setUsername(rs.getString("Username"));
            employee.setPassword(rs.getString("Password"));
            emplist.add(employee);
        }
        dbview.displayAllEmployee(emplist);
    }
}

