package ru.nabokovsg.measurement.service.diagnostics;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.measurement.client.MeasurementClient;
import ru.nabokovsg.measurement.dto.client.EquipmentDto;
import ru.nabokovsg.measurement.dto.geodesicMeasurement.NewGeodesicMeasurementDto;
import ru.nabokovsg.measurement.dto.geodesicMeasurement.ResponseGeodesicMeasurementDto;
import ru.nabokovsg.measurement.dto.geodesicMeasurement.UpdateGeodesicMeasurementDto;
import ru.nabokovsg.measurement.exceptions.BadRequestException;
import ru.nabokovsg.measurement.exceptions.NotFoundException;
import ru.nabokovsg.measurement.mapper.diagnostics.GeodesicMeasurementsMapper;
import ru.nabokovsg.measurement.model.diagnostics.GeodesicMeasurements;
import ru.nabokovsg.measurement.repository.diagnostics.GeodesicMeasurementsRepository;
import ru.nabokovsg.measurement.service.calculationGeodesicMeasurements.CalculationGeodeticMeasuringService;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GeodesicMeasurementsServiceImpl implements GeodesicMeasurementsService {

    private final GeodesicMeasurementsRepository repository;
    private final GeodesicMeasurementsMapper mapper;
    private final CalculationGeodeticMeasuringService geodeticMeasuringPointsService;
    private final MeasurementClient client;

    @Override
    public List<ResponseGeodesicMeasurementDto> save(NewGeodesicMeasurementDto measurementDto) {
        Set<GeodesicMeasurements> measurements = repository.findAllByEquipmentId(measurementDto.getEquipmentId());
        GeodesicMeasurements measurement = repository.findByEquipmentIdAndNumberMeasurementLocation(
                                                                      measurementDto.getEquipmentId()
                                                                    , measurementDto.getNumberMeasurementLocation());
        if (measurement == null) {
            measurement = mapper.mapToGeodesicMeasurementsPoint(measurementDto, getSequentialNumber(measurements));
            EquipmentDto equipment = client.getEquipment(measurement.getEquipmentId());
            validByEquipment(measurement.getSequentialNumber(), equipment);
            measurements.add(repository.save(measurement));
            geodeticMeasuringPointsService.save(measurements, equipment, measurement.getEquipmentId());
            return measurements.stream()
                               .map(mapper::mapToResponseGeodesicMeasurementsPointDto)
                               .toList();
        }
        throw new BadRequestException(String.format("GeodesicMeasurementЖ: %s, is found", measurementDto));
    }

    @Override
    public List<ResponseGeodesicMeasurementDto> update(UpdateGeodesicMeasurementDto measurementDto) {
        Map<Long, GeodesicMeasurements> measurements = repository.findAllByEquipmentId(
                        measurementDto.getEquipmentId())
                .stream()
                .collect(Collectors.toMap(GeodesicMeasurements::getId, g -> g));
        GeodesicMeasurements measurement = measurements.get(measurementDto.getId());
        if (measurement != null) {
            measurements.put(measurement.getId()
                    , repository.save(mapper.mapToUpdateGeodesicMeasurementsPoint(measurement, measurementDto)));
            geodeticMeasuringPointsService.update(new HashSet<>(measurements.values())
                                                              , client.getEquipment(measurementDto.getEquipmentId())
                                                                                  , measurement.getEquipmentId());
            return measurements.values()
                               .stream()
                               .map(mapper::mapToResponseGeodesicMeasurementsPointDto)
                               .toList();
        }
        throw new NotFoundException(String.format("GeodesicMeasurement not found for update: %s", measurementDto));
    }

    private void validByEquipment(int sequentialNumber, EquipmentDto equipment) {
        if (equipment.getGeodesyLocations() == null) {
            throw new BadRequestException(
                    String.format("GeodesyLocations should not be null: %s", equipment.getGeodesyLocations()));
        }
        if (sequentialNumber > equipment.getGeodesyLocations()) {
            throw new BadRequestException(
                    String.format("Geodetic measurements cannot be more than %s", equipment.getGeodesyLocations()));
        }
    }

    @Override
    public ResponseGeodesicMeasurementDto get(Long id) {
        return mapper.mapToResponseGeodesicMeasurementsPointDto(repository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        String.format("GeodesicMeasurement with id=%s not found", id))));
    }


    @Override
    public List<ResponseGeodesicMeasurementDto> getAll(Long equipmentId) {
        return repository.findAllByEquipmentId(equipmentId)
                         .stream()
                         .map(mapper::mapToResponseGeodesicMeasurementsPointDto)
                         .toList();
    }

    private int getSequentialNumber(Set<GeodesicMeasurements> measurements) {
        if (measurements.isEmpty()) {
            return 1;
        }
        int sequentialNumber = measurements.stream()
                                           .map(GeodesicMeasurements::getSequentialNumber)
                                           .max(Integer::compareTo)
                                           .get();
        return ++sequentialNumber;
    }
}