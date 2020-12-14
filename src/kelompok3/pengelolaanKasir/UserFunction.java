package kelompok3.pengelolaanKasir;

import java.sql.*;
import java.util.ArrayList;
import java.util.TreeMap;

public class UserFunction {
	
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/tbbpl?serverTimezone=Asia/Jakarta";
	static final String USERNAME = "root";
	static final String PASSWORD = "";
	
	static Connection conn;
	static Statement stmt;
	static PreparedStatement statement;
	UserData userData;
	User user;

	public UserFunction(){
		
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("Koneksi tidak tersambung");
		}
		
	}
	
	
	//	Login
	public Integer login(UserData userData) {
		
		Integer login = 0;
	
		try {	
			String query = "SELECT * FROM user WHERE username=? AND password=?";
			statement = conn.prepareStatement(query);
			statement.setString(1, userData.username);
			statement.setString(2, userData.password);
			ResultSet result = statement.executeQuery();
			
			if(result.next()) {
				
				try {
					String sql = "UPDATE user SET login_terakhir=? WHERE username=?";
					statement = conn.prepareStatement(sql);
					statement.setString(1, userData.date);
					statement.setString(2, userData.username);
					login = statement.executeUpdate();
					
					if(login == 1) {
						UserData.usr = userData.username;
						UserData.pass = userData.password;
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			} else {
				if(User.ulang>0) {
					System.out.println("Username dan password anda salah");
				} else if(User.ulang <= 0) {
					System.out.println("Password anda telah direset");
					
					user = new User();
					
					try {
						
						String reset = "UPDATE user SET password=? WHERE username =?";
						statement = conn.prepareStatement(reset);
						statement.setString(1, user.randomString());
						statement.setString(2, userData.username);
						statement.executeUpdate();
						
					} catch (SQLException e) {
						System.out.println("Terjadi kesalahan");
					}
					
				}
			}
			
		} catch (SQLException e) {
			System.out.println("Terjadi kesalahan");
		}
		
		
		return login;
	}
	
	
	//	Buat akun
	public Integer register(UserData userData, String confirm) {
		
		user = new User();
		Integer register = 0;
		
		// Melakukan pengecekan validitas email
		if (userData.email.contains("@")) {
			
			//	Melakukan pengecekan password
			if(userData.password.equals(confirm)) {
			
				try {
					
					// Melakukan pengecekan username sudah tersedia atau belum
					String cek = "SELECT username FROM user WHERE username = ?";
					statement = conn.prepareStatement(cek);
					statement.setString(1, userData.username);
					ResultSet resultCek = statement.executeQuery();
							
					if (resultCek.next()) {
						
						System.out.println("Username sudah terdaftar");
						user.tambahData();
						
					} else{
						
						try {
							String query = "INSERT INTO user VALUES (?,?,?,?)";
							statement = conn.prepareStatement(query);
							statement.setString(1, userData.username);
							statement.setString(2, userData.date);
							statement.setString(3, userData.email);
							statement.setString(4, userData.password);
							register = statement.executeUpdate();
									
						} catch (SQLException e) {
							
							System.out.println("Terjadi kesalahan");
						
						}

					}

				} catch (SQLException e) {
					
					System.out.println("Terjadi kesalahan");
					
				}
				
			} else {
				
				System.out.println("Password yang anda masukkan salah");
				user.tambahData();
				
			}
			
		} else{
					
			System.out.println("Masukkan email dengan benar");
			user.tambahData();
			
		}		
	
		return register;
		
	}
	
	
	//	Update password
	public Integer updateData(String passwordLama, String passwordBaru) {
		
		Integer update = 0;
		User user = new User();
		
		if (passwordLama.equals(UserData.pass)) {

			try {
				
				String query = "UPDATE user SET password=? WHERE username=?";
				statement = conn.prepareStatement(query);
				statement.setString(1, passwordBaru);
				statement.setString(2, UserData.usr);
				update = statement.executeUpdate();
				
				if(update == 1) {
					UserData.pass = passwordBaru;
				}
				
			} catch (SQLException e) {
				System.out.println("Terjadi kesalahan");
			}

		} else{
			
			System.out.println("Password yang anda masukkan salah");
			user.editData();
			
		}
		
		return update;
		
	}
	
	
	//	Hapus akun
	public Integer deleteData() {
		
		Integer delete = 0;
		
		try {
			
			String query = "DELETE FROM user WHERE username=?";
			statement = conn.prepareStatement(query);
			statement.setString(1, UserData.usr);
			delete = statement.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Terjadi kesalahan");
		}
		
		return delete;
		
	}
	
	
	//	Cari data user
	public ArrayList<UserData> search(String search){
		
		ArrayList<UserData> searchList = new ArrayList<>();
		
		try {
			
			String query = "SELECT * FROM user WHERE username LIKE ?";
			statement = conn.prepareStatement(query);
			statement.setString(1, "%" + search + "%");
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				userData = new UserData(
						result.getString("username"),
						result.getString("login_terakhir"), 
						result.getString("email"),
						result.getString("password")
				);
				searchList.add(userData);
			}
			
		} catch (SQLException e) {
			System.out.println("Terjadi kesalahan");
		}
		
		return searchList;
		
	}
	
	
	//	Lihat users
	public TreeMap<String, UserData> lihat(){
		
		TreeMap<String, UserData> userList = new TreeMap<>();
		
		try {
			
			String query = "SELECT * FROM user";
			stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery(query);
			
			while(result.next()){
                userData = new UserData(
                		result.getString("username"),
						result.getString("login_terakhir"), 
						result.getString("email"),
						result.getString("password")
                );
                userList.put(result.getString("username"), userData);
			}
              
			
		} catch (SQLException e) {
			System.out.println("Terjadi kesalahan");
		}
		
		return userList;
		
	}
	
	
	
}
