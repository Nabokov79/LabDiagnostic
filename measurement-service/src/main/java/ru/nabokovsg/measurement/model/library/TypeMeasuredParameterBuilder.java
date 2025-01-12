package ru.nabokovsg.measurement.model.library;

import ru.nabokovsg.measurement.model.common.MeasurementType;

public class TypeMeasuredParameterBuilder {

    private final MeasurementType libraryDataType;
    private final ParameterCalculationType calculation;
    private final DefectLibrary defect;
    private final RepairLibrary repair;

    public TypeMeasuredParameterBuilder(Builder builder) {
        this.libraryDataType = builder.libraryDataType;
        this.calculation = builder.calculation;
        this.defect = builder.defect;
        this.repair = builder.repair;
    }

    public MeasurementType getLibraryDataType() {
        return libraryDataType;
    }

    public ParameterCalculationType getCalculation() {
        return calculation;
    }

    public DefectLibrary getDefect() {
        return defect;
    }

    public RepairLibrary getRepair() {
        return repair;
    }

    public static class Builder {
        private MeasurementType libraryDataType;
        private ParameterCalculationType calculation;
        private DefectLibrary defect;
        private RepairLibrary repair;

        public Builder libraryDataType(MeasurementType libraryDataType) {
            this.libraryDataType = libraryDataType;
            return this;
        }

        public Builder calculation(ParameterCalculationType calculation) {
            this.calculation = calculation;
            return this;
        }

        public Builder defect(DefectLibrary defect) {
            this.defect = defect;
            return this;
        }

        public Builder repair(RepairLibrary repair) {
            this.repair = repair;
            return this;
        }

        public TypeMeasuredParameterBuilder build() {
            return new TypeMeasuredParameterBuilder(this);
        }
    }
}
