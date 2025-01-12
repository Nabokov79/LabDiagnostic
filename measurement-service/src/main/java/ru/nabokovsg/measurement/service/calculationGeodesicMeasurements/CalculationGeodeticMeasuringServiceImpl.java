package ru.nabokovsg.measurement.service.calculationGeodesicMeasurements;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.measurement.dto.calculationGeodesicMeasurements.ResponseCalculatingOppositePointDto;
import ru.nabokovsg.measurement.dto.calculationGeodesicMeasurements.ResponseCalculationControlPointDto;
import ru.nabokovsg.measurement.dto.calculationGeodesicMeasurements.ResponseCalculationNeighboringPointDto;
import ru.nabokovsg.measurement.dto.calculationGeodesicMeasurements.ResponseCalculationReferencePointDto;
import ru.nabokovsg.measurement.dto.client.EquipmentDto;
import ru.nabokovsg.measurement.exceptions.BadRequestException;
import ru.nabokovsg.measurement.exceptions.NotFoundException;
import ru.nabokovsg.measurement.mapper.calculationGeodesicMeasurements.CalculationGeodeticMeasuringMapper;
import ru.nabokovsg.measurement.model.calculationGeodesicMeasurements.*;
import ru.nabokovsg.measurement.model.diagnostics.GeodesicMeasurements;
import ru.nabokovsg.measurement.model.diagnostics.QGeodesicMeasurements;
import ru.nabokovsg.measurement.model.library.AcceptableDeviationsGeodesy;
import ru.nabokovsg.measurement.repository.calculationGeodesicMeasurements.CalculationGeodeticMeasuringRepository;
import ru.nabokovsg.measurement.repository.library.AcceptableDeviationsGeodesyRepository;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CalculationGeodeticMeasuringServiceImpl implements CalculationGeodeticMeasuringService {

    private final CalculationGeodeticMeasuringRepository repository;
    private final CalculationGeodeticMeasuringMapper mapper;
    private final RecalculateGeodesicMeasurementsService recalculateService;
    private final AcceptableDeviationsGeodesyRepository acceptableDeviationsGeodesyRepository;
    private final CalculationGeodeticReferencePointService referencePointService;
    private final CalculationGeodeticControlPointService controlPointService;
    private final CalculatingOppositeMeasurementPointService oppositeMeasurementPointService;
    private final CalculationNeighboringMeasurementPointService neighboringMeasurementPointService;
    private final EntityManager em;

    @Override
    public void save(Set<GeodesicMeasurements> measurements, EquipmentDto equipment, Long equipmentId) {
        if (validate(measurements,equipment.getGeodesyLocations())) {
            CalculationGeodeticMeasuring calculationGeodeticMeasuringPoints
                                        = repository.save(mapper.mapToCalculationGeodeticMeasuringPoints(equipmentId));
            AcceptableDeviationsGeodesy acceptableDeviationsGeodesy = acceptableDeviationsGeodesyRepository
                                                .findByEquipmentLibraryIdAndFullAndOld(equipment.getEquipmentLibraryId()
                                                                                     , equipment.isHeatCarrier()
                                                                                     , equipment.getOld());
            List<GeodesicMeasurements> recalculateMeasurements = recalculateService.recalculate(measurements);
            if (validateReferencePoint(recalculateMeasurements)) {
                referencePointService.save(acceptableDeviationsGeodesy
                        , recalculateMeasurements
                        , calculationGeodeticMeasuringPoints);
            }
            List<ControlPoint> controlPoints = controlPointService.save(recalculateMeasurements
                                                                      , calculationGeodeticMeasuringPoints);
            oppositeMeasurementPointService.save(controlPoints
                                               , acceptableDeviationsGeodesy
                                               , calculationGeodeticMeasuringPoints);
            neighboringMeasurementPointService.save(controlPoints
                                                  , acceptableDeviationsGeodesy
                                                  , calculationGeodeticMeasuringPoints);
        }
    }

    @Override
    public void update(Set<GeodesicMeasurements> measurements, EquipmentDto equipment, Long equipmentId) {
        AcceptableDeviationsGeodesy acceptableDeviationsGeodesy = acceptableDeviationsGeodesyRepository
                                                .findByEquipmentLibraryIdAndFullAndOld(equipment.getEquipmentLibraryId()
                                                                                     , equipment.isHeatCarrier()
                                                                                     , equipment.getOld());
        CalculationGeodeticMeasuring geodeticMeasuringPoints = get(equipmentId);
        List<GeodesicMeasurements> recalculateMeasurements = recalculateService.recalculate(measurements);
        referencePointService.update(acceptableDeviationsGeodesy
                                   , recalculateMeasurements
                                   , geodeticMeasuringPoints.getReferencePoints());
        List<ControlPoint> controlPoints = controlPointService.update(recalculateMeasurements
                                                                    , geodeticMeasuringPoints.getControlPoints());
        oppositeMeasurementPointService.update(controlPoints
                                            , acceptableDeviationsGeodesy
                                            , geodeticMeasuringPoints.getCalculatingOppositePoint());
        neighboringMeasurementPointService.update(controlPoints
                                                , acceptableDeviationsGeodesy
                                                , geodeticMeasuringPoints.getCalculationNeighboringPoint());
    }

    @Override
    public List<ResponseCalculationReferencePointDto> getAllReferencePoint(Long equipmentId) {
        QReferencePoint referencePoint = QReferencePoint.referencePoint;
        return new JPAQueryFactory(em).select(referencePoint)
                .from(referencePoint)
                .where(QGeodesicMeasurements.geodesicMeasurements.equipmentId.eq(equipmentId))
                .fetch()
                .stream().map(mapper::mapToResponseCalculationReferencePointDto)
                .toList();
    }

    @Override
    public List<ResponseCalculationControlPointDto> getAllControlPoint(Long equipmentId) {
        QControlPoint controlPoint = QControlPoint.controlPoint;
        return new JPAQueryFactory(em).select(controlPoint)
                .from(controlPoint)
                .where(QGeodesicMeasurements.geodesicMeasurements.equipmentId.eq(equipmentId))
                .fetch()
                .stream().map(mapper::mapToResponseCalculationControlPointDto)
                .toList();
    }

    @Override
    public List<ResponseCalculatingOppositePointDto> getAllOppositePoint(Long equipmentId) {
        QCalculatingOppositePoint oppositePoint = QCalculatingOppositePoint.calculatingOppositePoint;
        return new JPAQueryFactory(em).select(oppositePoint)
                .from(oppositePoint)
                .where(QGeodesicMeasurements.geodesicMeasurements.equipmentId.eq(equipmentId))
                .fetch()
                .stream().map(mapper::mapToResponseCalculatingOppositePointDto)
                .toList();
    }

    @Override
    public List<ResponseCalculationNeighboringPointDto> getAllNeighboringPoint(Long equipmentId) {
        QCalculationNeighboringPoint neighboringPoint = QCalculationNeighboringPoint.calculationNeighboringPoint;
        return new JPAQueryFactory(em).select(neighboringPoint)
                .from(neighboringPoint)
                .where(QGeodesicMeasurements.geodesicMeasurements.equipmentId.eq(equipmentId))
                .fetch()
                .stream().map(mapper::mapToResponseCalculationNeighboringPointDto)
                .toList();
    }

    private boolean validateReferencePoint(List<GeodesicMeasurements> recalculateMeasurements) {
        List<GeodesicMeasurements> referencePoints = recalculateMeasurements.stream()
                                                                .filter(point -> point.getReferencePointValue() != null)
                                                                .toList();
        return referencePoints.isEmpty();
    }
    private boolean validate(Set<GeodesicMeasurements> measurements, int geodesyLocations) {
        int sequentialNumber = measurements.stream()
                    .map(GeodesicMeasurements::getSequentialNumber)
                    .max(Integer::compare)
                    .orElseThrow(() -> new BadRequestException("Geodesic Measurement sequential number max not found"));
        return sequentialNumber == geodesyLocations;
    }

    private CalculationGeodeticMeasuring get(Long equipmentId)  {
        CalculationGeodeticMeasuring geodeticMeasuringPoints = repository.findByEquipmentId(equipmentId)
                .orElseThrow(() -> new NotFoundException(
                        String.format("CalculationGeodeticMeasuringPoints by equipmentId=%s not found", equipmentId)));
        if (geodeticMeasuringPoints.getControlPoints().isEmpty()) {
            throw new NotFoundException(String.format("ControlPoint not found: %s"
                                                                      , geodeticMeasuringPoints.getControlPoints()));
        }
        if (geodeticMeasuringPoints.getCalculatingOppositePoint().isEmpty()) {
            throw new NotFoundException(String.format("CalculatingOppositePoint not found: %s"
                                                            , geodeticMeasuringPoints.getCalculatingOppositePoint()));
        }
        if (geodeticMeasuringPoints.getCalculationNeighboringPoint().isEmpty()) {
            throw new NotFoundException(String.format("CalculationNeighboringPoint not found: %s"
                                                         , geodeticMeasuringPoints.getCalculationNeighboringPoint()));
        }
        return geodeticMeasuringPoints;
    }

}