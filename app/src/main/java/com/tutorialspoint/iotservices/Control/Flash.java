package com.tutorialspoint.iotservices.Control;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import com.tutorialspoint.iotservices.View.IOTActivity;

public class Flash {
    static Camera cam;
    public static Boolean flashState = false;


    public static boolean turnOff(Context context) {


        if (!hasFlashFeature(context)) {
            return false;
        }

        cam.stopPreview();
        cam.release();
        flashState = false;
        return true;

    }

    public static boolean turnOn(Context context) {


        if (!hasFlashFeature(context)) {
            return false;
        }


        cam = Camera.open();
        Camera.Parameters p = cam.getParameters();
        p.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
        cam.setParameters(p);
        cam.startPreview();
        flashState = true;
        return true;
    }

    public static void toggle(Context context) {
        if (flashState)
            turnOff(context);
        else
            turnOn(context);

    }

    public static boolean hasFlashFeature(Context context) {
        if (!context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {
            Toast.makeText(context, "Mobile Has No Flash", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;

    }

    public static boolean checkPermission(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String[] permissions = {Manifest.permission.CAMERA};
            activity.requestPermissions(permissions, 1);
        }
        return true;
    }
}