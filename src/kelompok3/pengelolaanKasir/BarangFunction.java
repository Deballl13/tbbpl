package kelompok3.pengelolaanKasir;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.TreeSet;

public class BarangFunction {
	
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/tbbpl?serverTimezone=Asia/Jakarta";
	static final String USERNAME = "root";
	static final String PASSWORD = "";
	
	static Connection conn;
	static Statement stmt;
	static PreparedStatement statement;
	
	Barang barang;
	BarangData barangData;
	
	public BarangFunction(){
		
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("Koneksi tidak tersambung");
		}
		
	}
	
	
	//	Tambah data barang	
	public Integer tambahBarang(BarangData barangData) {
		
		Integer insert = 0;
		barang = new Barang();
		
		try {
			
			//	Cek apakah sku sudah ada atau belum
			
			String cek = "SELECT sku FROM barang WHERE sku = ?";
			statement = conn.prepareStatement(cek);
			statement.setString(1, barangData.sku);
			ResultSet result = statement.executeQuery();
			
			if(result.next()) {
				
				System.out.println("SKU sudah ada");
				barang.tambahData();
				
			} else {
				
				String query = "INSERT INTO barang VALUES(?,?,?,?,?)";
				statement = conn.prepareStatement(query);
				statement.setString(1, barangData.sku);
				statement.setString(2, barangData.nama);
				statement.setInt(3, barangData.stock);
				statement.setInt(4, barangData.harga_beli);
				statement.setInt(5, barangData.harga_jual);
				insert = statement.executeUpdate();
				
			}
			
		} catch (SQLException e) {
			System.out.println("Terjadi kesalahan");
		}
		
		return insert;
		
	}
	
	
	//	Edit data barang
	public Integer editBarang(BarangData barangData) {
		
		Integer update = 0;
		
		try {
			
			String query = "UPDATE barang SET nama=?, harga_jual=? WHERE sku=?";
			statement = conn.prepareStatement(query);
			statement.setString(1, barangData.nama);
			statement.setInt(2, barangData.harga_jual);
			statement.setString(3, barangData.sku);
			update = statement.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Terjadi kesalahan");
		}
		
		return update;
		
	}
	
	
	//	Hapus data barang
	public Integer hapusBarang(String sku) {
		
		Integer delete = 0;
		
		try {
			
			String query = "DELETE FROM barang WHERE sku=?";
			statement = conn.prepareStatement(query);
			statement.setString(1, sku);
			delete = statement.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Terjadi kesalahan");
		}
		
		return delete;
		
	}
	
	
	//	Cari data barang
	public ArrayList<BarangData> cariBarang(String nama){
		
		ArrayList<BarangData> carilist = new ArrayList<>();
		
		try {
			
			String query = "SELECT * FROM barang WHERE nama LIKE ?";
			statement = conn.prepareStatement(query);
			statement.setString(1, "%" + nama + "%");
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				barangData = new BarangData(
						result.getString("sku"),
						result.getString("nama"), 
						result.getInt("stock"),
						result.getInt("harga_beli"),
						result.getInt("harga_jual")
				);
				carilist.add(barangData);
			}
			
		} catch (SQLException e) {
			System.out.println("Terjadi kesalahan");
		}
		
		return carilist;
		
	}
	
	
	//	Lihat data barang
	public TreeMap<String, BarangData> lihatBarang(){
		
		TreeMap<String, BarangData> daftar = new TreeMap<>();
		
		try {
			
			String query = "SELECT * FROM barang";
			stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery(query);
			
			while(result.next()){
                barangData = new BarangData(
                		result.getString("sku"),
						result.getString("nama"), 
						result.getInt("stock"),
						result.getInt("harga_beli"),
						result.getInt("harga_jual")
                );
                daftar.put(result.getString("nama") ,barangData);
			}
			
		} catch (SQLException e) {
			System.out.println("Terjadi kesalahan");
		}
		
		return daftar;
		
	}

}
