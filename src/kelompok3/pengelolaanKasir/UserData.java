package kelompok3.pengelolaanKasir;

public class UserData {
	
	private String username;
	private String password;
	private String email;
	private String date;
	private static String usr;
	private static String pass;
	
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

	public String getUsername() {
		return this.username;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public String getDate() {
		return this.date;
	}
	
	public String getUser() {
		return UserData.usr;
	}
	
	public void setUser(String user) {
		UserData.usr=user;
	}
	
	public String getPass() {
		return UserData.pass;
	}
	
	public void setPass(String pass) {
		UserData.pass=pass;
	}

}
