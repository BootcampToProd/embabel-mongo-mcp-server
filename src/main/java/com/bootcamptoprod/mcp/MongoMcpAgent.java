package com.bootcamptoprod.mcp;

import com.bootcamptoprod.dto.CollectionRequest;
import com.bootcamptoprod.dto.ComplexQueryRequest;
import com.bootcamptoprod.dto.CreateCollectionRequest;
import com.bootcamptoprod.dto.DbNameRequest;
import com.bootcamptoprod.dto.InsertDocumentRequest;
import com.bootcamptoprod.dto.MongoResponse;
import com.bootcamptoprod.dto.SimpleQueryRequest;
import com.bootcamptoprod.service.MongoServiceClient;
import com.embabel.agent.api.annotation.AchievesGoal;
import com.embabel.agent.api.annotation.Action;
import com.embabel.agent.api.annotation.Agent;
import com.embabel.agent.api.annotation.Export;
import com.embabel.agent.domain.io.UserInput;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Agent(description = "An MCP agent supporting various MongoDB operations")
public class MongoMcpAgent {

    private static final Logger logger = LoggerFactory.getLogger(MongoMcpAgent.class);

    private final MongoServiceClient mongoServiceClient;

    public MongoMcpAgent(MongoServiceClient mongoServiceClient) {
        this.mongoServiceClient = mongoServiceClient;
        logger.info("MongoMcpAgent initialized");
    }

    /**
     * List all databases in MongoDB.
     */
    @Action
    @AchievesGoal(
            description = "List all databases in MongoDB.",
            export = @Export(remote = true, name = "listDatabases", startingInputTypes = UserInput.class)
    )
    public MongoResponse<List<String>> listDatabases() {
        logger.info("Agent action: listDatabases");
        List<String> dbs = mongoServiceClient.listDatabases();
        return new MongoResponse<>(dbs);
    }

    /**
     * List all collections in a database.
     */
    @Action
    @AchievesGoal(
            description = "List collections in a MongoDB database.",
            export = @Export(remote = true, name = "listCollections", startingInputTypes = DbNameRequest.class)
    )
    public MongoResponse<List<String>> listCollections(DbNameRequest req) {
        logger.info("Agent action: listCollections for db='{}'", req.dbName());
        List<String> collections = mongoServiceClient.listCollections(req.dbName());
        return new MongoResponse<>(collections);
    }

    /**
     * Execute a simple query (field = value) on a collection.
     */
    @Action
    @AchievesGoal(
            description = "Execute a simple query on a collection.",
            export = @Export(remote = true, name = "simpleQuery", startingInputTypes = SimpleQueryRequest.class)
    )
    public MongoResponse<List<Document>> simpleQuery(SimpleQueryRequest req) {
        logger.info("Agent action: simpleQuery on {}.{} where {} = {}", req.dbName(), req.collectionName(), req.field(), req.value());
        List<Document> results = mongoServiceClient.simpleQuery(req.dbName(), req.collectionName(), req.field(), req.value());
        return new MongoResponse<>(results);
    }

    /**
     * Execute a complex JSON query on a collection.
     */
    @Action
    @AchievesGoal(
            description = "Execute a complex JSON query on a collection.",
            export = @Export(remote = true, name = "complexQuery", startingInputTypes = ComplexQueryRequest.class)
    )
    public MongoResponse<List<Document>> complexQuery(ComplexQueryRequest req) {
        logger.info("Agent action: complexQuery on {}.{} with query={}", req.dbName(), req.collectionName(), req.jsonQuery());
        List<Document> results = mongoServiceClient.complexQuery(req.dbName(), req.collectionName(), req.jsonQuery());
        return new MongoResponse<>(results);
    }

    /**
     * List indexes for a collection.
     */
    @Action
    @AchievesGoal(
            description = "List indexes for a MongoDB collection.",
            export = @Export(remote = true, name = "listIndexes", startingInputTypes = CollectionRequest.class)
    )
    public MongoResponse<List<Document>> listIndexes(CollectionRequest req) {
        logger.info("Agent action: listIndexes on {}.{}", req.dbName(), req.collectionName());
        List<Document> indexes = mongoServiceClient.listIndexes(req.dbName(), req.collectionName());
        return new MongoResponse<>(indexes);
    }

    /**
     * Create a new collection.
     */
    @Action
    @AchievesGoal(
            description = "Create a new collection in a MongoDB database.",
            export = @Export(remote = true, name = "createCollection", startingInputTypes = CreateCollectionRequest.class)
    )
    public MongoResponse<String> createCollection(CreateCollectionRequest req) {
        logger.info("Agent action: createCollection '{}' in db='{}'", req.collectionName(), req.dbName());
        String result = mongoServiceClient.createCollection(req.dbName(), req.collectionName());
        return new MongoResponse<>(result);
    }

    /**
     * Insert a JSON document into a collection.
     */
    @Action
    @AchievesGoal(
            description = "Insert a JSON document into a collection.",
            export = @Export(remote = true, name = "insertDocument", startingInputTypes = InsertDocumentRequest.class)
    )
    public MongoResponse<String> insertDocument(InsertDocumentRequest req) {
        logger.info("Agent action: insertDocument into {}.{} document={}", req.dbName(), req.collectionName(), req.jsonDocument());
        String result = mongoServiceClient.insertDocument(req.dbName(), req.collectionName(), req.jsonDocument());
        return new MongoResponse<>(result);
    }
}