package com.example.project_machine_maintenance.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "alerts")
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alert_id")
    private Integer alertId;

    @ManyToOne
    @JoinColumn(name = "machine_id", referencedColumnName = "machine_id", foreignKey = @ForeignKey(name = "FK_alerts_machines"))
    private Machine machine;

    @ManyToOne
    @JoinColumn(name = "tool_id", referencedColumnName = "tool_id", foreignKey = @ForeignKey(name = "FK_alerts_tools"))
    private Tool tool;

    @Column(name = "alert_type", columnDefinition = "ENUM('정비필요','부품교체필요','긴급정지')", nullable = false)
    private String alertType;

    @Temporal(TemporalType.DATE)
    @Column(name = "alert_date", nullable = false)
    private Date alertDate;

    @Column(name = "alert_message", columnDefinition = "TEXT", nullable = false)
    private String alertMessage;

    @Column(name = "resolved", nullable = false)
    private Boolean resolved = false;

}
