/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wspolbieznosc;

import java.util.EventListener;

/**
 *
 * @author Klaudiusz
 */
public interface WorkerListener extends EventListener  {
    void onWorkerStarted();
    void onWorkerStopped();
    void onTaskStarted( int taskNumber, String taskName );
    void onTaskCompleted( int taskNumber, String taskName );
}
