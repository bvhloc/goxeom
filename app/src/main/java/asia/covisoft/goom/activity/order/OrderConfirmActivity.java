package asia.covisoft.goom.activity.order;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import asia.covisoft.goom.ActivityAnim;
import asia.covisoft.goom.R;

public class OrderConfirmActivity extends AppCompatActivity {

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirm);
        mContext = this;

        findViewById(R.id.btnOrder).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(mContext, OrderMadeActivity.class));
                ActivityAnim.forward(mContext);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ActivityAnim.back(mContext);
    }
}
