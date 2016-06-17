package com.krislq.floating;

import android.app.Application;
import android.view.WindowManager;
/**
 * 
 * @author 挨踢人生
 * @website www.glmei.cn
 * @date Nov 29, 2014
 * @version 1.0.0
 *
 */
public class FloatApplication extends Application {
	private WindowManager.LayoutParams windowParams = new WindowManager.LayoutParams();

	public WindowManager.LayoutParams getWindowParams() {
		return windowParams;
	}
}
