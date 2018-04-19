package wspolbieznosc;

import static java.lang.Thread.sleep;

/**
 *
 * @author Klaudiusz
 */
public class Wspolbieznosc {

    public static void main(String[] args) {
        Worker worker = new Worker("test");
        worker.setListener(new WorkerListener() {
            @Override
            public void onWorkerStarted() {
                System.out.println("**Worker started**");
            }

            @Override
            public void onWorkerStopped() {
                System.out.println("**Worker stopped**");
            }

            @Override
            public void onTaskStarted(int taskNumber, String taskName) {
                System.out.println("**Task started: " + taskName + " [ " + taskNumber
                        + " ]**");
            }

            @Override
            public void onTaskCompleted(int taskNumber, String taskName) {
                System.out.println("**Task completed: " + taskName + " [ " + taskNumber
                        + " ]**");
            }
        });
        Task taskHighCPU = (int taskNumber) -> {
            final int NUM_TESTS = 1000;
            long start = System.nanoTime();
            for (int i = 0; i < NUM_TESTS; i++) {
                long sleepTime = 10 * 1000000L;
                long startTime = System.nanoTime();
                while ((System.nanoTime() - startTime) < sleepTime) {
                    if (Thread.interrupted()) {
                        return;
                    }
                }
            };
        };
        Task taskLowCPU = (int taskNumber) -> {
            Thread.sleep(10000);
        };
        Task taskYield = (int taskNumber) -> {
            final int NUM_TESTS = 1000;
            long start = System.nanoTime();
            for (int i = 0; i < NUM_TESTS; i++) {
                long sleepTime = 10 * 1000000L;
                long startTime = System.nanoTime();
                while ((System.nanoTime() - startTime) < sleepTime) {
                    Thread.yield();
                    if (Thread.interrupted()) {
                        return;
                    }
                }
            };
        };

        
        worker.enqueueTask("lowCPU", taskLowCPU);
        worker.enqueueTask("yieddld", taskYield);
        worker.enqueueTask("highCPU", taskHighCPU);
        worker.enqueueTask("yieddld", taskYield);
        worker.enqueueTask("lowCPU", taskLowCPU);
        worker.start();
        worker.enqueueTask("yieddld", taskYield);
        worker.enqueueTask("lowCPU", taskLowCPU);
        worker.enqueueTask("highCPU", taskHighCPU);
        worker.enqueueTask("yieddld", taskYield);
        worker.enqueueTask("lowCPU", taskLowCPU);
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {};
        worker.stop();
        worker.waitThread();
        System.out.println("Operacja na koniec w main");
    }
}
