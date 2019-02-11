package com.bebound.template.request.sendtextencrypted;

import com.bebound.sdk.BeBound;
import com.bebound.sdk.engine.request.builder.RequestBuilder;
import com.bebound.template.request.sendtextencrypted.listeners.MyOnFailListener;
import com.bebound.template.request.sendtextencrypted.listeners.MyOnSuccessListener;
import com.bebound.template.request.sendtextencrypted.listeners.MySentListener;

public class RequestSendTextEncrypted {


    /**
     * Manage the request
     * @param content is added to the Be-Bound request
     */
    public static void sendRequest(String content) {
        RequestBuilder request = BeBound.newRequest()
                                        // NOTE: Add the 'operationName' from the Be-App Manifest.
                                        .withOperationName("send_encrypted")
                                        // NOTE: Add the request 'name' from the Be-App Manifest.
                                        .put("text_encrypted", content)
                                        // NOTE: Set listeners to handle the response.
                                        .onSent(MySentListener.getListener())
                                        .onFailed(MyOnFailListener.getListener())
                                        .onSuccess(MyOnSuccessListener.getListener());
        // NOTE: Sends the request.
        request.send();
    }
}
