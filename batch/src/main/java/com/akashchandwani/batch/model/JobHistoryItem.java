package com.akashchandwani.batch.model;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class JobHistoryItem {
    private Integer jobHistoryId;
    private Date startDate;
    private Date endDate;
    private String jobTitle;
    private String companyId;
    private String companyName;
    private String industry;
    private String address;
}
