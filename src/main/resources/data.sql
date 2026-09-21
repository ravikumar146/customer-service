-- Customer
INSERT INTO customers (id, customer_number, first_name, email, phone_number, status, active, created_at, updated_at, version)
VALUES ('550e8400-e29b-41d4-a716-446655440000', 'CUST100001',
        'John', 'john.doe@example.com', '123-456-7890', 'ACTIVE', true,
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1);

-- Address
INSERT INTO customer_addresses (id, customer_id, type, address_line1, address_line2,
                                landmark, city, state, country, postal_code,
                                active, default_address, created_at, updated_at, version)
VALUES ('660e8400-e29b-41d4-a716-446655440000', '550e8400-e29b-41d4-a716-446655440000', 'HOME',
        '123 Main Street', 'Apartment 4B', 'Near Central Park', 'New York', 'New York',
        'USA', '10001', true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
        1);