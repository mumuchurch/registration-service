DROP TABLE IF EXISTS baptism_history;
CREATE TABLE baptism_history (
    id                  BIGSERIAL NOT NULL,
    parish_name         VARCHAR(255) NOT NULL,
    baptism_date        TIMESTAMP NOT NULL,
    confirmed_date      TIMESTAMP NOT NULL,
    parish_baptised_at  VARCHAR(255) NOT NULL,
    parish_confirmed_at VARCHAR(255) NOT NULL,
    version             INTEGER NOT NULL
);

DROP TABLE IF EXISTS addresses;
CREATE TABLE addresses (
    id BIGSERIAL NOT NULL,
    streetAddress VARCHAR(255) NOT NULL,
    streetAddressOne VARCHAR(255),
    city VARCHAR(100) NOT NULL,
    province VARCHAR(100) NOT NULL,
    zipCode VARCHAR(10) NOT NULL,
    country VARCHAR(100) NOT NULL,
    version INTEGER NOT NULL
);

DROP TABLE IF EXISTS dependants;
CREATE TABLE dependants (
    id BIGSERIAL NOT NULL,
    fullNames VARCHAR(255) NOT NULL,
    dateOfBirth TIMESTAMP NOT NULL,
    gender VARCHAR(100) NOT NULL
);

DROP TABLE IF EXISTS marriage_history;
CREATE TABLE marriage_history (
    id BIGSERIAL NOT NULL,
    marriageDate TIMESTAMP NOT NULL,
    parishMarriedAt VARCHAR(100) NOT NULL,
    spouseFullNames VARCHAR(255) NOT NULL
)