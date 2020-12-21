package kelompok3.pengelolaanKasir;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

	static Date date = new Date();
	static Scanner scanner = new Scanner(System.in);
	
	public static Integer landingPage() {
		
		Integer pilihan = 0;
		
		System.out.println("     Toko Selamat Pagi");
		System.out.println("============================");
		System.out.println(date+"\n");
		System.out.println("1. Login");
		System.out.println("2. Register");
		System.out.print("Tentukan pilihanmu : ");
		pilihan = scanner.nextInt();
		
		return pilihan;
	}
	
	
	public static void menuDashboard() {
		
		Transaksi transaksi = new Transaksi();
		Laporan laporan = new Laporan();
		
		System.out.println("\n\n--DASHBOARD--");
		System.out.println("1. Pengaturan");
		System.out.println("2. Data Master Transaksi");
		System.out.println("3. Data Master Barang");
		System.out.println("4. Penjualan");
		System.out.println("5. Laporan");
		System.out.println("6. Logout");
		System.out.print("Tentukan pilihanmu : ");
		
		try {
			
			Integer pilihan = scanner.nextInt();
			
			switch(pilihan) {
				case 1:
					pengaturanAkun();
					break;
					
				case 2:
					menuTransaksi();
					break;
					
				case 3:
					menuBarang();
					break;
					
				case 4: 
					transaksi.tambahData();
					break;
					
				case 5:
					laporan.laporanBulanan();
					laporan.laporanHarian();
					laporan.untungBulan();
					laporan.untungHarian();
					Menu.tunggu();
					break;
					
				case 6:
					Login.ulang=2;
					System.out.println("\n");
					Program.main(null);
					break;
					
				default:
					System.out.println("Pilihan tidak tersedia");
					menuDashboard();
					break;
			}
			
		} catch (InputMismatchException e) {
			System.out.println("Masukkan input dengan benar");
		
		}
		
	}
	
		
	public static void pengaturanAkun() {
		
		User user = new User();
		
		System.out.println("\n\n--PENGATURAN AKUN--");
		System.out.println("1. Ubah Password");
		System.out.println("2. Hapus Akun");
		System.out.println("3. Lihat Daftar User");
		System.out.println("4. Cari User");
		System.out.print("Tentukan pilihanmu : ");
		
		try {
			Integer pilihan = scanner.nextInt();
			
			switch(pilihan) {
			case 1:
				user.editData();
				break;
				
			case 2:
				user.hapusData();
				break;
				
			case 3:
				user.lihatData();
				break;
				
			case 4:
				user.cariData();
				break;
				
			default:
				System.out.println("Pilihan tidak tersedia");
				menuDashboard();
				break;
			}
			
		} catch(InputMismatchException e) {
			System.out.println("Masukkan input dengan benar");
		}
		
		
	}
	
	
	public static void tunggu() {
		
		InputStreamReader streamReader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(streamReader);
        
        try {
        	System.out.print("\nKlik enter untuk melanjutkan");
        	String lanjut = (bufferedReader.readLine());
    		menuDashboard();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static void menuBarang() {
		
		Barang barang = new Barang();
		Stock stock = new Stock();
		
		barang.lihatData();
		
		System.out.println("\n\n--DATA MASTER BARANG--");
		System.out.println("1. Tambah data barang");
		System.out.println("2. Ubah data barang");
		System.out.println("3. Hapus data barang");
		System.out.println("4. Cari data barang");
		System.out.println("5. Restock barang");
		System.out.print("Tentukan pilihanmu : ");
		
		try {
			
			Integer pilihan = scanner.nextInt();
			switch(pilihan) {
			
			case 1:
				barang.tambahData();
				break;
				
			case 2:
				barang.editData();
				break;
				
			case 3:
				barang.hapusData();
				break;
				
			case 4: 
				barang.cariData();
				break;
				
			case 5:
				stock.restock();
				break;
				
			default:
				System.out.println("Pilihan tidak tersedia");
				menuDashboard();
				break;
			
			}
			
		} catch (InputMismatchException e) {
			System.out.println("Masukkan input dengan benar");
		}
		
	}
	
	
	public static void menuTransaksi() {
		
		Transaksi transaksi = new Transaksi();
		transaksi.lihatData();
		
		System.out.println("\n\n--DATA MASTER TRANSAKSI--");
		System.out.println("1. Ubah data transaksi");
		System.out.println("2. Hapus data transaksi");
		System.out.println("3. Cari data transaksi");
		System.out.print("Tentukan pilihanmu : ");
		
		try {
			
			Integer pilihan = scanner.nextInt();
			switch(pilihan) {
			
			case 1:
				transaksi.editData();
				break;
				
			case 2:
				transaksi.hapusData();
				break;
				
			case 3:
				transaksi.cariData();
				break;
				
			default :

				break;
			
			}
			
		} catch (InputMismatchException e) {
			System.out.println("Masukkan input dengan benar");
		}
		
	}

}
