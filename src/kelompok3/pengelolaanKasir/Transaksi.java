package kelompok3.pengelolaanKasir;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.Stack;
import java.util.TreeMap;

public class Transaksi implements Kelola {
	
	Date date = new Date();
	Scanner scn = new Scanner(System.in);
	TransaksiData transaksiData = new TransaksiData();
	TransaksiFunction transaksiFunction;
	Barang barang;
	NumberFormat format = NumberFormat.getCurrencyInstance();
	
	public Transaksi() {
		try {
			transaksiFunction = new TransaksiFunction();
		} catch (NullPointerException e) {
			System.out.println("Masukkan data terlebih dahulu");
		}
	}
	

	@Override
	public void tambahData() {
		
		String ada;
		TreeMap<String, Integer> jual = new TreeMap<>();
		
		barang = new Barang();
		barang.lihatData();

		System.out.println("\n\n--PENJUALAN--");
		
		String dt = String.format("%tF", date);
		
		do {
			
			System.out.print("Nama Barang : ");
			String nama = scn.nextLine();
			String namafix = nama.substring(0, 1).toUpperCase() + nama.substring(1);
			
			System.out.print("Jumlah : ");
			Integer jumlah = scn.nextInt();
			
			jual.put(namafix, jumlah);
			
			scn.nextLine();
			
			System.out.print("Ada lagi??..(y/t)");
			ada = scn.next();
			
			scn.nextLine();
			
		} while(ada.equalsIgnoreCase("y"));
		
		if(ada.equalsIgnoreCase("t")) {
		
			if(transaksiFunction.tambah(dt, jual) == 1) {
				System.out.println("Total : " + format.format(transaksiFunction.getTotal()));
				
				System.out.print("Jumlah uang : ");
				Integer uang = scn.nextInt();
				
				Faktur faktur = new Faktur();
				faktur.invoice(transaksiFunction.getNoresi(), transaksiFunction.nama, 
						transaksiFunction.jumlah, transaksiFunction.harga, 
						transaksiFunction.getTotal(), uang, transaksiFunction.getIndex());
				Menu.menuDashboard();
			}
			
			Menu.menuDashboard();
			
		}
		
	}

	@Override
	public void editData() {
		
		System.out.println("\n\n--EDIT DATA TRANSAKSI--");
		
		System.out.print("Noresi : ");
		String noresi = scn.next().toUpperCase();
		
		scn.nextLine();
		
		System.out.print("Nama Barang : ");
		String nama = scn.nextLine();
		String namafix = nama.substring(0, 1).toUpperCase() + nama.substring(1);
		
		System.out.print("Jumlah barang : ");
		Integer jumlah = scn.nextInt();
		
		transaksiData = new TransaksiData(noresi, namafix, jumlah);
		
		if(transaksiFunction.edit(transaksiData) == 1) {
			System.out.println("Transaksi berhasil diupdate");
			Menu.menuDashboard();
		} else {
			Menu.menuDashboard();
		}
		
	}

	@Override
	public void hapusData() {
		
		System.out.println("\n\n--HAPUS DATA TRANSAKSI--");
		
		System.out.print("Noresi : ");
		String noresi = scn.next().toUpperCase();
		
		if(transaksiFunction.hapus(noresi) == 1) {
			System.out.println("Transaksi berhasil dihapus");
			Menu.menuDashboard();
		} else {
			System.out.println("Noresi tidak ditemukan");
			Menu.menuDashboard();
		}
		
	}

	@Override
	public void cariData() {
		
		System.out.println("\n\n--PENCARIAN DATA TRANSAKSI--");
		
		System.out.print("Username : ");
		String username = scn.next();
		
		ArrayList<TransaksiData> listcari = transaksiFunction.cari(username);
		
		System.out.print("Noresi");
        System.out.print("\t");
        System.out.print("Username");
        System.out.print("\t");
        System.out.print("Tanggal");
        System.out.print("\t\t");
        System.out.print("Nama Barang");
        System.out.print("\t");
        System.out.print("Jumlah");
        System.out.print("\t\t");
        System.out.println("Total");
        
        for(TransaksiData list : listcari) {
        	
        	System.out.print(list.getNoresi());
            System.out.print("\t");
            System.out.print(list.getUsername());
            System.out.print("\t\t");
            System.out.print(list.getDate());
            System.out.print("\t");
            System.out.print(list.getNamaBarang());
            System.out.print("\t\t");
            System.out.print(list.getJumlah());
            System.out.print("\t\t");
            System.out.println(list.getHarga());
        	
        }
        
        Menu.tunggu();
		
	}

	@Override
	public void lihatData() {
		
		System.out.println("\n\n--DAFTAR TRANSAKSI--");
		
		Stack<TransaksiData> listTransaksi = transaksiFunction.lihat();
		
		System.out.print("Noresi");
        System.out.print("\t");
        System.out.print("Username");
        System.out.print("\t");
        System.out.print("Tanggal");
        System.out.print("\t\t");
        System.out.print("Nama Barang");
        System.out.print("\t");
        System.out.print("Jumlah");
        System.out.print("\t\t");
        System.out.println("Total");
        
        for(TransaksiData list : listTransaksi) {
        	
        	System.out.print(list.getNoresi());
            System.out.print("\t");
            System.out.print(list.getUsername());
            System.out.print("\t\t");
            System.out.print(list.getDate());
            System.out.print("\t");
            System.out.print(list.getNamaBarang());
            System.out.print("\t\t");
            System.out.print(list.getJumlah());
            System.out.print("\t\t");
            System.out.println(list.getHarga());
        	
        }
		
	}

}
