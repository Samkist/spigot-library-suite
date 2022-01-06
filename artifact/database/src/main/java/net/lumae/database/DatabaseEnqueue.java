package net.lumae.database;

import net.lumae.database.wrapper.DatabaseOperation;

import java.util.PriorityQueue;
import java.util.Queue;

public class DatabaseEnqueue {

    private static final Queue<DatabaseOperation> queue = new PriorityQueue<>();

    public static void enqueueOperation(DatabaseOperation operation) {
        queue.add(operation);
    }


    public static void save() {
        queue.iterator().forEachRemaining(DatabaseOperation::queue);
        queue.clear();
    }
}
