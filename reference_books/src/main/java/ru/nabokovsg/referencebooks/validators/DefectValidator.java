package ru.nabokovsg.referencebooks.validators;

import ru.nabokovsg.referencebooks.dto.defectLibrary.NewDefectLibraryDto;
import ru.nabokovsg.referencebooks.dto.defectLibrary.UpdateDefectLibraryDto;

public interface DefectValidator {

    void validNew(NewDefectLibraryDto defect);

    void validUpdate(UpdateDefectLibraryDto defect);
}