package kelompok3.pengelolaanKasir;

public class BarangData {

	String sku, nama;
	Integer stock, harga_beli, harga_jual;
	
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
	
}
