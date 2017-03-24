package main.controling_task_finishing_in_executor;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * Created by G.Chalauri on 03/24/17.
 */
public class ResultTask extends FutureTask<String> {

    private String name;

    public ResultTask(Callable<String> callable) {
        super(callable);

        this.name = ((ExecutableTask) callable).getName();
    }

    @Override
    protected void done() {
        if (isCancelled()) {
            System.out.printf("%s: Has been canceled\n", name);
        } else {
            System.out.printf("%s: Has finished\n", name);
        }
    }
}
