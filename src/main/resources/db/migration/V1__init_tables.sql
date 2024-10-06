CREATE TABLE department
(
    id          BIGINT NOT NULL,
    name        VARCHAR(255),
    description VARCHAR(255),
    location    VARCHAR(255),
    CONSTRAINT pk_department PRIMARY KEY (id)
);

CREATE TABLE department_lectors
(
    department_id BIGINT NOT NULL,
    lectors_id    BIGINT NOT NULL,
    CONSTRAINT pk_department_lectors PRIMARY KEY (department_id, lectors_id)
);

CREATE TABLE lector
(
    id        BIGINT           NOT NULL,
    full_name VARCHAR(255)     NOT NULL,
    degree    SMALLINT         NOT NULL,
    salary    DOUBLE PRECISION NOT NULL,
    CONSTRAINT pk_lector PRIMARY KEY (id)
);

ALTER TABLE department_lectors
    ADD CONSTRAINT fk_deplec_on_department FOREIGN KEY (department_id) REFERENCES department (id);

ALTER TABLE department_lectors
    ADD CONSTRAINT fk_deplec_on_lector FOREIGN KEY (lectors_id) REFERENCES lector (id);