package juzix.com.web3jdemo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ListActivity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import web3j.ukey.BLEDeviceScanner;

public class DeviceScanActivity extends ListActivity {

    private LeDeviceListAdapter mLeDeviceListAdapter;

    private static final int REQUEST_ENABLE_BT = 1;

    private BluetoothAdapter mBluetoothAdapter;
    private BLEDeviceScanner mBleDevScanner;
    public static String EXTRA_DEVICE_ADDRESS = "device_addres";
    public static String EXTRA_DEVICE_INFO = "device_info";
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // getActionBar().setTitle(R.string.app_name);

        mBluetoothAdapter = BLEDeviceScanner
            .getBluetoothAdapter(getBaseContext());
        mBleDevScanner = new BLEDeviceScanner(getBaseContext(),null,
            new BLEDeviceScanner.IScanCallback() {
                @Override
                public void onScanBegin(BluetoothAdapter adapter) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            invalidateOptionsMenu();
                        }
                    });
                }

                @Override
                public void onDeviceFound(final BluetoothDevice device,
                                          int rssi, byte[] scanRecord) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mLeDeviceListAdapter.addDevice(device);
                            mLeDeviceListAdapter.notifyDataSetChanged();
                        }
                    });
                }

                @SuppressLint("LongLogTag")
                @Override
                public void onScanFinished(BluetoothAdapter adapter,
                                           List<BluetoothDevice> devices) {
                    for (BluetoothDevice dev : devices) {
                        Log.d("juzix -----> Device:", dev.getAddress());
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            // mLeDeviceListAdapter.addDevices(devices) ;
                            invalidateOptionsMenu();
                        }
                    });
                }

                @Override
                public void onError(int error) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            Toast.makeText(DeviceScanActivity.this,
                                "no",
                                Toast.LENGTH_SHORT).show();
                            finish();
                            return;
                        }
                    });
                }
            });

        // Use this check to determine whether BLE is supported on the device.
        // Then you can selectively disable BLE-related features.
        if (!getPackageManager().hasSystemFeature(
            PackageManager.FEATURE_BLUETOOTH_LE)) {
            Toast.makeText(this,"no",Toast.LENGTH_SHORT)
                .show();
            finish();
        }

        mBleDevScanner.scanLeDevice(true);
    }



    @Override
    protected void onResume() {
        super.onResume();

        // Ensures Bluetooth is enabled on the device. If Bluetooth is not
        // currently enabled,
        // fire an intent to display a dialog asking the user to grant
        // permission to enable it.
        // if (!mBluetoothAdapter.isEnabled())
        {
            if (!mBluetoothAdapter.isEnabled()) {
                Intent enableBtIntent = new Intent(
                    BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            }
        }

        // Initializes list view adapter.
        mLeDeviceListAdapter = new LeDeviceListAdapter();
        setListAdapter(mLeDeviceListAdapter);
//        mBleDevScanner.scanLeDevice(true);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // User chose not to enable Bluetooth.
        if (requestCode == REQUEST_ENABLE_BT
            && resultCode == Activity.RESULT_CANCELED) {
            finish();
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mBleDevScanner.inScanning())
            mBleDevScanner.scanLeDevice(false);
        mLeDeviceListAdapter.clear();
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        final BluetoothDevice device = mLeDeviceListAdapter.getDevice(position);
        if (device == null)
            return;


        if (mBleDevScanner.inScanning()) {
            mBleDevScanner.scanLeDevice(false);
        }
//        Utils.LogD( WDConstant.EXTRAS_DEVICE_ADDRESS);
        Intent intent = new Intent();

        intent.putExtra(EXTRA_DEVICE_ADDRESS,  device.getAddress());
        intent.putExtra(EXTRA_DEVICE_INFO, device.getName());
        setResult(RESULT_OK, intent);
        finish();
    }

    // Adapter for holding devices found through scanning.
    private class LeDeviceListAdapter extends BaseAdapter {
        private ArrayList<BluetoothDevice> mLeDevices;
        private LayoutInflater mInflator;

        public LeDeviceListAdapter() {
            super();
            mLeDevices = new ArrayList<BluetoothDevice>();
            mInflator = DeviceScanActivity.this.getLayoutInflater();
        }

        public void addDevice(BluetoothDevice device) {
            if (!mLeDevices.contains(device)) {
                mLeDevices.add(device);
            }
        }

        public BluetoothDevice getDevice(int position) {
            return mLeDevices.get(position);
        }

        public void clear() {
            mLeDevices.clear();
        }

        @Override
        public int getCount() {
            return mLeDevices.size();
        }

        @Override
        public Object getItem(int i) {
            return mLeDevices.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            // General ListView optimization code.
            if (view == null) {
                view = mInflator.inflate(R.layout.listitem_device, null);
                viewHolder = new ViewHolder();
                viewHolder.deviceAddress = (TextView) view
                    .findViewById(R.id.device_address);
                viewHolder.deviceName = (TextView) view
                    .findViewById(R.id.device_name);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }

            BluetoothDevice device = mLeDevices.get(i);
            final String deviceName = device.getName();
            if (deviceName != null && deviceName.length() > 0)
                viewHolder.deviceName.setText(deviceName);
            else
                viewHolder.deviceName.setText("unknow");
            viewHolder.deviceAddress.setText(device.getAddress());

            return view;
        }
    }

    static class ViewHolder {
        TextView deviceName;
        TextView deviceAddress;
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        setResult(RESULT_CANCELED, intent);
        finish();
    }
}
