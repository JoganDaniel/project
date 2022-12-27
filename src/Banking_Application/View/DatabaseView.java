package Banking_Application.View;
import java.sql.SQLException;
import java.util.List;
import Banking_Application.Model.Customer;
import Banking_Application.Model.Employee;
import Banking_Application.Model.TransactionModel;

public class DatabaseView {

    public void displayCustomerDetails(Customer customer) throws SQLException
    {
        //System.out.println("Name\t\tAddress\t\tMobile\t\tAadhar\t\tPan\t\tBalance\t\tAccount_number\t\tUsername");
        //System.out.println(customer.getName()+"\t\t"+customer.getAddress()+"\t\t"+customer.getMobile()+"\t\t"+customer.getAadhar()+"\t\t"+customer.getPan()+"\t\t"+customer.getBalance()+"\t\t"+customer.getAccnum()+"\t\t"+customer.getUsername());
        System.out.println("Name: "+customer.getName()+"  |  Address: "+customer.getAddress()+"  |  Mobile: "+customer.getMobile()+"  |  Aadhar: "+customer.getAadhar()+"  |  Pan: "+customer.getPan()+"  |  Balance: "+customer.getBalance()+"\n    Account number: "+customer.getAccnum()+"  |  Username: "+customer.getUsername());
        System.out.println("------------------------------------------------------".repeat(3));
    }
    public void displayCustomerDetails(Customer customer,int empid) throws SQLException
    {
        System.out.println("Database access granted. Id: "+empid);
        System.out.println("Name: "+customer.getName()+"  |  Address: "+customer.getAddress()+"  |  Mobile: "+customer.getMobile()+"  |  Aadhar: "+customer.getAadhar()+"  |  Pan: "+customer.getPan()+"  |  Balance: "+customer.getBalance()+"\n\tAccount number: "+customer.getAccnum()+"  |  Username: "+customer.getUsername()+"  |  Approved by: "+customer.getAddedby()+"  |  Approver Designation: "+customer.getDesignation());
        System.out.println("------------------------------------------------------".repeat(3));
    }

    public void displayUnapprovedCustomers(List<Customer> customerList) throws SQLException
    {
        for(Customer customer: customerList){
            System.out.println("Name: "+customer.getName()+"  |  Address: "+customer.getAddress()+"  |  Mobile: "+customer.getMobile()+"  |  Aadhar: "+customer.getAadhar()+"  |  Pan: "+customer.getPan()+"  |  Username: "+customer.getUsername());
            System.out.println("------------------------------------------------------".repeat(3));
        }
    }

    public void displayAllCustomers(List<Customer> customerList,int empid) throws SQLException
    {
        System.out.println("Database access granted. Id: "+empid);
        //System.out.println("\nName\t\t\tAddress\t\t\tMobile\t\t\tAadhar\t\t\tPan\t\t\tBalance\t\t\tAccount number\t\t\tUsername\t\t\tAdded by\t\t\tDesignation");
        //System.out.println("-------------------------------------------------".repeat(3));
        for(Customer customer: customerList){
            //System.out.println(customer.getName()+"\t\t"+customer.getAddress()+"\t\t"+customer.getMobile()+"\t\t"+customer.getAadhar()+"\t\t"+customer.getPan()+"\t\t"+customer.getBalance()+"\t\t"+customer.getAccnum()+"\t\t"+customer.getUsername()+"\t\t"+customer.getAddedby()+"\t\t"+customer.getDesignation());
            System.out.println("Name: "+customer.getName()+"  |  Address: "+customer.getAddress()+"  |  Mobile: "+customer.getMobile()+"  |  Aadhar: "+customer.getAadhar()+"  |  Pan: "+customer.getPan()+"  |  Balance: "+customer.getBalance()+"\n\tAccount number: "+customer.getAccnum()+"  |  Username: "+customer.getUsername()+"  |  Approved by: "+customer.getAddedby()+"  |  Approver Designation: "+customer.getDesignation());
            System.out.println("------------------------------------------------------".repeat(3));
        }
    }

    public void displayAccountTransactions(List<TransactionModel> transactionlist) throws SQLException
    {
        
        for(TransactionModel transaction: transactionlist)
        {
            System.out.println("Account number: "+transaction.getSender_acc()+"  |  Transaction id: "+transaction.getTransactionid()+"  |  Amount: "+transaction.getAmount()+"  |  Type: "+transaction.getType()+"  |  Date: "+transaction.getDate()+"  |  Receiver Account Number: "+transaction.getReceiver_acc());
            System.out.println("------------------------------------------------------".repeat(3));
        }
    }

    public void displayAllTransactions(List<TransactionModel> transactionlist) throws SQLException
    {
        for(TransactionModel transaction: transactionlist)
        {
            System.out.println("Account number: "+transaction.getSender_acc()+"  |  Transaction id: "+transaction.getTransactionid()+"  |  Amount: "+transaction.getAmount()+"  |  Type: "+transaction.getType()+"  |  Date: "+transaction.getDate()+"  |  Receiver Account Number: "+transaction.getReceiver_acc());
            System.out.println("------------------------------------------------------".repeat(3));
        
        }
    }

    public void displayEmployee(Employee employee)
    {
        System.out.println("Name: "+employee.getName()+"  |  Employee id: "+employee.getEmployee_id()+"  |  Address: "+employee.getAddress()+"  |  Mobile: "+employee.getMobile()+"  |  Pan: "+employee.getPan()+"  |  Username: "+employee.getUsername());
        System.out.println("------------------------------------------------------".repeat(3));
    }

    public void displayAllEmployee(List<Employee> employeelist)
    {
        for(Employee employee:employeelist)
        {
            System.out.println("Name: "+employee.getName()+"  |  Employee id: "+employee.getEmployee_id()+"  |  Address: "+employee.getAddress()+"  |  Mobile: "+employee.getMobile()+"  |  Pan: "+employee.getPan()+"  |  Username: "+employee.getUsername());
            System.out.println("------------------------------------------------------".repeat(3));
        }
    }
}
