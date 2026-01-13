package ru.nabokovsg.referencebooks.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.referencebooks.dto.diagnosisLibrary.NewDiagnosisLibraryDto;
import ru.nabokovsg.referencebooks.dto.diagnosisLibrary.ResponseDiagnosisLibraryDto;
import ru.nabokovsg.referencebooks.dto.diagnosisLibrary.UpdateDiagnosisLibraryDto;
import ru.nabokovsg.referencebooks.exceptions.BadRequestException;
import ru.nabokovsg.referencebooks.exceptions.NotFoundException;
import ru.nabokovsg.referencebooks.mapper.DiagnosisLibraryMapper;
import ru.nabokovsg.referencebooks.repository.DiagnosisLibraryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiagnosisLibraryServiceImpl implements DiagnosisLibraryService {

    private final DiagnosisLibraryRepository repository;
    private final DiagnosisLibraryMapper mapper;

    @Override
    public ResponseDiagnosisLibraryDto save(NewDiagnosisLibraryDto diagnosisDto) {
        if (repository.existsByDiagnosis(diagnosisDto.getDiagnosis())) {
            throw new BadRequestException("Обнаружен дубликат");
        }
        return mapper.mapToResponseDiagnosisLibraryDto(repository.save(mapper.mapToDiagnosisLibrary(diagnosisDto)));
    }

    @Override
    public ResponseDiagnosisLibraryDto update(UpdateDiagnosisLibraryDto diagnosisDto) {
        if (repository.existsById(diagnosisDto.getId())) {
            return mapper.mapToResponseDiagnosisLibraryDto(
                    repository.save(mapper.mapToUpdateDiagnosisLibrary(diagnosisDto)));
        }
        throw new NotFoundException("Диагностика/контроль не обнаружены.");
    }

    @Override
    public ResponseDiagnosisLibraryDto get(Long id) {
        return mapper.mapToResponseDiagnosisLibraryDto(repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Диагностика/контроль не обнаружены.")));
    }

    @Override
    public List<ResponseDiagnosisLibraryDto> getAll(String name) {
        if (name != null) {
            return repository.findAll()
                    .stream()
                    .filter(diagnosis -> diagnosis.getDiagnosis().contains(name))
                    .map(mapper::mapToResponseDiagnosisLibraryDto)
                    .toList();
        }
        return repository.findAll()
                .stream()
                .map(mapper::mapToResponseDiagnosisLibraryDto)
                .toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException("Диагностика/контроль не обнаружены.");
    }
}