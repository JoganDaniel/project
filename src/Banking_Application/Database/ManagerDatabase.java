package Banking_Application.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Banking_Application.Model.Manager;

public class ManagerDatabase {
    private DBconnection dbconnection=new DBconnection();
    private Connection conn=dbconnection.getConnection();

    public Manager getManager(String user) throws SQLException
    {
        Manager manager=new Manager();
        String query="select * from Manager where Username =(?)";
        PreparedStatement ps=conn.prepareStatement(query);
        ps.setString(1, user);
        ResultSet rs=ps.executeQuery();
        while(rs.next())
        {
            manager.setName(rs.getString("Name"));
            manager.setEmployee_id(rs.getInt("Employee_id"));
            manager.setMobile(rs.getLong("Mobile"));
            manager.setUsername(rs.getString("Username"));
            manager.setPassword(rs.getString("Password"));
        }
        return manager;
    }

    public void updateManager(Manager manager) throws SQLException
    {
        String query="update Manager set Name=?, Employee_id=?, Mobile=? ,Username=?, Password=? where Username= ?";
        PreparedStatement ps=conn.prepareStatement(query);
        ps.setString(1,manager.getName());
        ps.setInt(2,manager.getEmployee_id());
        ps.setLong(3, manager.getMobile());
        ps.setString(4, manager.getUsername());
        ps.setString(5, manager.getPassword());

        ps.executeUpdate();
        }
}
