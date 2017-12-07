package com.jorgesys.preferencefragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Display the fragment as the main content.
        FragmentManager mFragmentManager = getFragmentManager();
        FragmentTransaction mFragmentTransaction = mFragmentManager
                .beginTransaction();
        PrefsFragment mPrefsFragment = new PrefsFragment();
        mFragmentTransaction.replace(android.R.id.content, mPrefsFragment);
        mFragmentTransaction.commit();

    }

    public static class PrefsFragment extends PreferenceFragment {

        private int audio;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            // Load the preferences from an XML resource
            addPreferencesFromResource(R.xml.preferences);

            //Check the current value in preference.
            SharedPreferences switchPrefStatus = PreferenceManager.getDefaultSharedPreferences(getActivity());
            boolean switchPrefValue = switchPrefStatus.getBoolean("audio_switch", false);
            Toast.makeText(getActivity(), "Current value: " + switchPrefValue, Toast.LENGTH_SHORT).show();


            Preference switchPref = (Preference) findPreference("audio_switch");
            switchPref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object o) {

                    boolean isOn = (boolean) o;

                    //Do what you want with the value!:)
                    if (isOn) {
                        audio = 1;
                    }else{
                        audio = 0;
                    }

                    Toast.makeText(getActivity(), "Preference value changed to : " + isOn, Toast.LENGTH_SHORT).show();

                    return true;
                }
            });
        }



    }


}
