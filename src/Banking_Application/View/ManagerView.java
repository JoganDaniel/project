package Banking_Application.View;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

import Banking_Application.Bank.Validate;
import Banking_Application.Controller.BankControl;
import Banking_Application.Controller.CustomerControl;
import Banking_Application.Controller.DatabaseRetrieve;
import Banking_Application.Controller.EmployeeControl;
import Banking_Application.Model.Customer;
import Banking_Application.Model.Employee;
import Banking_Application.Model.Manager;

public class ManagerView {
    BankControl bankControl=new BankControl();
    public String z="----------------------------------".repeat(3);
    Scanner s=new Scanner(System.in);
    Manager manager=new Manager();

    public boolean verifyManager() throws SQLException
    {
        System.out.println("Enter Username:");
        String user=s.nextLine();
        manager=bankControl.getManager(user);
        if(user.equals(manager.getUsername()))
        {}
        else{
            System.out.println("Invalid login\n"+z);
            return false;
        }
        System.out.println(z+"\nEnter password:");
        String pass=s.nextLine();
        if(pass.equals(manager.getPassword()))
        {
            System.out.println("Logging in...");
        }
        else{
            System.out.println("Wrong password\n"+z);
            return false;
        }
      System.out.println(z);
      return true;
    }

    public void managerTasks() throws SQLException
    {
        DatabaseRetrieve databaseRetrieve=new DatabaseRetrieve();
        Customer customer=new Customer();
        Employee employee=new Employee();
        CustomerControl customerControl=new CustomerControl();
        EmployeeControl empControl=new EmployeeControl();
        Validate validate=new Validate();
        int choice=0;
        
        while(choice!=6)
        {
            System.out.println(z);
            System.out.println("1.Approve Account");
            System.out.println("2.Edit Database");
            System.out.println("3.View Database");
            System.out.println("4.View Transactions");
            System.out.println("5.Add employee");
            System.out.println("6.Log out");
            System.out.println(z);
            String tchoice;
            while(true){
            tchoice=s.nextLine();
            if(validate.choicecheck(tchoice)){
                break;
                    }
                }
            choice=Integer.parseInt(tchoice);
            switch(choice)
            {
                case 1:
                {
                    databaseRetrieve.UnapprovedCustomers();
                    //System.out.println(z);
                    System.out.println("Enter the username of the customer to be approved: ");
                    String user=s.nextLine();
                    if(customerControl.checkUser(user)==false){
                        System.out.println("No user found");
                        break;
                    }
                    customer=customerControl.getCustomer(user);
                    customer.setAddedby(manager.getName());
                    customer.setDesignation("Manager");

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
                    System.out.println("1.Edit Customer details\n2.Edit Employee details\n"+z);
                    //Edit customer and employee details(to be done)
                    break;
                }
                case 3:
                {
                    System.out.println("1.View Customer Database\n2.View Employee Database\n"+z);
                    String tch;
                    while(true){
                    tch=s.nextLine();
                    if(validate.choicecheck(tch)){
                    break;
                        }
                    }
                    int ch=Integer.parseInt(tch);
                    switch(ch)
                    {
                        case 1:
                        {
                            System.out.println("1.Display a Customer's Details\n2.Display All customers\n"+z);
                            int option=s.nextInt();
                            if(option==1){
                                System.out.println("Enter the username of the customer");
                                s.nextLine();
                                String user=s.nextLine();
                                if(customerControl.checkUser(user)==false){
                                    System.out.println("No users found");
                                    break;
                                }
                                databaseRetrieve.displayCustomer(user, manager.getEmployee_id());
                            }
                            else if(option==2){
                                databaseRetrieve.displayAllCustomers(manager.getEmployee_id());
                            }
                            else{
                                System.out.println("Wrong input");
                            }
                            break;
                        }
                        case 2:
                        {
                            System.out.println("1.Display an Employee's Details\n2.Display all Employees");
                            int option=s.nextInt();
                            if(option==1)
                            {
                                int eid=0;
                                try{
                                System.out.println("Enter the Employee id of the employee");
                                eid=s.nextInt();
                                }catch(InputMismatchException e){
                                    System.out.println("Wrong input\n"+z);
                                break;}
                                finally{
                                }
                                if(empControl.checkEmployeeexists(eid)==false){
                                    System.out.println("No employee found");
                                    break;
                                }    
                                databaseRetrieve.displayEmployee(eid);
                            }
                            else if(option==2){
                                databaseRetrieve.displayAllEmployees();
                            }
                            else{
                                System.out.println("wrong input!!!");
                            }
                            break;
                        }
                        default:
                        System.out.println("Enter either 1 or 2");
                        break;
                    }
                    break;
                }
                case 4:
                {
                    System.out.println("1.Display transaction of a customer\n2.Display all transactions\n"+z);
                    int ch=s.nextInt();
                    switch(ch)
                    {
                        case 1:
                        {
                            System.out.println("Enter the username of the customer");
                            s.nextLine();
                            String user=s.nextLine();
                            Customer customer2=customerControl.getCustomer(user);
                            databaseRetrieve.displayTransaction(customer2.getAccnum());
                            break;
                        }
                        case 2:
                        {
                            databaseRetrieve.displayAllTransaction();
                            break;
                        }
                        default:{
                            System.out.println("Enter either 1 or 2");
                            break;}
                    }
                    break;
                }
                case 5:
                {
                    
                    String name="";
                    System.out.println("Enter the name of employee");
                    while(true){
                        name=s.nextLine();
                        if(validate.Name(name)){
                            break;}
                    }
                    int empid;
                    try{
                    System.out.println("Enter Employee_id:");
                    while(true){
                    empid=s.nextInt();
                    if(empControl.checkEmployeeexists(empid)){
                        System.out.println("Id already exists enter another id");
                    }
                    else{
                        break;
                        }   
                    }
                    }catch(InputMismatchException e){
                        System.out.println("Enter a number");
                        break;
                        }
                    employee.setName(name);
                    employee.setEmployee_id(empid);
                    employee.setAddress(null);
                    employee.setMobile(0);
                    employee.setPan(null);
                    employee.setUsername(null);
                    employee.setPassword(null);

                    empControl.addtoEmployeeTable(employee);
                    System.out.println("Employee added successfully..");
                    break;
                }
                case 6:
                {
                    System.out.println("Logging out...");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                default:{
                System.out.println("Enter correct choice");
                break;}
            }
        }
    }
}