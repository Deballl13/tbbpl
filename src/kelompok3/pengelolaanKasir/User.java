package kelompok3.pengelolaanKasir;

import java.util.Scanner;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Random;

public class User implements Kelola{

	Scanner scn = new Scanner(System.in);
	Date date = new Date();
	UserData userData;
	UserFunction userFunction;
	Random random = new Random();
	static Integer ulang = 2;
	private String username1 = null, username2 = null, username3 = null;
	
	public User() {
		try {
			userFunction = new UserFunction();
		} catch (NullPointerException e) {
			System.out.println("Masukkan data terlebih dahulu");
		}
	}
	
	
	//	Random String
	public String randomString() {
		
		char[] karakter = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
		StringBuilder builder = new StringBuilder();
		String hasilRandom = null;
		
		for(Integer i=0; i<15; i++) {
			Character chara = karakter[random.nextInt(karakter.length)];
			builder.append(chara);
		}
		
		hasilRandom = builder.toString();
		builder.delete(0, 15);
		
		return hasilRandom;
		
	}
	
	
	// Login
    public void login() {
    	
    	System.out.println("\n\n--LOGIN--");
    	
    	System.out.print("Masukkan username : ");
		String username = scn.next();
		
		System.out.print("Masukkan password : ");
		String password = scn.next();

		String dt = String.format("%tF", date);

		userData = new UserData(username, dt, password);
		
		//	Melakukan pengecekan username yg diinputkan		
		if(ulang==2) {
			username1 = username;
		} else if(ulang==1) {
			username2 = username;
			if(!username2.equals(username1)) {
				username1=username;
				ulang = 2;
				username2=null;
			}
		} else if(ulang==0) {
			username3 = username;
			if(!username3.equals(username2)) {
				ulang = 2;
				username1=username;
				username2=null;
				username3=null;
			}
		}
		
		if(userFunction.login(userData) == 1) {
			System.out.println("Login berhasil");
			Menu.menuDashboard();
		} else {
			while(ulang>0) {
				ulang--;
				login();
			}
			
		}
		
	}


	// Register data
	@Override
	public void tambahData(){

		System.out.println("\n\n--SIGN UP--");

		System.out.print("Masukkan username : ");
		String username = scn.next();

		String dt = String.format("%tF", date);

		System.out.print("Masukkan email : ");
		String email = scn.next();

		System.out.print("Masukkan password : ");
		String password = scn.next();
		
		System.out.print("Konfirmasi password : ");
		String confirm = scn.next();
		
		userData = new UserData(username, dt, email, password);
		
		if(userFunction.register(userData,confirm) == 1) {
			System.out.println("Akun berhasil dibuat");
			login();
		}

	}


	// Mengedit password akun
	@Override
	public void editData(){
		
		System.out.println("\n\n--UBAH PASSWORD--");
		
		System.out.print("Masukkan password lama : ");
		String passwordLama = scn.next();

		System.out.print("Masukkan password baru :");
		String passwordBaru = scn.next();
		
		if(userFunction.updateData(passwordLama, passwordBaru) == 1) {
			System.out.println("Password berhasil diperbarui");
			Menu.menuDashboard();
		} else {
			System.out.println("Password gagal diperbarui");
			editData();
		}

	}


	// Hapus akun
	@Override
	public void hapusData(){

		System.out.println("\n\n--HAPUS--");

		System.out.print("Apakah anda yakin untuk menghapus akun anda ?..(y/t)  ");
		String lanjut = scn.next();

		if (lanjut.equalsIgnoreCase("y")) {
			if(userFunction.deleteData() == 1) {
				System.out.println("Akun berhasil di hapus\n\n");
				Program.main(null);
			}
		} else{
			Menu.menuDashboard();
		}

	}


	// Cari akun user
	@Override
	public void cariData(){
		System.out.println("\n\n--PENCARIAN--");

		System.out.print("Masukkan username : ");
		String search = scn.next();

		ArrayList<UserData> searchList = userFunction.search(search);
		
		System.out.print("Username");
        System.out.print("\t");
        System.out.print("Login terakhir");
        System.out.print("\t\t");
        System.out.println("Email");
		
		for(UserData userData : searchList){
            System.out.print(userData.username);
            System.out.print("\t\t");
            System.out.print(userData.date);
            System.out.print("\t\t");
            System.out.println(userData.email);
        }
		
		Menu.tunggu();

	}


	// Lihat daftar akun user
	@Override
	public void lihatData(){
		System.out.println("\n\n--DAFTAR USER--");

		TreeMap<String, UserData> userList = userFunction.lihat();
		
		System.out.print("Username");
        System.out.print("\t");
        System.out.print("Login terakhir");
        System.out.print("\t\t");
        System.out.println("Email");
        
        for(Map.Entry list : userList.entrySet()){
            UserData listUser = (UserData) list.getValue();
            
            System.out.print(list.getKey());
            System.out.print("\t\t");
            System.out.print(listUser.date);
            System.out.print("\t\t");
            System.out.println(listUser.email);
        }
		
		Menu.tunggu();

	}
	
}
