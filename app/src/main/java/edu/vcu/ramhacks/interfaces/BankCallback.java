package edu.vcu.ramhacks.interfaces;

import edu.vcu.ramhacks.utils.BankCallbackStatus;
import edu.vcu.ramhacks.utils.BankUtil;

/**
 * Created by Dani on 12/09/2015.
 */
public interface BankCallback {
    void onResult(BankUtil.ProbWeight result, BankCallbackStatus status);
}
