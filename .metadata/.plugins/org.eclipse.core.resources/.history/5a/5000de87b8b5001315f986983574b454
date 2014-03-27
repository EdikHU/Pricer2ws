package sed.pricer;

import java.util.concurrent.TimeUnit;

import sed.pricer.acts.ActProdCardList;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class Start extends Activity {

	private Start context;
	private int hehe;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
		context = this;

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
		
		new AsyncTask<Void, String, Void>(){

			@Override
			protected Void doInBackground(Void... params) {
				hehe = 2;
				try {
					for (int i=0;i<hehe ;i++){
						TimeUnit.SECONDS.sleep(1);
						publishProgress(new String[]{""+i});
					}
				} catch (InterruptedException e) {
				}
				return null;
			}

			@Override
			protected void onPostExecute(Void result) {
				super.onPostExecute(result);
				context.finish();
				//if (hehe != 0) 
				startActivity(new Intent(context,ActProdCardList.class));
			}
			
			protected void onProgressUpdate(String... s) {
				 ((TextView)context.findViewById(R.id.tv)).setText(s[0]); ;
			};
			
		}.execute();

	}

	public void onClick(View v){
//		context.finish();
		hehe = 0;
//		startActivity(new Intent(context,ActProdCardList.class));
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.start, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_start,
					container, false);
			return rootView;
		}
	}

}
