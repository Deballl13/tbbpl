package kelompok3.pengelolaanKasir;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.TreeMap;

public class BarangFunction {
	
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/tbbpl?serverTimezone=Asia/Jakarta";
	static final String USERNAME = "root";
	static final String PASSWORD = "";
	
	static Connection conn;
	static Statement stmt;
	static PreparedStatement statement;
	
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
		Integer i;
		
		try {
			
			//	cek nama barang
			String cek = "SELECT nama FROM barang WHERE nama=?";
			statement = conn.prepareStatement(cek);
			statement.setString(1, barangData.nama);
			ResultSet rscek = statement.executeQuery();
			
			if(!rscek.next()) {
				
				String ambilsku = "SELECT sku FROM barang WHERE sku IN (SELECT MAX(sku) FROM barang)";
				stmt= conn.createStatement();
				ResultSet result = stmt.executeQuery(ambilsku);
				
				if(!result.next()) {
					String dapatsku = result.getString("sku");
					String sku = dapatsku.substring(1);
					i = Integer.parseInt(sku);
					i++;
				} else {
					i=1;
				}
				
				String skufix = "B0" + i.toString();
				
				String query = "INSERT INTO barang VALUES(?,?,?,?,?)";
				statement = conn.prepareStatement(query);
				statement.setString(1, skufix);
				statement.setString(2, barangData.nama);
				statement.setInt(3, barangData.stock);
				statement.setInt(4, barangData.harga_beli);
				statement.setInt(5, barangData.harga_jual);
				insert = statement.executeUpdate();
				
			} else {
				System.out.println("Barang sudah terdaftar");
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
		barangData = new BarangData();
		
		try {
			
			String cekNama = "SELECT nama FROM barang WHERE sku=?";
			statement = conn.prepareStatement(cekNama);
			statement.setString(1, sku);
			ResultSet result = statement.executeQuery();
			result.next();
			
			String query = "DELETE FROM barang WHERE sku=?";
			statement = conn.prepareStatement(query);
			statement.setString(1, sku);
			delete = statement.executeUpdate();
			
			if(delete == 1) {
				barangData.stockBarang.remove(result.getString("nama"));
			}
			
		} catch (SQLException e) {
			System.out.println("Terjadi kesalahan");
			e.printStackTrace();
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
