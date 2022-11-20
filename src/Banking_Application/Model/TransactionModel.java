package Banking_Application.Model;
import java.sql.Date;

public class TransactionModel {
    private String sender_acc;
    private int transactionid;
    private double amount;
    private String type;
    private Date date;
    private String receiver_acc;
    
    public String getSender_acc() {
        return sender_acc;
    }
    public void setSender_acc(String sender_acc) {
        this.sender_acc = sender_acc;
    }
    public int getTransactionid() {
        return transactionid;
    }
    public void setTransactionid(int transactionid) {
        this.transactionid = transactionid;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public String getReceiver_acc() {
        return receiver_acc;
    }
    public void setReceiver_acc(String receiver_acc) {
        this.receiver_acc = receiver_acc;
    }
}
