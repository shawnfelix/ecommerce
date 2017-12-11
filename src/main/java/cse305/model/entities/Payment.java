package cse305.model.entities;

import java.util.Date;

public class Payment {
	Integer paymentId;
	String type;
	String cardNumber;
	String cardExp;

	public Integer getOrderId() {
		return paymentId;
	}

	public void setOrderId(Integer orderId) {
		this.paymentId = orderId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCardExp() {
		return cardExp;
	}

	public void setCardExp(String cardExp) {
		this.cardExp = cardExp;
	}
}
