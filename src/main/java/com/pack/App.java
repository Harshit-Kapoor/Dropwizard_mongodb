package com.pack;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App extends Application<Config> {

    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String args[]) throws Exception{
        new App().run(args);
    }

    public void initialize(Bootstrap<Config> b){
    }

    @Override
    public void run(Config configuration, Environment environment) throws Exception {
        MongoClient mongoClient = new MongoClient(configuration.getMongoHost(), configuration.getMongoPort());
        MongoManaged mongoManaged = new MongoManaged(mongoClient);
        environment.lifecycle().manage(mongoManaged);
        MongoDatabase db = mongoClient.getDatabase(configuration.getMongoDB());
        MongoCollection<Document> collection = db.getCollection(configuration.getCollectionName());
        logger.info("Registering RESTful API resources");
        environment.jersey().register(new MongoResource(collection, new MongoService()));
    }
}
