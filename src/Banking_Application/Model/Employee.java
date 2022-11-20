package Banking_Application.Model;

public class Employee {
    private String Name;
    private int Employee_id;
    private String Address;
    private long Mobile;
    private String Pan;
    private String Username;
    private String Password;


    public Employee(){}
    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }
    public int getEmployee_id() {
        return Employee_id;
    }
    public void setEmployee_id(int employee_id) {
        Employee_id = employee_id;
    }
    public String getAddress() {
        return Address;
    }
    public void setAddress(String address) {
        Address = address;
    }
    public long getMobile() {
        return Mobile;
    }
    public void setMobile(long mobile) {
        Mobile = mobile;
    }
    public String getPan() {
        return Pan;
    }
    public void setPan(String pan) {
        Pan = pan;
    }
    public String getUsername() {
        return Username;
    }
    public void setUsername(String username) {
        Username = username;
    }
    public String getPassword() {
        return Password;
    }
    public void setPassword(String password) {
        Password = password;
    }
    

}