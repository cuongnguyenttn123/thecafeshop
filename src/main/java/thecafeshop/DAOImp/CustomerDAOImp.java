package thecafeshop.DAOImp;

import thecafeshop.entity.Customer;

import java.util.List;


public interface CustomerDAOImp extends CommonDAOImp{
	
	public int addCustomer(Customer customer);

	public List<Customer> findAll();

	public Customer getInfoById(int customerid);

	public Boolean deleteCustomer(int customerid);

	public Boolean editCustomer(Customer customer);
}
