package ru.nabokovsg.measurement.model.diagnostics;

import ru.nabokovsg.measurement.dto.measuredParameter.NewMeasuredParameterDto;
import ru.nabokovsg.measurement.model.common.MeasurementType;
import ru.nabokovsg.measurement.model.library.MeasurementParameterLibrary;
import ru.nabokovsg.measurement.model.qualityControl.WeldDefectControl;

import java.util.List;
import java.util.Set;

public class ParameterMeasurementBuilder {

    private final MeasurementType libraryDataType;
    private final DefectMeasurement defect;
    private final WeldDefectControl weldDefect;
    private final RepairMeasurement repair;
    private final List<NewMeasuredParameterDto> newMeasuredParameters;
    private final Set<MeasuredParameter> measuredParameters;
    private final Set<MeasurementParameterLibrary> measurementParameterLibraries;

    public ParameterMeasurementBuilder(Builder builder) {
        this.libraryDataType = builder.libraryDataType;
        this.defect = builder.defect;
        this.weldDefect = builder.weldDefect;
        this.repair = builder.repair;
        this.measuredParameters = builder.measuredParameters;
        this.newMeasuredParameters = builder.newMeasuredParameters;
        this.measurementParameterLibraries = builder.measurementParameterLibraries;
    }

    public MeasurementType getLibraryDataType() {
        return libraryDataType;
    }

    public DefectMeasurement getDefect() {
        return defect;
    }

    public WeldDefectControl getWeldDefect() {
        return weldDefect;
    }

    public RepairMeasurement getRepair() {
        return repair;
    }

    public List<NewMeasuredParameterDto> getNewMeasuredParameters() {
        return newMeasuredParameters;
    }

    public Set<MeasuredParameter> getMeasuredParameters() {
        return measuredParameters;
    }

    public Set<MeasurementParameterLibrary> getMeasurementParameterLibraries() {
        return measurementParameterLibraries;
    }

    public static class Builder {

        private MeasurementType libraryDataType;
        private DefectMeasurement defect;
        private WeldDefectControl weldDefect;
        private RepairMeasurement repair;
        private List<NewMeasuredParameterDto> newMeasuredParameters;
        private Set<MeasuredParameter> measuredParameters;
        private Set<MeasurementParameterLibrary> measurementParameterLibraries;

        public Builder libraryDataType(MeasurementType libraryDataType) {
            this.libraryDataType = libraryDataType;
            return this;
        }

        public Builder defect(DefectMeasurement defect) {
            this.defect = defect;
            return this;
        }

        public Builder weldDefect(WeldDefectControl weldDefect) {
            this.weldDefect = weldDefect;
            return this;
        }

        public Builder repair(RepairMeasurement repair) {
            this.repair = repair;
            return this;
        }

        public Builder newMeasuredParameters(List<NewMeasuredParameterDto> newMeasuredParameters) {
            this.newMeasuredParameters = newMeasuredParameters;
            return this;
        }

        public Builder measuredParameters(Set<MeasuredParameter> measuredParameters) {
            this.measuredParameters = measuredParameters;
            return this;
        }

        public Builder measurementParameterLibraries(Set<MeasurementParameterLibrary> measurementParameterLibraries) {
            this.measurementParameterLibraries = measurementParameterLibraries;
            return this;
        }

        public ParameterMeasurementBuilder build() {
            return new ParameterMeasurementBuilder(this);
        }
    }
}