package kelompok3.pengelolaanKasir;

public class BarangData {

	private String sku, nama;
	private Integer stock, harga_beli, harga_jual;
	
	public BarangData() {
		
	}
	
	public BarangData(String sku, String nama, Integer stock, Integer harga_beli, Integer harga_jual) {
		this.sku=sku;
		this.nama=nama;
		this.stock=stock;
		this.harga_beli=harga_beli;
		this.harga_jual=harga_jual;
	}
	
	public BarangData(String nama, Integer harga_jual) {
		this.nama=nama;
		this.harga_jual=harga_jual;
	}
	
	public BarangData(String nama, Integer stock, Integer harga_beli, Integer harga_jual) {
		this.nama=nama;
		this.stock=stock;
		this.harga_beli=harga_beli;
		this.harga_jual=harga_jual;
	}
	
	public String getSku() {
		return this.sku;
	}
	
	public String getNama() {
		return this.nama;
	}
	
	public Integer getStock() {
		return this.stock;
	}
	
	public Integer getBeli() {
		return this.harga_beli;
	}
	
	public Integer getJual() {
		return this.harga_jual;
	}
	
}
