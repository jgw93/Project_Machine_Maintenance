package com.example.project_machine_maintenance.dto;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MachineDTO {

    private Integer machineId; // 기계 ID
    private String machineName; // 기계 이름
    private String machineType; // 기계 타입
    private Date setUpDate; // 설치 일자
    private Integer totalCycles; // 총 사이클 수
    private Date lastMaintenanceDate; // 마지막 정비 일자
    private Date lastReplacementDate; // 마지막 교체 일자

    // 알람 정보, 정비 스케줄, 도구 정보는 엔티티 대신 간단한 형태로 포함
    private List<AlertDTO> alerts; // 알람 목록
    private List<MaintenanceScheduleDTO> maintenanceSchedules; // 정비 스케줄 목록
    private List<ToolDTO> tools; // 도구 목록
}
