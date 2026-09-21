package com.nextgen.customerservice.customers.controller;

import com.nextgen.customerservice.customers.dto.AddressRequest;
import com.nextgen.customerservice.customers.dto.AddressResponse;
import com.nextgen.customerservice.customers.dto.CustomerCreateRequest;
import com.nextgen.customerservice.customers.dto.CustomerResponse;
import com.nextgen.customerservice.customers.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Customers",
        description = "APIs for managing customer-related operations"
)
@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Operation(summary = "Get customer details by customer number")
    @GetMapping("/{customerNumber}")
    public CustomerResponse getByCustomerNumber(@PathVariable String customerNumber) {
        return customerService.getByCustomerNumber(customerNumber);
    }

    @Operation(summary = "Get all customers")
    @GetMapping("/list")
    public List<CustomerResponse> getAllCustomers() {
        return customerService.getAll();
    }

    @Operation(summary = "Create a new customer")
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerResponse createCustomer(@Valid @RequestBody
                                           CustomerCreateRequest request) {
        return customerService.create(request);
    }

    @Operation(summary = "Create a new address against customer")
    @PostMapping("/{customerNumber}/addresses")
    @ResponseStatus(HttpStatus.CREATED)
    public AddressResponse addAddress(@PathVariable String customerNumber,
                                      @Valid @RequestBody AddressRequest request) {
        return customerService.addAddress(customerNumber, request);
    }

    @Operation(summary = "Fetch customer address based on customer number")
    @GetMapping("/{customerNumber}/addresses")
    public List<AddressResponse> getAddresses(@PathVariable String customerNumber) {
        return customerService.getAddresses(customerNumber);
    }
}
