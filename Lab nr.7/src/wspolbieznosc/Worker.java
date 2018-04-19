/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wspolbieznosc;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 * @author Klaudiusz
 */
public class Worker implements Runnable {

    Thread thread;
    boolean stopThread = false;
    Task taskRun;
    int taskNumber = 0;
    boolean working = false;
    private WorkerListener workerListener;
    BlockingQueue<TaskWithName> tasks = new LinkedBlockingQueue<>();
    //BlockingQueue<String> tasksNames = new LinkedBlockingQueue<>();
    Worker(String workerName) {
        thread = new Thread(this, "Worker" + workerName + "Thread");
    }
    synchronized void enqueueTask(String taskName, Task task) {
        System.out.println("Added task: " + taskName);
        TaskWithName newTask = new TaskWithName(taskName, task);
        tasks.add(newTask);
    }
    synchronized void start() {
        if (isStarted() == false) {
            System.out.println("Method: start()");
            try {
                thread.start();
            } catch (IllegalThreadStateException e) {
                e.printStackTrace();
            }
        }
    }
    void waitThread() {
        try {
            thread.join();
        } catch (Exception e) {
        };
    }
    void stop() {
        if (stopThread == false) {
            System.out.println("Method: stop()");
            stopThread = true;
            thread.interrupt();
            //try{
            //    thread.join();
            //}catch(InterruptedException e){}; 
        }
    }
    void setListener(WorkerListener workerListener) {
        this.workerListener = workerListener;
    }

    boolean isStarted() {
        if (thread.getState().toString().equals("NEW")) {
            return false;
        } else if (thread.getState().toString().equals("TERMINATED")) {
            thread = new Thread(this, thread.getName());
            return false;
        } else {
            return true;
        }

    }

    boolean isWorking() {
        return working;
    }

    @Override
    public void run() {
        workerListener.onWorkerStarted();
        while (stopThread == false) {
            try {
                TaskWithName task = tasks.take();
                working = true;
                String taskName = task.getName();
                taskRun = task.getTask();
                workerListener.onTaskStarted(taskNumber, taskName);
                taskRun.run(taskNumber);
                workerListener.onTaskCompleted(taskNumber++, taskName);

            } catch (Exception e) {
            };
        }
        stopThread = false;
        workerListener.onWorkerStopped();
    }
}
