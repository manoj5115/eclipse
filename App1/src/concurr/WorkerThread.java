/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concurr;

import java.util.logging.Level;
import java.util.logging.Logger;

public class WorkerThread implements Runnable {
    
    private String command;
    
    public WorkerThread(String s){
        this.command=s;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" Start. Command = "+command);
        processCommand(command);
        System.out.println(Thread.currentThread().getName()+" End.");
    }

    private void processCommand(String command){
        if(command.contains("7")){
            try {
                throw new Exception("Crash");
            } catch (Exception ex) {
                Logger.getLogger(WorkerThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString(){
        return this.command;
    }
}