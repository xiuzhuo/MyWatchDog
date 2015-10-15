package angel.zxiu.mywatchdog.listener;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;


public class OnRecyclerItemTouchListener implements RecyclerView.OnItemTouchListener {


    public interface OnFingerActionListener {
        public void onFingerMove(View view, int position);

        public void onFingerUp(View view, int position);
    }

    private OnFingerActionListener onFingerActionListener;


    public OnRecyclerItemTouchListener(Context context, OnFingerActionListener listener) {
        onFingerActionListener = listener;
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
        System.err.println(e + " onFingerActionListener=" + onFingerActionListener);
        if (onFingerActionListener!=null) {
            View childView = view.findChildViewUnder(e.getX(), e.getY());
            int position = view.getChildAdapterPosition(childView);
            switch (e.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    break;
                case MotionEvent.ACTION_MOVE:
                    break;
                case MotionEvent.ACTION_UP:
                    onFingerActionListener.onFingerUp(childView, position);
                    break;

            }
        }
        return onFingerActionListener != null;
    }

    @Override
    public void onTouchEvent(RecyclerView view, MotionEvent e) {
        System.out.println(e);
        View childView = view.findChildViewUnder(e.getX(), e.getY());
        int position = view.getChildAdapterPosition(childView);
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                onFingerActionListener.onFingerMove(childView, position);
                break;
            case MotionEvent.ACTION_UP:
                onFingerActionListener.onFingerUp(childView, position);
                break;
        }
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}