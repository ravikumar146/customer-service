package com.nextgen.customerservice.customers.dto;

import com.nextgen.customerservice.customers.entity.CustomerStatus;

import java.time.Instant;
import java.util.UUID;

public record CustomerResponse(

        UUID id,

        String customerNumber,

        String email,

        String phone,

        String firstName,

        String lastName,

        CustomerStatus status,

        Instant createdAt,

        Instant updatedAt
) {
}