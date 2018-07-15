package com.example.sachdeva.eleavegovernor;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.icu.util.Calendar;
import android.icu.util.TimeZone;
import android.os.Build;
import android.os.CancellationSignal;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

/**
 * Created by Sachdeva on 09-Nov-17.
 */

@RequiresApi(api = Build.VERSION_CODES.M)
public class FingerPrintHandler extends FingerprintManager.AuthenticationCallback  {
    private Context context;

    public FingerPrintHandler(Context context) {

        this.context = context;
    }

    public void startAuthentication(FingerprintManager fingerprintManager, FingerprintManager.CryptoObject cryptoObject) {

        CancellationSignal cenCancellationSignal = new CancellationSignal();
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED)
            return;
        fingerprintManager.authenticate(cryptoObject, cenCancellationSignal, 0, this, null);
    }

    @Override
    public void onAuthenticationFailed() {
        super.onAuthenticationFailed();
        ViewDialogBox.showDialogBox("Fingerprint Authentication failed!",context);
        //Toast.makeText(context, "Fingerprint Authentication failed!", Toast.LENGTH_SHORT).show();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
        super.onAuthenticationSucceeded(result);
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+05:30"));
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minutes = calendar.get(Calendar.MINUTE);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH)+1;
        int year = calendar.get(Calendar.YEAR);
        //ViewDialogBox.showDialogBox("Authentication Success  "+(month+1),context);
        //Toast.makeText(context.getApplicationContext(),"Authentication Success",Toast.LENGTH_LONG).show();
        String type = "fingerprint";
        AttendenceDBHandler attendenceDBHandler = new AttendenceDBHandler(context);
        attendenceDBHandler.execute(type,String.valueOf(hour),String.valueOf(minutes),String.valueOf(day),String.valueOf(month),String.valueOf(year));

    }
}
