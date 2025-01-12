package ru.nabokovsg.equipment.service.equipment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.equipment.dto.equipmentPassport.NewEquipmentPassportDto;
import ru.nabokovsg.equipment.dto.equipmentPassport.ResponseEquipmentPassportDto;
import ru.nabokovsg.equipment.dto.equipmentPassport.UpdateEquipmentPassportDto;
import ru.nabokovsg.equipment.exceptions.BadRequestException;
import ru.nabokovsg.equipment.exceptions.NotFoundException;
import ru.nabokovsg.equipment.mapper.equipment.EquipmentPassportMapper;
import ru.nabokovsg.equipment.repository.equipment.EquipmentPassportRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipmentPassportServiceImpl implements EquipmentPassportService {

    private final EquipmentPassportRepository repository;
    private final EquipmentPassportMapper mapper;

    @Override
    public ResponseEquipmentPassportDto save(NewEquipmentPassportDto passportDto) {
        if (repository.existsByEquipmentIdAndHeader(passportDto.getEquipmentId(), passportDto.getHeader())) {
            throw new BadRequestException(
                    String.format("Equipment passport with equipmentId=%s and tHeader=%s is found"
                                                        , passportDto.getEquipmentId(), passportDto.getHeader())
            );
        }
        return mapper.mapToResponseEquipmentPassportDto(repository.save(mapper.mapToEquipmentPassport(passportDto)));
    }

    @Override
    public ResponseEquipmentPassportDto update(UpdateEquipmentPassportDto passportDto) {
        if (repository.existsById(passportDto.getId())) {
            return mapper.mapToResponseEquipmentPassportDto(repository.save(mapper.mapToUpdateEquipmentPassport(passportDto)));
        }
        throw new NotFoundException(
                String.format("Equipment passport with id=%s not found for update", passportDto.getId())
        );
    }

    @Override
    public List<ResponseEquipmentPassportDto> getAll(Long id) {
        return repository.findAllByEquipmentId(id)
                         .stream()
                         .map(mapper::mapToResponseEquipmentPassportDto)
                         .toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("Equipment Passport with id=%s not found for delete", id));
    }
}