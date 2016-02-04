package asia.covisoft.goom.helper;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class PhoneHelper {

    private Context context;

    public PhoneHelper(Context context) {
        this.context = context;
    }

    public void dial(String phoneNumber) {

        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
        context.startActivity(intent);
    }

    public void message(String phoneNumber, String message) {

        Intent sendIntent = new Intent(Intent.ACTION_VIEW);
        sendIntent.setData(Uri.parse("sms:" + phoneNumber));
        sendIntent.putExtra("sms_body", message);
        context.startActivity(sendIntent);
    }
}
