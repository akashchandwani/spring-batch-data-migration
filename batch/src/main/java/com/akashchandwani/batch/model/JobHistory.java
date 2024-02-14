package com.akashchandwani.batch.model;

import java.util.Date;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class JobHistory {
    private Company company;
    private Date startDate;
    private Date endDate;
}
