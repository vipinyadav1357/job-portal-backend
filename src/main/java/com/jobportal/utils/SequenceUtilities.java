package com.jobportal.utils;

import com.jobportal.entity.Sequence;
import com.jobportal.exception.JobPortalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

@Component
public class SequenceUtilities {

    private static MongoOperations mongoOperations;

    @Autowired
    public SequenceUtilities(MongoOperations mongoOperations) {
        SequenceUtilities.mongoOperations = mongoOperations;
    }


    public static Long getNextSequence(String key) throws JobPortalException{
       Query query=new Query(Criteria.where("_id").is(key));
        Update update=new Update().inc("seq",1);
        FindAndModifyOptions options=new FindAndModifyOptions().returnNew(true);
        Sequence sequence = mongoOperations.findAndModify(query,update,options,Sequence.class);
        if (sequence==null) throw new JobPortalException("sequence is null");
        return sequence.getSeq();
    }
}
