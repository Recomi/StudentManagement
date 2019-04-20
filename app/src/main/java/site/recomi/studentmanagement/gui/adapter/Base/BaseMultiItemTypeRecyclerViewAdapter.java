package site.recomi.studentmanagement.gui.adapter.Base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.ViewGroup;

import java.util.List;

import site.recomi.studentmanagement.gui.adapter.MultiItemTypeSupport;
import site.recomi.studentmanagement.gui.adapter.ViewHolder;

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

    private void setMultiItemTypeSupport(MultiItemTypeSupport<T> mMultiItemTypeSupport) {
        this.mMultiItemTypeSupport = mMultiItemTypeSupport;
    }
}
