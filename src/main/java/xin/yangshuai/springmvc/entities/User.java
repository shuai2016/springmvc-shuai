package xin.yangshuai.springmvc.entities;

/**
 * User
 *
 * @author shuai
 * @date 2019/1/15
 */
public class User {
	private int id;
	private String username;
	private String password;
	private int age;
	private String email;
	private Address address;

	public User() {
	}

	public User(int id, String username, String password, int age, String email) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.age = age;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", age=" + age +
				", email='" + email + '\'' +
				'}';
	}
}