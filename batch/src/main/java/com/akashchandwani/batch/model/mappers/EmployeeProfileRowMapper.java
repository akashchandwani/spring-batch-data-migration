package com.akashchandwani.batch.model.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import com.akashchandwani.batch.model.Company;
import com.akashchandwani.batch.model.JobHistory;
import com.akashchandwani.batch.model.Person;

public class EmployeeProfileRowMapper implements RowMapper<Person>{

    @Override
    @Nullable
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {

        Company company = Company.builder()
            .name(rs.getString("company_name"))
            .address(rs.getString("company_address"))
            .industry(rs.getString("industry"))
            .build();

        JobHistory jobHistory = JobHistory.builder()
            .company(company)
            .startDate(rs.getDate("start_date"))
            .endDate(rs.getDate("end_date"))
            .build();

        List<JobHistory> jobHistoryList = new ArrayList<>();
        jobHistoryList.add(jobHistory);

        return Person.builder()
            .id(rs.getInt("id"))
            .firstName(rs.getString("first_name"))
            .lastName(rs.getString("last_name"))
            .email(rs.getString("email"))
            .jobHistories(jobHistoryList)
        .build();
    }
    
}
