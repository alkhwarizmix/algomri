////////////////////////////////////////////////////////////////////////////////
//  بسم الله الرحمن الرحيم
//
//  حقوق التأليف والنشر ١٤٣٨ هجري، فارس بلحواس (Copyright 2016 Fares Belhaouas)
//  كافة الحقوق محفوظة (All Rights Reserved)
//
//  NOTICE: Fares Belhaouas permits you to use, modify, and distribute this file
//  in accordance with the terms of the license agreement accompanying it.
//
////////////////////////////////////////////////////////////////////////////////

package dz.alkhwarizmix.algomri.android;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.telephony.SmsManager;
import android.widget.Toast;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 *
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٠٣ ربيع الثاني ١٤٣٨ (December 31, 2016)
 */
public class AlGomriAndroidSendSMSFunction implements FREFunction {

	@Override
	public FREObject call(final FREContext context, final FREObject[] args) {
		final AlGomriAndroidExtensionContext adec = (AlGomriAndroidExtensionContext) context;
		// final android.app.AlertDialog.Builder builder = new
		// android.app.AlertDialog.Builder(
		// adec.activity);
		try {
			// final String message = args[0].getAsString();
			// builder.setMessage(message).setNeutralButton("OK", null).show();
			sendSMS(adec.activity);
		} catch (final Exception e) {
			android.util.Log.e("AIR_AndroidDialog", e.getMessage());
		}
		return null;
	}

	// --sends an SMS message to another device---
	private void sendSMS(final Activity activity) {
		String phoneNumber = ""; // txtphoneNo.getText().toString();
		if (phoneNumber == "")
			phoneNumber = "+15146123375";
		String message = ""; // txtMessage.getText().toString();
		if (message == "")
			message = "Salam alaykoum 18:00";
		showMessage(activity, "Send " + message + " to " + phoneNumber);

		final String SENT = "SMS_SENT" + 123368;
		final String DELIVERED = "SMS_DELIVERED" + 123386;

		final PendingIntent sentPI = PendingIntent.getBroadcast(activity, 0,
				new Intent(SENT), 0);

		final PendingIntent deliveredPI = PendingIntent.getBroadcast(activity,
				0, new Intent(DELIVERED), 0);

		// ---when the SMS has been sent---
		activity.registerReceiver(new BroadcastReceiver() {
			@Override
			public void onReceive(final Context arg0, final Intent arg1) {
				switch (getResultCode()) {
				case Activity.RESULT_OK:
					showMessage(activity, "SMS sent");
					break;
				case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
					showMessage(activity, "Generic failure");
					break;
				case SmsManager.RESULT_ERROR_NO_SERVICE:
					showMessage(activity, "No service");
					break;
				case SmsManager.RESULT_ERROR_NULL_PDU:
					showMessage(activity, "Null PDU");
					break;
				case SmsManager.RESULT_ERROR_RADIO_OFF:
					showMessage(activity, "Radio off");
					break;
				}
			}
		}, new IntentFilter(SENT));

		// ---when the SMS has been delivered---
		activity.registerReceiver(new BroadcastReceiver() {
			@Override
			public void onReceive(final Context arg0, final Intent arg1) {
				switch (getResultCode()) {
				case Activity.RESULT_OK:
					showMessage(activity, "SMS delivered");
					break;
				case Activity.RESULT_CANCELED:
					showMessage(activity, "SMS not delivered");
					break;
				}
			}
		}, new IntentFilter(DELIVERED));

		final SmsManager sms = SmsManager.getDefault();
		sms.sendTextMessage(phoneNumber, null, message, sentPI,
				deliveredPI);
	}

	private void showMessage(final Activity activity, final String message) {
		Toast.makeText(activity.getBaseContext(), message, Toast.LENGTH_SHORT)
				.show();
	}

}
