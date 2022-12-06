package Banking_Application.Controller;
import java.sql.SQLException;
import java.util.Random;

import Banking_Application.Database.ManagerDatabase;
import Banking_Application.Database.TransactionDatabase;
import Banking_Application.Model.Manager;
import Banking_Application.Model.TransactionModel;
import Banking_Application.View.BankView;

public class BankControl
{
    public static void main(String[] args) {
        BankView ob=new BankView();
        ob.app();
    }

    public void addToTransactionTable(String sender_acc,double amount,String type,String receiver_acc)
        {
            TransactionModel transaction=new TransactionModel();
            TransactionDatabase transactionDatabase=new TransactionDatabase();
            try {
                Random rand = new Random();
                int transactionid=0,n=0;
                for(int i =0; i < 10;i++)
                {
                n = Math.abs(rand.nextInt(10));
                
                if(i==0 && n==0){
                    n=3;
                }
                if(n<0){
                    n=7;
                }
                transactionid=Math.abs(transactionid*10+n);         
                }
                java.util.Date javaDate = new java.util.Date();
                java.sql.Date dbDate = new java.sql.Date(javaDate.getTime());
                transaction.setSender_acc(sender_acc);
                transaction.setTransactionid(transactionid);
                transaction.setAmount(amount);
                transaction.setType(type);
                transaction.setDate(dbDate);
                transaction.setReceiver_acc(receiver_acc);
                transactionDatabase.addToTransactionTable(transaction);
                } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        ManagerDatabase managerDatabase=new ManagerDatabase();
        public Manager getManager(String user) throws SQLException
        {
            return managerDatabase.getManager(user);
        }
}