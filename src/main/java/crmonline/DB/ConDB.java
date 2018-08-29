package crmonline.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConDB {
	
private static Connection con;
private static String dns = "jdbc:mysql://localhost:3306/db";
private static String user = "roott";
private static String pass = "";

public static Connection getConnection(){
	if(con == null){
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			con = DriverManager.getConnection(dns,user,pass);
			System.out.println("DB conectado com sucesso!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Erro ao conectar no DB");
			e.printStackTrace();
		}
	}
	return con;
}
}
