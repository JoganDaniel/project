package Banking_Application.View;
import java.util.*;

import Banking_Application.Bank.Transaction;
import Banking_Application.Bank.Validate;
import Banking_Application.Controller.BankControl;
import Banking_Application.Controller.CustomerControl;
import Banking_Application.Controller.DatabaseRetrieve;
import Banking_Application.Model.Customer;

import java.sql.SQLException;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class CustomerView
{
    public String z="-------------------------------------".repeat(3);
    Scanner s=new Scanner(System.in);
    private CustomerControl customerControl=new CustomerControl();
    public Customer logcustomer;
    Validate validate=new Validate();

    public void AccountRegisration()
    {
        String name1="";
        System.out.println(z+"\nPlease enter your details below\n"+z);
        System.out.print("Enter your name: ");
        while(true){
            name1=s.nextLine();
            if(validate.Name(name1)){
                break;}
        }
        System.out.println(z);
        System.out.print("Enter your current address: ");
        String address=s.nextLine();
        System.out.println(z);

        String tempnum="";
        System.out.print("Enter your mobile number: ");
        while(true){
            tempnum=s.nextLine();
            if(validate.Mobilenumber(tempnum)){
                break;}
        }
        long mobile=Long.parseLong(tempnum);

        System.out.println(z);
        String tempaadhar="";
        System.out.print("Enter your Aadhar number: ");
        while(true){
            tempaadhar=s.nextLine();
            if(validate.Aadhar(tempaadhar)){
                break;}
        }
        long aadhar=Long.parseLong(tempaadhar);

        System.out.println(z);
        String pan,username,password;
        System.out.print("Enter your PAN: ");
        while(true)
        {
            pan=s.nextLine();
            if(validate.Pan(pan)){
                break;
            }
        }
        System.out.println(z+"\nYour details are successfully filled...\n");
        while(true){
        System.out.println("Please enter a username with minimum 5 characters excluding spaces\n"+z);
        username=s.nextLine();
        if(validate.Username(username)){
            break;
        }
        }
        while(true){
        try {
            if(customerControl.checkUser(username)){
                System.out.println("Username already exists\nEnter another username ");
                username=s.nextLine();
            }
            else{
                break;
            }
        } catch (SQLException e) {}
        }

        System.out.println("Enter a password for your account\n"+z);
        while(true){
            password=s.nextLine();
            if(validate.Password(password)){
                break;
            }
        }
        System.out.println(z+"\n\t\tTHANK YOU\nYour account is being created please wait....\n");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e){}


        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formatdate = date.format(myFormatObj);
        System.out.println(z+"\nCongatulations "+name1+" you have opened the account successfully on "+formatdate);
        System.out.println("Registration is done.You will be able to login after your account is verified....Please wait\n"+z);

        Customer customer=new Customer();
        customer.setName(name1);
        customer.setAddress(address);
        customer.setMobile(mobile);
        customer.setAadhar(aadhar);
        customer.setPan(pan);
        customer.setUsername(username);
        customer.setPassword(password);
        customer.setAccnum(null);
        customer.setBalance(0);
        customer.setAddedby(null);
        customer.setDesignation(null);

        customerControl.addToCustomerTable(customer);
    }

    public boolean AccountLogin() throws SQLException
    {
        System.out.print("Enter your username: ");
        String user,pass;
        while(true){
            user=s.nextLine();
            if(validate.Username(user)){
                break;
            }
            System.out.println("Please enter correct username\n"+z);
        }
            if(customerControl.checkUser(user)){
            }
            else{
                System.out.println("You have not signed up yet");
                return false;
            }
        logcustomer=customerControl.getCustomer(user);

        System.out.println(z);
        if(logcustomer.getAccnum()==null){
            System.out.println("Your account is not verified yet...Please wait");
            return false;
        }
        System.out.print("Enter your password: ");
        while(true){
            pass=s.nextLine();
            if(validate.Password(pass)){
                break;
            }
        }
        if(pass.equals(logcustomer.getPassword())){
        }
        else{
            System.out.println("Password mismatch\n");
            return false;
        }
        if(logcustomer.getBalance()==0){
            System.out.println(z+"\nYour account was verified successfully. Your account number is "+logcustomer.getAccnum()+"\n"+z);
            System.out.println("Enter your initial deposit amount(Minimum Rs.2000)");
            //*************
            double bal=s.nextDouble();
            logcustomer.setBalance(bal);
            customerControl.updateCustomer(logcustomer);
            BankControl bControl=new BankControl();
            bControl.addToTransactionTable(logcustomer.getAccnum(), bal, "Cr", "-");
        }
        CustomerOptions();
        return true;
    }

    public void CustomerOptions(){
        int ch=0;
        Transaction transaction=new Transaction();
        DatabaseRetrieve dbview=new DatabaseRetrieve();
        while(ch!=7){
        System.out.println(z);
        System.out.println("1.View Account details");
        System.out.println("2.Deposit");
        System.out.println("3.Withdraw");
        System.out.println("4.Transfer");
        System.out.println("5.Check Balance");
        System.out.println("6.Logout");
        System.out.println(z);
        String tch;
        while(true){
        tch=s.nextLine();
        if(validate.choicecheck(tch)){
        break;
            }
        }
        ch=Integer.parseInt(tch);
        switch(ch)
        {
            case 1:
            {
                
                try {
                    dbview.displayCustomer(logcustomer.getUsername());
                } catch (SQLException e) {}
                break;
            }
            case 2:
            {
                System.out.println("Enter the deposit amount: ");
                double amount=s.nextDouble();
                transaction.deposit(logcustomer,amount);
                System.out.println(z+"\n\t\tBANK OF ZOZO\n"+z);
                System.out.println("Name: "+logcustomer.getName()+"\t\tAccount number: "+logcustomer.getAccnum());
                System.out.println("\n\t\tAmount Deposited: "+amount);
                System.out.println("\tAvailable balance in account: "+(logcustomer.getBalance()));
                System.out.println(z);
                break;
            }
            case 3:
            {
                System.out.println("Enter the amount you need to withdraw: ");
                double amount=s.nextDouble();
                if(logcustomer.getBalance()-amount<2000){
                    System.out.println("Withdrawal not possible. Enter higher amount!!");
                    break;
                }
                transaction.withdraw(logcustomer,amount);
                System.out.println(z+"\n\t\tBANK OF ZOZO\n"+z);
                System.out.println("Name: "+logcustomer.getName()+"\t\tAccount number: "+logcustomer.getAccnum());
                System.out.println("\n\t\tAmount Withdrawn: "+amount);
                System.out.println("\tAvailable balance in account: "+(logcustomer.getBalance()));
                System.out.println(z);
                
                break;
            }
            case 4:
            {
                System.out.println("Enter the account number of the receiver: ");
                String receiver_num=s.nextLine();
                try{
                if(customerControl.checkAcnum(receiver_num)){}
                else{
                    System.out.println("Account unavailable....");
                    break;
                }}
                catch(SQLException e){}
                System.out.println("Enter the amount you want to transfer: ");
                double amount=s.nextDouble();
                if(logcustomer.getBalance()-amount<2000){
                    System.out.println("Cannot transfer amount. Minimum balance should be maintained...");
                    break;
                }
                transaction.transfer(logcustomer,amount,receiver_num);
                System.out.println(z+"\n\t\tBANK OF ZOZO\n"+z);
                System.out.println("Name: "+logcustomer.getName()+"\t\tAccount number: "+logcustomer.getAccnum());
                System.out.println("\n\t"+amount+" transferred to account "+receiver_num+" successfully");
                System.out.println("\t\tAvailable balance in account: "+(logcustomer.getBalance()));
                System.out.println(z);
                
                break;
            }
            case 5:
            {
                System.out.println("Hi "+logcustomer.getName()+" the balance in your account "+logcustomer.getAccnum()+" is "+logcustomer.getBalance());
                break;
            }
            case 6:
            {
                try {
                    dbview.displayTransaction(logcustomer.getAccnum());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            case 7:
            {
                System.out.println("Logging out....Please wait\n"+z);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {}
                break;
            }
            default:{
            System.out.println("Enter valid option");
            break;}
        }
    }
    }
}