package br.com.unirio.sagui.pdfjobs;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(catalog = "sagui", name = "scheduler_job_info")
public class SchedulerJobInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String jobName;

    private String jobGroup;

    private String jobClass;

    private String cronExpression;

    private Long repeatTime;

    private Boolean cronJob;
}