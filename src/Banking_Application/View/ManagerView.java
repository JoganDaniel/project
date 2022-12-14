package Banking_Application.View;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

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
        if(user.equals(manager.getUsername())==false){
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
        System.out.println(z);
        while(choice!=7)
        {
            System.out.println("1.Approve Account");
            System.out.println("2.Edit Database");
            System.out.println("3.View Database");
            System.out.println("4.View Transactions");
            System.out.println("5.Add employee");
            System.out.println("6.Remove a Customer or Employee");
            System.out.println("7.Log out");
            System.out.println(z);
            String tchoice;
            while(true){
            tchoice=s.nextLine();
            if(validate.choicecheck(tchoice)){
                break;
                    }
                }
            choice=Integer.parseInt(tchoice);
            System.out.println(z);
            switch(choice)
            {
                case 1:
                {
                    if(databaseRetrieve.checkUnapprovedCustomers()==false){
                        System.out.println("No accounts to be approved\n"+z);
                        break;
                    }
                    databaseRetrieve.UnapprovedCustomers();
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
                            Customer editcustomer;
                            System.out.println("Enter the username of the customer you want to edit");
                            String user=s.nextLine();
                            if(customerControl.checkUser(user)){
                                editcustomer=customerControl.getCustomer(user);
                            }
                            else{
                                System.out.println("No accounts exist");
                                break;
                            }
                            System.out.println("What do you want to edit\n"+z+"\n1.Name\n2.Address\n3.Mobile\n4.Username\n5.Aadhar\n6.Pan");
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
                                editcustomer.setName(name1);
                                System.out.println("Value Updated....");
                            }
                            else if(editch==2){
                                System.out.println("Enter new address");
                                String address=s.nextLine();
                                editcustomer.setAddress(address); 
                                System.out.println("Value Updated....");
                            }
                            else if(editch==3){
                                String tempnum="";
                                System.out.print("Enter new mobile number: ");
                                while(true){
                                    tempnum=s.nextLine();
                                    if(validate.Mobilenumber(tempnum)){
                                        break;}
                                }
                                editcustomer.setMobile(Long.parseLong(tempnum));
                                System.out.println("Value Updated....\n"+z);
                            }
                            else if(editch==4){
                                String username="";
                                while(true){
                                    System.out.println("Enter new Username\n"+z);
                                    username=s.nextLine();
                                    if(validate.Username(username)){
                                        break;
                                    }
                                }
                                if(customerControl.checkUser(username)){
                                    System.out.println("Username already exists...\n"+z);
                                }
                                else{
                                editcustomer.setUsername(username);
                                System.out.println("Value Updated....");
                                }
                            }
                            else if(editch==5){
                                String tempaadhar="";
                                System.out.print("Enter new Aadhar number: ");
                                while(true){
                                    tempaadhar=s.nextLine();
                                    if(validate.Aadhar(tempaadhar)){
                                        break;}
                                }
                                editcustomer.setAadhar(Long.parseLong(tempaadhar));
                                System.out.println("Value Updated....");
                            }
                            else if(editch==6){
                                String pan;
                                System.out.print("Enter new PAN: ");
                                while(true)
                                {
                                    pan=s.nextLine();
                                    if(validate.Pan(pan)){
                                        break;
                                    }
                                }
                                editcustomer.setPan(pan);
                                System.out.println("Value Updated....");
                            }
                            else{
                                System.out.println("Enter correct choice!");
                                break;
                            }
                            customerControl.updateCustomer(editcustomer);
                            break;
                        }
                        case 2:
                        {
                            Employee editemployee;
                            System.out.println("Enter the Employee id of the bank Staff");
                            int employee_id=s.nextInt();
                            if(empControl.checkEmployeeexists(employee_id)){
                                editemployee=empControl.getEmployee(employee_id);
                            }
                            else{
                                System.out.println("Wrong id");
                                break;
                            }
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
                                editemployee.setName(name1);
                                System.out.println("Value Updated....");
                            }
                            else if(editch==2){
                                System.out.println("Enter new address");
                                String address=s.nextLine();
                                editemployee.setAddress(address); 
                                System.out.println("Value Updated....");
                            }
                            else if(editch==3){
                                String tempnum="";
                                System.out.print("Enter new mobile number: ");
                                while(true){
                                    tempnum=s.nextLine();
                                    if(validate.Mobilenumber(tempnum)){
                                        break;}
                                }
                                editemployee.setMobile(Long.parseLong(tempnum));
                                System.out.println("Value Updated....");
                            }
                            else if(editch==4){
                                String username="";
                                while(true){
                                    System.out.println("Enter new Username\n"+z);
                                    username=s.nextLine();
                                    if(validate.Username(username)){
                                        break;
                                    }
                                }
                                if(customerControl.checkUser(username)){
                                    System.out.println("Username Already exists..\n"+z);
                                }
                                else{
                                editemployee.setUsername(username);
                                System.out.println("Value Updated....");
                                }
                            }
                            else if(editch==5){
                                String pan;
                                System.out.print("Enter new PAN: ");
                                while(true)
                                {
                                    pan=s.nextLine();
                                    if(validate.Pan(pan)){
                                        break;
                                    }
                                }
                                System.out.println("Value Updated....");
                                editemployee.setPan(pan);
                            }
                            else{
                                System.out.println("Enter correct choice..");
                            }
                            empControl.updateEmployee(editemployee);
                            break;
                        }
                    }
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
                            String t;
                            while(true){
                            t=s.nextLine();
                            if(validate.choicecheck(t)){
                            break;
                                }
                            }
                            int option=Integer.parseInt(t);
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
                            System.out.println("Enter the username of the customer");
                            s.nextLine();
                            String user=s.nextLine();
                            Customer customer2=customerControl.getCustomer(user);
                            System.out.println("Your account number: "+customer2.getAccnum()+"\n"+z);
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
                        System.out.println("Id already exists. Enter another id");
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
                    System.out.println("1.Remove Customer\n2.Remove Employee\n"+z);
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
                            String username="";
                                    System.out.println("Enter the Username of the customer\n"+z);
                                    username=s.nextLine();
                                    if(validate.Username(username)==false){
                                        System.out.println("Enter a valid Username");
                                        break;
                                    }
                            if(customerControl.checkUser(username)){
                                customerControl.deleteCustomer(username);
                                System.out.println("Customer removed..\n"+z);
                            }    
                            else{
                                System.out.println("Username not exixts");
                            }
                            break;
                        }
                        case 2:
                        {
                
                            System.out.println("Enter the Employee id of the staff to be removed");
                            int eid=s.nextInt();
                            if(empControl.checkEmployeeexists(eid)){
                                empControl.deleteEmployee(eid);
                            }
                            else{
                                System.out.println("Id incorrect...");
                            }
                            break;
                        }
                        default:
                        {
                            System.out.println("Wrong choice");
                            break;
                        }
                    }
                    break;
                }
                case 7:
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
                System.out.println("Enter correct choice!!!");
                break;}
            }
        }
    }
}
