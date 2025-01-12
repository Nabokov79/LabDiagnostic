package ru.nabokovsg.laboratoryqc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.laboratoryqc.dto.journalDiagnoticsEquipment.NewJournalDiagnoticsEquipmentDto;
import ru.nabokovsg.laboratoryqc.dto.journalDiagnoticsEquipment.ResponseJournalDiagnoticsEquipmentDto;
import ru.nabokovsg.laboratoryqc.dto.journalDiagnoticsEquipment.UpdateJournalDiagnoticsEquipmentDto;
import ru.nabokovsg.laboratoryqc.exceptions.NotFoundException;
import ru.nabokovsg.laboratoryqc.mapper.JournalDiagnoticsEquipmentMapper;
import ru.nabokovsg.laboratoryqc.model.JournalCompletedWork;
import ru.nabokovsg.laboratoryqc.model.JournalType;
import ru.nabokovsg.laboratoryqc.model.SearchDataJournalCompletedWork;
import ru.nabokovsg.laboratoryqc.repository.JournalCompletedWorkRepository;
import ru.nabokovsg.laboratoryqc.service.journalCompletedWorkBuilder.JournalCompletedWorkBuilder;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JournalDiagnoticsEquipmentServiceImpl implements JournalDiagnoticsEquipmentService {

    private final JournalCompletedWorkRepository repository;
    private final JournalDiagnoticsEquipmentMapper mapper;
    private final RequestJournalRecordsByPredicateService requestJournalRecordsService;
    private final JournalCompletedWorkBuilder builder;

    @Override
    public ResponseJournalDiagnoticsEquipmentDto save(NewJournalDiagnoticsEquipmentDto journalDto) {
        if (requestJournalRecordsService.exists(JournalType.EQUIPMENT, journalDto.getDate()
                                              , journalDto.getEquipmentId(), journalDto.getAddressId())) {
            throw new NotFoundException(
                    String.format("Recording of the journal diagnostics equipment heat supply found : %s", journalDto));
        }
        JournalCompletedWork journal = mapper.mapToJournalCompletedWork(journalDto, JournalType.EQUIPMENT.label);
        builder.build(journal, JournalType.EQUIPMENT, journalDto.getEmployeesIds(), journalDto.getDocumentTypeId());
        return mapper.mapToResponseJournalDiagnoticsEquipmentDto(repository.save(journal));
    }

    @Override
    public ResponseJournalDiagnoticsEquipmentDto update(UpdateJournalDiagnoticsEquipmentDto journalDto) {
        if (repository.existsById(journalDto.getId())) {
            JournalCompletedWork journal = mapper.mapToUpdateJournalCompletedWork(journalDto, JournalType.EQUIPMENT.label);
            builder.build(journal, JournalType.EQUIPMENT, journalDto.getEmployeesIds(), journalDto.getDocumentTypeId());
            return mapper.mapToResponseJournalDiagnoticsEquipmentDto(repository.save(journal));
        }
        throw new NotFoundException(
                String.format("Recording of the journal of the equipment diagnostics not found for update : %s", journalDto));
    }

    @Override
    public ResponseJournalDiagnoticsEquipmentDto get(Long id) {
        return mapper.mapToResponseJournalDiagnoticsEquipmentDto(
                repository.findById(id).orElseThrow(() -> new NotFoundException(
                        String.format("Recording of the journal of the equipment diagnostics with id=%s not found", id)))
        );
    }

    @Override
    public List<ResponseJournalDiagnoticsEquipmentDto> getAll(SearchDataJournalCompletedWork search) {
        return requestJournalRecordsService.getByPredicate(search, JournalType.EQUIPMENT)
                .stream()
                .map(mapper::mapToResponseJournalDiagnoticsEquipmentDto)
                .toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
        throw new NotFoundException(
                String.format("Recording of the journal of the equipment diagnostics with id=%s not found for delete", id));
    }
}