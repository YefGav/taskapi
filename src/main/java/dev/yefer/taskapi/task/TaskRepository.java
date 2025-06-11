package dev.yefer.taskapi.task;

import dev.yefer.taskapi.task.Task;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.util.Optional;

@Repository
public class TaskRepository {

    private final DynamoDbTable<Task> taskTable;

    public TaskRepository(DynamoDbClient dynamoDbClient) {
        DynamoDbEnhancedClient enhancedClient = DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbClient)
                .build();

        this.taskTable = enhancedClient.table("task-yefer", TableSchema.fromBean(Task.class));
    }

    public void save(Task task) {
        taskTable.putItem(task);
    }

    public Optional<Task> findById(String id) {
        Task task = taskTable.getItem(r -> r.key(k -> k.partitionValue(id)));
        return Optional.ofNullable(task);
    }

    public void delete(String id) {
        taskTable.deleteItem(r -> r.key(k -> k.partitionValue(id)));
    }
}

