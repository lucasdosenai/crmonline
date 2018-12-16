package crmonline.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConDB {
	
private static Connection con;
private static String dns = "jdbc:mysql://localhost:3306/banco?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
private static String user = "root";
private static String pass = "";

public static Connection getConnection(){
	if(con == null){
		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			con = DriverManager.getConnection(dns,user,pass);
			System.out.println("CRMONLINE CONECTADO COM SUCESSO!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("CRMONLINE FALHOU AO SE CONECTAR COM O SERVIDOR!");
			e.printStackTrace();
		}
	}
	return con;
}
}
