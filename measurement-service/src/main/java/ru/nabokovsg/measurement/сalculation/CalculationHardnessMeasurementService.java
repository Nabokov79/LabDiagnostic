package ru.nabokovsg.measurement.сalculation;

import ru.nabokovsg.measurement.model.diagnostics.HardnessMeasurement;

public interface CalculationHardnessMeasurementService {

    int getAverageMeasurementValue(Integer measurementValue, Integer measurementValueDto);

    void setMeasurementStatus(HardnessMeasurement measurement);
}