package ru.nabokovsg.equipment.service.equipment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.equipment.dto.remarkSpecialist.NewRemarkSpecialistDto;
import ru.nabokovsg.equipment.dto.remarkSpecialist.ResponseRemarkSpecialistDto;
import ru.nabokovsg.equipment.dto.remarkSpecialist.UpdateRemarkSpecialistDto;
import ru.nabokovsg.equipment.exceptions.NotFoundException;
import ru.nabokovsg.equipment.mapper.equipment.RemarkSpecialistMapper;
import ru.nabokovsg.equipment.model.equipment.Element;
import ru.nabokovsg.equipment.model.equipment.PartElement;
import ru.nabokovsg.equipment.model.equipment.RemarkSpecialist;
import ru.nabokovsg.equipment.repository.equipment.RemarkSpecialistRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RemarkSpecialistServiceImpl implements RemarkSpecialistService {

    private final RemarkSpecialistRepository repository;
    private final RemarkSpecialistMapper mapper;
    private final ElementService elementService;

    @Override
    public ResponseRemarkSpecialistDto save(NewRemarkSpecialistDto remarkDto) {
        Element element = elementService.get(remarkDto.getElementId());
        RemarkSpecialist remark = mapper.mapWithEquipmentElement(remarkDto, element);
        if (remarkDto.getPartElementId() != null) {
            PartElement partElement = element.getPartsElement()
                    .stream()
                    .collect(Collectors.toMap(PartElement::getPartElementLibraryId, p -> p))
                    .get(remarkDto.getPartElementId());
            mapper.mapWithEquipmentPartElement(remark, partElement);
        }
        return mapper.mapToResponseRemarkSpecialistDto(repository.save(remark));
    }

    @Override
    public ResponseRemarkSpecialistDto update(UpdateRemarkSpecialistDto remarkDto) {
        return mapper.mapToResponseRemarkSpecialistDto(repository.save(
                mapper.mapToUpdateRemarkByEquipment(getById(remarkDto.getId()), remarkDto.getRemark()))
        );
    }

    @Override
    public List<ResponseRemarkSpecialistDto> getAll(Long equipmentId) {
        return repository.findAllByEquipmentId(equipmentId)
                .stream()
                .map(mapper::mapToResponseRemarkSpecialistDto)
                .toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("RemarkSpecialist with id=%s not found for delete", id));
    }

    private RemarkSpecialist getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("RemarkSpecialist by id=%s not found", id)));
    }
}