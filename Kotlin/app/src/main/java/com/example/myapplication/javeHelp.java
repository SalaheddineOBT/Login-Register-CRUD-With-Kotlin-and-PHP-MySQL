package com.example.myapplication;

import android.content.Context;
import android.content.pm.*;
import android.content.pm.Signature;
import android.util.*;
import java.security.*;

public class javeHelp {

    public static void printHashKey(Context pContext) {
        try {
            PackageInfo info = pContext.getPackageManager().getPackageInfo(
                    pContext.getPackageName(),
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String hashKey = new String(Base64.encode(md.digest(), 0));
                Log.i("KeyHash", "printHashKey() Hash Key: " + hashKey);
            }
        } catch (NoSuchAlgorithmException e) {
            Log.e("KeyHash", "printHashKey()", e);
        } catch (Exception e) {
            Log.e("KeyHash", "printHashKey()", e);
        }
    }

}
