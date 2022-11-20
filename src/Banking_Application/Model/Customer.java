package Banking_Application.Model;


public class Customer
{
private String name;
private String accnum;
private String address;
private long mobile;
private double balance;
private String username;
private String password;
private long aadhar;
private String pan;
private String addedby;
private String designation;


public Customer() {

}
public String getName() {
    return name;
}
public void setName(String name) {
    this.name = name;
}
public String getAccnum() {
    return accnum;
}
public void setAccnum(String accnum){
    this.accnum=accnum;
}
public String getAddress() {
    return address;
}
public void setAddress(String address) {
    this.address = address;
}
public long getMobile() {
    return mobile;
}
public void setMobile(long mobile) {
    this.mobile = mobile;
}
public double getBalance() {
    return balance;
}
public void setBalance(double balance) {
    this.balance = balance;
}
public String getUsername() {
    return username;
}
public void setUsername(String username) {
    this.username = username;
}
public String getPassword() {
    return password;
}
public void setPassword(String password) {
    this.password = password;
} 
public long getAadhar() {
    return aadhar;
}
public void setAadhar(long aadhar) {
    this.aadhar = aadhar;
}
public String getPan() {
    return pan;
}
public void setPan(String pan) {
    this.pan = pan;
}
public String getAddedby() {
    return addedby;
}
public void setAddedby(String addedby) {
    this.addedby = addedby;
}
public String getDesignation() {
    return designation;
}
public void setDesignation(String designation) {
    this.designation = designation;
}
}