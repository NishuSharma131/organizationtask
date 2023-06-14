package org.rsystems.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "designation")
public class Designation {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "designation_id")
	int designation_id;

	@Column(name = "designation_name")
	String designation_name;
	
	@Column(name = "capital")
	Double capital;
	

	@Column(name = "last_modified")
	Date last_modified;

	public int getDesignation_id() {
		return designation_id;
	}

	public void setDesignation_id(int designation_id) {
		this.designation_id = designation_id;
	}

	public String getDesignation_name() {
		return designation_name;
	}

	public void setDesignation_name(String designation_name) {
		this.designation_name = designation_name;
	}

	public Double getCapital() {
		return capital;
	}

	public void setCapital(Double capital) {
		this.capital = capital;
	}

	public Date getLast_modified() {
		return last_modified;
	}

	public void setLast_modified(Date last_modified) {
		this.last_modified = last_modified;
	}
}
