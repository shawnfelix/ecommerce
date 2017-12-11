package cse305.web.model;

public class PaymentModel {

	Integer orderId;

	String type;
	String cardNumber;
	String cardExpiration;

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

	public String getCardExpiration() {
		return cardExpiration;
	}

	public void setCardExpiration(String cardExpiration) {
		this.cardExpiration = cardExpiration;
	}

	public Integer getUserId() {
		return orderId;
	}

	public void setUserId(Integer userId) {
		this.orderId = userId;
	}

}
