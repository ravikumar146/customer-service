package com.nextgen.customerservice.customers.dto;

import com.nextgen.customerservice.customers.entity.AddressType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AddressRequest(

        @NotNull(message = "Address type is required")
        AddressType type,

        @NotBlank
        @Size(max = 200)
        String addressLine1,

        @Size(max = 200)
        String addressLine2,

        @Size(max = 100)
        String landmark,

        @NotBlank
        @Size(max = 100)
        String city,

        @NotBlank
        @Size(max = 100)
        String state,

        @NotBlank
        @Size(max = 100)
        String country,
        @NotBlank
        @Size(max = 20)
        String postalCode,

        boolean defaultAddress
) {
}