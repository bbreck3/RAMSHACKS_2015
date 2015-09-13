package edu.vcu.ramhacks.utils;

import com.reimaginebanking.api.java.Models.Account;
import com.reimaginebanking.api.java.Models.Customer;
import com.reimaginebanking.api.java.Models.Merchant;
import com.reimaginebanking.api.java.Models.Purchase;
import com.reimaginebanking.api.java.NessieClient;
import com.reimaginebanking.api.java.NessieException;
import com.reimaginebanking.api.java.NessieResultsListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import timber.log.Timber;

/**
 * Created by Dani on 12/09/2015.
 */
public class BankUtil {
    private final NessieClient nessie = NessieClient.getInstance();
    private ArrayList<Merchant> merchants;
    private final HashMap<String, ProbWeight> merchantIdWeight = new HashMap<>();
    private final HashMap<String, ProbWeight> merchantNameWeight = new HashMap<>();
    private final Pattern merchantWhitelistRegex = Pattern.compile("bar|liquor|pub|club", Pattern.CASE_INSENSITIVE);
    private final Pattern merchantBlacklistRegex = Pattern.compile("sport", Pattern.CASE_INSENSITIVE);
    private final static double REGEX_PROB = 1, REGEX_WEIGHT = 0.5;
    private AtomicInteger accountsLoaded = new AtomicInteger();
    private AtomicInteger probabilityRequirements = new AtomicInteger();
    private double totalBalance;
    private int amountOfCreditCards;

    private final Semaphore semaphore = new Semaphore(0);

    private String customerId;
    private final ArrayList<String> accountIds = new ArrayList<String>();
    private final ArrayList<ProbWeight> probabilities = new ArrayList<ProbWeight>();

    private BankCallback callback;

    public class ProbWeight {
        private final double probability;
        private final double weight;

        public ProbWeight(double probability, double weight) {
            this.probability = probability;
            this.weight = weight;
        }

        //Weight defaults to 1
        public ProbWeight(double probability) {
            this(probability, 1);
        }

        public double getProbability() {
            return probability;
        }

        public double getWeight() {
            return weight;
        }
    }

    public BankUtil() {
        nessie.setAPIKey("3761c2c940e5adf646a324007cfa8a68");

        //Add alcohol sellers (lower case)
        merchantNameWeight.put("abc", new ProbWeight(0.95, 10));
        merchantNameWeight.put("liquor store", new ProbWeight(0.8));
        merchantNameWeight.put("stubhub", new ProbWeight(1, .75));
        //...
    }

    private void computeProbability() {
        for(String accountId : accountIds) {
            nessie.getPurchases(accountId, new NessieResultsListener() {
                @Override
                public void onSuccess(Object o, NessieException e) {
                    double result = 0, weightSum = 0;
                    if (e == null) {
                        ArrayList<Purchase> purchases = (ArrayList<Purchase>) o;
                        for (Purchase p : purchases) {
                            if (merchantIdWeight.containsKey(p.getMerchant_id())) {
                                ProbWeight pw = merchantIdWeight.get(p.getMerchant_id());
                                if(pw != null) {
                                    probabilities.add(pw);
                                }
                            }
                        }
                    } else {
                        Timber.e("Nessie error: " + e);
                    }
                    if(accountsLoaded.incrementAndGet() == accountIds.size()) {
                        //double result = 0, weightSum = 0;
                        for(ProbWeight pw : probabilities) {
                            result += pw.getProbability() * pw.getWeight();
                            weightSum += pw.getWeight();
                        }
                        if(totalBalance > 1000) {
                            result += Math.log10(totalBalance)-3;
                            weightSum += Math.log10(totalBalance)-3;
                        }
                        if(amountOfCreditCards == 0) {
                            weightSum += 0.5;
                        } else if(amountOfCreditCards < 3) {
                            result += 0.5;
                            weightSum += 0.5;
                        }
                        callback.onResult(new ProbWeight(result, weightSum));
                    }
                }
            });
        }
    }

    public void getProbability(String firstName, String lastName, BankCallback callback) {
        probabilityRequirements.set(0);
        this.callback = callback;
        this.getMerchantList();
        this.getUserId(firstName, lastName);
    }

    private void getUserId(final String firstName, final String lastName) {
        nessie.getCustomers(new NessieResultsListener() {
            @Override
            public void onSuccess(Object o, NessieException e) {
                if (e == null) {
                    ArrayList<Customer> accounts = (ArrayList<Customer>) o;
                    for (Customer c : accounts) {
                        if (c.getFirst_name().equalsIgnoreCase(firstName) && c.getLast_name().equalsIgnoreCase(lastName)) {
                            customerId = c.get_id();
                            getAccounts();
                            return;
                        }
                    }
                } else {
                    Timber.e("Nessie error: " + e);
                }
                semaphore.release();
            }
        });
    }

    private void getAccounts() {
        if(customerId == null) {
            //TODO: error
        } else {
            amountOfCreditCards = 0;
            totalBalance = 0;
            nessie.getCustomerAccounts(customerId, new NessieResultsListener() {
                @Override
                public void onSuccess(Object o, NessieException e) {
                    if (e == null) {
                        ArrayList<Account> accounts = (ArrayList<Account>) o;
                        accountIds.clear();
                        for(Account a : accounts) {
                            accountIds.add(a.get_id());
                            totalBalance += a.getBalance();
                            if(a.getType() != null && a.getType().toString().equalsIgnoreCase("Credit Card")) {
                                amountOfCreditCards++;
                            }
                        }
                        if(probabilityRequirements.incrementAndGet() == 2) {
                            computeProbability();
                        }
                    } else {
                        Timber.e("Nessie error: " + e);
                    }
                }
            });
        }
    }

    private void getMerchantList() {
        nessie.getMerchants(new NessieResultsListener() {
            @Override
            public void onSuccess(Object o, NessieException e) {
                if ((e == null)) {
                    merchants = (ArrayList<Merchant>) o;
                    for (Merchant m : merchants) {
                        if(merchantNameWeight.containsKey(m.getName().toLowerCase())) {
                            merchantIdWeight.put(m.get_id(), merchantNameWeight.get(m.getName().toLowerCase()));
                        } else {
                            Matcher whitelistMatcher = merchantWhitelistRegex.matcher(m.getName()),
                                    blacklistMatcher = merchantBlacklistRegex.matcher(m.getName());
                            if(!blacklistMatcher.find() && whitelistMatcher.find()) {
                                merchantIdWeight.put(m.get_id(), new ProbWeight(REGEX_PROB, REGEX_WEIGHT));
                            }
                        }
                    }
                    if (probabilityRequirements.incrementAndGet() == 2) {
                        computeProbability();
                    }
                } else {
                    Timber.e("Nessie error: " + e);
                }
            }
        });
    }
}
