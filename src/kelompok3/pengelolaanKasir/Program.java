package kelompok3.pengelolaanKasir;

import java.util.Scanner;

import java.util.Date;
import java.util.InputMismatchException;

public class Program {

	static User user = new User() ;
	static Date date = new Date();

	public static void main(String[] args){	

		try {
			switch (Menu.landingPage()) {
			case 1:
				user.login();
				break;
	
			case 2:
				user.tambahData();
				break;
		
			default:
				System.out.println("Pilihan tidak tersedia");
				break;
			}
		} catch (InputMismatchException e) {
			System.out.println("Masukkan input dengan benar");
		}
			
	}

}
