package com.fit2081.week6labtask;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;
import android.telephony.SmsMessage;

public class SMSReceiver extends BroadcastReceiver {
    public static final String SMS_FILTER = "SMS_FILTER";
    public static final String SMS_MSG_KEY = "SMS_MSG_KEY";
    //Second Method for SMS
    //TokenizerInterface handler;

    //Second Method for SMS
    //public SMSReceiver(TokenizerInterface handler){this.handler=handler}
    /*
     * This method 'onReceive' will be invoked with each new incoming SMS
     * */
    @Override
    public void onReceive(Context context, Intent intent) {
        /*
         * Use the Telephony class to extract the incoming messages from the intent
         * */
        SmsMessage[] messages = Telephony.Sms.Intents.getMessagesFromIntent(intent);
        for (int i = 0; i < messages.length; i++) {
            SmsMessage currentMessage = messages[i];
            String message = currentMessage.getDisplayMessageBody();

            //Second Method for SMS
            //handler.sendData(message);
            /*
             * Now, for each new message, send a broadcast contains the new message to MainActivity
             * The MainActivity has to tokenize the new message and update the UI
             * */
            Intent msgIntent = new Intent();
            msgIntent.setAction(SMS_FILTER);
            msgIntent.putExtra(SMS_MSG_KEY, message);
            context.sendBroadcast(msgIntent);
        }
    }
}