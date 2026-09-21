package com.nextgen.customerservice.customers.service;

import com.nextgen.customerservice.customers.dto.AddressRequest;
import com.nextgen.customerservice.customers.dto.AddressResponse;
import com.nextgen.customerservice.customers.dto.CustomerCreateRequest;
import com.nextgen.customerservice.customers.dto.CustomerResponse;

import java.util.List;

/**
 * Service interface for managing customer-related operations.
 */
public interface CustomerService {

    /**
     * Retrieves a customer by their ID.
     *
     * @param customerNumber the ID of the customer
     * @return the customer with the specified ID
     */
    CustomerResponse getByCustomerNumber(String customerNumber);

    /**
     * Retrieves all customers.
     *
     * @return a list of all customers
     */

    List<CustomerResponse> getAll();

    /**
     * Creates a new customer.
     *
     * @param request the customer to be created
     * @return the created customer
     */
    CustomerResponse create(CustomerCreateRequest request);

    AddressResponse addAddress(String customerNumber, AddressRequest request);

    List<AddressResponse> getAddresses(String customerNumber);
}
