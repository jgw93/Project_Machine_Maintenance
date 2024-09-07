package com.example.project_machine_maintenance.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "tools")
public class Tool {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tool_id")
    private Integer toolId;

    @ManyToOne
    @JoinColumn(name = "machine_id", referencedColumnName = "machine_id", foreignKey = @ForeignKey(name = "FK_tools_machines"))
    private Machine machine;

    @Column(name = "tool_name", nullable = false)
    private String toolName;

    @Temporal(TemporalType.DATE)
    @Column(name = "manufacture_date", nullable = false)
    private Date manufactureDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "tool_set_up_date", nullable = false)
    private Date toolSetUpDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "next_replacement_date", nullable = false)
    private Date nextReplacementDate;
}
