package com.marcosoft.jiraslamanagment.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import javax.persistence.*;
import java.math.BigInteger;

@Entity(name = "jira_sla_results")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SlaResult {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "issue_key")
    private String issueKey;
    @Column(name = "working_duration")
    private String workingDuration;
    @Column(name = "working_duration_as_seconds")
    private Integer workingDurationAsSeconds;
    @Column(name = "paused_duration")
    private String pausedDuration;
    @Column(name = "paused_duration_as_seconds")
    private Integer pausedDurationAsSeconds;
    @Column(name = "sla_value_as_minutes")
    private Integer slaValueAsMinutes;
    @Column(name = "sla_value_as_time_string")
    private String slaValueAsTimeString;
    @Column(name = "sla_status")
    private String status;
    @Column(name = "origin_date")
    private BigInteger originDate;
    @Column(name = "expected_target_date")
    private BigInteger expectedTargetDate;
    @Column(name = "working_calendar")
    private String workingCalendar;
    @Column(name = "sla_name")
    private String slaName;
    private Boolean finished;
    private Boolean paused;
    private Boolean started;
    private String project;

}
