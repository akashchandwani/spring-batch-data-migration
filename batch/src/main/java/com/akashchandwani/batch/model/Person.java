package com.akashchandwani.batch.model;

import java.util.List;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Person {
    private Integer personId;
    private String firstName;
    private String lastName;
    private String email;
    private List<JobHistoryItem> jobHistory;
}
