package com.example.project_machine_maintenance.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MaintenanceScheduleDTO {

    private Integer scheduleId; // 스케줄 ID
    private Integer machineId; // 기계 ID
    private String maintenanceType; // 정비 유형
    private Integer requiredCycles; // 정비가 필요한 사이클 수
    private Date nextMaintenanceDate; // 다음 정비 예정일
}
