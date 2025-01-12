CREATE TABLE IF NOT EXISTS ADDRESSES
(
    id              BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    index           INTEGER,
    city            VARCHAR                                 NOT NULL,
    street          VARCHAR                                 NOT NULL,
    house_number    VARCHAR                                 NOT NULL,
    building_number VARCHAR,
    letter          VARCHAR,
    CONSTRAINT pk_address PRIMARY KEY (id),
    CONSTRAINT UQ_ADDRESSES UNIQUE (city, street, house_number, building_number, letter)
);

CREATE TABLE IF NOT EXISTS ORGANIZATIONS
(
    id         BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    full_name  VARCHAR                                 NOT NULL,
    short_name VARCHAR                                 NOT NULL,
    address    VARCHAR,
    CONSTRAINT pk_organization PRIMARY KEY (id),
    CONSTRAINT UQ_ORGANIZATIONS UNIQUE (full_name, short_name)
);

CREATE TABLE IF NOT EXISTS BRANCHES
(
    id              BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    full_name       VARCHAR                                 NOT NULL,
    short_name      VARCHAR                                 NOT NULL,
    address         VARCHAR,
    organization_id BIGINT                                  NOT NULL,
    CONSTRAINT pk_branch PRIMARY KEY (id),
    CONSTRAINT UQ_BRANCHES UNIQUE (full_name, short_name),
    CONSTRAINT FK_BRANCHES_ON_ORGANIZATIONS FOREIGN KEY (organization_id) REFERENCES organizations (id)
);

CREATE TABLE IF NOT EXISTS DEPARTMENTS
(
    id         BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    full_name  VARCHAR                                 NOT NULL,
    short_name VARCHAR                                 NOT NULL,
    address    VARCHAR,
    branch_id  BIGINT                                  NOT NULL,
    CONSTRAINT pk_department PRIMARY KEY (id),
    CONSTRAINT UQ_DEPARTMENTS UNIQUE (full_name, short_name),
    CONSTRAINT FK_DEPARTMENTS_ON_BRANCHES FOREIGN KEY (branch_id) REFERENCES branches (id)
);

CREATE TABLE IF NOT EXISTS HEAT_SUPPLY_AREAS
(
    id            BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    full_name     VARCHAR                                 NOT NULL,
    short_name    VARCHAR                                 NOT NULL,
    branch_id     BIGINT                                  NOT NULL,
    CONSTRAINT pk_heatSupplyArea PRIMARY KEY (id),
    CONSTRAINT UQ_HEAT_SUPPLY_AREAS UNIQUE (full_name, short_name)
);

CREATE TABLE IF NOT EXISTS EXPLOITATION_REGIONS
(
    id         BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    full_name  VARCHAR                                 NOT NULL,
    short_name VARCHAR                                 NOT NULL,
    branch_id  BIGINT                                  NOT NULL,
    CONSTRAINT pk_exploitationRegion PRIMARY KEY (id),
    CONSTRAINT UQ_EXPLOITATION_REGIONS UNIQUE (full_name, short_name),
    CONSTRAINT FK_EXPLOITATION_REGIONS_ON_BRANCHES FOREIGN KEY (branch_id) REFERENCES branches (id)
);

CREATE TABLE IF NOT EXISTS BUILDINGS
(
    id            BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    building_type VARCHAR                                 NOT NULL,
    login         VARCHAR,
    region_id     BIGINT                                  NOT NULL,
    address       VARCHAR                                 NOT NULL,
    CONSTRAINT pk_building PRIMARY KEY (id),
    CONSTRAINT UQ_BUILDINGS UNIQUE (building_type, login),
    CONSTRAINT FK_BUILDINGS_ON_EXPLOITATION_REGIONS FOREIGN KEY (region_id) REFERENCES exploitation_regions (id)
);