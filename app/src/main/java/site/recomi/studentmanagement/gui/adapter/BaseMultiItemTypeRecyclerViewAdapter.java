package site.recomi.studentmanagement.gui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.ViewGroup;

import java.util.List;

public abstract class BaseMultiItemTypeRecyclerViewAdapter<T> extends BaseRecycleViewAdapter<T> {
    protected MultiItemTypeSupport<T> mMultiItemTypeSupport;

    public BaseMultiItemTypeRecyclerViewAdapter(Context context, List<T> lists,MultiItemTypeSupport<T> multiItemTypeSupport) {
        super(context, lists, -1);
        this.mMultiItemTypeSupport = multiItemTypeSupport;
    }

    @Override
    public int getItemViewType(int position) {
        return mMultiItemTypeSupport.getItemViewType(position,mList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layoutId = mMultiItemTypeSupport.getLayoutId(viewType);
        return ViewHolder.get(mContext,parent,layoutId);
    }
}
