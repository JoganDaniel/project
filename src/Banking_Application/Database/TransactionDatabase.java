package Banking_Application.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Banking_Application.Model.TransactionModel;

public class TransactionDatabase {
    private DBconnection dbconnection=new DBconnection();
    private Connection conn=dbconnection.getConnection();

    public void addToTransactionTable(TransactionModel transaction) throws SQLException
    {
        String query="insert into Transactions (Accountnumber,Transaction_id,Amount,Type,Date,Receiver_Accountnumber) values (?,?,?,?,?,?)";
        PreparedStatement ps=conn.prepareStatement(query);
        ps.setString(1,transaction.getSender_acc());
        ps.setInt(2,transaction.getTransactionid());
        ps.setDouble(3,transaction.getAmount());
        ps.setString(4,transaction.getType());
        ps.setDate(5,transaction.getDate());
        ps.setString(6,transaction.getReceiver_acc());

        ps.executeUpdate();
    }


}
