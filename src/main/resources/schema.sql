DROP TABLE IF EXISTS baptism_history;
CREATE TABLE baptism_history (
    id                  BIGSERIAL  PRIMARY KEY NOT NULL,
    parish_name         VARCHAR(255) NOT NULL,
    baptism_date        DATE NOT NULL,
    confirmed_date      DATE NOT NULL,
    parish_baptised_at  VARCHAR(255) NOT NULL,
    parish_confirmed_at VARCHAR(255) NOT NULL,
    user_id             BIGINT NOT NULL
)