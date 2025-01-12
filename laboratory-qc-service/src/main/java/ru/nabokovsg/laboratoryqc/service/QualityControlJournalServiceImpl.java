package ru.nabokovsg.laboratoryqc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.laboratoryqc.dto.qualityControlJournal.NewQualityControlJournalDto;
import ru.nabokovsg.laboratoryqc.dto.qualityControlJournal.ResponseQualityControlJournalDto;
import ru.nabokovsg.laboratoryqc.dto.qualityControlJournal.UpdateQualityControlJournalDto;
import ru.nabokovsg.laboratoryqc.exceptions.NotFoundException;
import ru.nabokovsg.laboratoryqc.mapper.QualityControlJournalMapper;
import ru.nabokovsg.laboratoryqc.model.JournalCompletedWork;
import ru.nabokovsg.laboratoryqc.model.JournalType;
import ru.nabokovsg.laboratoryqc.model.MeasuringTool;
import ru.nabokovsg.laboratoryqc.model.SearchDataJournalCompletedWork;
import ru.nabokovsg.laboratoryqc.repository.JournalCompletedWorkRepository;
import ru.nabokovsg.laboratoryqc.repository.MeasuringToolRepository;
import ru.nabokovsg.laboratoryqc.service.journalCompletedWorkBuilder.JournalCompletedWorkBuilder;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QualityControlJournalServiceImpl implements  QualityControlJournalService {

    private final JournalCompletedWorkRepository repository;
    private final QualityControlJournalMapper mapper;
    private final RequestJournalRecordsByPredicateService requestJournalRecordsService;
    private final JournalCompletedWorkBuilder builder;
    private final MeasuringToolRepository measuringToolRepository;

    @Override
    public ResponseQualityControlJournalDto save(NewQualityControlJournalDto journalDto) {
        if (requestJournalRecordsService.exists(JournalType.QUALITY_CONTROL, journalDto.getDate()
                , journalDto.getEquipmentId(), journalDto.getAddressId())) {
            throw new NotFoundException(
                    String.format("Recording of the journal of the quality control found : %s", journalDto));
        }
        JournalCompletedWork journal = mapper.mapToJournalCompletedWork(journalDto
                                                , JournalType.QUALITY_CONTROL.label
                                                , createMeasuringInstruments(journalDto.getMeasuringInstrumentsIds()));
        builder.build(journal, JournalType.QUALITY_CONTROL
                    , journalDto.getEmployeesIds(), journalDto.getDocumentTypeId());
        return mapper.mapToResponseJournalDiagnoticsEquipmentDto(repository.save(journal));
    }

    @Override
    public ResponseQualityControlJournalDto update(UpdateQualityControlJournalDto journalDto) {
        if (repository.existsById(journalDto.getId())) {
            JournalCompletedWork journal = mapper.mapToUpdateJournalCompletedWork(journalDto
                                                , JournalType.QUALITY_CONTROL.label
                                                , createMeasuringInstruments(journalDto.getMeasuringInstrumentsIds()));
            builder.build(journal, JournalType.QUALITY_CONTROL
                        , journalDto.getEmployeesIds(), journalDto.getDocumentTypeId());
            return mapper.mapToResponseJournalDiagnoticsEquipmentDto(repository.save(journal));
        }
        throw new NotFoundException(
                String.format("Recording of the journal of the quality control  not found for update : %s", journalDto));
    }

    @Override
    public ResponseQualityControlJournalDto get(Long id) {
        return mapper.mapToResponseJournalDiagnoticsEquipmentDto(
                repository.findById(id).orElseThrow(() -> new NotFoundException(
                        String.format("Recording of the journal of the quality control with id=%s not found", id)))
        );
    }

    @Override
    public List<ResponseQualityControlJournalDto> getAll(SearchDataJournalCompletedWork search) {
        return requestJournalRecordsService.getByPredicate(search, JournalType.QUALITY_CONTROL).stream()
                             .map(mapper::mapToResponseJournalDiagnoticsEquipmentDto)
                             .toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
        throw new NotFoundException(
                String.format("Recording of the journal of the quality control with id=%s not found for delete", id));
    }

    private String createMeasuringInstruments(List<Long> ids) {
        if (ids == null) {
            return null;
        }
        List<MeasuringTool> measuringTools = measuringToolRepository.findAllById(ids);
        measuringTools.sort(Comparator.comparing(MeasuringTool::getTollName));
        if (measuringTools.isEmpty()) {
            throw new NotFoundException(String.format("MeasuringTool with ids=%s not found", ids));
        }
        String measuringInstruments = " ";
        for (MeasuringTool tool : measuringTools) {
            String measuringInstrument = String.join(" ", tool.getTollName(), tool.getModel(), "зав. № ", tool.getWorkNumber());
            if (measuringInstruments.isBlank()) {
                measuringInstruments = measuringInstrument;
            } else {
                measuringInstruments = String.join("; ", measuringInstruments, measuringInstrument);
            }
        }
        return measuringInstruments;
    }
}