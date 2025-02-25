import java.sql.*;


public class Bank {

    Connection c;
    Statement s;

    public Bank(){
        try {
            //Class.forName(com.mysql.cj.jdbc.Driver);
            c = DriverManager.getConnection("jdbc:mysql:///bankmanagementsystem", "root","!@ann2024@!");
            s = c.createStatement();


        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
