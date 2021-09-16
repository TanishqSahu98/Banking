import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
/*
 * 			JDBC 
 * 			  |
 *     		Driver -> interface
 *     |         |      |
 *     Oracle   MS    
 *     
 *     
 *     STEP 1 - DriverManager.registerDriver()  interface to load/register the driver
 * 	   STEP 2 - Using DriverManager.getConnection("dsn", username, pwd) -> connection
 *	   	  
 */
public class SelectTest {

	public static void main(String[] args) {
		
		try {
			DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
			System.out.println("Driver registered");
			
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "hr", "hr");
			
			System.out.println("Connected to Database " + conn);
		
			Statement stmt = conn.createStatement();
			System.out.println("Select Job to Search");
			Scanner scan = new Scanner(System.in);
				
			String job = scan.next();
					;
			String Q1 = "SELECT * FROM EMP WHERE JOB = '" + job + "'";
			
			ResultSet rs = stmt.executeQuery(Q1);
			
			System.out.println("EMPNO \tENAME \tSAL");
			while(rs.next()) {
				
				System.out.println(rs.getString("EMPNO") + "\t" + rs.getString("ENAME") +  "\t" +rs.getString("SAL"));				
			}
			
			
			System.out.println("Search joining b/w dates");
			
			scan = new Scanner(System.in);
			String fromDate = scan.next();
			
			scan.nextLine();
			
			String toDate = scan.next();
	
			rs = stmt.executeQuery("SELECT * FROM EMP WHERE HIREDATE BETWEEN '" + fromDate + "' AND '" + toDate + "'");
			while(rs.next()) {	
				System.out.println(rs.getString("EMPNO") + "\t" + rs.getString("ENAME") +  "\t" +rs.getString("DEPTNO") + "\t" +rs.getString("HIREDATE"));				
			}
			rs.close();
			stmt.close();
			conn.close();
			} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
