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
@Table(name="Vehicle")
public class Vehicle {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY )
	@Column(name="vid")
	private int vid;
	@Column(name="vehicleNumber")
	private String vehicleNumber;
	@Column(name="ownername")
	private String ownername;
	@Column(name="model")
	private String model;
	@Column(name="year")
	private int year;
	@Column(name="color")
	private String color;

}
