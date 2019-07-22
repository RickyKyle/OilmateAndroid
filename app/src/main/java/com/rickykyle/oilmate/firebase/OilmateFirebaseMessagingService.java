package com.rickykyle.oilmate.firebase;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class OilmateFirebaseMessagingService extends FirebaseMessagingService {

    public OilmateFirebaseMessagingService() {

    }

    @Override
    public void onNewToken(String token){
        super.onNewToken(token);
        Log.e("NEW FCM TOKEN: ", token);

        sendRegistrationToServer(token);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage){
        super.onMessageReceived(remoteMessage);

    }

    public void sendRegistrationToServer(String token){

    }
}
