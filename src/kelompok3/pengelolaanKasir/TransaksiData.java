package kelompok3.pengelolaanKasir;

public class TransaksiData {

	String noresi;
	String sku;
	String username;
	String date; 
	String namaBarang; 
	Integer jumlah;
	Integer total;
	static Integer stock;
	
	public TransaksiData() {
		
	}
	
	public TransaksiData(String noresi, String username, String date, String nama, Integer jumlah, Integer total) {
		this.noresi=noresi;
		this.username=username;
		this.date=date;
		this.namaBarang=nama;
		this.jumlah=jumlah;
		this.total=total;
	}
	
	public TransaksiData(String noresi, String nama, Integer jumlah) {
		this.noresi=noresi;
		this.namaBarang=nama;
		this.jumlah=jumlah;
	}
	
	public TransaksiData(String sku, String nama, Integer jumlah, Integer harga) {
		this.sku=sku;
		this.namaBarang=nama;
		this.jumlah=jumlah;
		this.total=harga;
	}
	
}
