package com.rickykyle.oilmate.firebase;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/*
 * This class generates Firebase tokens when the user first installs Oilmate on
 * their device or when they reinstall or update the application.  Firebase
 * implementation was learned from this tutorial:
 * https://www.techotopia.com/index.php/Firebase_Cloud_Messaging
 */
public class OilmateFirebaseMessagingService extends FirebaseMessagingService {

    public OilmateFirebaseMessagingService() {

    }

    @Override
    public void onNewToken(String token){
        super.onNewToken(token);
        Log.e("NEW FCM TOKEN: ", token);

    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage){
        super.onMessageReceived(remoteMessage);

    }

}
