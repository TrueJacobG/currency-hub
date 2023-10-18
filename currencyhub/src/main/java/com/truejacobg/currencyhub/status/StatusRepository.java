package com.truejacobg.currencyhub.status;

import com.truejacobg.currencyhub.status.entity.StatusEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends MongoRepository<StatusEntity, String> {
}
