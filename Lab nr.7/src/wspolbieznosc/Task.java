/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wspolbieznosc;

/**
 *
 * @author Klaudiusz
 */
public interface Task {
    void run( int taskNumber ) throws InterruptedException;
}
