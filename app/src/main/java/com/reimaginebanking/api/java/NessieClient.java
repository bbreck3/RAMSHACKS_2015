package com.reimaginebanking.api.java;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.reimaginebanking.api.java.Adapters.BillTypeAdapter;
import com.reimaginebanking.api.java.models.*;
import com.reimaginebanking.api.java.models.Transfer;
import com.reimaginebanking.api.java.requests.NessieService;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;

/**
 * Created by kco942 on 4/9/15.
 */
public class NessieClient {

    private NessieService service;

    private String key;

    private static final NessieClient instance = new NessieClient();

    private NessieClient() {

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Bill.class, new BillTypeAdapter())
                .create();

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://api.reimaginebanking.com")
                .setConverter(new GsonConverter(gson))
                .build();

//        RestAdapter restAdapter = new RestAdapter.Builder()
//                .setEndpoint("http://nessie.no-ip.org")
//                .build();

        service = restAdapter.create(NessieService.class);

    }

    public static NessieClient getInstance(){
        return instance;
    }

    public void setAPIKey(String key){
        this.key = key;
    }

    //ACCOUNT

    public void getAccounts(final NessieResultsListener mlistener){
        service.getAccounts(this.key, new Callback<List<Account>>() {
            public void success(List<Account> accounts, Response response) {
                mlistener.onSuccess(accounts, null);
            }

            public void failure(RetrofitError error) {
                mlistener.onSuccess(null, new NessieException(error));
            }
        });
    }

    public void getAccount(String accountID, final NessieResultsListener mlistener){
        service.getAccount(this.key, accountID, new Callback<Account>() {
            public void success(Account account, Response response) {
                mlistener.onSuccess(account, null);
            }

            public void failure(RetrofitError error) {
                mlistener.onSuccess(null, new NessieException(error));
            }
        });
    }

    public void getCustomerAccounts(String customerID, final NessieResultsListener mlistener){
        service.getCustomerAccounts(this.key, customerID, new Callback<List<Account>>() {
            public void success(List<Account> accounts, Response response) {
                mlistener.onSuccess(accounts, null);
            }

            public void failure(RetrofitError error) {
                mlistener.onSuccess(null, new NessieException(error));
            }
        });
    }


    public void createAccount(String customerID, Account newAccount, final NessieResultsListener mlistener){
        service.createAccount(this.key, customerID, newAccount, new Callback<RequestResponse>() {
            public void success(RequestResponse requestResponse, Response response) {
                mlistener.onSuccess(requestResponse, null);
            }

            public void failure(RetrofitError error) {
                mlistener.onSuccess(null, new NessieException(error));
            }
        });
    }

    public void updateAccount(String accountId, Account updatedAccount, final NessieResultsListener mlistener){
        service.updateAccount(this.key, accountId, updatedAccount, new Callback<RequestResponse>() {
            public void success(RequestResponse requestResponse, Response response) {
                mlistener.onSuccess(requestResponse, null);
            }

            public void failure(RetrofitError error) {
                mlistener.onSuccess(null, new NessieException(error));
            }
        });
    }

    public void deleteAccount(String accountID, final NessieResultsListener mlistener){
        service.deleteAccount(this.key, accountID, new Callback<RequestResponse>() {
            public void success(RequestResponse requestResponse, Response response) {
                requestResponse = new RequestResponse(response.getStatus(), "Account Deleted");
                mlistener.onSuccess(requestResponse, null);
            }

            public void failure(RetrofitError error) {
                mlistener.onSuccess(null, new NessieException(error));
            }
        });
    }

    //ATM

    public void getATMs(final NessieResultsListener mlistener){
        service.getATMs(this.key, new Callback<List<ATM>>() {
            public void success(List<ATM> atms, Response response) {
                mlistener.onSuccess(atms, null);
            }

            public void failure(RetrofitError error) {
                mlistener.onSuccess(null, new NessieException(error));
            }
        });
    }

    public void getATM(String atmID, final NessieResultsListener mlistener){
        service.getATM(this.key, atmID, new Callback<ATM>() {
            public void success(ATM atm, Response response) {
                mlistener.onSuccess(atm, null);
            }

            public void failure(RetrofitError error) {
                mlistener.onSuccess(null, new NessieException(error));
            }
        });
    }

    //BILL

    public void getBills(String accountID, final NessieResultsListener mlistener){
        service.getBills(this.key, accountID, new Callback<List<Bill>>() {
            @Override
            public void success(List<Bill> bills, Response response) {
                mlistener.onSuccess(bills, null);
            }

            @Override
            public void failure(RetrofitError error) {
                mlistener.onSuccess(null, new NessieException(error));
            }
        });
    }

    public void getBill(String billID, final NessieResultsListener mlistener){
        service.getBill(this.key, billID, new Callback<Bill>() {
            @Override
            public void success(Bill bill, Response response) {
                mlistener.onSuccess(bill, null);
            }

            @Override
            public void failure(RetrofitError error) {
                mlistener.onSuccess(null, new NessieException(error));
            }
        });
    }

    public void getCustomerBills(String customerID, final NessieResultsListener mlistener){
        service.getCustomerBills(this.key, customerID, new Callback<List<Bill>>() {
            public void success(List<Bill> bills, Response response) {
                mlistener.onSuccess(bills, null);
            }

            public void failure(RetrofitError error) {
                mlistener.onSuccess(null, new NessieException(error));
            }
        });
    }

    public void createBill(String accountID, Bill newBill, final NessieResultsListener mlistener){
        service.createBill(this.key, accountID, newBill, new Callback<RequestResponse>() {
            public void success(RequestResponse requestResponse, Response response) {
                mlistener.onSuccess(requestResponse, null);
            }

            public void failure(RetrofitError error) {
                mlistener.onSuccess(null, new NessieException(error));
            }
        });
    }

    public void updateBill(String billID, Bill updatedBill, final NessieResultsListener mlistener){
        service.updateBill(this.key, billID, updatedBill, new Callback<RequestResponse>() {
            public void success(RequestResponse requestResponse, Response response) {
                mlistener.onSuccess(requestResponse, null);
            }

            public void failure(RetrofitError error) {
                mlistener.onSuccess(null, new NessieException(error));
            }
        });
    }

    public void deleteBill(String billId, final NessieResultsListener mlistener){
        service.deleteBill(this.key, billId, new Callback<RequestResponse>() {
            public void success(RequestResponse requestResponse, Response response) {
                requestResponse = new RequestResponse(response.getStatus(), "Bill Deleted");
                mlistener.onSuccess(requestResponse, null);
            }

            public void failure(RetrofitError error) {
                mlistener.onSuccess(null, new NessieException(error));
            }
        });
    }

    //BRANCH
    public void getBranches(final NessieResultsListener mlistener){
        service.getBranches(this.key, new Callback<List<Branch>>() {
            public void success(List<Branch> branches, Response response) {
                mlistener.onSuccess(branches, null);
            }

            public void failure(RetrofitError error) {
                mlistener.onSuccess(null, new NessieException(error));
            }
        });
    }

    public void getBranch(String branchID, final NessieResultsListener mlistener){
        service.getBranch(this.key, branchID, new Callback<Branch>() {
            public void success(Branch branch, Response response) {
                mlistener.onSuccess(branch, null);
            }

            public void failure(RetrofitError error) {
                mlistener.onSuccess(null, new NessieException(error));
            }
        });
    }

    //CUSTOMER

    public void getAccountCustomer(String accountID, final NessieResultsListener mlistener){
        service.getAccountCustomer(this.key, accountID, new Callback<Customer>() {
            public void success(Customer customer, Response response) {
                mlistener.onSuccess(customer, null);
            }

            public void failure(RetrofitError error) {
                mlistener.onSuccess(null, new NessieException(error));
            }
        });
    }

    public void getCustomers(final NessieResultsListener mlistener){
        service.getCustomers(this.key, new Callback<List<Customer>>() {
            public void success(List<Customer> customers, Response response) {
                mlistener.onSuccess(customers, null);
            }

            public void failure(RetrofitError error) {
                mlistener.onSuccess(null, new NessieException(error));
            }
        });
    }

    public void getCustomer(String customerID, final NessieResultsListener mlistener){
        service.getCustomer(this.key, customerID, new Callback<Customer>() {
            public void success(Customer customer, Response response) {
                mlistener.onSuccess(customer, null);
            }

            public void failure(RetrofitError error) {
                mlistener.onSuccess(null, new NessieException(error));
            }
        });
    }

    public void createCustomer(Customer newCustomer, final NessieResultsListener mlistener){
        service.createCustomer(this.key, newCustomer, new Callback<RequestResponse>() {
            public void success(RequestResponse requestResponse, Response response) {
                mlistener.onSuccess(requestResponse, null);
            }

            public void failure(RetrofitError error) {
                mlistener.onSuccess(null, new NessieException(error));
            }
        });
    }

    public void updateCustomer(String customerID, Customer updatedCustomer, final NessieResultsListener mlistener){
        service.updateCustomer(this.key, customerID, updatedCustomer, new Callback<RequestResponse>() {
            public void success(RequestResponse requestResponse, Response response) {
                mlistener.onSuccess(requestResponse, null);
            }

            public void failure(RetrofitError error) {
                mlistener.onSuccess(null, new NessieException(error));
            }
        });
    }

    //DEPOSIT

    public void getDeposits(String accountId, final NessieResultsListener mlistener){
        service.getDeposits(this.key, accountId, new Callback<List<Deposit>>() {
            @Override
            public void success(List<Deposit> transactions, Response response) {
                mlistener.onSuccess(transactions, null);
            }

            @Override
            public void failure(RetrofitError error) {
                mlistener.onSuccess(null, new NessieException(error));
            }
        });
    }

    public void getDeposit(String depositId, final NessieResultsListener mlistener){
        service.getDeposit(this.key, depositId, new Callback<Deposit>() {
            @Override
            public void success(Deposit transaction, Response response) {
                mlistener.onSuccess(transaction, null);
            }

            @Override
            public void failure(RetrofitError error) {
                mlistener.onSuccess(null, new NessieException(error));
            }
        });
    }

    public void createDeposit(String accountId, Deposit deposit, final NessieResultsListener mlistener){
        service.createDeposit(this.key, accountId, deposit, new Callback<RequestResponse>() {

            @Override
            public void success(RequestResponse requestResponse, Response response) {
                mlistener.onSuccess(requestResponse, null);
            }

            @Override
            public void failure(RetrofitError error) {
                mlistener.onSuccess(null, new NessieException(error));
            }
        });
    }

    public void updateDeposit(String depositId, Deposit deposit, final NessieResultsListener mlistener){
        service.updateDeposit(this.key, depositId, deposit, new Callback<RequestResponse>() {
            @Override
            public void success(RequestResponse requestResponse, Response response) {
                mlistener.onSuccess(requestResponse, null);
            }

            @Override
            public void failure(RetrofitError error) {
                mlistener.onSuccess(null, new NessieException(error));
            }
        });
    }

    public void deleteDeposit(String depositId, final NessieResultsListener mlistener){
        service.deleteDeposit(this.key, depositId, new Callback<RequestResponse>() {
            @Override
            public void success(RequestResponse requestResponse, Response response) {
                mlistener.onSuccess(requestResponse, null);
            }

            @Override
            public void failure(RetrofitError error) {
                mlistener.onSuccess(null, new NessieException(error));
            }
        });
    }

    //MERCHANT
    public void getMerchants(final NessieResultsListener mlistener){
        service.getMerchants(this.key, new Callback<List<Merchant>>() {
            @Override
            public void success(List<Merchant> merchants, Response response) {
                mlistener.onSuccess(merchants, null);
            }

            @Override
            public void failure(RetrofitError error) {
                mlistener.onSuccess(null, new NessieException(error));
            }
        });
    }

    public void getMerchants(String lat, String lng, String rad, final NessieResultsListener mlistener){
        service.getMerchants(this.key, lat, lng, rad, new Callback<List<Merchant>>() {
            @Override
            public void success(List<Merchant> merchants, Response response) {
                mlistener.onSuccess(merchants, null);
            }

            @Override
            public void failure(RetrofitError error) {
                mlistener.onSuccess(null, new NessieException(error));
            }
        });
    }


    public void getMerchant(String merchantId, final NessieResultsListener mlistener){
        service.getMerchant(this.key, merchantId, new Callback<Merchant>() {
            @Override
            public void success(Merchant merchant, Response response) {
                mlistener.onSuccess(merchant, null);
            }

            @Override
            public void failure(RetrofitError error) {
                mlistener.onSuccess(null, new NessieException(error));
            }
        });
    }

    public void createMerchant(Merchant merchant, final NessieResultsListener mlistener){
        service.createMerchant(this.key, merchant, new Callback<RequestResponse>() {

            @Override
            public void success(RequestResponse requestResponse, Response response) {
                mlistener.onSuccess(requestResponse, null);
            }

            @Override
            public void failure(RetrofitError error) {
                mlistener.onSuccess(null, new NessieException(error));
            }
        });
    }

    public void updateMerchant(String merchantId, Merchant updatedMerchant, final NessieResultsListener mlistener){
        service.updateMerchant(this.key, merchantId, updatedMerchant, new Callback<RequestResponse>() {
            @Override
            public void success(RequestResponse requestResponse, Response response) {
                mlistener.onSuccess(requestResponse, null);
            }

            @Override
            public void failure(RetrofitError error) {
                mlistener.onSuccess(null, new NessieException(error));
            }
        });
    }

    //PURCHASE

    public void getPurchases(String accountId, final NessieResultsListener mlistener){
        service.getPurchases(this.key, accountId, new Callback<List<Purchase>>() {
            @Override
            public void success(List<Purchase> transactions, Response response) {
                mlistener.onSuccess(transactions, null);
            }

            @Override
            public void failure(RetrofitError error) {
                mlistener.onSuccess(null, new NessieException(error));
            }
        });
    }

    public void getPurchase(String purchaseId, final NessieResultsListener mlistener){
        service.getPurchase(this.key, purchaseId, new Callback<Purchase>() {
            @Override
            public void success(Purchase transaction, Response response) {
                mlistener.onSuccess(transaction, null);
            }

            @Override
            public void failure(RetrofitError error) {
                mlistener.onSuccess(null, new NessieException(error));
            }
        });
    }

    public void createPurchase(String accountId, Purchase purchase, final NessieResultsListener mlistener){
        service.createPurchase(this.key, accountId, purchase, new Callback<RequestResponse>() {

            @Override
            public void success(RequestResponse requestResponse, Response response) {
                mlistener.onSuccess(requestResponse, null);
            }

            @Override
            public void failure(RetrofitError error) {
                mlistener.onSuccess(null, new NessieException(error));
            }
        });
    }

    public void updatePurchase(String purchaseId, Purchase purchase, final NessieResultsListener mlistener){
        service.updatePurchase(this.key, purchaseId, purchase, new Callback<RequestResponse>() {
            @Override
            public void success(RequestResponse requestResponse, Response response) {
                mlistener.onSuccess(requestResponse, null);
            }

            @Override
            public void failure(RetrofitError error) {
                mlistener.onSuccess(null, new NessieException(error));
            }
        });
    }

    public void deletePurchase(String merchantId, final NessieResultsListener mlistener){
        service.deletePurchase(this.key, merchantId, new Callback<RequestResponse>() {
            @Override
            public void success(RequestResponse requestResponse, Response response) {
                mlistener.onSuccess(requestResponse, null);
            }

            @Override
            public void failure(RetrofitError error) {
                mlistener.onSuccess(null, new NessieException(error));
            }
        });
    }

    //TRANSFER
    public void getTransfers(String accountId, final NessieResultsListener mlistener){
        service.getTransfers(this.key, accountId, new Callback<List<Transfer>>() {
            @Override
            public void success(List<Transfer> transactions, Response response) {
                mlistener.onSuccess(transactions, null);
            }

            @Override
            public void failure(RetrofitError error) {
                mlistener.onSuccess(null, new NessieException(error));
            }
        });
    }

    public void getTransfer(String transferId, final NessieResultsListener mlistener){
        service.getTransfer(this.key, transferId, new Callback<Transfer>() {
            @Override
            public void success(Transfer transaction, Response response) {
                mlistener.onSuccess(transaction, null);
            }

            @Override
            public void failure(RetrofitError error) {
                mlistener.onSuccess(null, new NessieException(error));
            }
        });
    }

    public void createTransfer(String accountId, Transfer transfer, final NessieResultsListener mlistener){
        service.createTransfer(this.key, accountId, transfer, new Callback<RequestResponse>() {

            @Override
            public void success(RequestResponse requestResponse, Response response) {
                mlistener.onSuccess(requestResponse, null);
            }

            @Override
            public void failure(RetrofitError error) {
                mlistener.onSuccess(null, new NessieException(error));
            }
        });
    }

    public void updateTransfer(String transferId, Transfer transfer, final NessieResultsListener mlistener){
        service.updateTransfer(this.key, transferId, transfer, new Callback<RequestResponse>() {
            @Override
            public void success(RequestResponse requestResponse, Response response) {
                mlistener.onSuccess(requestResponse, null);
            }

            @Override
            public void failure(RetrofitError error) {
                mlistener.onSuccess(null, new NessieException(error));
            }
        });
    }

    public void deleteTransfer(String transferId, final NessieResultsListener mlistener){
        service.deleteTransfer(this.key, transferId, new Callback<RequestResponse>() {
            @Override
            public void success(RequestResponse requestResponse, Response response) {
                mlistener.onSuccess(requestResponse, null);
            }

            @Override
            public void failure(RetrofitError error) {
                mlistener.onSuccess(null, new NessieException(error));
            }
        });
    }

    //WITHDRAWAL
    public void getWithdrawals(String accountId, final NessieResultsListener mlistener){
        service.getWithdrawals(this.key, accountId, new Callback<List<Withdrawal>>() {
            @Override
            public void success(List<Withdrawal> transactions, Response response) {
                mlistener.onSuccess(transactions, null);
            }

            @Override
            public void failure(RetrofitError error) {
                mlistener.onSuccess(null, new NessieException(error));
            }
        });
    }

    public void getWithdrawal(String withdrawalId, final NessieResultsListener mlistener){
        service.getWithdrawal(this.key, withdrawalId, new Callback<Withdrawal>() {
            @Override
            public void success(Withdrawal transaction, Response response) {
                mlistener.onSuccess(transaction, null);
            }

            @Override
            public void failure(RetrofitError error) {
                mlistener.onSuccess(null, new NessieException(error));
            }
        });
    }

    public void createWithdrawal(String accountId, Withdrawal withdrawal, final NessieResultsListener mlistener){
        service.createWithdrawal(this.key, accountId, withdrawal, new Callback<RequestResponse>() {

            @Override
            public void success(RequestResponse requestResponse, Response response) {
                mlistener.onSuccess(requestResponse, null);
            }

            @Override
            public void failure(RetrofitError error) {
                mlistener.onSuccess(null, new NessieException(error));
            }
        });
    }

    public void updateWithdrawal(String withdrawalId, Withdrawal withdrawal, final NessieResultsListener mlistener){
        service.updateWithdrawal(this.key, withdrawalId, withdrawal, new Callback<RequestResponse>() {
            @Override
            public void success(RequestResponse requestResponse, Response response) {
                mlistener.onSuccess(requestResponse, null);
            }

            @Override
            public void failure(RetrofitError error) {
                mlistener.onSuccess(null, new NessieException(error));
            }
        });
    }

    public void deleteWithdrawal(String withdrawalId, final NessieResultsListener mlistener){
        service.deleteWithdrawal(this.key, withdrawalId, new Callback<RequestResponse>() {
            @Override
            public void success(RequestResponse requestResponse, Response response) {
                mlistener.onSuccess(requestResponse, null);
            }

            @Override
            public void failure(RetrofitError error) {
                mlistener.onSuccess(null, new NessieException(error));
            }
        });
    }

    //ENTERPRISE ACCOUNT
    public void getAccountsAsEnterprise(final NessieResultsListener mlistener){
        service.getAccountsAsEnterprise(this.key, new Callback<List<Account>>() {
            public void success(List<Account> accounts, Response response) {
                mlistener.onSuccess(accounts, null);
            }

            public void failure(RetrofitError error) {
                mlistener.onSuccess(null, new NessieException(error));
            }
        });
    }

    public void getAccountAsEnterprise(String accountID, final NessieResultsListener mlistener){
        service.getAccountAsEnterprise(this.key, accountID, new Callback<Account>() {
            public void success(Account account, Response response) {
                mlistener.onSuccess(account, null);
            }

            public void failure(RetrofitError error) {
                mlistener.onSuccess(null, new NessieException(error));
            }
        });
    }

    //ENTERPRISE BILL
    public void getBillsAsEnterprise(final NessieResultsListener mlistener){
        service.getBillsAsEnterprise(this.key, new Callback<List<Bill>>() {
            public void success(List<Bill> bills, Response response) {
                mlistener.onSuccess(bills, null);
            }

            public void failure(RetrofitError error) {
                mlistener.onSuccess(null, new NessieException(error));
            }
        });
    }

    public void getBillAsEnterprise(String billID, final NessieResultsListener mlistener){
        service.getBillAsEnterprise(this.key, billID, new Callback<Bill>() {
            public void success(Bill bill, Response response) {
                mlistener.onSuccess(bill, null);
            }

            public void failure(RetrofitError error) {
                mlistener.onSuccess(null, new NessieException(error));
            }
        });
    }

    //ENTERPRISE CUSTOMER
    public void getCustomersAsEnterprise(final NessieResultsListener mlistener){
        service.getCustomersAsEnterprise(this.key, new Callback<List<Customer>>() {
            public void success(List<Customer> customers, Response response) {
                mlistener.onSuccess(customers, null);
            }

            public void failure(RetrofitError error) {
                mlistener.onSuccess(null, new NessieException(error));
            }
        });
    }

    public void getCustomerAsEnterprise(String customerID, final NessieResultsListener mlistener){
        service.getCustomerAsEnterprise(this.key, customerID, new Callback<Customer>() {
            public void success(Customer customer, Response response) {
                mlistener.onSuccess(customer, null);
            }

            public void failure(RetrofitError error) {
                mlistener.onSuccess(null, new NessieException(error));
            }
        });
    }

    //ENTERPRISE MERCHANT
    public void getMerchantsAsEnterprise(final NessieResultsListener mlistener){
        service.getMerchantsAsEnterprise(this.key, new Callback<List<Merchant>>() {
            public void success(List<Merchant> customers, Response response) {
                mlistener.onSuccess(customers, null);
            }

            public void failure(RetrofitError error) {
                mlistener.onSuccess(null, new NessieException(error));
            }
        });
    }

    public void getMerchantAsEnterprise(String merchantID, final NessieResultsListener mlistener){
        service.getMerchantAsEnterprise(this.key, merchantID, new Callback<Merchant>() {
            public void success(Merchant merchant, Response response) {
                mlistener.onSuccess(merchant, null);
            }

            public void failure(RetrofitError error) {
                mlistener.onSuccess(null, new NessieException(error));
            }
        });
    }

    //ENTERPRISE TRANSFER
    public void getTransfersAsEnterprise(final NessieResultsListener mlistener){
        service.getTransfersAsEnterprise(this.key, new Callback<List<Transfer>>() {
            public void success(List<Transfer> customers, Response response) {
                mlistener.onSuccess(customers, null);
            }

            public void failure(RetrofitError error) {
                mlistener.onSuccess(null, new NessieException(error));
            }
        });
    }

    public void getTransferAsEnterprise(String transferID, final NessieResultsListener mlistener){
        service.getTransferAsEnterprise(this.key, transferID, new Callback<Transfer>() {
            public void success(Transfer transfer, Response response) {
                mlistener.onSuccess(transfer, null);
            }

            public void failure(RetrofitError error) {
                mlistener.onSuccess(null, new NessieException(error));
            }
        });
    }

    //ENTERPRISE WITHDRAWAL
    public void getWithdrawalsAsEnterprise(final NessieResultsListener mlistener){
        service.getWithdrawalsAsEnterprise(this.key, new Callback<List<Withdrawal>>() {
            public void success(List<Withdrawal> withdrawals, Response response) {
                mlistener.onSuccess(withdrawals, null);
            }

            public void failure(RetrofitError error) {
                mlistener.onSuccess(null, new NessieException(error));
            }
        });
    }

    public void getWithdrawalAsEnterprise(String withdrawalID, final NessieResultsListener mlistener){
        service.getWithdrawalAsEnterprise(this.key, withdrawalID, new Callback<Withdrawal>() {
            public void success(Withdrawal withdrawal, Response response) {
                mlistener.onSuccess(withdrawal, null);
            }

            public void failure(RetrofitError error) {
                mlistener.onSuccess(null, new NessieException(error));
            }
        });
    }
}
