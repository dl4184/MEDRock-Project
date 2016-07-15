package marand.drjames;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaRecorder;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {





    private static final String LOG_TAG = "AudioRecordTest";
    private static String mFileName = null;

    private MediaRecorder mRecorder = null;

    public void AudioRecordTest() {
        mFileName = Environment.getExternalStorageDirectory().getAbsolutePath();
        mFileName += "/audiorecordtest.3gp";
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AudioRecordTest();

    }


    private void startRecording() {
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setOutputFile(mFileName);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            mRecorder.prepare();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }

        mRecorder.start();
    }

    private void stopRecording() {
        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;
    }


    private void beep(){
        ToneGenerator toneG = new ToneGenerator(AudioManager.STREAM_RING, 100);
        toneG.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 200);
    }

    public void openAboutActivity(View view){
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }

    public void buttonClick(View view){
        TextView t =(TextView) findViewById(R.id.instructions_start);
        t.setText(R.string.instruction_start2);
        TextView t2 =(TextView) findViewById(R.id.instructions);
        t2.setText(R.string.instructions2);

        final View view1 = view;

        Button button =(Button) findViewById(R.id.button);
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.scale);
        view1.startAnimation(anim);
        beep();
        startRecording();

        new Timer().schedule(new TimerTask() {

            @Override
            public void run() {
                runOnUiThread(new Runnable() {

                    @Override
                    public void run()
                    {
                        stopRecording();
                        Log.println(Log.INFO,"bla",mFileName);
                        beep();
                        Intent intent = new Intent(MainActivity.this, TestPositiveActivity.class);
                        startActivity(intent);
                    }
                });

            }
        }, 6000);



    }

}
