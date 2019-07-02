package com.pack;

import com.codahale.metrics.annotation.Timed;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/employee")
@Produces(MediaType.APPLICATION_JSON)
public class MongoResource {

    private MongoCollection<Document> collection;
    private final MongoService mongoService;

    public MongoResource(MongoCollection<Document> collection, MongoService mongoService){
        this.collection = collection;
        this.mongoService = mongoService;
    }

    @GET
    @Timed
    public Response getTemplate(){
        List<Document> documents = mongoService.find(collection);
        return Response.ok(documents).build();
    }
}
