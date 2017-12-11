package cse305.web.service;

import java.util.List;

import cse305.model.dao.PaymentDao;
import cse305.model.entities.Payment;

public class PaymentService {

	public List<Payment> getPayments(int customerId) {
		PaymentDao paymentDao = new PaymentDao();
		List<Payment> payments = paymentDao.getPaymentMethods(customerId);

		return payments;
	}

}
