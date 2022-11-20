package Banking_Application.Bank;

import Banking_Application.Model.Customer;

public interface BankingDetails {

    final double min=2000,max=30000;
public void deposit(Customer customer,double amount);
public void withdraw(Customer customer,double amount);
public void transfer(Customer customer,double amount,String receiver_name);

}
