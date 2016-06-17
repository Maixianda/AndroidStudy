package com.krislq.floating;

import android.content.Context;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.ImageView;
/**
 * 
 * @author 挨踢人生
 * @website www.glmei.cn
 * @date Nov 29, 2014
 * @version 1.0.0
 *
 */
public class FloatView extends ImageView{
	//region 静态变量
	private static FloatView mFloatView = null;
	//endregion 静态变量
	//region 成员变量
	private Context mContext = null;
	//endregion 成员变量
	/**
	 * 组件左上角为原点的x
	 */
	private float mTouchX;
	/**
	 * 组件左上角为原点的y
	 */
	private float mTouchY;
	/**屏幕左上角为原点的x */
	private float x;
	/**
	 * 屏幕左上角为原点的y
	 */
	private float y;
	private float mStartX;
	private float mStartY;
	private OnClickListener mClickListener;

	private WindowManager windowManager = (WindowManager) getContext()
			.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
	// 此windowManagerParams变量为获取的全局变量，用以保存悬浮窗口的属性
	private WindowManager.LayoutParams windowManagerParams = ((FloatApplication) getContext()
			.getApplicationContext()).getWindowParams();

	public FloatView(Context context) {
		super(context);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		//获取到状态栏的高度
		Rect frame =  new  Rect();  
		getWindowVisibleDisplayFrame(frame);
		int  statusBarHeight = frame.top - 48; 
		System.out.println("statusBarHeight:"+statusBarHeight);
		// 获取相对屏幕的坐标，即以屏幕左上角为原点
		x = event.getRawX();
		y = event.getRawY() - statusBarHeight; // statusBarHeight是系统状态栏的高度
		Log.i("tag", "currX" + x + "====currY" + y);
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN: // 捕获手指触摸按下动作
			// 获取相对View的坐标，即以此View左上角为原点
			mTouchX = event.getX();
			mTouchY = event.getY();
			mStartX = x;
			mStartY = y;
			Log.i("tag", "startX" + mTouchX + "====startY"
					+ mTouchY);
			break;

		case MotionEvent.ACTION_MOVE: // 捕获手指触摸移动动作
			updateViewPosition();
			break;

		case MotionEvent.ACTION_UP: // 捕获手指触摸离开动作
			updateViewPosition();
			mTouchX = mTouchY = 0;
			if ((x - mStartX) < 5 && (y - mStartY) < 5) {
				if(mClickListener!=null) {
					mClickListener.onClick(this);
				}
			}
			break;
		}
		return true;
	}
	@Override
	public void setOnClickListener(OnClickListener l) {
		this.mClickListener = l;
	}

	private static final String TAG = "FloatView";
	private void updateViewPosition() {
		// 更新浮动窗口位置参数
		DisplayMetrics dm = new DisplayMetrics();
		windowManager.getDefaultDisplay().getMetrics(dm);
		windowManagerParams.x = (int) (dm.widthPixels-x);
		windowManagerParams.y = (int) (dm.heightPixels-y);
		Log.d(TAG, "updateViewPosition: 屏幕左上角x:"+ x +
				"组件左上角touchx"+mTouchX+
				"差"+windowManagerParams.x+
				"屏幕左上角y"+y+
				"屏幕左上角touchy"+mTouchY+"差"+windowManagerParams.y);
		windowManager.updateViewLayout(this, windowManagerParams); // 刷新显示
	}

	protected void setContext(Context context)
	{
		mContext = context;
	}

	/**
	 * 创建可拖动浮动按钮
	 * @param context 要加入浮动按钮的Context
	 * @param onClickListener 浮动按钮的点击事件
	 * @param nResId 浮动按钮的图片资源ID
     */
	public static void CreateFloatingView(Context context,OnClickListener onClickListener,int nResId){
		if (null ==mFloatView)
		{
			mFloatView = new FloatView(context.getApplicationContext());
		}
		mFloatView.setContext(context);
		mFloatView.setOnClickListener(onClickListener);
		mFloatView.setImageResource(R.drawable.ic_launcher);
	}
}
