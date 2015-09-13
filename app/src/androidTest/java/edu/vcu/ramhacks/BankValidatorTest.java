package edu.vcu.ramhacks;

//import android.test.ActivityTestCase;
//import android.util.Log;

import android.test.InstrumentationTestCase;

import java.util.concurrent.Semaphore;

import edu.vcu.ramhacks.interfaces.BankCallback;
import edu.vcu.ramhacks.utils.BankCallbackStatus;
import edu.vcu.ramhacks.utils.BankUtil;

/**
 * Created by Dani on 13/09/2015.
 */
public class BankValidatorTest extends InstrumentationTestCase {

    public void test() {
        try {
            BankUtil banking = new BankUtil();
            System.out.println("TEST1");
            final Semaphore semaphore = new Semaphore(0);
            banking.getProbability("Manu", "Bennett", new BankCallback() {
                @Override
                public void onResult(BankUtil.ProbWeight result, BankCallbackStatus status) {
                    System.out.println("TEST2 " + result.getWeight() + " " + result.getProbability());
                    assertNotNull(result);
                    assertEquals(status, BankCallbackStatus.OK);
                    semaphore.release();
                }
            });
            BankUtil banking2 = new BankUtil();
            banking2.getProbability("invented", "name", new BankCallback() {
                @Override
                public void onResult(BankUtil.ProbWeight result, BankCallbackStatus status) {
                    //System.out.println("TEST3 " + result.getWeight() + " " + result.getProbability());
                    assertNull(result);
                    assertEquals(status, BankCallbackStatus.USER_NOT_FOUND);
                    semaphore.release();
                }
            });
            semaphore.acquire(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
