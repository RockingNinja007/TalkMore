package com.example.vikasratre.talkMore.services;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Vikas Ratre on 11/28/2017.
 */

public class MyFirebaseInstanceIdService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        //super.onTokenRefresh();

        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        //Log.d(TAG, "Refreshed token: " + refreshedToken);
        sendRegistrationToServer(refreshedToken);
    }

    public String sendRegistrationToServer(String toketString){
        return toketString;
    }

}
