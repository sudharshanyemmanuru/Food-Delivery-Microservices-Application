package com.fooddelivaryapp.order_service.service;

import com.fooddelivaryapp.order_service.entity.Sequence;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;


import org.springframework.data.mongodb.core.query.Update;

@Service
public class SequenceGeneratorService {
    @Autowired
    private MongoOperations mongoOperations;
    public int generateNextOrderId(){
        try {
            Query query = new Query(Criteria.where("_id").is("sequence"));
            Update update = new Update().inc("sequence", 1);
            FindAndModifyOptions options = new FindAndModifyOptions().returnNew(true).upsert(true);
            Sequence sequence = mongoOperations.findAndModify(query, update, options, Sequence.class);
            return sequence != null ? sequence.getSequence() : 1;
        }catch (Exception ex){
            System.out.println(ex.getLocalizedMessage());
        }
        return 3;
    }
}
