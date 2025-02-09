CREATE TABLE IF NOT EXISTS ACCEPTABLE_DEVIATIONS_GEODESY
(
    id                                BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    equipment_library_id              BIGINT                                  NOT NULL,
    fulls                             BOOLEAN                                 NOT NULL,
    old                               BOOLEAN                                 NOT NULL,
    volume                            INTEGER                                 NOT NULL,
    acceptable_precipitation          INTEGER                                 NOT NULL,
    max_difference_neighboring_points INTEGER                                 NOT NULL,
    max_difference_diametric_points   INTEGER                                 NOT NULL,
    CONSTRAINT pk_acceptableDeviationsGeodesy PRIMARY KEY (id),
    CONSTRAINT UQ_ACCEPTABLE_DEVIATIONS_GEODESY UNIQUE (equipment_library_id, fulls, old)
);

CREATE TABLE IF NOT EXISTS ACCEPTABLE_METAL_HARDNESS
(
    id                      BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    equipment_library_id    BIGINT                                  NOT NULL,
    element_library_id      BIGINT                                  NOT NULL,
    part_element_library_id BIGINT,
    standard_size_string    VARCHAR                                 NOT NULL,
    thickness               DOUBLE PRECISION,
    min_diameter            INTEGER,
    min_thickness           DOUBLE PRECISION,
    max_diameter            INTEGER,
    max_thickness           DOUBLE PRECISION,
    min_hardness            INTEGER                                 NOT NULL,
    max_hardness            INTEGER,
    measurement_error       FLOAT,
    CONSTRAINT pk_acceptableMetalHardness PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS ACCEPTABLE_RESIDUAL_THICKNESS
(
    id                      BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    equipment_library_id    BIGINT                                  NOT NULL,
    element_library_id      BIGINT                                  NOT NULL,
    part_element_library_id BIGINT,
    standard_size_string    VARCHAR                                 NOT NULL,
    acceptable_thickness    DOUBLE PRECISION,
    acceptable_percent      INTEGER,
    measurement_error       FLOAT,
    CONSTRAINT pk_acceptableResidualThickness PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS DEFECTS_LIBRARY
(
    id                      BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    defect_name             VARCHAR                                 NOT NULL,
    unacceptable            BOOLEAN                                 NOT NULL,
    use_calculate_thickness VARCHAR                                 NOT NULL,
    calculation             VARCHAR                                 NOT NULL,
    CONSTRAINT pk_defectLibrary PRIMARY KEY (id),
    CONSTRAINT UQ_DEFECTS_LIBRARY UNIQUE (defect_name)
);

CREATE TABLE IF NOT EXISTS REPAIRS_LIBRARY
(
    id          BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    repair_name VARCHAR                                 NOT NULL,
    calculation VARCHAR                                 NOT NULL,
    CONSTRAINT pk_repairLibrary PRIMARY KEY (id),
    CONSTRAINT UQ_REPAIRS_LIBRARY UNIQUE (repair_name)
);

CREATE TABLE IF NOT EXISTS LIBRARY_MEASUREMENT_PARAMETERS
(
    id               BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    parameter_name   VARCHAR                                 NOT NULL,
    unit_measurement VARCHAR                                 NOT NULL,
    defect_id        BIGINT,
    repair_id        BIGINT,
    CONSTRAINT pk_measurementParameterLibrary PRIMARY KEY (id),
    CONSTRAINT FK_LIBRARY_MEASUREMENT_PARAMETERS_ON_DEFECTS_LIBRARY
        FOREIGN KEY (defect_id) REFERENCES defects_library (id),
    CONSTRAINT FK_LIBRARY_MEASUREMENT_PARAMETERS_ON_REPAIRS_LIBRARY
        FOREIGN KEY (repair_id) REFERENCES repairs_library (id)
);

CREATE TABLE IF NOT EXISTS REPAIR_MEASUREMENTS
(
    id                BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    repair_library_id BIGINT                                  NOT NULL,
    repair_id         BIGINT,
    equipment_id      BIGINT                                  NOT NULL,
    element_id        BIGINT                                  NOT NULL,
    part_element_id   BIGINT,
    element_name      VARCHAR                                 NOT NULL,
    part_element_name VARCHAR,
    repair_name       VARCHAR                                 NOT NULL,
    parameters_string VARCHAR                                 NOT NULL,
    calculation       VARCHAR                                 NOT NULL,
    CONSTRAINT pk_repairMeasurement PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS GEODESIC_MEASUREMENTS
(
    id                          BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    equipment_id                BIGINT                                  NOT NULL,
    measurement_number          INTEGER                                 NOT NULL,
    sequential_number           INTEGER                                 NOT NULL,
    number_measurement_location INTEGER                                 NOT NULL,
    reference_point_value       INTEGER,
    control_point_value         INTEGER                                 NOT NULL,
    transition_value            INTEGER,
    CONSTRAINT pk_geodesicMeasurements PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS HARDNESS_MEASUREMENTS
(
    id                 BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    measurement_date   DATE                                    NOT NULL,
    equipment_id       BIGINT                                  NOT NULL,
    element_id         BIGINT                                  NOT NULL,
    part_element_id    BIGINT,
    measurement_number INTEGER,
    standard_size      VARCHAR                                 NOT NULL,
    measurement_value  DOUBLE PRECISION                        NOT NULL,
    status             VARCHAR                                 NOT NULL,
    measurement_status VARCHAR                                 NOT NULL,
    acceptable         BOOLEAN,
    invalid            BOOLEAN,
    no_standard        BOOLEAN,
    CONSTRAINT pk_hardnessMeasurement PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS DEFECT_MEASUREMENTS
(
    id                      BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    equipment_id            BIGINT                                  NOT NULL,
    element_name            VARCHAR                                 NOT NULL,
    part_element_name       VARCHAR,
    defect_library_id       BIGINT                                  NOT NULL,
    defect_name             VARCHAR                                 NOT NULL,
    unacceptable            BOOLEAN,
    quality_assessment      VARCHAR,
    use_calculate_thickness BOOLEAN                                 NOT NULL,
    element_id              BIGINT                                  NOT NULL,
    part_element_id         BIGINT,
    parameters_string       VARCHAR                                 NOT NULL,
    calculation             VARCHAR                                 NOT NULL,
    CONSTRAINT pk_defectMeasurement PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS ULTRASONIC_MEASUREMENTS
(
    id                    BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    measurement_date      DATE                                    NOT NULL,
    equipment_id          BIGINT                                  NOT NULL,
    element_id            BIGINT                                  NOT NULL,
    part_element_id       BIGINT,
    element_name          VARCHAR                                 NOT NULL,
    part_element_name     VARCHAR,
    standard_size         VARCHAR                                 NOT NULL,
    measurement_number    INTEGER,
    min_measurement_value DOUBLE PRECISION                        NOT NULL,
    max_measurement_value DOUBLE PRECISION                        NOT NULL,
    max_corrosion         DOUBLE PRECISION,
    residual_thickness    DOUBLE PRECISION                        NOT NULL,
    min_acceptable_value  DOUBLE PRECISION,
    measurement_status    VARCHAR                                 NOT NULL,
    status                VARCHAR                                 NOT NULL,
    CONSTRAINT pk_ultrasonicResidualThicknessMeasurement PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS WELD_DEFECT_CONTROL
(
    id                  BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    equipment_id        BIGINT                                  NOT NULL,
    welded_joint_number VARCHAR                                 NOT NULL,
    standard_size       VARCHAR                                 NOT NULL,
    defect_name         VARCHAR                                 NOT NULL,
    parameters_string   VARCHAR                                 NOT NULL,
    coordinates         VARCHAR                                 NOT NULL,
    quality_assessment  VARCHAR                                 NOT NULL,
    CONSTRAINT pk_weldDefectControl PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS ULTRASONIC_CONTROL
(
    id                  BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    equipment_id        BIGINT                                  NOT NULL,
    welded_joint_number VARCHAR                                 NOT NULL,
    standard_size       VARCHAR                                 NOT NULL,
    description_defect  VARCHAR                                 NOT NULL,
    coordinates         VARCHAR                                 NOT NULL,
    quality_assessment  VARCHAR                                 NOT NULL,
    CONSTRAINT pk_ultrasonicControl PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS MEASURED_PARAMETERS
(
    id               BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    parameter_id     BIGINT                                  NOT NULL,
    parameter_name   VARCHAR                                 NOT NULL,
    value            DOUBLE PRECISION                        NOT NULL,
    unit_measurement VARCHAR                                 NOT NULL,
    defect_id        BIGINT,
    repair_id        BIGINT,
    weld_defect_id   BIGINT,
    CONSTRAINT pk_measuredParameter PRIMARY KEY (id),
    CONSTRAINT FK_MEASURED_PARAMETERS_ON_IDENTIFIED_DEFECT_MEASUREMENTS FOREIGN KEY (defect_id)
        REFERENCES defect_measurements (id),
    CONSTRAINT FK_MEASURED_PARAMETERS_ON_COMPLETED_REPAIR_MEASUREMENTS FOREIGN KEY (repair_id)
        REFERENCES repair_measurements (id),
    CONSTRAINT FK_MEASURED_PARAMETERS_ON_WELD_DEFECT_CONTROL
        FOREIGN KEY (weld_defect_id) REFERENCES weld_defect_control (id)
);

CREATE TABLE IF NOT EXISTS CALCULATION_GEODETIC_MEASURINGS
(
    id           BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    equipment_id BIGINT                                  NOT NULL,
    CONSTRAINT pk_calculationGeodeticMeasuringPoints PRIMARY KEY (id),
    CONSTRAINT UQ_CALCULATION_GEODETIC_MEASURINGS UNIQUE (equipment_id)
);

CREATE TABLE IF NOT EXISTS REFERENCE_POINTS
(
    id                          BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    geodesic_measurement_id     BIGINT                                  NOT NULL,
    number_measurement_location INTEGER                                 NOT NULL,
    reference_point_value       INTEGER                                 NOT NULL,
    deviation                   INTEGER                                 NOT NULL,
    precipitation               INTEGER,
    acceptable_precipitation    BOOLEAN,
    CONSTRAINT pk_referencePoint PRIMARY KEY (id),
    CONSTRAINT FK_REFERENCE_POINTS_ON_CALCULATION_GEODETIC_MEASURINGS
        FOREIGN KEY (geodesic_measurement_id) REFERENCES calculation_geodetic_measurings (id)
);

CREATE TABLE IF NOT EXISTS DEVIATION_YEARS
(
    id                 BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    reference_point_id BIGINT                                  NOT NULL,
    year               INTEGER                                 NOT NULL,
    deviation          INTEGER                                 NOT NULL,
    CONSTRAINT pk_deviationYear PRIMARY KEY (id),
    CONSTRAINT UQ_DEVIATION_YEARS UNIQUE (reference_point_id, year),
    CONSTRAINT FK_DEVIATION_YEARS_ON_REFERENCE_POINTS FOREIGN KEY (reference_point_id) REFERENCES reference_points (id)
);

CREATE TABLE IF NOT EXISTS CONTROL_POINTS
(
    id                          BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    geodesic_measurement_id     BIGINT                                  NOT NULL,
    number_measurement_location INTEGER                                 NOT NULL,
    control_point_value         INTEGER                                 NOT NULL,
    deviation                   INTEGER                                 NOT NULL,
    CONSTRAINT pk_controlPoint PRIMARY KEY (id),
    CONSTRAINT FK_CONTROL_POINTS_ON_CALCULATION_GEODETIC_MEASURINGS
        FOREIGN KEY (geodesic_measurement_id) REFERENCES calculation_geodetic_measurings (id)
);

CREATE TABLE IF NOT EXISTS OPPOSITE_POINTS
(
    id                      BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    geodesic_measurement_id BIGINT                                  NOT NULL,
    first_place_number      INTEGER                                 NOT NULL,
    second_place_number     INTEGER                                 NOT NULL,
    deviation               INTEGER                                 NOT NULL,
    acceptable_difference   BOOLEAN,
    CONSTRAINT pk_calculatingOppositePoint PRIMARY KEY (id),
    CONSTRAINT FK_OPPOSITE_POINTS_ON_CALCULATION_GEODETIC_MEASURINGS
        FOREIGN KEY (geodesic_measurement_id) REFERENCES calculation_geodetic_measurings (id)
);

CREATE TABLE IF NOT EXISTS NEIGHBORING_POINTS
(
    id                      BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    geodesic_measurement_id BIGINT                                  NOT NULL,
    first_place_number      INTEGER                                 NOT NULL,
    second_place_number     INTEGER                                 NOT NULL,
    deviation               INTEGER                                 NOT NULL,
    acceptable_difference   BOOLEAN,
    CONSTRAINT pk_calculationNeighboringPoint PRIMARY KEY (id),
    CONSTRAINT FK_NEIGHBORING_POINTS_ON_CALCULATION_GEODETIC_MEASURINGS
        FOREIGN KEY (geodesic_measurement_id) REFERENCES calculation_geodetic_measurings (id)
);

CREATE TABLE IF NOT EXISTS CALCULATION_DEFECT_MEASUREMENTS
(
    id                BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    defect_id         BIGINT,
    equipment_id      BIGINT                                  NOT NULL,
    element_id        BIGINT                                  NOT NULL,
    part_element_id   BIGINT,
    defect_name       VARCHAR                                 NOT NULL,
    element_name      VARCHAR                                 NOT NULL,
    part_element_name VARCHAR,
    unacceptable      BOOLEAN,
    parameters_string VARCHAR                                 NOT NULL,
    CONSTRAINT pk_calculationDefectMeasurement PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS CALCULATION_REPAIR_MEASUREMENTS
(
    id                BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    repair_id         BIGINT,
    equipment_id      BIGINT                                  NOT NULL,
    element_id        BIGINT                                  NOT NULL,
    part_element_id   BIGINT,
    repair_name       VARCHAR                                 NOT NULL,
    element_name      VARCHAR                                 NOT NULL,
    part_element_name VARCHAR,
    parameters_string VARCHAR                                 NOT NULL,
    CONSTRAINT pk_calculationRepairMeasurement PRIMARY KEY (id)
);