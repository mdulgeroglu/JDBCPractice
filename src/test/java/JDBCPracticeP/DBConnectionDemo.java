package JDBCPracticeP;

import org.testng.annotations.Test;

import java.sql.*;

public class DBConnectionDemo {
    private final String URL = "jdbc:mysql://localhost:3306/sakila";
    private final String USER = "root";
    private final String PASS = "password";


    @Test
    public void testDBConnection() throws SQLException {

        Connection connection = DriverManager.getConnection(URL, USER, PASS);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("SELECT * FROM actor");


        System.out.println(rowCount(resultSet));

        while (resultSet.next()){
            System.out.println(resultSet.getString("first_name") + " " + resultSet.getString("last_name"));
        }

        resultSet.close();
        statement.close();
        connection.close();
    }

    public int rowCount(ResultSet rs) {
        int i = 0;
        try {
            rs.last();
            i = rs.getRow();
        } catch (SQLException e) {}
        return i;
    }
}
