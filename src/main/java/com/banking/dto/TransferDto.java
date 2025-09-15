package com.banking.dto;

import lombok.Data;

@Data
public class TransferDto {

	private long fromId;

	private long toId;

	private double amount;

	
	public long getFromId() {
		return fromId;
	}

	public void setFromId(long fromId) {
		this.fromId = fromId;
	}

	public long getToId() {
		return toId;
	}

	public void setToId(long toId) {
		this.toId = toId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	

}
