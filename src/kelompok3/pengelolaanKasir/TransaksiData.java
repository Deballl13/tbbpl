package kelompok3.pengelolaanKasir;

public class TransaksiData {

	private String noresi;
	private String sku;
	private String username;
	private String date; 
	private String namaBarang; 
	private Integer jumlah;
	private Integer total;
	private static Integer stock;
	
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

	public String getNoresi() {
		return this.noresi;
	}

	public String getSku() {
		return this.sku;
	}

	public String getUsername() {
		return this.username;
	}

	public String getDate() {
		return date;
	}

	public String getNamaBarang() {
		return namaBarang;
	}

	public Integer getTotal() {
		return total;
	}

	public Integer getJumlah() {
		return jumlah;
	}

	public static Integer getStock() {
		return stock;
	}
	
}
