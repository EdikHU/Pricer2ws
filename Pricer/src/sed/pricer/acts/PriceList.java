package sed.pricer.acts;

import java.util.List;
import sed.pricer.R;
import sed.pricer.data.DB;
import sed.pricer.data.Price;
import sed.pricer.data.Product;
import sed.pricer.modview.ButtonDate;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ListView;

public class PriceList extends Activity implements OnClickListener{

	protected static final int DIALOG_DATE = 2673;
	private Product prod;
	private List<Price> priceList;
	private PriceListAdapter priceListAdapter;
	private Price price;
	private Context context;
	private int year;
	private int monthOfYear;
	private int dayOfMonth;
	private ButtonDate btnDate;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.context = this;
		setContentView(R.layout.price_list);
		
		btnDate = new ButtonDate(context);
		((LinearLayout)findViewById(R.id.price_list_date)).addView(btnDate);
		btnDate.setOnClickListener(new OnClickListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				System.out.println("here 2 ");
				showDialog(DIALOG_DATE);
			}
		});
		
		prod = DB.inst.getProductDao().load(((Product)getIntent().getSerializableExtra(ProductDetail.FIELD_PRICE)).getId());
		priceList = prod.getPrices();
		
		ListView lv = (ListView)findViewById(R.id.price_list_list_view);
		priceListAdapter = new PriceListAdapter(this,priceList); 
		lv.setAdapter(priceListAdapter );
		
		
	}

	@Override
	@Deprecated
	protected Dialog onCreateDialog(int id) {
		if (id == DIALOG_DATE){
			DatePickerDialog dpd = new DatePickerDialog(context, dpdCallBack, year, monthOfYear, dayOfMonth);
			return dpd;
		}
		return super.onCreateDialog(id);
	}
	
	
	OnDateSetListener dpdCallBack = new OnDateSetListener() {
		
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			btnDate.setText("["+year+"]["+monthOfYear+"]["+dayOfMonth+"]");
		}
	};
	
	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.price_list_btn_new){
			price = new Price();
			price.setProductId(prod.getId());
			DB.insert(price);
			fillViewFromPrice();
			priceList.add(price);
			DB.update(prod);
			priceListAdapter.notifyDataSetChanged();
		} else if(v.getId() == R.id.price_list_btn_modify){
//			Product tmpProd = DB.inst.getProductDao().load(prod.getId());
//			System.out.println("HERE (pricelist) * ["+tmpProd.getPrices()+"]");
			fillPriceFromView();
			System.out.println("["+prod+"]["+price+"]");
			price.setProductId(prod.getId());
			DB.update(price);
			priceList.add(price);
			DB.update(prod);
			priceListAdapter.notifyDataSetChanged();
		}
		
	}

	private void fillPriceFromView() {
		// TODO Auto-generated method stub
		
	}

	private void fillViewFromPrice() {
	}

	
}

//priceList.clear();
//Shop s = new Shop();
//s.setName("aushan3");
//DB.insert(s);
//
//Price p = new Price();
//p.setDate(new Date());
//p.setCost((float) 3.14);
//p.setShop(s);
//
//DB.insert(p);
//priceList.add(p);
//DB.update(prod);
//
//System.out.println("-----\n"+priceList);
