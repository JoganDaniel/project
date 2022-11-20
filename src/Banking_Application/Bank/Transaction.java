package Banking_Application.Bank;
import java.sql.SQLException;
import Banking_Application.Controller.BankControl;
import Banking_Application.Controller.CustomerControl;
import Banking_Application.Model.Customer;

public class Transaction implements BankingDetails {
    CustomerControl customerControl=new CustomerControl();
    BankControl bankControl=new BankControl();
    @Override
    public void deposit(Customer customer, double amount) {
        customer.setBalance(customer.getBalance()+amount);
        try {
            customerControl.updateCustomer(customer);
            bankControl.addToTransactionTable(customer.getAccnum(), amount, "Cr", "-");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void withdraw(Customer customer, double amount) {
       double balance=customer.getBalance();
       if(balance-amount>=min){
        customer.setBalance(balance-amount);
        try {
            customerControl.updateCustomer(customer);
            bankControl.addToTransactionTable(customer.getAccnum(), amount, "Dr", " ");
        } catch (SQLException e) {
            e.printStackTrace();
            }
        }
    }

    @Override
    public void transfer(Customer customer, double amount, String receiver_num) {
        customer.setBalance(customer.getBalance()-amount);
        try {
            Customer receiver=customerControl.getCustomerAc(receiver_num);
            receiver.setBalance(receiver.getBalance()+amount);
            customerControl.updateCustomer(customer);
            customerControl.updateCustomer(receiver);
            bankControl.addToTransactionTable(customer.getAccnum(), amount, "Tr", receiver_num);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    }