package kelompok3.pengelolaanKasir;

public class UserData {
	
	String username;
	String password;
	String email;
	String date;
	static String usr;
	static String pass;
	
	public UserData() {
		
	}
	
	public UserData(String username, String date, String password) {
		this.username = username;
		this.date = date;
		this.password = password;
	}
	
	public UserData(String username, String date, String email, String password) {
		this.username = username;
		this.date = date;
		this.email = email;
		this.password = password;
	}

}
