package Banking_Application.Database;
import java.sql.*;
public class DBconnection {
    
    private Connection con = null;
    public Connection getConnection(){
        if(con==null){
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BankDb","root","johandan77");
        }
        catch (SQLException e) {
            System.out.println(e);
        }
        }
    return con;
    }
}
