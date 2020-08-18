package com.example.jpushdemo;

import android.accounts.AbstractAccountAuthenticator;
import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.accounts.AccountManager;
import android.accounts.NetworkErrorException;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import cn.jiguang.api.JCoreManager;

/**
 * Created by shikk on 2020-02-13 .
 */
public class AccountAuthenticatorService extends Service {

    private AccountAuthenticator accountAuthenticator;

    @Override
    public void onCreate() {
        accountAuthenticator = new AccountAuthenticator(this);
        accountAuthenticator.toString();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.e("AccountAuthenticator", accountAuthenticator.toString());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(intent != null){
            Log.e("AccountAuthenticator", "onStartCommand:"+intent.getExtras().getString("p"));
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        if (intent.getAction().equals(
                AccountManager.ACTION_AUTHENTICATOR_INTENT)){

            if(intent != null&&intent.getExtras()!=null){
                Log.e("AccountAuthenticator", "onbind:"+intent.getExtras().getString("p"));
            }
            return accountAuthenticator.getIBinder();
        }

        return null;
    }

    private static class AccountAuthenticator extends
            AbstractAccountAuthenticator {
        final Context context;

        public AccountAuthenticator(Context context) {
            super(context);
            this.context = context;
        }

        @Override
        public Bundle addAccount(AccountAuthenticatorResponse response,
                                 String accountType, String authTokenType,
                                 String[] requiredFeatures, Bundle options)
                throws NetworkErrorException {
            Logger.e("shikk","addAccount()");
            Intent intent = new Intent(context, MainActivity.class);
            intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE,
                    response);
            Bundle bundle = new Bundle();
            bundle.putParcelable(AccountManager.KEY_INTENT, intent);
            if(options != null){
                Log.e("AccountAuthenticator", "options:"+options.getString("from_package"));
                Log.e("AccountAuthenticator", options.toString());
            }
            return bundle;
        }

        @Override
        public Bundle confirmCredentials(AccountAuthenticatorResponse response,
                                         Account account, Bundle options) throws NetworkErrorException {
            return null;
        }

        @Override
        public Bundle editProperties(AccountAuthenticatorResponse response,
                                     String accountType) {
            return null;
        }

        @Override
        public Bundle getAuthToken(AccountAuthenticatorResponse response,
                                   Account account, String authTokenType, Bundle options)
                throws NetworkErrorException {
            return null;
        }

        @Override
        public String getAuthTokenLabel(String authTokenType) {
            return null;
        }

        @Override
        public Bundle hasFeatures(AccountAuthenticatorResponse response,
                                  Account account, String[] features)
                throws NetworkErrorException {
            return null;
        }

        @Override
        public Bundle updateCredentials(AccountAuthenticatorResponse response,
                                        Account account, String authTokenType, Bundle options)
                throws NetworkErrorException {
            return null;
        }

    }
}
