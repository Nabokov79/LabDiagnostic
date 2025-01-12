package ru.nabokovsg.laboratoryqc.model;

import java.time.LocalDate;

public class SearchDataJournalCompletedWork {

    private final LocalDate startPeriod;
    private final LocalDate endPeriod;
    private final Long addressId;
    private final Long employeesId;
    private final Long equipmentId;

    public SearchDataJournalCompletedWork(Builder builder) {
        this.startPeriod = builder.startPeriod;
        this.endPeriod = builder.endPeriod;
        this.addressId = builder.addressId;
        this.employeesId = builder.employeesId;
        this.equipmentId = builder.equipmentId;
    }

    public LocalDate getStartPeriod() {
        return startPeriod;
    }

    public LocalDate getEndPeriod() {
        return endPeriod;
    }

    public Long getAddressId() {
        return addressId;
    }

    public Long getEmployeesId() {
        return employeesId;
    }

    public Long getEquipmentId() {
        return equipmentId;
    }

    public static class Builder {

        private LocalDate startPeriod;
        private LocalDate endPeriod;
        private Long addressId;
        private Long employeesId;
        private Long equipmentId;

        public Builder startPeriod(LocalDate startPeriod) {
            this.startPeriod = startPeriod;
            return this;
        }

        public Builder endPeriod(LocalDate endPeriod) {
            this.endPeriod = endPeriod;
            return this;
        }

        public Builder addressId(Long addressId) {
            this.addressId = addressId;
            return this;
        }

        public Builder employeesId(Long employeesId) {
            this.employeesId = employeesId;
            return this;
        }

        public Builder equipmentId(Long equipmentId) {
            this.equipmentId = equipmentId;
            return this;
        }

        public SearchDataJournalCompletedWork build() {
            return new SearchDataJournalCompletedWork(this);
        }
    }
}