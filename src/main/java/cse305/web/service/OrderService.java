package cse305.web.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cse305.model.dao.OrderDao;
import cse305.web.model.OrderModel;

@Service
public class OrderService {

	
	public List<OrderModel> getRecentOrders(int customerId){
		OrderDao orderDao = new OrderDao();
		List<OrderModel> orders = orderDao.getRecentOrders(customerId);
		
		return orders;
	}
}
