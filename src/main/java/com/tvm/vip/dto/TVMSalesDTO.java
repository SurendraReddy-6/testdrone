package com.tvm.vip.dto;

public class TVMSalesDTO {

	private Long id;
	private Long amount ;
	private Long transaction_id;
	private String productCode;
	private String ticketid	;
	private String productType;
	private Long cashAmount;
	private Long changeAmount;
	private Long ticketAmount;
	private Long createdon 	;
	private Long tvmid 	;
	private String payment_method;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	public Long getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(Long transaction_id) {
		this.transaction_id = transaction_id;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getTicketid() {
		return ticketid;
	}
	public void setTicketid(String ticketid) {
		this.ticketid = ticketid;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public Long getCashAmount() {
		return cashAmount;
	}
	public void setCashAmount(Long cashAmount) {
		this.cashAmount = cashAmount;
	}
	public Long getChangeAmount() {
		return changeAmount;
	}
	public void setChangeAmount(Long changeAmount) {
		this.changeAmount = changeAmount;
	}
	public Long getTicketAmount() {
		return ticketAmount;
	}
	public void setTicketAmount(Long ticketAmount) {
		this.ticketAmount = ticketAmount;
	}
	public Long getCreatedon() {
		return createdon;
	}
	public void setCreatedon(Long createdon) {
		this.createdon = createdon;
	}
	public Long getTvmid() {
		return tvmid;
	}
	public void setTvmid(Long tvmid) {
		this.tvmid = tvmid;
	}
	public String getPayment_method() {
		return payment_method;
	}
	public void setPayment_method(String payment_method) {
		this.payment_method = payment_method;
	}
}
