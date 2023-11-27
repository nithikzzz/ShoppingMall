package com.programming.restapi;

import java.time.LocalDate;
//import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_details")
public class OrderDetails {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int orderId;
		@Column(name = "date_of_purchase")
		private LocalDate dateOfPurchase;
		@Column
		private float total;
		
		@ManyToOne
		@JoinColumn(name = "customer_id") // This references the "id" column in the "customer" table
		private Customer customer;
		
		@Column(name = "payment_mode")
		private String paymentMode;
		@Column(name = "shop_id")
		private int shopId;

			
		 // Constructors, getters, setters...
		
		public OrderDetails() {
			// TODO Auto-generated constructor stub
		}


		/**
		 * @param dateOfPurchase
		 * @param total
		 * @param customer
		 * @param paymentMode
		 * @param shopId
		 */
		public OrderDetails(LocalDate dateOfPurchase, float total, Customer customer, String paymentMode,
				int shopId) {
			super();
			this.dateOfPurchase = dateOfPurchase;
			this.total = total;
			this.customer = customer;
			this.paymentMode = paymentMode;
			this.shopId = shopId;
		}


		public int getOrderId() {
			return orderId;
		}


		public void setOrderId(int orderId) {
			this.orderId = orderId;
		}


		public LocalDate getDateOfPurchase() {
			return dateOfPurchase;
		}


		public void setDateOfPurchase(LocalDate dateOfPurchase) {
			this.dateOfPurchase = dateOfPurchase;
		}


		public float getTotal() {
			return total;
		}


		public void setTotal(float total) {
			this.total = total;
		}


		public Customer getCustomer() {
			return customer;
		}


		public void setCustomer(Customer customer) {
			this.customer = customer;
		}


		public String getPaymentMode() {
			return paymentMode;
		}


		public void setPaymentMode(String paymentMode) {
			this.paymentMode = paymentMode;
		}


		public int getShopId() {
			return shopId;
		}


		public void setShopId(int shopId) {
			this.shopId = shopId;
		}


		@Override
		public String toString() {
			return "OrderDetails [orderId=" + orderId + ", dateOfPurchase=" + dateOfPurchase + ", total=" + total
					+ ", customer=" + customer + ", paymentMode=" + paymentMode + ", shopId=" + shopId + "]";
		}
				
}

