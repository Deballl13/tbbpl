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
		Integer i = 1;
		
		if(barangData.getStock()>0 && barangData.getBeli()>0 && barangData.getJual()>0) {
			
			try {
				
				//	cek nama barang
				String cek = "SELECT nama FROM barang WHERE nama=?";
				statement = conn.prepareStatement(cek);
				statement.setString(1, barangData.getNama());
				ResultSet rscek = statement.executeQuery();
				
				if(!rscek.next()) {
					
					String ambilsku = "SELECT sku FROM barang "
							+ "ORDER BY CAST(SUBSTRING(sku,3) AS INT) ASC";
					stmt= conn.createStatement();
					ResultSet result = stmt.executeQuery(ambilsku);
					
					while(result.next()) {
						
						String dapatsku = result.getString("sku");
						String sku = dapatsku.substring(2);
						
						if(i!=Integer.parseInt(sku)) {
							break;
						}
						
						i++;
						
					}
					
					String skufix = "BR" + i.toString();
					
					String query = "INSERT INTO barang VALUES(?,?,?,?,?)";
					statement = conn.prepareStatement(query);
					statement.setString(1, skufix);
					statement.setString(2, barangData.getNama());
					statement.setInt(3, barangData.getStock());
					statement.setInt(4, barangData.getBeli());
					statement.setInt(5, barangData.getJual());
					insert = statement.executeUpdate();
					
				} else {
					System.out.println("Barang sudah terdaftar");
				}
				
			} catch (SQLException e) {
				System.out.println("Terjadi kesalahan");
				e.printStackTrace();
			}
			
		} else if(barangData.getStock()<=0) {
			System.out.println("Stock tidak boleh kecil sama dari 0");
		} else if(barangData.getBeli()<=0 && barangData.getJual()<=0) {
			System.out.println("Harga tidak boleh kecil sama dari 0");
		}
		
		return insert;
		
	}
	
	
	//	Edit data barang
	public Integer editBarang(BarangData barangData) {
		
		Integer update = 0;
		
		if(barangData.getJual()>0) {
			
			try {
				
				String query = "UPDATE barang SET nama=?, harga_jual=? WHERE sku=?";
				statement = conn.prepareStatement(query);
				statement.setString(1, barangData.getNama());
				statement.setInt(2, barangData.getJual());
				statement.setString(3, barangData.getSku());
				update = statement.executeUpdate();
				
			} catch (SQLException e) {
				System.out.println("Terjadi kesalahan");
			}
			
		} else {
			System.out.println("Harga tidak boleh kecil sama dari 0");
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
