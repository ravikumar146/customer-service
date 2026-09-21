package com.nextgen.customerservice.customers.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nextgen.customerservice.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "customer_addresses",
        indexes = {
                @Index(
                        name = "idx_address_customer",
                        columnList = "customer_id"
                ),
                @Index(
                        name = "idx_address_city",
                        columnList = "city"
                ),
                @Index(
                        name = "idx_address_postal_code",
                        columnList = "postal_code"
                )
        }
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false,
            foreignKey = @ForeignKey(
                    name = "fk_address_customer"
            )
    )
    @JsonIgnore
    private Customer customer;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private AddressType type;

    @Column(nullable = false, length = 200)
    private String addressLine1;

    @Column(length = 200)
    private String addressLine2;

    @Column(length = 100)
    private String landmark;

    @Column(nullable = false, length = 100)
    private String city;

    @Column(nullable = false, length = 100)
    private String state;

    @Column(nullable = false, length = 100)
    private String country;

    @Column(name = "postal_code", nullable = false, length = 20)
    private String postalCode;

    @Column(nullable = false)
    @Builder.Default
    private boolean defaultAddress = false;
}
