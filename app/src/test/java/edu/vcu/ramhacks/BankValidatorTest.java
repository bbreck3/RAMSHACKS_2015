package edu.vcu.ramhacks;

import android.test.ActivityTestCase;
import android.util.Log;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.Semaphore;

import static org.junit.Assert.*;

import edu.vcu.ramhacks.utils.BankCallback;
import edu.vcu.ramhacks.utils.BankUtil;
import timber.log.Timber;

/**
 * Created by Dani on 13/09/2015.
 */
public class BankValidatorTest {

    @Test
    public void bankValidator1() {
        BankUtil banking = new BankUtil();
        System.out.println("TEST1");
        final Semaphore semaphore = new Semaphore(0);
        banking.getProbability("Manu", "Bennett", new BankCallback() {
            @Override
            public void onResult(BankUtil.ProbWeight result) {
                System.out.println("TEST " + result.getWeight() + " " + result.getProbability());
                Assert.assertNotNull(result);
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
