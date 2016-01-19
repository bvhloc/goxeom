package asia.covisoft.goom.activity.order;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import asia.covisoft.goom.base.BaseActivity;
import asia.covisoft.goom.utils.Constant;
import asia.covisoft.goom.R;

public class OrderMadeActivity extends BaseActivity {

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_made);
        mContext = this;

        findViewById(R.id.btnCancelBooking).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });
        findViewById(R.id.btnNewBooking).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = getBaseContext().getPackageManager()
                        .getLaunchIntentForPackage(getBaseContext().getPackageName());
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra(Constant.TAB_POSTION, 1);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        //disable backPress
    }
}
