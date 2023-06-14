package org.rsystems.model;

import javax.persistence.*;

@Entity
@Table(name = "organization")
public class Organization {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "org_id")
	int org_id;

	@Column(name = "org_name")
	String org_name;
	
	@Column(name = "total_cost")
	Double total_cost;
	

	@Column(name = "project_id")
	int project_id;

	public int getOrg_id() {
		return org_id;
	}

	public void setOrg_id(int org_id) {
		this.org_id = org_id;
	}

	public String getOrg_name() {
		return org_name;
	}

	public void setOrg_name(String org_name) {
		this.org_name = org_name;
	}

	public Double getTotal_cost() {
		return total_cost;
	}

	public void setTotal_cost(Double total_cost) {
		this.total_cost = total_cost;
	}

	public int getProject_id() {
		return project_id;
	}

	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}
}
