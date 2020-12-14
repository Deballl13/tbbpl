package kelompok3.pengelolaanKasir;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StockFunction {

	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/tbbpl?serverTimezone=Asia/Jakarta";
	static final String USERNAME = "root";
	static final String PASSWORD = "";
	
	static Connection conn;
	static Statement stmt;
	static PreparedStatement statement;
	
	public StockFunction(){
		
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("Koneksi tidak tersambung");
		}
		
	}
	
	
	//	Menambah stock barang
	public Integer restock(String sku, Integer restock) {
		
		Integer tambah = 0;
		Stock stock = new Stock();
		
		//	Cek SKU barang	
		try {
			
			String cek = "SELECT * FROM barang WHERE sku=?";
			statement = conn.prepareStatement(cek);
			statement.setString(1, sku);
			ResultSet result = statement.executeQuery();
			
			if(!result.next()) {
				
				System.out.println("SKU barang tidak ditemukan");
				stock.restock();
				
			} else {
				
				Integer jumlah = result.getInt("stock") + restock;
				
				String query = "UPDATE barang SET stock=? WHERE sku=?";
				statement = conn.prepareStatement(query);
				statement.setInt(1, jumlah);
				statement.setString(2, sku);
				tambah = statement.executeUpdate();
				
			}
			
		} catch (SQLException e) {
			System.out.println("Terjadi kesalahan");
		}
		
		return tambah;
	}
	
}
