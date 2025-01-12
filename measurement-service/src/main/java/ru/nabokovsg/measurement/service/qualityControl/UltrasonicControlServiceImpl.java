package ru.nabokovsg.measurement.service.qualityControl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.measurement.dto.ultrasonicControl.NewUltrasonicControlDto;
import ru.nabokovsg.measurement.dto.ultrasonicControl.ResponseUltrasonicControlDto;
import ru.nabokovsg.measurement.dto.ultrasonicControl.UpdateUltrasonicControlDto;
import ru.nabokovsg.measurement.exceptions.NotFoundException;
import ru.nabokovsg.measurement.mapper.qualityControl.UltrasonicControlMapper;
import ru.nabokovsg.measurement.model.qualityControl.QUltrasonicControl;
import ru.nabokovsg.measurement.model.qualityControl.UltrasonicControl;
import ru.nabokovsg.measurement.repository.qualityControl.UltrasonicControlRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UltrasonicControlServiceImpl implements UltrasonicControlService {

    private final UltrasonicControlRepository repository;
    private final UltrasonicControlMapper mapper;
    private final EntityManager em;

    @Override
    public ResponseUltrasonicControlDto save(NewUltrasonicControlDto defectDto) {
        UltrasonicControl defect = mapper.mapToUltrasonicControl(defectDto);
        if (!exists(defect)) {
            if (defectDto.getDescriptionDefect() == null) {
                saveWithPositiveQualityAssessment(defect);
            }
        }
        return mapper.mapToResponseUltrasonicControlDto(repository.save(defect));
    }

    @Override
    public ResponseUltrasonicControlDto update(UpdateUltrasonicControlDto defectDto) {
        if (repository.existsById(defectDto.getId())) {
            UltrasonicControl defect = mapper.mapToUpdateUltrasonicControl(defectDto);
            if (defectDto.getDescriptionDefect() == null) {
                saveWithPositiveQualityAssessment(defect);
            }
            return mapper.mapToResponseUltrasonicControlDto(repository.save(defect));
        }
        throw new NotFoundException(
                String.format("Ultrasonic control result with id=%s not found for update", defectDto.getId()));

    }

    @Override
    public ResponseUltrasonicControlDto get(Long id) {
        return mapper.mapToResponseUltrasonicControlDto(
                repository.findById(id)
                          .orElseThrow(() -> new NotFoundException(
                                  String.format("Ultrasonic control result with id=%s not found for delete", id))));
    }

    @Override
    public List<ResponseUltrasonicControlDto> getAll(Long equipmentId) {
        return repository.findAllByEquipmentId(equipmentId)
                                                .stream()
                                                .map(mapper::mapToResponseUltrasonicControlDto)
                                                .toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
        throw new NotFoundException(String.format("Ultrasonic control result with id=%s not found for delete", id));
    }

    private void saveWithPositiveQualityAssessment(UltrasonicControl defect) {
        mapper.mapToPositiveQualityAssessment(defect, "Дефекты не зафиксированы", "-");
    }

    private boolean exists(UltrasonicControl defect) {
        QUltrasonicControl ultrasonicControl = QUltrasonicControl.ultrasonicControl;
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(ultrasonicControl.equipmentId.eq(defect.getEquipmentId()));
        builder.and(ultrasonicControl.weldedJointNumber.eq(defect.getWeldedJointNumber()));
        builder.and(ultrasonicControl.standardSize.eq(defect.getStandardSize()));
        if (defect.getDescriptionDefect() != null) {
            builder.and(ultrasonicControl.descriptionDefect.eq(defect.getDescriptionDefect()));
        }
        builder.and(ultrasonicControl.qualityAssessment.eq(defect.getQualityAssessment()));
        return new JPAQueryFactory(em).from(ultrasonicControl)
                .select(ultrasonicControl)
                .where(builder)
                .fetchOne() == null;
    }
}