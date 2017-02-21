package jasrsir.com.rssmysql.recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import jasrsir.com.rssmysql.utils.ClickListener;

/**
 * Created by usuario on 13/02/17.
 */

public class RecyclerTouchListener  implements RecyclerView.OnItemTouchListener{
    private ClickListener clickListener;
    private GestureDetector gestureDetector;

    public RecyclerTouchListener(Context context, final RecyclerView recycleView, final ClickListener clicklistener){
        this.clickListener = clicklistener;
        gestureDetector=new GestureDetector(context,new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
            @Override
            public void onLongPress(MotionEvent e) {
                View child=recycleView.findChildViewUnder(e.getX(),e.getY());
                if(child!=null && clicklistener!=null){
                    clicklistener.onLongClick(child,recycleView.getChildAdapterPosition(child));
                }
            }
        });
    }
    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View child=rv.findChildViewUnder(e.getX(),e.getY());
        if(child!=null &&clickListener!= null && gestureDetector.onTouchEvent(e)){
            clickListener.onClick(child,rv.getChildAdapterPosition(child));
        }
        return false;
    }
    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
    }
    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
    }

}
