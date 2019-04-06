package site.recomi.studentmanagement.gui.adapter.Base;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

public class BaseRecyclerViewOnScrollListener extends RecyclerView.OnScrollListener {
    @Override
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);

        if( ! recyclerView.canScrollVertically(1)){
            onScrolledBotton();     //滚动到底部时调用
        }
        if( ! recyclerView.canScrollVertically(-1)){
            onScrolledTop();     //滚动到顶端时调用
        }
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
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
