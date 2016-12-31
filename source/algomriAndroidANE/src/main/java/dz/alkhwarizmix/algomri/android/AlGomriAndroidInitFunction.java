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

/**
 * <p>
 * TODO: Javadoc
 * </p>
 *
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٠٣ ربيع الثاني ١٤٣٨ (December 31, 2016)
 */
public class AlGomriAndroidInitFunction implements FREFunction {

	@Override
	public FREObject call(final FREContext context, final FREObject[] args) {
		final AlGomriAndroidExtensionContext adec = (AlGomriAndroidExtensionContext) context;
		final android.app.Activity activity = adec.getActivity();
		adec.activity = activity;
		return null;
	}

}
