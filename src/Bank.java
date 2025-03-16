import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Bank {

    Connection c;
    Statement s;

    public Bank(){
        try {
            //Class.forName(com.mysql.cj.jdbc.Driver);
            c = DriverManager.getConnection("jdbc:mysql:///bankmanagementsystem", "root","khemchhun250306");
            s = c.createStatement();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public List<String> getTransactionHistory(String pin) {
        List<String> history = new ArrayList<>();
        try {
            String query = "SELECT * FROM bank WHERE pin = '" + pin + "'";
            ResultSet rs = s.executeQuery(query);
            while (rs.next()) {
                String transaction = "Date: " + rs.getString("date") + ", Type: " + rs.getString("type") + ", Amount: "
                        + rs.getString("amount");
                history.add(transaction);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return history;
    }
}
