package ru.nabokovsg.measurement.service.calculationGeodesicMeasurements;

import ru.nabokovsg.measurement.dto.calculationGeodesicMeasurements.ResponseCalculatingOppositePointDto;
import ru.nabokovsg.measurement.dto.calculationGeodesicMeasurements.ResponseCalculationControlPointDto;
import ru.nabokovsg.measurement.dto.calculationGeodesicMeasurements.ResponseCalculationNeighboringPointDto;
import ru.nabokovsg.measurement.dto.calculationGeodesicMeasurements.ResponseCalculationReferencePointDto;
import ru.nabokovsg.measurement.dto.client.EquipmentDto;
import ru.nabokovsg.measurement.model.diagnostics.GeodesicMeasurements;

import java.util.List;
import java.util.Set;

public interface CalculationGeodeticMeasuringService {

    void save(Set<GeodesicMeasurements> measurements, EquipmentDto equipment, Long equipmentId);

    void update(Set<GeodesicMeasurements> measurements, EquipmentDto equipment, Long equipmentId);

    List<ResponseCalculationReferencePointDto> getAllReferencePoint(Long equipmentId);

    List<ResponseCalculationControlPointDto> getAllControlPoint(Long equipmentId);

    List<ResponseCalculatingOppositePointDto> getAllOppositePoint(Long equipmentId);

    List<ResponseCalculationNeighboringPointDto> getAllNeighboringPoint(Long equipmentId);
}