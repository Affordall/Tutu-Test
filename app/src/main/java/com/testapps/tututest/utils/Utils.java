package com.testapps.tututest.utils;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;
import com.testapps.tututest.BuildConfig;
import com.testapps.tututest.R;
import com.testapps.tututest.app.TutuTestApp;

import org.jetbrains.annotations.Contract;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    private static int deviceAPI = Build.VERSION.SDK_INT;
    private static final Handler mHandler = new Handler();

    private static Context mContext = TutuTestApp.getContext();

    public static boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager
                .getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

    public static void shareIt(String title) {
        mHandler.post(() -> {
            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            sharingIntent.putExtra(Intent.EXTRA_TEXT, title);
            Intent intent = Intent.createChooser(sharingIntent, mContext.getString(R.string.share_via));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(intent);
        });
    }

    public static void launchIntentToSendFeedback(Context context) {
        mHandler.post(() -> {
            Intent it = new Intent(Intent.ACTION_SEND);
            String[] tos = {context.getString(R.string.emailAddress)};
            it.putExtra(Intent.EXTRA_EMAIL , tos);
            it.putExtra(Intent.EXTRA_SUBJECT , context.getString(R.string.emailSubject));
            it.putExtra(Intent.EXTRA_TEXT , context.getString(R.string.emailBody));
            it.setType("text/plain");

            it.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_NEW_TASK |
                    Intent.FLAG_ACTIVITY_MULTIPLE_TASK);

            try {
                context.startActivity(Intent.createChooser(it, context.getString(R.string.sendMail)));
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(context, R.string.noMailClient, Toast.LENGTH_LONG).show();
            }
        });
    }

    public static void showAboutDialog(Context context) {
        mHandler.post(() -> {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(context.getString(R.string.about_dialog_title));
        String versionName = BuildConfig.VERSION_NAME;
        builder.setMessage(context.getString(R.string.about_dialog_message) + " " + versionName);

        String positiveText = context.getString(android.R.string.ok);
        builder.setPositiveButton(positiveText,
                (dialog, which) -> {
                    dialog.dismiss();
                });
        AlertDialog dialog = builder.create();
        dialog.show();
        });
    }

    public static String convertDate(String strCurrentDate) {
        SimpleDateFormat format = new SimpleDateFormat("dd, MM, yyyy");
        Date newDate = null;
        try {
            newDate = format.parse(strCurrentDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        format = new SimpleDateFormat("dd MMM yyyy");
        return format.format(newDate);
    }

    public static boolean isEmpty(MaterialEditText etText) {
        return etText.getText().toString().trim().length() <= 0;
    }

    @Contract(pure = true)
    public static boolean isKitkat() {
        return deviceAPI >= Build.VERSION_CODES.KITKAT;
    }

    @Contract(pure = true)
    public static boolean isLollipop() {
        return deviceAPI >= Build.VERSION_CODES.LOLLIPOP;
    }

    @Contract(pure = true)
    public static boolean isMarshmallow() {
        return deviceAPI >= Build.VERSION_CODES.M;
    }

    @Contract(pure = true)
    public static boolean isNougat() {
        return deviceAPI >= Build.VERSION_CODES.N;
    }

    public static void logError(Exception e) {
        Log.e("-CHP-ERROR:", " " + e.getMessage());
    }

    public static boolean isTablet(Context context) {
        boolean xlarge = ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == 4);
        boolean large = ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE);
        return (xlarge || large);
    }
}
