package kelompok3.pengelolaanKasir;

import java.util.Scanner;

public class Stock {

	Scanner scn = new Scanner(System.in);
	StockFunction stockFunction;
	
	public Stock() {
		try {
			stockFunction = new StockFunction();
		} catch (NullPointerException e) {
			System.out.println("Masukkan data terlebih dahulu");
		}
	}
	
	
	//	Restok
	public void restock() {
		
		System.out.println("\n\n--RESTOCK BARANG--");
		
		System.out.print("SKU : ");
		String sku = scn.next();
		
		System.out.print("Stock baru : ");
		Integer stock = scn.nextInt();
		
		if(stockFunction.restock(sku, stock) == 1) {
			System.out.println("Restock success");
			Menu.menuDashboard();
		}
		
	}
	
	
}
