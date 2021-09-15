import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertTest {

	public static void main(String[] args) {
		try {
			DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
			System.out.println("Driver registered");
			
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "hr", "hr");
			
			System.out.println("Connected to Database " + conn);
		
			//this is the child of Statement interface
			PreparedStatement preparedStatement = 
					conn.prepareStatement("INSERT INTO EMP VALUES (?,?,?,?,?,?,?,?)");
			
			System.out.println("Select From Date");
			Scanner scan = new Scanner(System.in);
			String fromDate = scan.next();
			
			System.out.println("Select End Date");
			Scanner scan1 = new Scanner(System.in);
			String toDate = scan.next();


			String ename, mgr, hiredate, job;
			int empno, salary, dept, comm;
			
			scan.nextLine();
			empno = scan.nextInt();
			
			scan.nextLine();
			ename = scan.next();
			
			scan.nextLine();
			mgr = scan.next();
			
			scan.nextLine();
			hiredate = scan.next();
			
			scan.nextLine();
			salary = scan.nextInt();
			
			scan.nextLine();
			dept = scan.nextInt();
			
			scan.nextLine();
			comm = scan.nextInt();
			
			scan.nextLine();
			job = scan.next();
			
			preparedStatement.setInt(1, empno);
			preparedStatement.setString(2, ename);
			preparedStatement.setString(3, mgr);
			preparedStatement.setString(4, hiredate);
			preparedStatement.setString(5, job);
			preparedStatement.setInt(6, salary);
			preparedStatement.setInt(7, comm);
			preparedStatement.setInt(8, dept);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			System.out.println("EMPNO \tENAME \tHIREDATE");
			while(rs.next()) {
				System.out.println(rs.getInt("EMPNO") + "\t" + rs.getString("ENAME") +  "\t" +rs.getString("HIREDATE"));				
			}
			
			rs.close();
			preparedStatement.close();
			conn.close();
			} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
