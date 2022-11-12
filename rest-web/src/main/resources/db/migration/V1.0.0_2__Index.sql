-- Index for Phone Number

CREATE INDEX phone_number_db_phone_number_phone_type
ON phone_number_db (phone_number, phone_type);