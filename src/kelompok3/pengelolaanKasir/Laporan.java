package kelompok3.pengelolaanKasir;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.TreeMap;

public class Laporan {

	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/tbbpl?serverTimezone=Asia/Jakarta";
	static final String USERNAME = "root";
	static final String PASSWORD = "";
	
	static Connection conn;
	static Statement stmt;
	static PreparedStatement statement;
	
	TransaksiData transaksiData;

	public Laporan(){
		
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("Koneksi tidak tersambung");
		}
		
	}
	
	
	//	Laporan penjualan perbulan
	public void laporanBulanan(){
		
		TreeMap<String, TransaksiData> penjualan = new TreeMap<>();
		
		try {
			
			File file = new File("Laporan Penjualan Bulanan.txt");
			PrintWriter cetak = new PrintWriter(new FileWriter(file, false));
			
			String query = "SELECT t.tanggal, b.sku, b.nama, "
					+ "SUM(IF(YEAR(t.tanggal)=YEAR(now()) AND "
					+ "MONTH(t.tanggal)=MONTH(now()), dt.jumlah, 0)) "
					+ "AS jumlah, SUM(IF(YEAR(t.tanggal)=YEAR(now()) "
					+ "AND MONTH(t.tanggal)=MONTH(now()), dt.harga, 0)) "
					+ "AS harga FROM transaksi AS t INNER JOIN "
					+ "detail_transaksi AS dt ON t.noresi=dt.noresi "
					+ "INNER JOIN barang AS b ON dt.sku=b.sku GROUP BY b.nama";
			stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery(query);
			
			while(result.next()) {
				transaksiData = new TransaksiData(
						result.getString("sku"),
						result.getString("nama"),
						result.getInt("jumlah"),
						result.getInt("harga")
				);
				penjualan.put(result.getString("sku"), transaksiData);
			}
			
			cetak.print("SKU");
	        cetak.print("\t");
	        cetak.print("Nama Barang");
	        cetak.print("\t");
	        cetak.print("Jumlah");
	        cetak.print("\t\t");
	        cetak.println("Total Penjualan");
	        
	        for(Map.Entry list : penjualan.entrySet()){
	            TransaksiData listLapor = (TransaksiData) list.getValue();
	            
	            cetak.print(list.getKey());
	            cetak.print("\t");
	            cetak.print(listLapor.getNamaBarang());
	            cetak.print("\t\t");
	            cetak.print(listLapor.getJumlah());
	            cetak.print("\t\t");
	            cetak.println(listLapor.getTotal());
	            
	        }
	        
	        System.out.println("Laporan penjualan bulanan sudah dicetak");
	        
	        cetak.close();
			
		} catch (SQLException e) {
			System.out.println("Terjadi kesalahan pada query data");
		} catch (IOException e) {
			System.out.println("Terjadi kesalahan pada laporan");
		}
		
	}
	
	
	//	Laporan penjualan harian
	public void laporanHarian() {
		
		TreeMap<String, TransaksiData> penjualan = new TreeMap<>();
		String tanggal = null;
		
		try {
			
			File file = new File("Laporan Penjualan Harian.txt");
			PrintWriter cetak = new PrintWriter(new FileWriter(file, false));
			
			String query = "SELECT t.tanggal, b.sku, b.nama, " + 
					"SUM(IF(YEAR(t.tanggal)=YEAR(now()) AND "
					+ "MONTH(t.tanggal)=MONTH(now()) AND "
					+ "DAY(t.tanggal)=DAY(t.tanggal), dt.jumlah, 0)) "
					+ "AS jumlah, SUM(IF(YEAR(t.tanggal)=YEAR(now()) "
					+ "AND MONTH(t.tanggal)=MONTH(now()) AND "
					+ "DAY(t.tanggal)=DAY(t.tanggal), dt.harga, 0)) "
					+ "AS harga FROM transaksi AS t INNER JOIN "
					+ "detail_transaksi AS dt ON t.noresi=dt.noresi "
					+ "INNER JOIN barang AS b ON dt.sku=b.sku WHERE "
					+ "YEAR(t.tanggal)=YEAR(now()) AND "
					+ "MONTH(T.tanggal)=MONTH(now()) "
					+ "GROUP BY t.tanggal, b.nama";
			stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery(query);
			
			while(result.next()) {
				
				if(tanggal!=null && !result.getString("tanggal").equals(tanggal)) {
					
					cetak.println("Tanggal " + tanggal);
					cetak.print("SKU");
			        cetak.print("\t");
			        cetak.print("Nama Barang");
			        cetak.print("\t");
			        cetak.print("Jumlah");
			        cetak.print("\t\t");
			        cetak.println("Total Penjualan");
					
					for(Map.Entry list : penjualan.entrySet()){
			            TransaksiData listLapor = (TransaksiData) list.getValue();
			            
			            cetak.print(list.getKey());
			            cetak.print("\t");
			            cetak.print(listLapor.getNamaBarang());
			            cetak.print("\t\t");
			            cetak.print(listLapor.getJumlah());
			            cetak.print("\t\t");
			            cetak.println(listLapor.getTotal());
			            
			        }
			        
			        cetak.println();
			        penjualan.clear();
					
				}

				transaksiData = new TransaksiData(
						result.getString("sku"),
						result.getString("nama"),
						result.getInt("jumlah"),
						result.getInt("harga")
				);
				
				tanggal = result.getString("tanggal");
				penjualan.put(result.getString("sku"), transaksiData);
				
			}
			
			cetak.println("Tanggal " + tanggal);
			cetak.print("SKU");
	        cetak.print("\t");
	        cetak.print("Nama Barang");
	        cetak.print("\t");
	        cetak.print("Jumlah");
	        cetak.print("\t\t");
	        cetak.println("Total Penjualan");
			
			for(Map.Entry list : penjualan.entrySet()){
	            TransaksiData listLapor = (TransaksiData) list.getValue();
	            
	            cetak.print(list.getKey());
	            cetak.print("\t");
	            cetak.print(listLapor.getNamaBarang());
	            cetak.print("\t\t");
	            cetak.print(listLapor.getJumlah());
	            cetak.print("\t\t");
	            cetak.println(listLapor.getTotal());
	            
	        }
	        
	        cetak.println();
	        penjualan.clear();
			
	        System.out.println("Laporan penjualan harian sudah dicetak");
	        
	        cetak.close();
			
		} catch (SQLException e) {
			System.out.println("Terjadi kesalahan pada query data");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Terjadi kesalahan pada laporan");
		}
		
	}
	
	
	//	Keuntungan perbulan
	public void untungBulan() {
		
		Integer untung = 0;
		
		try {
			
			File file = new File("Laporan Keuntungan Bulanan.txt");
			PrintWriter cetak = new PrintWriter(new FileWriter(file, false));
			
			String query = "SELECT t.tanggal, b.nama, "
					+ "SUM(IF(YEAR(t.tanggal)=YEAR(now()) "
					+ "AND MONTH(t.tanggal)=MONTH(now()), "
					+ "dt.jumlah, 0)) AS jumlah, "
					+ "SUM(IF(YEAR(t.tanggal)=YEAR(now()) "
					+ "AND MONTH(t.tanggal)=MONTH(now()), "
					+ "dt.harga, 0)) AS harga, b.harga_beli, "
					+ "b.harga_jual FROM transaksi AS t "
					+ "INNER JOIN detail_transaksi AS dt "
					+ "ON t.noresi=dt.noresi INNER JOIN barang "
					+ "AS b ON dt.sku=b.sku GROUP BY b.nama";
			stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery(query);
			while(result.next()) {
				
				untung += (result.getInt("jumlah") * (result.getInt("harga_jual")-result.getInt("harga_beli")));
				
			}
		
			cetak.println("Untung bulan ini = " + untung);
			
			System.out.println("Silahkan cek keuntungan anda!!");
			
			cetak.close();
			
		} catch (SQLException e) {
			System.out.println("Terjadi kesalahan pada query data");
		} catch (IOException e) {
			System.out.println("Terjadi kesalahan pada laporan");
		}
		
	}
	
	
	//	Laporan keuntungan harian
	public void untungHarian() {
		
		Integer untung = 0;
		String tanggal = null;
		
		try {
			
			File file = new File("Laporan Keuntungan Harian.txt");
			PrintWriter cetak = new PrintWriter(new FileWriter(file, false));
			
			String query = "SELECT DAY(t.tanggal) AS tanggal, b.nama, " + 
					"SUM(IF(YEAR(t.tanggal)=YEAR(now()) "
					+ "AND MONTH(t.tanggal)=MONTH(now()) "
					+ "AND DAY(t.tanggal)=DAY(t.tanggal), "
					+ "dt.jumlah, 0)) AS jumlah, \r\n" + 
					"SUM(IF(YEAR(t.tanggal)=YEAR(now()) "
					+ "AND MONTH(t.tanggal)=MONTH(now()) "
					+ "AND DAY(t.tanggal)=DAY(t.tanggal), "
					+ "dt.harga, 0)) AS harga,\r\n" + 
					"b.harga_beli, b.harga_jual\r\n" + 
					"FROM transaksi AS t INNER JOIN "
					+ "detail_transaksi AS dt ON "
					+ "t.noresi=dt.noresi INNER JOIN barang "
					+ "AS b ON dt.sku=b.sku WHERE "
					+ "YEAR(t.tanggal)=YEAR(now()) "
					+ "AND MONTH(T.tanggal)=MONTH(now()) "
					+ "GROUP BY t.tanggal, b.nama";
			stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery(query);
			
			while(result.next()) {
				
				if(tanggal !=null && !result.getString("tanggal").equals(tanggal)) {
						
					cetak.println("Keuntungan tanggal " + tanggal + " = " + untung);
					untung = 0;
					
				}
				
				tanggal = result.getString("tanggal");
				untung += (result.getInt("jumlah") * (result.getInt("harga_jual")-result.getInt("harga_beli")));
				
			}
			
			cetak.println("Keuntungan tanggal " + tanggal + " = " + untung);
			cetak.close();
			
		} catch (SQLException e) {
			System.out.println("Terjadi kesalahan pada query data");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Terjadi kesalahan pada laporan");
		}
		
	}
	
}
