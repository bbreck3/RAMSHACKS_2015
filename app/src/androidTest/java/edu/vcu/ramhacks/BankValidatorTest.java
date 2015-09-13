package edu.vcu.ramhacks;

//import android.test.ActivityTestCase;
//import android.util.Log;

import android.test.InstrumentationTestCase;

import java.util.concurrent.Semaphore;

import edu.vcu.ramhacks.utils.BankCallback;
import edu.vcu.ramhacks.utils.BankUtil;

/**
 * Created by Dani on 13/09/2015.
 */
public class BankValidatorTest extends InstrumentationTestCase {

    public void test() {
        BankUtil banking = new BankUtil();
        System.out.println("TEST1");
        final Semaphore semaphore = new Semaphore(0);
        banking.getProbability("Manu", "Bennett", new BankCallback() {
            @Override
            public void onResult(BankUtil.ProbWeight result) {
                System.out.println("TEST " + result.getWeight() + " " + result.getProbability());
                assertNotNull(result);
                semaphore.release();
            }
        });
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
