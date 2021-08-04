package JDBCPracticeP;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DB_Utility {
    private static Connection conn;
    private static ResultSet rs;

    // Creates connection to db
    public static void createConnection(){
        final String URL = "jdbc:mysql://localhost:3306/sakila";
        final String USER = "root";
        final String PASS = "password";

        try {
            conn = DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            System.out.println("conn err");
        }
    }

    // Queries the db | Accepts String input and returns ResultSet
    public static ResultSet runQuery(String query) {

        try {
            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = statement.executeQuery(query);
        } catch (SQLException e) {
            System.out.println("sql statement err");
        }

        return rs;
    }

    // get number of columns returned from query using ResultSetMetaData
    public static int colCount() {
        int colCount = 0;
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            colCount = rsmd.getColumnCount();
        } catch (SQLException e) {
            System.out.println("colCount err");
        }

        return colCount;
    }

    // iterate through query to find lenght of longest
    public static int maxColCount() {
        int max = 0;
        int sss = colCount();
        try {
            rs.beforeFirst();
            while (rs.next()){
                for (int x = 1; x <= sss; x++) {
                    int cur = rs.getString(x).length();
                    if (cur > max) {max = cur;};
                }
            }
            rs.beforeFirst();
        } catch (SQLException e) {
            System.out.println("maxColCount err");
        }

        return max;
    }

    public static void displayAllRows(){
        try {
            rs.beforeFirst();
            while (rs.next()) {
                for (int i = 1; i <= colCount(); i++) {
                    System.out.print("| " + rs.getString(i));
                }
                System.out.println();
            }
            rs.beforeFirst();
        } catch (SQLException e) {
            System.out.println("displayAllRows err");
        }

    }

    public static String displayByColAndRow(int rowNum, int colNum) {
        String res = "";
        try {
            rs.absolute(rowNum);
            res = rs.getString(colNum);
        } catch (SQLException e) {
            System.out.println("displayByColAndRow err");
        }
        return res;
    }

    public static List<String> displayRowAsList(int rowNum) {
        List<String> rows = new ArrayList<>();
        try {
            rs.absolute(rowNum);
            for (int i = 1; i < colCount(); i++) {
                rows.add(rs.getString(i));
            }
            rs.beforeFirst();
        } catch (SQLException e) {
            System.out.println("displayRowAsList err");
        }
        return rows;
    }


}
