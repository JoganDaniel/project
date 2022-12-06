package Banking_Application.Controller;

import java.sql.SQLException;

import Banking_Application.Database.CustomerDatabase;
import Banking_Application.Model.Customer;

public class CustomerControl {
    CustomerDatabase customerDatabase=new CustomerDatabase();

    public void setCustomer( String name,String accnum,String address,long mobile,double balance,String username,String password,long aadhar,String pan,String addedby,String designation)
     {

     }
    public void addToCustomerTable(Customer customer)
    {
        try {
            customerDatabase.addToCustomerTable(customer);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean checkUser(String user) throws SQLException{
        return customerDatabase.checkExists(user);
    }
    public boolean checkAcnum(String accnum) throws SQLException
    {
        return customerDatabase.checkAccnum(accnum);
    }

    public Customer getCustomer(String user) throws SQLException
    {
        return customerDatabase.getCustomer(user);
    }

    public Customer getCustomerAc(String accnum) throws SQLException
    {
        return customerDatabase.getCustomerAc(accnum);
    }

    public void updateCustomer(Customer customer) throws SQLException
    {
        customerDatabase.updateCustomer(customer);
    }

    public void deleteCustomer(String user) throws SQLException
    {
        customerDatabase.deleteCustomer(user);
    }


}
