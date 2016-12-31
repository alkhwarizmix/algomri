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

import java.util.Map;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 *
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٠٣ ربيع الثاني ١٤٣٨ (December 31, 2016)
 */
public class AlGomriAndroidExtensionContext extends FREContext {

	public android.app.Activity activity;

	@Override
	public void dispose() {
		activity = null;

	}

	@Override
	public Map<String, FREFunction> getFunctions() {
		final Map<String, FREFunction> functionMap = new java.util.HashMap<String, FREFunction>();
		functionMap.put("ffiShowDialogMessage",
				new AlGomriAndroidSendSMSFunction());
		functionMap.put("ffiInit", new AlGomriAndroidInitFunction());

		return functionMap;
	}

}
