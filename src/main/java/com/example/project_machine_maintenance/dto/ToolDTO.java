package com.example.project_machine_maintenance.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ToolDTO {

    private Integer toolId; // 도구 ID
    private Integer machineId; // 기계 ID (엔티티 대신 ID로 참조)
    private String toolName; // 도구 이름
    private Date manufactureDate; // 제조일자
    private Date toolSetUpDate; // 도구 설치일자
    private Date nextReplacementDate; // 다음 교체일자
}
