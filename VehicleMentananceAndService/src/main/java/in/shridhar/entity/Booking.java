package in.shridhar.entity;

import java.sql.Date;
import java.time.LocalDateTime;

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
@Table(name="Booking")
@NoArgsConstructor

public class Booking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="sid")
	private int sid;
	@Column(name="customername")
    private String customerName;
	@Column(name="vehicleNumber")
    private String vehicleNumber;
	@Column(name="centerName")
    private String centerName;
	@Column(name="serviceType")
    private String serviceType;
	@Column(name="serviceDate")
    private Date serviceDate;
	@Column(name="status")
    private String status;
	@Column(name="cost")
    private double cost;
	 @Column(name = "email")
	private String email;
	

}
