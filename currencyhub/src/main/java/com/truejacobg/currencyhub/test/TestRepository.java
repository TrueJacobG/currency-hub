package com.truejacobg.currencyhub.test;

import com.truejacobg.currencyhub.test.dto.TestItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface TestRepository extends MongoRepository<TestItem, String> {

    @Query("{name:  '?0'}")
    TestItem findTestItemByName(String name);
}
