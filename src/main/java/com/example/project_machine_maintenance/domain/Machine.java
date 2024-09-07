package com.example.project_machine_maintenance.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "machines")
public class Machine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "machine_id")
    private Integer machineId;

    @Column(name = "machine_name", nullable = false)
    private String machineName;

    @Column(name = "machine_type", nullable = false)
    private String machineType;

    @Temporal(TemporalType.DATE)
    @Column(name = "set_up_date", nullable = false)
    private Date setUpDate;

    @Column(name = "total_cycles", nullable = false)
    private Integer totalCycles;

    @Temporal(TemporalType.DATE)
    @Column(name = "last_maintenance_date", nullable = false)
    private Date lastMaintenanceDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "last_replacement_date", nullable = false)
    private Date lastReplacementDate;

    @OneToMany(mappedBy = "machine", cascade = CascadeType.ALL)
    private List<Alert> alerts;

    @OneToMany(mappedBy = "machine", cascade = CascadeType.ALL)
    private List<MaintenanceSchedule> maintenanceSchedules;

    @OneToMany(mappedBy = "machine", cascade = CascadeType.ALL)
    private List<Tool> tools;

}
