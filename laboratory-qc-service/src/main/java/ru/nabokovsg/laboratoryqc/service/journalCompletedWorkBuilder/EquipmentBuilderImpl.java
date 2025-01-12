package ru.nabokovsg.laboratoryqc.service.journalCompletedWorkBuilder;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.laboratoryqc.client.ClientService;
import ru.nabokovsg.laboratoryqc.dto.equipmentDiagnosedQCLService.EquipmentDto;
import ru.nabokovsg.laboratoryqc.mapper.EquipmentDataBuilderMapper;
import ru.nabokovsg.laboratoryqc.model.JournalCompletedWork;
import ru.nabokovsg.laboratoryqc.model.JournalType;

@Service
@RequiredArgsConstructor
public class EquipmentBuilderImpl implements EquipmentBuilder {

    private final ClientService client;
    private final EquipmentDataBuilderMapper mapper;

    @Override
    public void build(JournalCompletedWork journal, JournalType journalType) {
        mapper.mapToEquipment(journal, getEquipmentName(journal, getEquipment(journal, journalType)));
    }

    private EquipmentDto getEquipment(JournalCompletedWork journal, JournalType journalType) {
        return client.getEquipment(journal.getEquipmentId()
                , journal.getElementId()
                , journalType);
    }

    private String getEquipmentName(JournalCompletedWork journal, EquipmentDto equipment) {
        String equipmentName = equipment.getEquipmentName();
        if (equipment.getModel() != null) {
            equipmentName = String.join(" ", equipmentName, equipment.getModel());
        }
        if (equipment.getStationaryNumber() != null) {
            equipmentName = String.join("", equipmentName, ",");
            equipmentName = String.join(" ", equipmentName
                    , "ст. №"
                    , String.valueOf(equipment.getStationaryNumber()));
        }
        if (equipment.getVolume() != null) {
            equipmentName = String.join(" ", equipmentName
                    , "V = "
                    , String.valueOf(equipment.getVolume())
                    , "м3");
        }
        if (journal.getElementId() != null) {
            equipmentName = String.join(", ", equipment.getElementName(), equipmentName);
        }
        return equipmentName;
    }
}
