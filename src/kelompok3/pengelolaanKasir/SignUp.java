package kelompok3.pengelolaanKasir;

import java.util.Date;
import java.util.Scanner;

public class SignUp extends User {

Scanner scn = new Scanner(System.in);
Date date = new Date();
UserFunction userFunction;	
Login login = new Login();
UserData userData;
	
	public SignUp() {
		try {
			userFunction = new UserFunction();
		} catch (NullPointerException e) {
			System.out.println("Masukkan data terlebih dahulu");
		}
	}


	// Register data
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
			login.login();
		}

	}
	
}
