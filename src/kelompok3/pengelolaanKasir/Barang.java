package kelompok3.pengelolaanKasir;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Barang implements Kelola{
	
	Scanner scn = new Scanner(System.in);
	BarangData barangData; // mendeklarasikan variabel barangData dengan tipe data BarangData
	BarangFunction barangFunction;
	
	// constructor
	public Barang() {
		try {
			barangFunction = new BarangFunction();
		} catch (NullPointerException e) {
			System.out.println("Masukkan data terlebih dahulu");
		}
	}
	
	
	@Override
	public void tambahData() {
		System.out.println("\n\n--TAMBAH DATA--");
		
		System.out.print("Nama barang : ");
		String nama = scn.nextLine();
		String namafix = nama.substring(0, 1).toUpperCase() + nama.substring(1);
		
		System.out.print("Stock : ");
		Integer stock = scn.nextInt();
		
		System.out.print("Harga beli : ");
		Integer harga_beli = scn.nextInt();
		
		System.out.print("Harga jual : ");
		Integer harga_jual = scn.nextInt();
		
		barangData = new BarangData(namafix, stock, harga_beli, harga_jual);
		
		if(barangFunction.tambahBarang(barangData) == 1) {
			System.out.println("Barang berhasil ditambahkan");
			Menu.menuDashboard();
		} else {
			Menu.menuDashboard();
		}
		
	}
	
	@Override
	public void editData() {
		
		System.out.println("\n\n--EDIT DATA BARANG--");
		
		System.out.print("Nama barang : ");
		String nama = scn.nextLine();
		String namafix = nama.substring(0, 1).toUpperCase() + nama.substring(1);
		
		System.out.print("Harga jual : ");
		Integer harga_jual = scn.nextInt();
		
		barangData = new BarangData(namafix, harga_jual);
		
		if(barangFunction.editBarang(barangData) == 1) {
			System.out.println("Data berhasil diupdate");
			Menu.menuDashboard();
		} else {
			System.out.println("SKU tidak ditemukan");
			Menu.menuDashboard();
		}
		
	}
	
	@Override
	public void hapusData() {
		
		System.out.println("\n\n--HAPUS DATA BARANG--");
		
		System.out.print("sku : ");
		String sku = scn.next().toUpperCase();
		
		if(barangFunction.hapusBarang(sku) == 1) {
			System.out.println("Data berhasil dihapus");
			Menu.menuDashboard();
		} else {
			System.out.println("SKU tidak ditemukan");
			Menu.menuDashboard();
		}
		
	}
	
	@Override
	public void cariData() {
		
		System.out.println("\n\n--PENCARIAN--");
		
		System.out.print("Nama barang : ");
		String nama = scn.nextLine();
		
		ArrayList<BarangData> searchList = barangFunction.cariBarang(nama);
		
		System.out.print("SKU");
        System.out.print("\t");
        System.out.print("Nama Barang");
        System.out.print("\t");
        System.out.print("Stock");
        System.out.print("\t\t");
        System.out.print("Harga Beli");
        System.out.print("\t");
        System.out.println("Harga Jual");
		
		for(BarangData barangData : searchList){
            System.out.print(barangData.getSku());
            System.out.print("\t");
            System.out.print(barangData.getNama());
            System.out.print("\t\t");
            System.out.print(barangData.getStock());
            System.out.print("\t\t");
            System.out.print(barangData.getBeli());
            System.out.print("\t\t");
            System.out.println(barangData.getJual());
        }
		
		Menu.tunggu();
		
	}
	
	@Override
	public void lihatData() {
		
		System.out.println("\n\n--DAFTAR BARANG--");
		
		TreeMap<String, BarangData> daftar = barangFunction.lihatBarang();
		
		System.out.print("SKU");
        System.out.print("\t");
        System.out.print("Nama Barang");
        System.out.print("\t\t");
        System.out.print("Stock");
        System.out.print("\t\t");
        System.out.print("Harga Beli");
        System.out.print("\t");
        System.out.println("Harga Jual");
        
        for(Map.Entry list : daftar.entrySet()){
            BarangData listUser = (BarangData) list.getValue();
            
            System.out.print(listUser.getSku());
            System.out.print("\t");
            System.out.print(list.getKey());
            System.out.print("\t\t");
            System.out.print(listUser.getStock());
            System.out.print("\t\t");
            System.out.print(listUser.getBeli());
            System.out.print("\t\t");
            System.out.println(listUser.getJual());
            
        }
		
	}

	
		
	
}



