package com.example.admin.xmltest.Vietnamese.BangChuCai;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.admin.xmltest.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class BangChuCai extends AppCompatActivity implements TextToSpeech.OnInitListener {

    DatabaseReference mDatabase;
    ListView lvtest;
    ArrayAdapter adapter=null;
    private ViewPager mVPalpha;
    BCCAdapter bccAdapter ;
    ArrayList<BCC> mlist;
    ArrayList<BCC> mlist1;
    private TextToSpeech tts;
    private Button btnSound;
    private TextView tvAlpha;
    private String textt;
    private int mCurrentPageIndex;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bang_chu_cai);
        tts = new TextToSpeech(this, this);

        btnSound = (Button) findViewById(R.id.btnSound);
        tvAlpha = (TextView) findViewById(R.id.tvAlpha);

        mlist = new ArrayList<BCC>();
        mlist1 = new ArrayList<BCC>();
        bccAdapter = new BCCAdapter(this, R.layout.item_bangchucai, mlist);
        mVPalpha = (ViewPager) findViewById(R.id.vpAlpha);
        mVPalpha.setAdapter(bccAdapter);
        mVPalpha.setCurrentItem(mCurrentPageIndex);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Alphabet").child("Phát âm").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot dt : dataSnapshot.getChildren()){
                    BCC bcc = dt.getValue(BCC.class);
                    mlist.add(bcc);

                }
                bccAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        mVPalpha.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mCurrentPageIndex = position;

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        // button on click event
        btnSound.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                BCC alpha = mlist.get(mCurrentPageIndex);
                textt = alpha.getContent();
                speak(alpha.getContent());
            }
        });
    }

    @Override
    public void onDestroy() {
        // Don't forget to shutdown tts!
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

    @Override
    public void onInit(int status) {

        if (status == TextToSpeech.SUCCESS) {

            int result = tts.setLanguage(Locale.getDefault());

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported");
            } else {
                btnSound.setEnabled(true);
                speak(textt);
            }

        } else {
            Log.e("TTS", "Initilization Failed!");
        }

    }


    public void speak(String text) {
        if (tts != null) {

            if (tts.isSpeaking()) {
                tts.stop();
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                ttsGreater21(text);
            } else {
                ttsUnder20(text);
            }

        }
    }

    @SuppressWarnings("deprecation")
    private void ttsUnder20(String text) {
        HashMap<String, String> map = new HashMap<>();
        map.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "MessageId");
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, map);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void ttsGreater21(String text) {
        String utteranceId = this.hashCode() + "";

        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, utteranceId);
    }
}
