package kelompok3.pengelolaanKasir;

import java.util.TreeMap;

public class BarangData {

	String sku, nama;
	Integer stock, harga_beli, harga_jual;
	TreeMap<String, Integer> stockBarang = new TreeMap<>();
	
	public BarangData() {
		
	}
	
	public BarangData(String sku, String nama, Integer stock, Integer harga_beli, Integer harga_jual) {
		this.sku=sku;
		this.nama=nama;
		this.stock=stock;
		this.harga_beli=harga_beli;
		this.harga_jual=harga_jual;
	}
	
	public BarangData(String sku, String nama, Integer harga_jual) {
		this.sku=sku;
		this.nama=nama;
		this.harga_jual=harga_jual;
	}
	
	public BarangData(String nama, Integer stock, Integer harga_beli, Integer harga_jual) {
		this.nama=nama;
		this.stock=stock;
		this.harga_beli=harga_beli;
		this.harga_jual=harga_jual;
	}
	
	public BarangData(String nama, Integer jumlah) {
		this.stockBarang.put(nama, jumlah);
	}
	
}
