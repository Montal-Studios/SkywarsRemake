package net.montal.skywarsremake.utils;

import lombok.experimental.UtilityClass;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Task manager for running tasks asynchronously
 */
@UtilityClass
public class TaskManager {

    private final ExecutorService service = Executors.newCachedThreadPool();

    /**
     * Submit a task to be run asynchronously
     *
     * @param task Lambda to be run
     * @return Object from the task
     */
    public <T> T submit(Callable<T> task) {
        try {
            return service.submit(task).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}