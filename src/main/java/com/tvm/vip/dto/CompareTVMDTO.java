package com.tvm.vip.dto;

import java.util.List;

public class CompareTVMDTO {

	private List<Long> tvmIds;
	private long totalAmount;
	private long totalCashAmount;
	private long totalChangeAmount;
	private long totalTicketAmount;
	private long avgAmount;
	private long avgCashAmount;
	private long avgChangeAmount;
	private long avgTicketAmount;
	private List<String> paymentMethod;
	
	public long getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(long totalAmount) {
		this.totalAmount = totalAmount;
	}

	public long getTotalCashAmount() {
		return totalCashAmount;
	}

	public void setTotalCashAmount(long totalCashAmount) {
		this.totalCashAmount = totalCashAmount;
	}

	public long getTotalChangeAmount() {
		return totalChangeAmount;
	}

	public void setTotalChangeAmount(long totalChangeAmount) {
		this.totalChangeAmount = totalChangeAmount;
	}

	public long getTotalTicketAmount() {
		return totalTicketAmount;
	}

	public void setTotalTicketAmount(long totalTicketAmount) {
		this.totalTicketAmount = totalTicketAmount;
	}

	public List<Long> getTvmIds() {
		return tvmIds;
	}

	public void setTvmIds(List<Long> tvmIds) {
		this.tvmIds = tvmIds;
	}
	public long getAvgAmount() {
		return avgAmount;
	}

	public void setAvgAmount(long avgAmount) {
		this.avgAmount = avgAmount;
	}

	public long getAvgCashAmount() {
		return avgCashAmount;
	}

	public void setAvgCashAmount(long avgCashAmount) {
		this.avgCashAmount = avgCashAmount;
	}

	public long getAvgChangeAmount() {
		return avgChangeAmount;
	}

	public void setAvgChangeAmount(long avgChangeAmount) {
		this.avgChangeAmount = avgChangeAmount;
	}

	public long getAvgTicketAmount() {
		return avgTicketAmount;
	}

	public void setAvgTicketAmount(long avgTicketAmount) {
		this.avgTicketAmount = avgTicketAmount;
	}

	public List<String> getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(List<String> paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
}
