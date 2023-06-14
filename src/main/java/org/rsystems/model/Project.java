package org.rsystems.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "projects")
public class Project {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "project_id")
	int project_id;

	@Column(name = "project_name")
	String project_name;
	
	@Column(name = "allocated_capital")
	Double allocated_capital;

	
	@Column(name = "last_modified")
	Date last_modified;

//	@Column(name = "resource_id")
//	int resource_id;
//	@Column(name = "used_capital")//need to delete
//	Double used_capital;

	Double usedCapital;

	public Double getUsedCapital() {
		return usedCapital;
	}

	public void setUsedCapital(Double usedCapital) {
		this.usedCapital = usedCapital;
	}

	public int getProject_id() {
		return project_id;
	}

	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}

	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	public Double getAllocated_capital() {
		return allocated_capital;
	}

	public void setAllocated_capital(Double allocated_capital) {
		this.allocated_capital = allocated_capital;
	}

	public Date getLast_modified() {
		return last_modified;
	}

	public void setLast_modified(Date last_modified) {
		this.last_modified = last_modified;
	}


}
