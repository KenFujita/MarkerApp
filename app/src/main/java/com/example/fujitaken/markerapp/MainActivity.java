package com.example.fujitaken.markerapp;

import android.app.NotificationManager;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.fujitaken.markerapp.R.id.spinner;

public class MainActivity extends AppCompatActivity {

    int i=0;
    int j=0;
    ImageButton ib;
    ImageButton ib2;
    ImageButton ib3;
    ImageButton ib4;
    ImageButton ib5;
    ImageButton ib6;
    ImageButton ib7;
    ImageButton ib8;
    ImageButton ib9;
    ImageButton ibn1;
    ImageButton ibn2;
    ImageButton ibn3;
    ImageButton ibn4;
    ImageButton ibn5;
    ImageButton ibn6;
    ImageButton ibn7;
    ImageButton ibn8;
    ImageButton ibn9;
    ImageButton ibn10;

    ImageButton imagbtn;

    String item;
    SharedPreferences data;
    SharedPreferences.Editor editor;

    BluetoothAdapter mBluetoothAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ib = (ImageButton)findViewById(R.id.imageButton);
        ib2 = (ImageButton)findViewById(R.id.imageButton2);
        ib3 = (ImageButton)findViewById(R.id.imageButton3);
        ib4 = (ImageButton)findViewById(R.id.imageButton4);
        ib5 = (ImageButton)findViewById(R.id.imageButton5);
        ib6 = (ImageButton)findViewById(R.id.imageButton6);
        ib7 = (ImageButton)findViewById(R.id.imageButton7);
        ib8 = (ImageButton)findViewById(R.id.imageButton8);
        ib9 = (ImageButton)findViewById(R.id.imageButton9);
        ibn1 = (ImageButton)findViewById(R.id.imageButtonN);
        ibn2 = (ImageButton)findViewById(R.id.imageButtonN2);
        ibn3 = (ImageButton)findViewById(R.id.imageButtonN3);
        ibn4 = (ImageButton)findViewById(R.id.imageButtonN4);
        ibn5 = (ImageButton)findViewById(R.id.imageButtonN5);
        ibn6 = (ImageButton)findViewById(R.id.imageButtonN6);
        ibn7 = (ImageButton)findViewById(R.id.imageButtonN7);
        ibn8 = (ImageButton)findViewById(R.id.imageButtonN8);
        ibn9 = (ImageButton)findViewById(R.id.imageButtonN9);
        ibn10 = (ImageButton)findViewById(R.id.imageButtonN10);

        ib.setTag("wbath");          ibn1.setTag("mbath");
        ib2.setTag("208");          ibn2.setTag("207");
        ib3.setTag("209");          ibn3.setTag("206");
        ib4.setTag("wt");           ibn4.setTag("205");
        ib2.setTag("mt");           ibn5.setTag("204");
        ib2.setTag("210");          ibn6.setTag("203");
        ib2.setTag("211");          ibn7.setTag("202");
        ib2.setTag("2C");           ibn8.setTag("201");
        ib2.setTag("2D");           ibn9.setTag("2B");
                                    ibn10.setTag("2A");

        data = getSharedPreferences("DataSave", Context.MODE_PRIVATE);
        editor = data.edit();


        editor.putInt("女湯", R.id.imageButton);
        editor.putInt("208室", R.id.imageButton2);
        editor.putInt("209室", R.id.imageButton3);
        editor.putInt("女子トイレ", R.id.imageButton4);
        editor.putInt("男子トイレ", R.id.imageButton5);
        editor.putInt("210室", R.id.imageButton6);
        editor.putInt("211室", R.id.imageButton7);
        editor.putInt("2C", R.id.imageButton8);
        editor.putInt("2D", R.id.imageButton9);
        editor.putInt("男湯", R.id.imageButtonN);
        editor.putInt("207室", R.id.imageButtonN2);
        editor.putInt("206室", R.id.imageButtonN3);
        editor.putInt("205室", R.id.imageButtonN4);
        editor.putInt("204室", R.id.imageButtonN5);
        editor.putInt("203室", R.id.imageButtonN6);
        editor.putInt("202室", R.id.imageButtonN7);
        editor.putInt("201室", R.id.imageButtonN8);
        editor.putInt("2B", R.id.imageButtonN9);
        editor.putInt("2A", R.id.imageButtonN10);
        editor.apply();

        // デバイスがBLEに対応しているかを確認する.
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            Toast.makeText(this, "BLE未対応", Toast.LENGTH_SHORT).show();
        }

        //ble準備
        final BluetoothManager bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        mBluetoothAdapter = bluetoothManager.getAdapter();

        /*Bleの有効化
        if (mBluetoothAdapter == null || !mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }*/

        Spinner sp = (Spinner)findViewById(spinner);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){

                //スピナーの要素がタップされた時の処理
                if(j!=0){//初回以外
                    imagbtn.setVisibility(View.INVISIBLE);
                }
                j++;
                Spinner sp = (Spinner) parent;
                Toast.makeText(MainActivity.this,
                        String.format("選択項目: %s", sp.getSelectedItem()),
                        Toast.LENGTH_SHORT).show();
                item = (String)sp.getSelectedItem();
                int prefId = data.getInt(item,1);
                imagbtn = (ImageButton)findViewById(prefId);
                imagbtn.setImageResource(R.drawable.pin);
                imagbtn.setVisibility(View.VISIBLE);

                String selectSpName = (String) sp.getSelectedItem();
                Log.d("spinner",selectSpName);

                if(selectSpName.equals("2C")){
                    mBluetoothAdapter.startLeScan(mLeScanCallback);
                }
                else{
                    mBluetoothAdapter.stopLeScan(mLeScanCallback);
                }

            }

            public void onNothingSelected(AdapterView<?> parent){

            }
        });
    }

    BluetoothAdapter.LeScanCallback mLeScanCallback = new BluetoothAdapter.LeScanCallback() {
        @Override
        public void onLeScan(final BluetoothDevice device, int rssi, byte[] scanRecord) {
            // デバイスが検出される度に呼び出される。
            int tx_power = -60;  //電波強度(raspiと合わせている)

            /*デバッグここから*/
            String uuid = "";

            if(device.getAddress().equals("B8:27:EB:D2:76:CA")) {
                String msg = "name=" + device.getName() + ", bondStatus="
                        + device.getBondState() + ", address="
                        + device.getAddress() + ", type" + device.getType()
                        + ", uuids=" + uuid + ", rssi=" + rssi;
                Log.d("AAAA", msg);

                double distance = Math.pow(10.0, (tx_power - rssi) / 18.0);  //スマホ-raspi間の距離

                if(distance <= 30.0){
                    sendNotification();
                }

                String distance_s = String.format("%.1f", distance);

                Log.d("AAAA", distance_s + "メートル");
                /*デバッグここまで*/
            }
        }
    };

    public void onClick(View v){
        SharedPreferences data = getSharedPreferences("DataSave", Context.MODE_PRIVATE);
        if(i==0){
            ib.setImageResource(R.drawable.pin);
            ib.setVisibility(v.VISIBLE);
            i++;
        }
        else{
            ib.setVisibility(v.INVISIBLE);
            i=0;
        }
    }

    private void sendNotification(){
        NotificationCompat.Builder mBuilder =
                (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("教室到着連絡")
                        .setContentText("教室付近です")
                        .setTicker("notification is displayed !!");

        int mNotificationId = 001;

        // Gets an instance of the NotificationManager service
        NotificationManager mNotifyMgr =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        // Builds the notification and issues it.
        mNotifyMgr.notify(mNotificationId, mBuilder.build());
    }
}
