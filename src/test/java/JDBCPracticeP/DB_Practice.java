// Muhammed Dulgeroglu
// DB_Practice.java
// August 1, 2021
// Practicing with JDBC connection using connection from DB_Utility.java

package JDBCPracticeP;

import JDBCPracticeP.DB_Utility;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DB_Practice {

    @Test
    public static void test1() {
        DB_Utility.createConnection();

        ResultSet rs = DB_Utility.runQuery("SELECT * FROM actor");

        int colCount = DB_Utility.colCount();
        int maxColv = DB_Utility.maxColCount();

        try {
            rs.beforeFirst();
            while (rs.next()) {
                System.out.print("|");
                for (int i = 1; i < colCount; i++) {
                    String res = String.format(" %-" + maxColv + "s| ", rs.getString(i));
                    System.out.print(res);
                }
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println("Program err");
        }
    }

    @Test
    public static void test2() {
        DB_Utility.createConnection();
        ResultSet rs = DB_Utility.runQuery("SELECT * FROM city");
        System.out.println(DB_Utility.displayRowAsList(6));
        //System.out.println(DB_Utility.displayByColAndRow(2, 2));
    }

    public static void test3() {
        DB_Utility.createConnection();
        ResultSet rs = DB_Utility.runQuery("SELECT * FROM actor")
    }

}
