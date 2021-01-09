package kelompok3.pengelolaanKasir;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public class Faktur {

	Date date = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat();
	TransaksiData transaksiData = new TransaksiData();
	TransaksiFunction transaksiFunction = new TransaksiFunction();
	BarangData barangData = new BarangData();
	NumberFormat format = NumberFormat.getCurrencyInstance();
	
	public void invoice(String noresi, String[] nama, Integer[] jumlah, Integer[] harga, 
			Integer total, Integer uang, Integer index) {
		
		System.out.println("\n--INVOICE--");
		System.out.println("-----------");
		System.out.println("noresi : " + noresi);
		System.out.println("List Barang : ");
		System.out.println(nama.length);
		
		for(int i=0; i<index; i++) {
			
			System.out.println(nama[i]);
			System.out.println(jumlah[i] + " x " + format.format(harga[i]) + "\t" + 
			format.format(jumlah[i]*harga[i]));
			
		}
		
		System.out.println("Grand Total :   " + format.format(total));
		System.out.println("Jumlah Uang :   " + format.format(uang));
		
		Integer kembalian = uang-total;
		System.out.println("Kembalian   :   " + format.format(kembalian));
		
	}
	
}
