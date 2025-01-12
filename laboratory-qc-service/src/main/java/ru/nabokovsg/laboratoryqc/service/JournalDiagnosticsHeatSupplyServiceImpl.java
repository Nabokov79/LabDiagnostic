package ru.nabokovsg.laboratoryqc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.laboratoryqc.dto.journalDiagnosticsHeatSupply.NewJournalDiagnosticsHeatSupplyDto;
import ru.nabokovsg.laboratoryqc.dto.journalDiagnosticsHeatSupply.ResponseJournalDiagnosticsHeatSupplyDto;
import ru.nabokovsg.laboratoryqc.dto.journalDiagnosticsHeatSupply.UpdateJournalDiagnosticsHeatSupplyDto;
import ru.nabokovsg.laboratoryqc.exceptions.NotFoundException;
import ru.nabokovsg.laboratoryqc.mapper.JournalDiagnosticsHeatSupplyMapper;
import ru.nabokovsg.laboratoryqc.model.JournalCompletedWork;
import ru.nabokovsg.laboratoryqc.model.JournalType;
import ru.nabokovsg.laboratoryqc.model.SearchDataJournalCompletedWork;
import ru.nabokovsg.laboratoryqc.repository.JournalCompletedWorkRepository;
import ru.nabokovsg.laboratoryqc.service.journalCompletedWorkBuilder.JournalCompletedWorkBuilder;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JournalDiagnosticsHeatSupplyServiceImpl implements JournalDiagnosticsHeatSupplyService {

    private final JournalCompletedWorkRepository repository;
    private final JournalDiagnosticsHeatSupplyMapper mapper;
    private final RequestJournalRecordsByPredicateService requestJournalRecordsService;
    private final JournalCompletedWorkBuilder builder;

    @Override
    public ResponseJournalDiagnosticsHeatSupplyDto save(NewJournalDiagnosticsHeatSupplyDto journalDto) {
        if (requestJournalRecordsService.exists(JournalType.HEAT_SUPPLY, journalDto.getDate()
                , journalDto.getEquipmentId(), journalDto.getAddressId())) {
            throw new NotFoundException(
                    String.format("Recording of the journal diagnostics equipment heat supply found : %s", journalDto));
        }
        JournalCompletedWork journal = mapper.mapToJournalCompletedWork(journalDto, JournalType.HEAT_SUPPLY.label);
        builder.build(journal, JournalType.HEAT_SUPPLY, journalDto.getEmployeesIds(), journalDto.getDocumentTypeId());
        return mapper.mapToResponseJournalDiagnoticsEquipmentDto(repository.save(journal));
    }

    @Override
    public ResponseJournalDiagnosticsHeatSupplyDto update(UpdateJournalDiagnosticsHeatSupplyDto journalDto) {
        if (repository.existsById(journalDto.getId())) {
            JournalCompletedWork journal = mapper.mapToUpdateJournalCompletedWork(journalDto
                                                                                , JournalType.HEAT_SUPPLY.label);
            builder.build(journal, JournalType.HEAT_SUPPLY
                        , journalDto.getEmployeesIds(), journalDto.getDocumentTypeId());
            return mapper.mapToResponseJournalDiagnoticsEquipmentDto(repository.save(journal));
        }
        throw new NotFoundException(
                String.format("Recording of the journal of the heat supply diagnostics" +
                                                                            " not found for update : %s", journalDto));
    }

    @Override
    public ResponseJournalDiagnosticsHeatSupplyDto get(Long id) {
        return mapper.mapToResponseJournalDiagnoticsEquipmentDto(
                repository.findById(id).orElseThrow(() -> new NotFoundException(
                        String.format("Recording of the journal of the heat supply" +
                                                                            " diagnostics with id=%s not found", id)))
        );
    }

    @Override
    public List<ResponseJournalDiagnosticsHeatSupplyDto> getAll(SearchDataJournalCompletedWork search) {
        return requestJournalRecordsService.getByPredicate(search, JournalType.HEAT_SUPPLY)
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
                         String.format("Recording of the journal of the heat supply" +
                                                                   " diagnostics with id=%s not found for delete", id));
    }
}