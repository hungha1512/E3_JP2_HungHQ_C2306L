package Service;

import Entity.Customer;

import java.util.List;

public class CustomerService {
    public static List<Customer> customerList;

    public CustomerService() {
        ;
    }

    public static Customer setCustomer(String id, String cusName, String cusPhone) {
        Customer customer = new Customer();
        if (checkInformation(id)) {
            System.out.println("Customer already exists!");
        } else {
            customer.setId(id);
            customer.setCusName(cusName);
            customer.setCusPhone(cusPhone);
            customerList.add(customer);
            return customer;
        }
        return getCustomer(id);
    }

    public static boolean checkInformation(String id) {
        return customerList.stream().anyMatch(c -> c.getId().equals(id));
    }

    public static Customer getCustomer(String id) {
        return customerList.stream().filter(c -> c.getId().equals(id)).findFirst().get();
    }
}
