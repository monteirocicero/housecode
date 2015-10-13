package br.com.housecode.store.models;

import java.math.BigDecimal;

public class PaymentData {
	
	private BigDecimal value;
	
	public PaymentData() {
	}

	public PaymentData(BigDecimal total) {
		this.value = total;
	}
	
	public BigDecimal getValue() {
		return value;
	}

}
