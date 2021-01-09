package kelompok3.pengelolaanKasir;

public class TransaksiData {

	private String noresi;
	private String sku;
	private String username;
	private String date; 
	private String namaBarang; 
	private Integer jumlah;
	private Integer total;
	private Integer harga;
	
	public TransaksiData() {
		
	}
	
	public TransaksiData(String noresi, String username, String date, String nama, Integer jumlah, Integer harga) {
		this.noresi=noresi;
		this.username=username;
		this.date=date;
		this.namaBarang=nama;
		this.jumlah=jumlah;
		this.harga=harga;
	}
	
	public TransaksiData(String noresi, String nama, Integer jumlah) {
		this.noresi=noresi;
		this.namaBarang=nama;
		this.jumlah=jumlah;
	}
	
	public TransaksiData(String sku, String nama, Integer jumlah, Integer total) {
		this.sku=sku;
		this.namaBarang=nama;
		this.jumlah=jumlah;
		this.total=total;
	}

	public String getNoresi() {
		return this.noresi;
	}
	
	public void setNoresi(String noresi) {
		this.noresi=noresi;
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
	
	public void setTotal(Integer total) {
		
		if(this.total==null) {
			this.total=total;
		} else {
			this.total+=total;
		}
		
	}

	public Integer getJumlah() {
		return jumlah;
	}
	
	public String sku(){
		return this.sku;
	}
	
	public Integer getHarga() {
		return this.harga;
	}
	
}
