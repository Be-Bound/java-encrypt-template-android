package com.bebound.template.crypto;

import android.util.Log;

import com.bebound.template.request.sendtextencrypted.RequestSendTextEncrypted;

public class CryptoUtils {

    public static String encryptDefaultKey(String content) {
        String key = "Bar12345Bar12345"; // 128 bit key
        String initVector = "RandomInitVector"; // 16 bytes IV
        String contentEncrypted = AESCrypt.encrypt(key, initVector, content);
        Log.d(RequestSendTextEncrypted.class.getCanonicalName(), "contentEncrypted = " + contentEncrypted);
        return contentEncrypted;
    }

}
