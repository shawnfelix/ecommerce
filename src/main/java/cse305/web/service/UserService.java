package cse305.web.service;

import org.springframework.stereotype.Service;

import cse305.model.dao.CustomerDao;
import cse305.model.dao.EmployeeDao;
import cse305.web.form.ManageAccountForm;
import cse305.web.model.UserModel;

@Service
public class UserService {

	
	public UserModel signInAttempt(String username, String pass) {
		CustomerDao custDao = new CustomerDao();
		UserModel cust = custDao.getCustomerDetailsByUsernamePass(username, pass);

		EmployeeDao empDao = new EmployeeDao();
		UserModel emp = empDao.getEmployeeDetailsByUsername(username, pass);
		
		if (cust != null) {
			cust.setEmployee(false);
			return cust;			
		}
		else if (emp != null) {
			emp.setEmployee(true);
			return emp;
		}
		else {
			return null;
		}
		
	}
	
	public UserModel updateUserModel(ManageAccountForm form, int userId) {
		CustomerDao custDao = new CustomerDao();
		
		//update
		custDao.setCustomerDetails(form, userId);
		
		//get new model
		return custDao.getCustomerDetailsById(userId);
	}
	
	
	
}
