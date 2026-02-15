package ru.nabokovsg.referencebooks.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.referencebooks.dto.diagnosisLibrary.NewDiagnosisLibraryDto;
import ru.nabokovsg.referencebooks.dto.diagnosisLibrary.ResponseShortDiagnosisLibraryDto;
import ru.nabokovsg.referencebooks.dto.diagnosisLibrary.ResponseDiagnosisLibraryDto;
import ru.nabokovsg.referencebooks.dto.diagnosisLibrary.UpdateDiagnosisLibraryDto;
import ru.nabokovsg.referencebooks.exceptions.BadRequestException;
import ru.nabokovsg.referencebooks.exceptions.NotFoundException;
import ru.nabokovsg.referencebooks.mapper.DiagnosisLibraryMapper;
import ru.nabokovsg.referencebooks.model.DiagnosisLibrary;
import ru.nabokovsg.referencebooks.model.ExceptionMassage;
import ru.nabokovsg.referencebooks.repository.DiagnosisLibraryRepository;
import ru.nabokovsg.referencebooks.search.SearchService;
import ru.nabokovsg.referencebooks.toStringService.ToStringService;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DiagnosisLibraryServiceImpl implements DiagnosisLibraryService {

    private final DiagnosisLibraryRepository repository;
    private final DiagnosisLibraryMapper mapper;
    private final EquipmentLibraryService equipmentService;
    private final SearchService searchService;
    private final ToStringService toString;
    private final static String MASSAGE = "Диагностика не обнаружена.";

    @Override
    public ResponseShortDiagnosisLibraryDto save(NewDiagnosisLibraryDto diagnosisDto) {
        DiagnosisLibrary diagnosisLibrary = mapper.mapToDiagnosisLibrary(diagnosisDto);
        build(diagnosisLibrary, diagnosisDto.getMeasurementsType(), diagnosisDto.getEquipmentId());
        return mapper.mapToResponseShortDiagnosisLibraryDto(repository.save(diagnosisLibrary));
    }

    @Override
    public ResponseShortDiagnosisLibraryDto update(UpdateDiagnosisLibraryDto diagnosisDto) {
        DiagnosisLibrary diagnosisLibrary = mapper.mapToUpdateDiagnosisLibrary(diagnosisDto);
        build(diagnosisLibrary, diagnosisDto.getMeasurementsType(), diagnosisDto.getEquipmentId());
        return mapper.mapToResponseShortDiagnosisLibraryDto(repository.save(diagnosisLibrary));
    }

    @Override
    public ResponseDiagnosisLibraryDto get(Long id) {
        DiagnosisLibrary diagnosisLibrary = getById(id);
        return mapper.mapToResponseDiagnosisLibraryDto(diagnosisLibrary, List.of(diagnosisLibrary.getMeasurementsType()));
    }

    @Override
    public List<ResponseShortDiagnosisLibraryDto> getAll(String search) {
        Set<DiagnosisLibrary> diagnostics = repository.findAllOrderByEquipmentLibrary();
        if (search != null) {
            diagnostics = diagnostics
                    .stream()
                    .filter(diagnosis ->
                            searchService.search(search, List.of(diagnosis.getEquipment().getEquipmentFullName()
                                                               , diagnosis.getDiagnosis())))
                    .collect(Collectors.toSet());
        }
        return diagnostics.stream()
                          .map(mapper::mapToResponseShortDiagnosisLibraryDto)
                          .sorted(Comparator.comparing(ResponseShortDiagnosisLibraryDto::getEquipmentFullName))
                          .toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(MASSAGE);
    }

    private DiagnosisLibrary getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(MASSAGE));
    }

    private void build(DiagnosisLibrary diagnosisLibrary, List<String> measurementsType, Long equipmentId) {
        mapper.mapFields(diagnosisLibrary
                , equipmentService.getById(equipmentId)
                , toString.getMeasurements(measurementsType)
                , String.join(",", measurementsType));
        exists(diagnosisLibrary);
    }

    private void exists(DiagnosisLibrary diagnosisLibrary) {
        boolean exists = false;
        if (diagnosisLibrary.getId() == null) {
            exists = repository.existsByEquipmentAndDiagnosis(diagnosisLibrary.getEquipment()
                    , diagnosisLibrary.getDiagnosis());
        } else {
            Long id = repository.findIdByEquipmentAndDiagnosis(diagnosisLibrary.getEquipment()
                    , diagnosisLibrary.getDiagnosis());
            if (id != null) {
                exists = !diagnosisLibrary.getId().equals(id);
            }
        }
        if (exists) {
            throw new BadRequestException(String.join("", ExceptionMassage.DUPLICATE.label,
                    String.join(" ", diagnosisLibrary.getDiagnosis()
                            , "для", diagnosisLibrary.getEquipment().getEquipmentFullName())));
        }
    }
}