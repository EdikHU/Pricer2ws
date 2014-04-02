package sed.pricer.modview;

import java.util.Date;

import android.content.Context;
import android.view.View;
import android.widget.Button;

public class ButtonDate extends Button {


	private Date date;
	private ButtonDate inst;

	public ButtonDate(Context context) {
		super(context);
		inst = this;
		this.date = new Date();
		this.setText(""+date);
		setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				System.out.println("here 1");
//				date = new Date();
//				inst.setText(""+date);
//				inst.s
			}
		});
	}

}
