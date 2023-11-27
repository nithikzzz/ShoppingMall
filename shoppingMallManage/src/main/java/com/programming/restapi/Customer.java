package com.programming.restapi;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "cus_name")
	private String name;
	@Column
	private int order_id;
	@Column(name = "cus_phoneno")
	private String phoneno;
	@Column(name = "cus_email")
	private String email;
	
	@OneToMany(mappedBy = "customer")
	private List<OrderDetails> orderDetailsList;
	
	public Customer() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param name
	 * @param order_id
	 * @param phoneno
	 * @param email
	 */
	public Customer(Integer id,String name, int order_id, String phoneno, String email) {
		super();
		this.id=id;
		this.name = name;
		this.order_id = order_id;
		this.phoneno = phoneno;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getOrder_Id() {
		return order_id;
	}

	public void setOrder_Id(int order_id) {
		this.order_id = order_id;
	}

	public String getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", order_id=" + order_id + ", phoneno=" + phoneno + ", email="
				+ email + "]";
	}
		
	
}

