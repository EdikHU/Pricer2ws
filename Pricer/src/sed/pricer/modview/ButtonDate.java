package sed.pricer.modview;

import java.util.Calendar;
import java.util.Date;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

@SuppressLint("ValidFragment")
public class ButtonDate extends Button {

	private Context thisContext;
	private static Date date = new Date();
	
	public ButtonDate(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public ButtonDate(Context context) {
		super(context);
		init(context);
	}

	private void init(Context context) {
		this.thisContext = context;
		setText(""+ getDate());
		
		this.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				DialogFragment df = new TheDialogFragment(v);
				FragmentManager manager = ((Activity)thisContext).getFragmentManager();
				df.show(manager, "");
			}
		});
	}

	public Date getDate() {
		return date;
	}

	public static void setDate(Date date) {
		ButtonDate.date = date;
	}

	private static class TheDialogFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{

		Calendar c;
		private Button parent;
		
		@SuppressLint("ValidFragment")
		public TheDialogFragment(View v) {
			parent = (Button)v;
		}

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR);
			int monthOfYear = c.get(Calendar.MONTH);
			int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);
			return new DatePickerDialog(getActivity(), this, year, monthOfYear, dayOfMonth);
		}
		
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			c.set(year, monthOfYear, dayOfMonth);
			parent.setText(""+ new Date(c.getTimeInMillis()));
			setDate(new Date(c.getTimeInMillis()));
		}
	}
	
}
