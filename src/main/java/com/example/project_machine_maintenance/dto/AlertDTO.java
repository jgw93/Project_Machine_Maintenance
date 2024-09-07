package com.example.project_machine_maintenance.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AlertDTO {

    private Integer alertId; // 알람 ID
    private Integer machineId; // 기계 ID
    private Integer toolId; // 도구 ID
    private String alertType; // 알람 유형
    private Date alertDate; // 알람 발생 날짜
    private String alertMessage; // 알람 메시지
    private Boolean resolved; // 해결 여부
}
