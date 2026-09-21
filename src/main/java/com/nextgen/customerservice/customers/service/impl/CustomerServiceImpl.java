package com.nextgen.customerservice.customers.service.impl;

import com.nextgen.customerservice.common.exception.ResourceNotFoundException;
import com.nextgen.customerservice.customers.dto.AddressRequest;
import com.nextgen.customerservice.customers.dto.AddressResponse;
import com.nextgen.customerservice.customers.dto.CustomerCreateRequest;
import com.nextgen.customerservice.customers.dto.CustomerResponse;
import com.nextgen.customerservice.customers.entity.Address;
import com.nextgen.customerservice.customers.entity.Customer;
import com.nextgen.customerservice.customers.entity.CustomerStatus;
import com.nextgen.customerservice.customers.repository.AddressRepository;
import com.nextgen.customerservice.customers.repository.CustomerRepository;
import com.nextgen.customerservice.customers.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of the CustomerService interface.
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public CustomerResponse getByCustomerNumber(String customerNumber) {
        Customer customer = getCustomer(customerNumber);
        return toResponse(customer);
    }

    @Override
    public List<CustomerResponse> getAll() {
        return customerRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private String generateCustomerNumber() {
        return "CUST" + (customerRepository.count() + 100001);
    }

    @Override
    public CustomerResponse create(CustomerCreateRequest request) {

        if (customerRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("Email already exists");
        }

        Customer customer = Customer.builder().customerNumber(generateCustomerNumber()).firstName(request.firstName())
                .lastName(request.lastName()).email(request.email())
                .phoneNumber(request.phone()).status(CustomerStatus.ACTIVE).build();

        Customer saved = customerRepository.save(customer);
        return toResponse(saved);
    }


    @Override
    public AddressResponse addAddress(String customerNumber, AddressRequest request) {
        Customer customer = getCustomer(customerNumber);
        Address address = Address.builder().customer(customer).type(request.type())
                .addressLine1(request.addressLine1()).addressLine2(request.addressLine2())
                .landmark(request.landmark()).city(request.city()).state(request.state())
                .country(request.country()).postalCode(request.postalCode())
                .defaultAddress(request.defaultAddress()).build();

        customer.addAddress(address);
        addressRepository.save(address);
        return toAddressResponse(address);
    }

    @Override
    public List<AddressResponse> getAddresses(String customerNumber) {
        getCustomer(customerNumber);
        return addressRepository
                .findByCustomer_CustomerNumber(customerNumber)
                .stream()
                .map(this::toAddressResponse)
                .toList();
    }

    private AddressResponse toAddressResponse(
            Address address) {
        return new AddressResponse(
                address.getId(),
                address.getType(),
                address.getAddressLine1(),
                address.getAddressLine2(),
                address.getLandmark(),
                address.getCity(),
                address.getState(),
                address.getCountry(),
                address.getPostalCode(),
                address.isDefaultAddress()
        );
    }

    private CustomerResponse toResponse(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getCustomerNumber(),
                customer.getEmail(),
                customer.getPhoneNumber(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getStatus(),
                customer.getCreatedAt(),
                customer.getUpdatedAt()
        );
    }

    private Customer getCustomer(String customerNumber) {
        return customerRepository.findByCustomerNumber(customerNumber)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Customer not found with customer number: "
                                        + customerNumber
                        )
                );
    }
}
