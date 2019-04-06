package site.recomi.studentmanagement.gui.adapter.Base;

import android.support.v4.widget.NestedScrollView;

public abstract class BaseNestedSVOnScrollChangeListener implements NestedScrollView.OnScrollChangeListener {
    @Override
    public void onScrollChange(NestedScrollView nestedScrollView, int i, int i1, int i2, int i3) {
        if( ! nestedScrollView.canScrollVertically(1)){
            onScrolledBotton();     //滚动到底部时调用
        }
        if( ! nestedScrollView.canScrollVertically(-1)){
            onScrolledTop();     //滚动到顶端时调用
        }
    }

    /**
     * //滚动到底部时调用
     * */
    public void onScrolledBotton() {

    };

    /**
     * //滚动到顶端时调用
     * */
    public void onScrolledTop() {

    };
}
