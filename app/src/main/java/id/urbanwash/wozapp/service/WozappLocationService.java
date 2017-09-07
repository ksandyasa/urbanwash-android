package id.urbanwash.wozapp.service;

import android.Manifest;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by apridosandyasa on 3/30/16.
 */
public class WozappLocationService extends Service implements LocationListener {
    private static final String TAG = WozappLocationService.class.getSimpleName();

    protected LocationManager mLocationGPSManager;
    protected LocationManager mLocationNetworkManager;

    private Location mLastLocation;
    private Location mNetworkLocation;

    boolean isGPSEnabled = false;

    // flag for network status
    boolean isNetworkEnabled = false;

    // boolean flag to toggle periodic location updates
    private boolean mRequestingLocationUpdates = false;

    // Location updates intervals in sec
    private static int UPDATE_INTERVAL = 5000; // 10 sec
    private static int FATEST_INTERVAL = 2500; // 5 sec
    private static int DISPLACEMENT = 10; // 10 meters

    private Messenger messageHandler;

    @Override
    public void onLocationChanged(Location location) {
        //mLastLocation = location;
        mNetworkLocation = location;

        Log.i(TAG, "Location changed!");

        //sendMessageToActivity(101, mLastLocation);
        sendMessageToActivity(101, mNetworkLocation);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d(TAG,
                "Provider " + provider + " are changed, Status " + status);

    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d(TAG,
                "Provider " + provider + " are enabled");
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d(TAG,
                "Provider " + provider + " are disabled");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public IBinder onUnBind(Intent arg0) {
        // TO DO Auto-generated method
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        createLocationRequest();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Bundle bundleExtras = intent.getExtras();
        messageHandler = (Messenger) bundleExtras.get("MESSENGER");
        if (mNetworkLocation != null)
            sendMessageToActivity(101, mNetworkLocation);

        return START_STICKY;
    }

    private void createLocationRequest() {
        try {
            this.mLocationNetworkManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
            this.mLocationGPSManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);

            isGPSEnabled = this.mLocationGPSManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            Log.d(TAG,
                    "GPS " + isGPSEnabled);

            isNetworkEnabled = this.mLocationNetworkManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            Log.d(TAG,
                    "Network " + isNetworkEnabled);

            if (!isGPSEnabled && !isNetworkEnabled) {
                Toast.makeText(getApplicationContext(),
                        "GPS & Network is disabled!",
                        Toast.LENGTH_SHORT).show();
            } else {
                /*
                if (isGPSEnabled) {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }

                    this.mLocationGPSManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                            0,
                            0,
                            this);
                    Log.d(TAG,
                            "request Location from GPS");

                    displayLocationFromGPS();
                } else{
                    Log.d(TAG,
                            "GPS is disabled");
                }*/

                if (isNetworkEnabled) {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }

                    this.mLocationNetworkManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                            0,
                            0,
                            this);
                    Log.d(TAG,
                            "request Location from Network");

                    displayLocationFromNetwork();
                } else{
                    Log.d(TAG,
                            "Network is disabled");
                }

            }
        } catch (Exception e) {
        }
    }

    private void displayLocationFromNetwork() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        if (isNetworkEnabled) {
            this.mNetworkLocation = this.mLocationNetworkManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            Log.d(TAG,
                    "Network Latitude " + this.mNetworkLocation.getLatitude() + ", " +
                            "Network Longitude" + this.mNetworkLocation.getLongitude());


            sendMessageToActivity(101, this.mNetworkLocation);
        }
    }

    private void displayLocationFromGPS() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        if (isGPSEnabled) {
            this.mLastLocation = this.mLocationGPSManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            Log.d(TAG,
                    "GPS Latitude " + this.mLastLocation.getLatitude() + ", " +
                            "GPS Longitude " + this.mLastLocation.getLongitude());

            sendMessageToActivity(101, this.mLastLocation);
        }
    }

    private void sendMessageToActivity(int state, Location location) {
        Message message = Message.obtain();
        switch (state) {
            case 101:
                Bundle bundleData = new Bundle();
                bundleData.putDouble("LATITUDE", location.getLatitude());
                bundleData.putDouble("LONGITUDE", location.getLongitude());
                message.arg1 = 101;
                message.setData(bundleData);
                try {
                    messageHandler.send(message);
                } catch (RemoteException e) {
                    Log.d(TAG, "Error send message " + e.getMessage());
                }

                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (this.mLocationNetworkManager != null) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            this.mLocationNetworkManager.removeUpdates(this);
        }

        if (this.mLocationGPSManager != null) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            this.mLocationGPSManager.removeUpdates(this);
        }

    }

}
