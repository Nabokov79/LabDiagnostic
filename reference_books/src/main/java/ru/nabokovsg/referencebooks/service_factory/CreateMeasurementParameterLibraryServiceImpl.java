package ru.nabokovsg.referencebooks.service_factory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.referencebooks.dto.measurementParameterLibrary.MeasurementParameterLibraryDto;
import ru.nabokovsg.referencebooks.exceptions.BadRequestException;
import ru.nabokovsg.referencebooks.mapper.MeasurementParameterLibraryMapper;
import ru.nabokovsg.referencebooks.model.MeasurementParameterLibrary;
import ru.nabokovsg.referencebooks.model.MeasurementParameterType;
import ru.nabokovsg.referencebooks.model.ParameterCalculationType;
import ru.nabokovsg.referencebooks.model.UnitMeasurementType;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CreateMeasurementParameterLibraryServiceImpl implements CreateMeasurementParameterLibraryService {

    private final MeasurementParameterLibraryMapper mapper;

    @Override
    public void replaceEquals(List<MeasurementParameterLibrary> measuredParametersLibrary, List<MeasurementParameterLibraryDto> measuredParameters) {
        Integer[] indexList = {measuredParametersLibrary.size()-1};
        measuredParameters.forEach(v -> {
            int index = indexList[0];
            mapper.replace(measuredParametersLibrary.get(index), create(v));
            indexList[0] = index -1;
        });
    }

    @Override
    public void replaceMore(List<MeasurementParameterLibrary> measuredParametersLibrary, List<MeasurementParameterLibraryDto> measuredParameters, List<Long> delete) {
        Integer[] indexList = {measuredParameters.size()-1};
        List<MeasurementParameterLibrary> parameters = new ArrayList<>(measuredParameters.size());
        measuredParametersLibrary.forEach(parameter -> {
            int index = indexList[0];
            if (index >= 0) {
                mapper.replace(parameter, create(measuredParameters.get(index)));
                indexList[0] = index -1;
                parameters.add(parameter);
            } else {
                delete.add(parameter.getId());
            }
        });
        measuredParametersLibrary.clear();
        measuredParametersLibrary.addAll(parameters);
    }

    @Override
    public void replaceLess(List<MeasurementParameterLibrary> measuredParametersLibrary, List<MeasurementParameterLibraryDto> measuredParameters) {
        Integer[] indexList = {measuredParametersLibrary.size()-1};
        measuredParameters.forEach(v -> {
            int index = indexList[0];
            if (index >= 0) {
                mapper.replace(measuredParametersLibrary.get(index), create(v));
                indexList[0] = index -1;
            } else {
                measuredParametersLibrary.add(create(v));
            }
        });
    }

    @Override
    public MeasurementParameterLibrary create(MeasurementParameterLibraryDto parameter) {
        ParameterCalculationType calculationType = getParameterCalculationType(parameter.getCalculation());
        MeasurementParameterLibrary measuredParameter = mapper.mapToMeasuredParameter(parameter);
        mapper.mapToReplacement(measuredParameter
                , getMeasurementParameterType(parameter.getName())
                , getUnitMeasurementType(parameter.getUnitMeasurement())
                , calculationType
                , calculationType.label);
        return measuredParameter;
    }

    private ParameterCalculationType getParameterCalculationType(String calculation) {
        return ParameterCalculationType.from(calculation)
                .orElseThrow(() -> new BadRequestException(
                        String.format("Недопустимый тип расчета: %s", calculation)));
    }

    private String getMeasurementParameterType(String name) {
        return MeasurementParameterType.from(name)
                .orElseThrow(() -> new BadRequestException(
                        String.format("Недопустимое наименование: %s", name))).label;
    }

    private String getUnitMeasurementType(String unitMeasurement) {
        return UnitMeasurementType.from(unitMeasurement)
                .orElseThrow(() -> new BadRequestException(
                        String.format("Недопустимая единица измерения: %s", unitMeasurement))).label;
    }
}