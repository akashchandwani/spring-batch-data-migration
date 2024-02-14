package com.akashchandwani.batch.model;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Company {
    private String name;
    private String industry;
    private String address;
}
