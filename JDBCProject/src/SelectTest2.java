import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


/*
 * Using preparedStatement interface to remove the use of 
 * inverted commas
 */
public class SelectTest2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
			System.out.println("Driver registered");
			
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "hr", "hr");
			
			System.out.println("Connected to Database " + conn);
		
			//this is the child of Statement interface
			PreparedStatement preparedStatement = 
					conn.prepareStatement("SELECT * FROM EMP WHERE HIREDATE BETWEEN ? AND ?");
			
			System.out.println("Select From Date");
			Scanner scan = new Scanner(System.in);
			String fromDate = scan.next();
			
			System.out.println("Select End Date");
			Scanner scan1 = new Scanner(System.in);
			String toDate = scan.next();
			
			preparedStatement.setString(1, fromDate);
			preparedStatement.setString(2, toDate);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			System.out.println("EMPNO \tENAME \tHIREDATE");
			while(rs.next()) {
				
				System.out.println(rs.getInt("EMPNO") + "\t" + rs.getString("ENAME") +  "\t" +rs.getString("JOB")
				+ "\t" + rs.getString("MGR") + "\t" + rs.getString("HIREDATE") + "\t" + rs.getString("SAL"));				
			}
			
			rs.close();
			preparedStatement.close();
			conn.close();
			} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
