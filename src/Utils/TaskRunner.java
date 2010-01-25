/*
 * Catcher
 *
 * License: GPL v3
 * Authors: richard_jonsson@hotmail.com, tommyc@lavabit.com
 */
package Utils;

import java.util.Vector;

/**
 * A list of Tasks that are run one by one
 */
public class TaskRunner implements Runnable {

    private final Vector workerList;
    private final Vector taskList;
    private final String name;
    private int numWorkers;
    private boolean started;

    public TaskRunner(String name, int numWorkers) {
        taskList = new Vector();
        workerList = new Vector(numWorkers);
        this.name = name;
        this.numWorkers = numWorkers;
        started = false;
    }

    public synchronized int getNumWorkers() {
        return numWorkers;
    }

    public synchronized void setNumWorkers(int numWorkers) {
        this.numWorkers = numWorkers;
    }
    
    public void addTask(Task task) {
        synchronized(this) {
            taskList.addElement(task);
            this.notify();
        }
    }


    public void run() {

        while(shouldStillRun()) {

            try {

                Task current = null;
                synchronized(this) {
                    if (!taskList.isEmpty()) {
                        current = (Task)taskList.firstElement();
                        taskList.removeElement(current);
                    }
                }
                if (current != null) {

                    current.run();

                } else {
                    synchronized(this) {
                        try {
                            this.wait();
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private boolean shouldStillRun() {
       return started;
    }

    public synchronized void stop() {
        if (!started) return;

        started = false;
        for (int i = 0; i < workerList.size(); i++) {
            Thread t = (Thread)workerList.elementAt(i);
            t.interrupt();
        }
        workerList.removeAllElements();
    }

    public synchronized void start() {
        if (started) return;
        
        started = true;
        for(int i = 0; i < numWorkers; i++) {
            Thread worker = new Thread(this, name + " worker "+i);
            worker.start();
            workerList.addElement(worker);
        }
    }

    public boolean isStarted() {
        return started;
    }

}
