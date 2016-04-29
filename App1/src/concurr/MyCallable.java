/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concurr;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MyCallable implements Callable<String> {

    private String command;

    public MyCallable(String s) {
        this.command = s;
    }

    @Override
    public String call() throws Exception {
        if (command.contains("4") || command.contains("8")) {
            throw new Exception("Crash");

        }
        Thread.sleep(1000);
        //return the thread name executing this callable task
        return command;
    }

    public static void main(String args[]) {
        //Get ExecutorService from Executors utility class, thread pool size is 10
        ExecutorService executor = Executors.newFixedThreadPool(5);
        //create a list to hold the Future object associated with Callable
        Map<Integer, Future<String>> map = new HashMap<Integer, Future<String>>();
        //Create MyCallable instance
        for (int i = 0; i < 10; i++) {
            Callable<String> callable = new MyCallable("" + i);
            //submit Callable tasks to be executed by thread pool
            Future<String> future = executor.submit(callable);

            //add Future to the list, we can get return value using Future
            map.put(i, future);
        }
        System.out.println("Assigned");
        map.forEach(MyCallable::handleResult);
//        for (Future<String> fut : list) {
//            try {
//                //print the return value of Future, notice the output delay in console
//                // because Future.get() waits for task to get completed
//                System.out.println(new Date() + "::" + fut.get());
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                System.out.println("Handled in main thread - Crash");
//            }
//        }
        System.out.println("About to shutdown");
        //shut down the executor service now
        executor.shutdown();
        System.out.println("Shutdown");
    }

    public static void handleResult(int k, Future<String> v) {
        try {
            //print the return value of Future, notice the output delay in console
            // because Future.get() waits for task to get completed
            System.out.println(new Date() + "::" + v.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            System.out.println(k + " Handled in main thread - " + e.getCause().getMessage());
        }
    }
}
