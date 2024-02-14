//package com.akashchandwani.batch.configuration;
//
//import com.akashchandwani.batch.model.Person;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.batch.item.Chunk;
//import org.springframework.batch.item.ItemWriter;
//import org.springframework.batch.item.data.MongoItemWriter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.core.query.Query;
//import org.springframework.data.mongodb.core.query.Update;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Component
//@Slf4j
//public class MongoUpsertWriter implements ItemWriter<Person> {
//
//    @Autowired
//    private MongoTemplate mongoTemplate;
//
//    @Override
//    public void write(Chunk<? extends Person> persons) throws Exception {
//        for(Person person : persons) {
//            // Define the query to find the employee with the given employeeId
//            Query query = new Query(Criteria.where("id").is(person.getId()));
//            List<Person> person1 = mongoTemplate.find(query, Person.class);
//            log.info(person1.toString());
//
//            // Define the update operation to upsert the jobHistory
//            Update update = new Update()
//                    .addToSet("jobHistories", person.getJobHistories());
//
//            // Perform the upsert operation
//            mongoTemplate.upsert(query, update, Person.class);
//        }
//
//    }
//}
//package com.akashchandwani.batch.configuration;
//
//import com.akashchandwani.batch.model.Person;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.batch.item.Chunk;
//import org.springframework.batch.item.ItemWriter;
//import org.springframework.batch.item.data.MongoItemWriter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.core.query.Query;
//import org.springframework.data.mongodb.core.query.Update;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Component
//@Slf4j
//public class MongoUpsertWriter implements ItemWriter<Person> {
//
//    @Autowired
//    private MongoTemplate mongoTemplate;
//
//    @Override
//    public void write(Chunk<? extends Person> persons) throws Exception {
//        for(Person person : persons) {
//            // Define the query to find the employee with the given employeeId
//            Query query = new Query(Criteria.where("id").is(person.getId()));
//            List<Person> person1 = mongoTemplate.find(query, Person.class);
//            log.info(person1.toString());
//
//            // Define the update operation to upsert the jobHistory
//            Update update = new Update()
//                    .addToSet("jobHistories", person.getJobHistories());
//
//            // Perform the upsert operation
//            mongoTemplate.upsert(query, update, Person.class);
//        }
//
//    }
//}
