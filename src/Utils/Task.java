/*
 * Catcher
 *
 * License: GPL v3
 * Authors: richard_jonsson@hotmail.com, tommyc@lavabit.com
 */

package Utils;


public abstract class Task implements Runnable {

    private boolean completed;
    private TaskCompletedListener listener;
    private Thread thread;

    public Task() {
        completed = false;
    }

    public void run() {
        synchronized(this) {
            completed = false;
        }

        task();

        TaskCompletedListener tempListener = null;
        synchronized(this) {
            completed = true;
            tempListener = listener;
        }
        if (tempListener != null) tempListener.taskCompleted(this);
    }

    public void runAsync() {
        thread = new Thread(this);
        thread.start();
    }

    public synchronized boolean getCompleted() {
        return completed;
    }

    public abstract void task();

    public void setTaskCompletedListener(TaskCompletedListener listener)
    {
        synchronized(this) {
            this.listener = listener;
        }
    }

}
