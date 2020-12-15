package kelompok3.pengelolaanKasir;

import java.util.InputMismatchException;

public class Program {

	public static void main(String[] args){
		
		Login login = new Login();
		SignUp signup = new SignUp();

		try {
			switch (Menu.landingPage()) {
			case 1:
				login.login();
				break;
	
			case 2:
				signup.tambahData();
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
