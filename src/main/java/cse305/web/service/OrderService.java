package cse305.web.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cse305.model.dao.OrderDao;
import cse305.web.model.OrderModel;

@Service
public class OrderService {

	
	public List<OrderModel> getOrders(int customerId, int limit){
		OrderDao orderDao = new OrderDao();
		List<OrderModel> orders = orderDao.getOrders(customerId, limit);
		
		return orders;
	}
	
	public OrderModel getActiveOrder(int customerId) {
		OrderDao orderDao = new OrderDao();
		return orderDao.getActiveOrderModel(customerId);
	}
	
	public void checkout(OrderModel orderModel) {
		OrderDao orderDao = new OrderDao();

		orderDao.checkout(orderModel.getCustomerId(), orderModel);
		
	}
	
	public OrderModel addItemToCart(int customerId, int itemId, int quantity) {
		OrderDao orderDao = new OrderDao();
		
		//insert into cart table
		Integer cartId = orderDao.addItemToCustomerCart(customerId, itemId, quantity);
		
		//insert into order table
		OrderModel model = orderDao.addOrderRecord(customerId, cartId, 1);
		
		return model;
	}
	
	public OrderModel removeItemFromCart(int customerId, int itemId) {
		OrderDao orderDao = new OrderDao();
		int cartId = orderDao.getActiveOrderCartId(customerId);
		
		return orderDao.removeItemFromOrder(customerId, cartId, itemId);
	}
}
