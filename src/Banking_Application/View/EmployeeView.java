package Banking_Application.View;

import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

import Banking_Application.Bank.Validate;
import Banking_Application.Controller.CustomerControl;
import Banking_Application.Controller.DatabaseRetrieve;
import Banking_Application.Controller.EmployeeControl;
import Banking_Application.Model.Customer;
import Banking_Application.Model.Employee;

public class EmployeeView {
    public String z="----------------------------------".repeat(3);
    Scanner s=new Scanner(System.in);
    EmployeeControl empControl=new EmployeeControl();
    Validate validate=new Validate();
    Employee logemployee;

    public void EmployeeFirstLogin()
    {
        try{
        System.out.println("Hi its your first login....let's get started");
        System.out.println("Enter your Employee id");
        int empid;
        while(true)
        {
            empid=s.nextInt();
            if(empControl.checkEmployeeexists(empid))
            {
                break;
            }
            else{
                System.out.println("Enter correct id");
            }
        }
        Employee employee=empControl.getEmployee(empid);
        System.out.println("Fill your details\n"+z);
        System.out.println("Enter your Address");
        s.nextLine();
        String address=s.nextLine();
        String tempnum="";
        System.out.print("Enter your mobile number: ");
        while(true){
            tempnum=s.nextLine();
            if(validate.Mobilenumber(tempnum)){
                break;}
        }
        long mobile=Long.parseLong(tempnum);
        
        String pan;
        System.out.print("Enter your PAN: ");
        while(true)
        {
            pan=s.nextLine();
            if(validate.Pan(pan)){
                break;
            }
        }
        String user;
        while(true){
            System.out.println("Please enter a username with minimum 5 characters excluding spaces\n"+z);
            user=s.nextLine();
            if(validate.Username(user)){
                break;
            }
            }

        System.out.println("Enter a password for logging in\n"+z);
        String password;
        while(true){
            password=s.nextLine();
            if(validate.Password(password)){
                break;}
        }
        employee.setAddress(address);
        employee.setMobile(mobile);
        employee.setPan(pan);
        employee.setUsername(user);
        employee.setPassword(password);

        empControl.updateEmployee(employee);
    }catch(SQLException e){}
    System.out.println("Registration successful");
        System.out.println(z);
    }


    public boolean EmployeeLogin() throws SQLException
    {
        System.out.println("Enter your id number");
        int empid=s.nextInt();
        try {
            if(empControl.checkEmployeeexists(empid)==false)
            {
                System.out.println("No records found");
                return false;
            }
        } catch (SQLException e) {}
        logemployee=empControl.getEmployee(empid);
        System.out.println("Enter your password");
        String pass;
        s.nextLine();
        while(true)
        {
            pass=s.nextLine();
            if(validate.Password(pass))
            {
                break;
            }
        }
        if(pass.equals(logemployee.getPassword()))
        {
            EmployeeTasks();
            System.out.println(z);
            return true;
        }
        else{
            return false;
        }
    }
    public void EmployeeTasks()
    {
        int choice=0;
        DatabaseRetrieve databaseRetrieve=new DatabaseRetrieve();
        Customer customer=new Customer();
        CustomerControl customerControl=new CustomerControl();
     
        while(choice!=6)
        {
        System.out.println("1.Approve account");
        System.out.println("2.Edit your details");
        System.out.println("3.View a Customer information");
        System.out.println("4.View all customer details");
        System.out.println("5.View Transactions ");
        System.out.println("6.Log out");
        System.out.println(z);
        choice=s.nextInt();
        try{
        switch(choice)
        {
            case 1:
            {
                databaseRetrieve.UnapprovedCustomers();
                //System.out.println(z);
                System.out.println("Enter the username of the customer to be approved: ");
                s.nextLine();
                String user=s.nextLine();
                if(customerControl.checkUser(user)==false){
                    System.out.println("No user found");
                    break;
                }
                customer=customerControl.getCustomer(user);
                customer.setAddedby(logemployee.getName());
                customer.setDesignation("Bank staff");

                Random rand = new Random();
                String accnum="";
                int count = 0;
                int n = 0;
                for(int i =0; i < 14;i++)
                {
                if(count == 4)
                {
                accnum += " ";
                count =0;
                }
                else 
                    n = rand.nextInt(10);
                accnum += Integer.toString(n);
                count++;            
                }
                customer.setAccnum(accnum);
                customerControl.updateCustomer(customer);
                System.out.println(z+"\nUser Approved!!\n"+z);
                break;
            }
            case 2:
                {
                    System.out.println("What do you want to edit\n"+z);
                    System.out.println("1.Name\n2.Address\n3.Mobile\n4.Username\n5.Pan");           
                    String temch;
                    while(true){
                    temch=s.nextLine();
                    if(validate.choicecheck(temch)){
                    break;
                        }
                    }
                    int editch=Integer.parseInt(temch);
                    if(editch==1){
                        String name1="";
                        System.out.print("Enter new name: ");
                        while(true){
                            name1=s.nextLine();
                        if(validate.Name(name1)){
                        break;}
                        }
                        logemployee.setName(name1);
                    }
                    if(editch==2){
                        System.out.println("Enter new address");
                        String address=s.nextLine();
                        logemployee.setAddress(address); 
                    }
                    if(editch==3){
                        String tempnum="";
                        System.out.print("Enter new mobile number: ");
                        while(true){
                            tempnum=s.nextLine();
                            if(validate.Mobilenumber(tempnum)){
                                break;}
                        }
                        logemployee.setMobile(Long.parseLong(tempnum));
                    }
                    if(editch==4){
                        String username="";
                        while(true){
                            System.out.println("Enter new Username\n"+z);
                            username=s.nextLine();
                            if(validate.Username(username)){
                                break;
                            }
                        }
                        logemployee.setUsername(username);
                    }
                    if(editch==5){
                        String pan;
                        System.out.print("Enter new PAN: ");
                        while(true)
                        {
                            pan=s.nextLine();
                            if(validate.Pan(pan)){
                                break;
                            }
                        }
                        logemployee.setPan(pan);
                    }
                    empControl.updateEmployee(logemployee);
                    break;
                }
            case 3:
                {
                    System.out.println("Enter the username of the customer");
                    s.nextLine();
                    String user=s.nextLine();
                    databaseRetrieve.displayCustomer(user, logemployee.getEmployee_id());
                    break;
                }
            case 4:
                {
                    databaseRetrieve.displayAllCustomers(logemployee.getEmployee_id());
                    break;
                }
            case 5:
                {
                    System.out.println("1.Display transaction of a customer\n2.Display all transactions\n"+z);
                    int ch=s.nextInt();
                    switch(ch)
                    {
                        case 1:
                            System.out.println("Enter the username of the customer");
                            s.nextLine();
                            String user=s.nextLine();
                            Customer customer2=customerControl.getCustomer(user);
                            databaseRetrieve.displayTransaction(customer2.getAccnum());
                            break;
                        case 2:
                            databaseRetrieve.displayAllTransaction();
                            break;
                        default:
                            System.out.println("Enter either 1 or 2");
                            break;
                    }
                    break;
                }
            case 6:
                {
                    System.out.println("Logging out...");
                    break;
                }
            default:
                System.out.println("Enter correct choice");
                break;
            }
        }catch(SQLException e){}
    }
    }
}