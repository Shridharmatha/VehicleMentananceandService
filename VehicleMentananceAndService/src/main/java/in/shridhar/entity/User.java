package in.shridhar.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name="User")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="uid")
	private int uid;
	@Column(name="uname")
	private String uname;
	@Column(name="uphone")
	private String uphone;
	@Column(name="umail")
	private String umail;
	@Column(name="upass")
	private String upass;
	@Column(name="uaddress")
	private String uaddress;
	@Column(name="uroll")
	private String uroll;

}
