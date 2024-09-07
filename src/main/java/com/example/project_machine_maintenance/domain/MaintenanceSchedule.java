package com.example.project_machine_maintenance.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "maintenance_schedules")
public class MaintenanceSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Integer scheduleId;

    @ManyToOne
    @JoinColumn(name = "machine_id", referencedColumnName = "machine_id", foreignKey = @ForeignKey(name = "FK_maintenance_schedules_machines"))
    private Machine machine;

    @Column(name = "maintenance_type", columnDefinition = "ENUM('긴급정비','간단정비','정밀정비')", nullable = false)
    private String maintenanceType;

    @Column(name = "required_cycles", nullable = false)
    private Integer requiredCycles;

    @Temporal(TemporalType.DATE)
    @Column(name = "next_maintenance_date", nullable = false)
    private Date nextMaintenanceDate;
}
