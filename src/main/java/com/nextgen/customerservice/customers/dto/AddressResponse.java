package com.nextgen.customerservice.customers.dto;

import com.nextgen.customerservice.customers.entity.AddressType;

import java.util.UUID;

public record AddressResponse(

        UUID id,

        AddressType type,

        String addressLine1,

        String addressLine2,

        String landmark,

        String city,

        String state,

        String country,

        String postalCode,

        boolean defaultAddress
) {
}
