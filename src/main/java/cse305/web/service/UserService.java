package cse305.web.service;

import org.springframework.stereotype.Service;

import cse305.model.dao.CustomerDao;
import cse305.model.dao.EmployeeDao;
import cse305.web.model.UserModel;

@Service
public class UserService {

	
	public UserModel signInAttempt(String username, String pass) {
		CustomerDao custDao = new CustomerDao();
		UserModel cust = custDao.getCustomerDetailsByUsername(username, pass);

		EmployeeDao empDao = new EmployeeDao();
		UserModel emp = empDao.getEmployeeDetailsByUsername(username, pass);
		
		if (cust != null) {
			return cust;			
		}
		else if (emp != null) {
			return emp;
		}
		else {
			return null;
		}
		
	}
	
}
