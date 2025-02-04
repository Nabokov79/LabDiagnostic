package ru.nabokovsg.measurement.service.library;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.measurement.dto.defectLibrary.NewDefectLibraryDto;
import ru.nabokovsg.measurement.dto.defectLibrary.ResponseDefectLibraryDto;
import ru.nabokovsg.measurement.dto.defectLibrary.ResponseShortDefectLibraryDto;
import ru.nabokovsg.measurement.dto.defectLibrary.UpdateDefectLibraryDto;
import ru.nabokovsg.measurement.exceptions.BadRequestException;
import ru.nabokovsg.measurement.exceptions.NotFoundException;
import ru.nabokovsg.measurement.mapper.library.DefectLibraryMapper;
import ru.nabokovsg.measurement.model.library.DefectLibrary;
import ru.nabokovsg.measurement.model.common.MeasurementType;
import ru.nabokovsg.measurement.model.library.ParameterCalculationType;
import ru.nabokovsg.measurement.model.library.TypeMeasuredParameterBuilder;
import ru.nabokovsg.measurement.repository.library.DefectLibraryRepository;
import ru.nabokovsg.measurement.service.synchronizing.SynchronizingService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefectLibraryServiceImpl implements DefectLibraryService {

    private final DefectLibraryRepository repository;
    private final DefectLibraryMapper mapper;
    private final MeasuredParameterLibraryService parameterService;
    private final SynchronizingService synchronizingService;

    @Override
    public ResponseDefectLibraryDto save(NewDefectLibraryDto defectDto) {
        if (repository.existsByDefectName(defectDto.getDefectName())) {
            throw new BadRequestException(String.format("DefectLibrary with defectDto=%s is found", defectDto));
        }
        DefectLibrary defect = mapper.mapToTypeDefectLibrary(defectDto);
        addTypeCalculation(defect, defectDto.getCalculation());
        defect = repository.save(defect);
        defect.setMeasuredParameters(
                parameterService.save(new TypeMeasuredParameterBuilder.Builder()
                                                                      .libraryDataType(MeasurementType.DEFECT)
                                                                      .calculation(defect.getCalculation())
                                                                      .defect(defect)
                                                                      .build()
              , defectDto.getMeasuredParameters()));
        return mapper.mapToResponseTypeDefectLibraryDto(defect);
    }

    @Override
    public ResponseDefectLibraryDto update(UpdateDefectLibraryDto defectDto) {
        DefectLibrary defect = getById(defectDto.getId());
        mapper.mapToUpdateTypeDefectLibrary(defect, defectDto);
        addTypeCalculation(defect, defectDto.getCalculation());
        defect = repository.save(defect);
        defect.setMeasuredParameters(
                parameterService.update(new TypeMeasuredParameterBuilder.Builder()
                                .libraryDataType(MeasurementType.DEFECT)
                                .calculation(defect.getCalculation())
                                .defect(defect)
                                .measuredParameters(defect.getMeasuredParameters())
                                .build()
                        , defectDto.getMeasuredParameters()));
        synchronizingService.updateDefectName(defect);
        return mapper.mapToResponseTypeDefectLibraryDto(defect);
    }

    @Override
    public ResponseDefectLibraryDto get(Long id) {
        return mapper.mapToResponseTypeDefectLibraryDto(getById(id));
    }

    @Override
    public List<ResponseShortDefectLibraryDto> getAll() {
        return repository.findAll()
                         .stream()
                         .map(mapper::mapToResponseShortTypeDefectLibraryDto)
                         .toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("DefectLibrary with id=%s not found for delete", id));
    }

    private void addTypeCalculation(DefectLibrary defect, String calculation) {
        ParameterCalculationType calculationType = ParameterCalculationType.from(calculation).orElseThrow(
                () -> new BadRequestException(String.format("Unsupported calculation type=%s", calculation)));
        mapper.mapWithParameterCalculationType(defect, calculationType);
    }

    @Override
    public DefectLibrary getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("DefectLibrary with id=%s not found", id)));
    }
}