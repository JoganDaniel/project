package Banking_Application.View;
import java.sql.SQLException;
import java.util.*;
public class BankView {

    private Scanner s=new Scanner(System.in);
    Validate validate=new Validate();
    public String z="----------------------------------".repeat(3);
    public void app()
    {
        int ch=0;
        System.out.println(z+"\n\t\t\tWELCOME TO BANK OF ZOZO\n"+z);
        while(ch!=4){
            System.out.println("Enter 1 -> Manager");
            System.out.println("Enter 2 -> Bank Employee");
            System.out.println("Enter 3 -> Customer");
            System.out.println("Enter 4 -> Exit");
            System.out.println(z);
            String c;
            while(true){
                c=s.nextLine();
                if(validate.choicecheck(c)){
                    break;
                }
            }
            ch=Integer.parseInt(c);
            switch(ch)
            {
                case 1:
                {
                    ManagerView managerOperations=new ManagerView();
                    try {
                        if(managerOperations.verifyManager())
                        {
                            managerOperations.managerTasks();
                        }
                        else{
                            break;
                        }
                    } catch (SQLException e) {}
                    break;
                }
                case 2:
                {
                    int choice=0;
                    while(choice!=3)
                    {
                    System.out.println("Enter 1 -> New Employee Login");
                    System.out.println("Enter 2 -> Login to your Account");
                    System.out.println("Enter 3 -> Exit");
                    String tch;
                    while(true){
                    tch=s.nextLine();
                    if(validate.choicecheck(tch)){
                    break;
                        }
                    }
                    choice=Integer.parseInt(tch);
                    EmployeeView employeeOperations=new EmployeeView();
                    switch(choice)
                    {
                        case 1:
                        {
                            employeeOperations.EmployeeFirstLogin();
                            break;
                        }
                        case 2:
                        {
                            boolean y=true;
                            try {
                                 y=employeeOperations.EmployeeLogin();;
                                 if(y==false){
                                    break;
                                }
                            } catch (SQLException e){}
                            break;
                        }
                        case 3:
                        {
                            System.out.println("Thank you\n"+z);
                            break;
                        }
                        default:
                        {
                            System.out.println(z+"\nPlease enter correct choice\n"+z);
                            break;
                        }
                    }
                }
                break;
                }
                case 3:
                {
                    int choice=0;
                    while(choice!=3)
                    {
                    System.out.println("Enter 1 -> New Account Registration");
                    System.out.println("Enter 2 -> Login to your Account");
                    System.out.println("Enter 3 -> Exit");
                    String tch;
                    while(true){
                    tch=s.nextLine();
                    if(validate.choicecheck(tch)){
                    break;
                        }
                    }
                    choice=Integer.parseInt(tch);
                    CustomerView customeroperation=new CustomerView();
                    switch(choice)
                    {
                        case 1:
                        {
                            customeroperation.AccountRegisration();
                            break;
                        }
                        case 2:
                        {
                            boolean y=true;
                            try {
                                 y=customeroperation.AccountLogin();
                                 if(y==false){
                                    break;
                                }
                            } catch (SQLException e){}

                            break;
                        }
                        case 3:
                        {
                            System.out.println(z+"\nThank you for banking with us\n"+z);
                            break;
                        }
                        default:
                        {
                            System.out.println(z+"\nPlease enter correct choice\n"+z);
                            break;
                        }
                    }
                }
                break;
            }
            case 4:
            {
                System.out.println(z+"\nThank you\n"+z);
                break;
            }
            default:
            {
                System.out.println("Enter input from 1-4");
            }
        }
    }
    }
}