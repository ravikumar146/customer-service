package com.nextgen.customerservice.customers.entity;

import com.nextgen.customerservice.common.entity.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Entity class representing a customer in the e-commerce system.
 */
@Entity
@Table(
        name = "customers",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_customer_email", columnNames = "email")
        },
        indexes = {
                @Index(name = "idx_customer_email", columnList = "email")
        })
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        name = "Customer",
        description = "Represents customer information in e-commerce system."
)
public class Customer extends BaseEntity {

    @Column(name = "customer_number", unique = true, nullable = false)
    @Schema(description = "Customer Number", example = "CUST1001")
    private String customerNumber;

    @Column(nullable = false, length = 100)
    @Schema(description = "Name of the customer.", example = "John Doe")
    private String firstName;

    @Column(length = 100)
    private String lastName;

    @Column(nullable = false, length = 255)
    @Schema(description = "Email address of the customer.", example = "john.doe@example.com")
    private String email;

    @Column
    @Schema(description = "Phone number of the customer.", example = "+1-555-1234")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private CustomerStatus status;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> addresses = new ArrayList<>();

    public void addAddress(Address address) {
        addresses.add(address);
        address.setCustomer(this);
    }

    public void removeAddress(Address address) {
        addresses.remove(address);
        address.setCustomer(null);
    }
}
