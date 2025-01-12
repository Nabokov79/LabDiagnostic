CREATE TABLE IF NOT EXISTS QCL_EMPLOYEES
(
    id         BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    chief      BOOLEAN                                 NOT NULL,
    name       VARCHAR                                 NOT NULL,
    patronymic VARCHAR                                 NOT NULL,
    surname    VARCHAR                                 NOT NULL,
    post       VARCHAR                                 NOT NULL,
    phone      VARCHAR,
    fax        VARCHAR,
    email      VARCHAR                                 NOT NULL,
    CONSTRAINT pk_qclEmployee PRIMARY KEY (id),
    CONSTRAINT UQ_QCL_EMPLOYEES UNIQUE (email)
);

CREATE TABLE IF NOT EXISTS QCL_DOCUMENT_TYPES
(
    id             BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    diagnosis_type VARCHAR                                 NOT NULL,
    type           VARCHAR                                 NOT NULL,
    document_title VARCHAR                                 NOT NULL,
    CONSTRAINT pk_qclDocumentType PRIMARY KEY (id),
    CONSTRAINT UQ_QCL_DOCUMENT_TYPES UNIQUE (diagnosis_type, type, document_title)
);

CREATE TABLE IF NOT EXISTS JOURNAL_COMPLETED_WORKS
(
    id                      BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    journal_type            VARCHAR                                 NOT NULL,
    date                    DATE,
    branch_id               BIGINT,
    heat_supply_area_id     BIGINT,
    heat_supply_area        VARCHAR,
    exploitation_region_id  BIGINT,
    address_id              BIGINT,
    branch                  VARCHAR,
    exploitation_region     VARCHAR,
    address                 VARCHAR,
    equipment_id            BIGINT                                  NOT NULL,
    element_id              BIGINT,
    equipment_name          VARCHAR                                 NOT NULL,
    document                VARCHAR                                 NOT NULL,
    document_type_id        BIGINT                                  NOT NULL,
    task_source             VARCHAR,
    measuring_instruments   VARCHAR,
    comment                 VARCHAR,
    chief_id                BIGINT                                  NOT NULL,
    chief                   VARCHAR                                 NOT NULL,
    employees               VARCHAR                                 NOT NULL,
    document_number         INTEGER,
    methodological_document VARCHAR,
    regulatory_document     VARCHAR,
    next_date               DATE,
    status                  VARCHAR                                 NOT NULL,
    CONSTRAINT pk_journalCompletedWork PRIMARY KEY (id),
    CONSTRAINT FK_JOURNAL_COMPLETED_WORKS_ON_QCL_EMPLOYEES FOREIGN KEY (chief_id) REFERENCES qcl_employees (id),
    CONSTRAINT FK_JOURNAL_COMPLETED_WORKS_ON_QCL_DOCUMENT_TYPES
        FOREIGN KEY (document_type_id) REFERENCES qcl_document_types (id)
);

CREATE TABLE IF NOT EXISTS JOURNAL_COMPLETED_WORK_EMPLOYEES
(
    journal_id  BIGINT,
    employee_id BIGINT,
    CONSTRAINT pk_journal_completed_works_of_qcl_employees PRIMARY KEY (journal_id, employee_id)
);

CREATE TABLE IF NOT EXISTS REGULATORY_TECHNICAL_DOCUMENTATIONS
(
    id     BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    view   VARCHAR,
    number VARCHAR,
    title  VARCHAR                                 NOT NULL,
    CONSTRAINT pk_regulatoryTechnicalDocumentation PRIMARY KEY (id),
    CONSTRAINT UQ_REGULATORY_TECHNICAL_DOCUMENTATIONS UNIQUE (title)
);

CREATE TABLE IF NOT EXISTS QCL_CERTIFICATES
(
    id              BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    document_name   VARCHAR                                 NOT NULL,
    document_number VARCHAR                                 NOT NULL,
    date_issue      DATE                                    NOT NULL,
    validity_period DATE                                    NOT NULL,
    organization    VARCHAR                                 NOT NULL,
    CONSTRAINT pk_qualityControlLaboratoryCertificate PRIMARY KEY (id),
    CONSTRAINT UQ_QCL_CERTIFICATES UNIQUE (document_name, document_number, date_issue, validity_period)
);

CREATE TABLE IF NOT EXISTS QCL_EMPLOYEE_CERTIFICATES
(
    id              BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    document_name   VARCHAR                                 NOT NULL,
    document_number VARCHAR                                 NOT NULL,
    control_name    VARCHAR                                 NOT NULL,
    level           VARCHAR                                 NOT NULL,
    date_issue      DATE                                    NOT NULL,
    validity_period DATE                                    NOT NULL,
    points          VARCHAR                                 NOT NULL,
    organization    VARCHAR                                 NOT NULL,
    employee_id     BIGINT                                  NOT NULL,
    CONSTRAINT pk_employeeCertificate PRIMARY KEY (id),
    CONSTRAINT UQ_QCL_EMPLOYEE_CERTIFICATES UNIQUE (control_name, employee_id),
    CONSTRAINT FK_QCL_EMPLOYEE_CERTIFICATES_ON_QCL_EMPLOYEES
        FOREIGN KEY (employee_id) REFERENCES qcl_employees (id)
);

CREATE TABLE IF NOT EXISTS MEASURING_TOOLS
(
    id                     BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    toll_name              VARCHAR                                 NOT NULL,
    model                  VARCHAR                                 NOT NULL,
    work_number            VARCHAR,
    purpose                VARCHAR,
    manufacturing          DATE,
    exploitation           DATE,
    manufacturer           VARCHAR,
    measuring_range        VARCHAR,
    characteristics        VARCHAR,
    owner                  VARCHAR                                 NOT NULL,
    verification_date      DATE,
    next_verification_date DATE,
    organization           VARCHAR,
    certificate_number     VARCHAR,
    registry               VARCHAR,
    note                   VARCHAR,
    control_name           VARCHAR                                 NOT NULL,
    employee_id            BIGINT,
    CONSTRAINT pk_measuringTool PRIMARY KEY (id),
    CONSTRAINT UQ_MEASURING_TOOL UNIQUE (toll_name, model, work_number)
);