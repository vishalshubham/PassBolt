package passbolt.com.passbolt;

import android.os.Bundle;
import android.preference.PreferenceFragment;

public class RemoteSettingsFragment extends PreferenceFragment {

	@Override
	public void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.remote_preferences);
	}
	
}
