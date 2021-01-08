package kelompok3.pengelolaanKasir;

import java.util.Scanner;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class User implements Kelola{

	Scanner scn = new Scanner(System.in);
	Date date = new Date();
	UserData userData;
	UserFunction userFunction;
	Login login = new Login();
	
	public User() {
		try {
			userFunction = new UserFunction();
		} catch (NullPointerException e) {
			System.out.println("Masukkan data terlebih dahulu");
		}
	}


	// Register data
	@Override
	public void tambahData(){

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
            System.out.print(userData.getUsername());
            System.out.print("\t\t");
            System.out.print(userData.getDate());
            System.out.print("\t\t");
            System.out.println(userData.getEmail());
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
            System.out.print(listUser.getDate());
            System.out.print("\t\t");
            System.out.println(listUser.getEmail());
        }
		
		Menu.tunggu();

	}
	
}
