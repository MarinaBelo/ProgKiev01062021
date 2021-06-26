package ua.kiev.prog.automation.tools;

public class Waiters {
    static public void sleep (long ms){
        try {Thread.sleep(ms);} catch (Throwable e) {/*Ignore*/}
    }
}
