package kelompok3.pengelolaanKasir;

import java.sql.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;

public class TransaksiFunction {
	
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/tbbpl?serverTimezone=Asia/Jakarta";
	static final String USERNAME = "root";
	static final String PASSWORD = "";
	
	static Connection conn;
	static Statement stmt;
	static PreparedStatement statement;
	TransaksiData transaksiData;
	UserData userData = new UserData();

	public TransaksiFunction(){
		
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("Koneksi tidak tersambung");
		}
		
	}
	
	
	//	Input data penjualan
	public Integer tambah(String date, TreeMap<String, Integer> jual) {
		
		Integer tambah = 0; 
		Integer i = 1;
		Integer total = 0;
		
		try {
			
			//	cek nomor resi terakhir
			String ambilresi = "SELECT noresi FROM transaksi "
					+ "ORDER BY CAST(SUBSTRING(noresi,2) AS INT) ASC";
			stmt= conn.createStatement();
			ResultSet result = stmt.executeQuery(ambilresi);
			
			while(result.next()) {
				
				String dapatresi = result.getString("noresi");
				String noresi = dapatresi.substring(1);
				
				if(i!=Integer.parseInt(noresi)) {
					break;
				}
				
				i++;
				
			}
			
			String noresifix = "T" + i.toString();
			
			String query = "INSERT INTO transaksi VALUES(?,?,?)";
			statement = conn.prepareStatement(query);
			statement.setString(1, noresifix);
			statement.setString(2, date);
			statement.setString(3, userData.getUser());
			statement.executeUpdate();
			
			for(Map.Entry list : jual.entrySet()) {
				
				//	ambil sku barang, stock, harga
				String ambilsku = "SELECT sku, stock, harga_jual FROM barang WHERE nama=?";
				statement = conn.prepareStatement(ambilsku);
				statement.setString(1, (String) list.getKey());
				ResultSet rs = statement.executeQuery();
				if(rs.next() && rs.getInt("stock")>0 && (rs.getInt("stock") - (Integer) list.getValue())>0) {
					
					// memasukkan data ke tabel detail
					String sql = "INSERT INTO detail_transaksi(sku, noresi, jumlah, harga) VALUES(?,?,?,?)";
					statement = conn.prepareStatement(sql);
					statement.setString(1, rs.getString("sku"));
					statement.setString(2, noresifix);
					statement.setInt(3, (Integer) list.getValue());
					statement.setInt(4, rs.getInt("harga_jual")*(Integer) list.getValue());
					tambah = statement.executeUpdate();
					
					total += (rs.getInt("harga_jual")*(Integer) list.getValue());
					
					//	mengurangi jumlah stock
					String stock = "UPDATE barang SET stock=? WHERE sku=?";
					statement = conn.prepareStatement(stock);
					statement.setInt(1, rs.getInt("stock")-(Integer) list.getValue());
					statement.setString(2, rs.getString("sku"));
					statement.executeUpdate();
					
				} else if(rs.next() && rs.getInt("stock")<=0 && (rs.getInt("stock") - (Integer) list.getValue())<=0){
					System.out.println("Stock " + list.getKey() + " tidak mencukupi");
				}else {
					System.out.println(list.getKey() + " tidak ada");
				}
				
			}
			
			System.out.println("Total belanja : " + total);
			
		} catch (SQLException e) {
			System.out.println("Terjadi kesalahan");
			e.printStackTrace();
		}
		
		return tambah;
		
	}
	
	
	//	Lihat data barang
	public Stack<TransaksiData> lihat(){
		
		Stack<TransaksiData> listTransaksi = new Stack<>();
		
		try {
			
			String query = "SELECT t.noresi, t.username, t.tanggal, "
					+ "b.nama, dt.jumlah, dt.harga FROM transaksi "
					+ "AS t INNER JOIN detail_transaksi AS dt ON "
					+ "t.noresi=dt.noresi INNER JOIN barang AS b ON dt.sku=b.sku";
			stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery(query);
			
			while(result.next()) {
				transaksiData = new TransaksiData(
						result.getString("noresi"),
						result.getString("username"), 
						result.getString("tanggal"),
						result.getString("nama"),
						result.getInt("jumlah"),
						result.getInt("harga")
				);
				listTransaksi.add((TransaksiData) transaksiData);
			}
			
		} catch (SQLException e) {
			System.out.println("Terjadi kesalahan");
		}
		
		return listTransaksi;
		
	}
	
	
	//	Hapus data transaksi
	public Integer hapus(String noresi) {
		
		Integer hapus = 0;
		
		try {
			
			String query = "DELETE FROM detail_transaksi WHERE noresi=?";
			statement = conn.prepareStatement(query);
			statement.setString(1, noresi);
			statement.executeUpdate();
			
			String sql = "DELETE FROM transaksi WHERE noresi=?";
			statement = conn.prepareStatement(sql);
			statement.setString(1, noresi);
			hapus = statement.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Terjadi kesalahan");
		}
		
		return hapus;
		
	}
	
	
	//	Cari data transaksi
	public ArrayList<TransaksiData> cari(String username){
		
		ArrayList<TransaksiData> cari = new ArrayList<>();
		
		try {
			
			String query = "SELECT t.noresi, t.username, t.tanggal, b.nama, "
					+ "dt.jumlah, dt.harga FROM transaksi AS t INNER JOIN detail_transaksi "
					+ "AS dt ON t.noresi=dt.noresi INNER JOIN barang AS b "
					+ "ON dt.sku=b.sku WHERE t.username LIKE ?";
			statement = conn.prepareStatement(query);
			statement.setString(1, "%" + username + "%");
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				transaksiData = new TransaksiData(
						result.getString("noresi"),
						result.getString("username"), 
						result.getString("tanggal"),
						result.getString("nama"),
						result.getInt("jumlah"),
						result.getInt("harga")
				);
				cari.add(transaksiData);
			}
			
		} catch (SQLException e) {
			System.out.println("Terjadi kesalahan");
		}
		
		return cari;
		
	}
	
	
	//	Edit data transaksi
	public Integer edit(TransaksiData transaksiData) {
		
		Integer edit = 0;
		try {
			
			//	cek sku dari barang
			String stock = "SELECT sku, stock FROM barang WHERE nama=?";
			statement = conn.prepareStatement(stock);
			statement.setString(1, transaksiData.getNamaBarang());
			ResultSet rsstock = statement.executeQuery();
			rsstock.next();
			
			//	cek sku dan jumlah
			String cek = "SELECT jumlah, sku FROM detail_transaksi WHERE noresi=? AND sku=?";
			statement = conn.prepareStatement(cek);
			statement.setString(1, transaksiData.getNoresi());
			statement.setString(2, rsstock.getString("sku"));
			ResultSet rscek = statement.executeQuery();
			
			if(rscek.next()) {
				
				String query = "UPDATE detail_transaksi SET jumlah=? WHERE noresi=? AND sku=?";
				statement = conn.prepareStatement(query);
				statement.setInt(1, transaksiData.getJumlah());
				statement.setString(2, transaksiData.getNoresi());
				statement.setString(3, rsstock.getString("sku"));
				edit = statement.executeUpdate();
				
				if(transaksiData.getJumlah() > rscek.getInt("jumlah")) {
					
					String barang = "UPDATE barang SET stock=? WHERE nama=?";
					statement = conn.prepareStatement(barang);
					statement.setInt(1, rsstock.getInt("stock") - (transaksiData.getJumlah()-rscek.getInt("jumlah")));
					statement.setString(2, transaksiData.getNamaBarang());
					statement.executeUpdate();
					
				} else if(transaksiData.getJumlah() < rscek.getInt("jumlah")) {
					
					String barang = "UPDATE barang SET stock=? WHERE nama=?";
					statement = conn.prepareStatement(barang);
					statement.setInt(1, rsstock.getInt("stock") + (rscek.getInt("jumlah")-transaksiData.getJumlah()));
					statement.setString(2, transaksiData.getNamaBarang());
					statement.executeUpdate();
					
				} else if (transaksiData.getJumlah() == rscek.getInt("jumlah")) {
					System.out.println("Jumlah barang sama saja");
				}
				
			} else {
				System.out.println("Transaksi gagal diupdate");
			}
				
		} catch (SQLException e) {
			System.out.println("Terjadi kesalahan");
			e.printStackTrace();
		}
		
		return edit;
		
	}

}
