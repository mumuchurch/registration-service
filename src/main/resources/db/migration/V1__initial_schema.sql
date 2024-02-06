CREATE TABLE CONGREGANT
(
    id BIGSERIAL NOT NULL,
    title              VARCHAR(15)  NOT NULL,
    first_names        VARCHAR(50)  NOT NULL,
    surname            VARCHAR(50)  NOT NULL,
    email              VARCHAR(100) NOT NULL,
    dob                DATE         NOT NULL,
    gender             VARCHAR(10) CHECK (gender IN ('MALE', 'FEMALE', 'NOT_STATED')),
    profession         VARCHAR(100) NOT NULL,
    created_date       TIMESTAMP    NOT NULL,
    last_modified_date TIMESTAMP,
    version            INT8         NOT NULL,
    primary key (id)
);

CREATE TABLE ADDRESS
(
    id BIGSERIAL NOT NULL,
    congregant         BIGINT,
    city               VARCHAR(255),
    country            VARCHAR(255),
    province           VARCHAR(255),
    street_address     VARCHAR(255),
    street_address_one VARCHAR(255),
    zip_code           VARCHAR(255),
    created_date       TIMESTAMP    NOT NULL,
    last_modified_date TIMESTAMP,
    version            INT8         NOT NULL,
    primary key (id),
    FOREIGN KEY (congregant) REFERENCES CONGREGANT (id)
);

CREATE TABLE BAPTISM_HISTORY
(
    id BIGSERIAL NOT NULL,
    baptism_date        DATE         NOT NULL,
    confirmed_date      DATE         NOT NULL,
    version             INTEGER      NOT NULL,
    parish_baptised_at  VARCHAR(255) NOT NULL,
    parish_confirmed_at VARCHAR(255) NOT NULL,
    parish_name         VARCHAR(255) NOT NULL,
    created_date        TIMESTAMP    NOT NULL,
    last_modified_date  TIMESTAMP,
    primary key (id),
    congregant          BIGINT UNIQUE,
    FOREIGN KEY (congregant) REFERENCES CONGREGANT (id)
);

CREATE TABLE DEPENDANT
(
    id bigserial NOT NULL,
    date_of_birth      DATE      NOT NULL,
    full_names         VARCHAR(255),
    gender             VARCHAR(255) CHECK (gender IN ('MALE', 'FEMALE', 'NOT_STATED')),
    created_date       TIMESTAMP NOT NULL,
    last_modified_date TIMESTAMP,
    version            INT8      NOT NULL,
    congregant         BIGINT,
    PRIMARY KEY (id),
    FOREIGN KEY (congregant) REFERENCES CONGREGANT (id)
);
-- Marriage history
CREATE TABLE MARRIAGE_HISTORY
(
    id BIGSERIAL not null,
    marriage_date      DATE         NOT NULL,
    parish_married_at  VARCHAR(255) NOT NULL,
    spouse_full_names  VARCHAR(255) NOT NULL,
    created_date       TIMESTAMP    NOT NULL,
    last_modified_date TIMESTAMP,
    primary key (id),
    version            INT8         NOT NULL,
    congregant         BIGINT UNIQUE,
    FOREIGN KEY (congregant) REFERENCES CONGREGANT (id)
);