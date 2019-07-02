package com.pack;

import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class MongoService {

    public List<Document> find(MongoCollection<Document> collection){
        return collection.find().into(new ArrayList<>());
    }

}
