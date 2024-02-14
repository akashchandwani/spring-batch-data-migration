package com.akashchandwani.batch.model.mappers;

import java.lang.runtime.ObjectMethods;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bson.json.JsonObject;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import com.akashchandwani.batch.model.Person;

public class EmployeeProfileRowMapper implements RowMapper<Person>{

    @Override
    @Nullable
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {


        String result = rs.getString("employee_profile");

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return new ObjectMapper().readValue(result, Person.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    
}
