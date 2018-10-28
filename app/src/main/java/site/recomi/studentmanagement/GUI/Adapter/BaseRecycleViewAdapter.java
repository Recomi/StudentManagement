package site.recomi.studentmanagement.gui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

public abstract class BaseRecycleViewAdapter<T> extends RecyclerView.Adapter<ViewHolder> {
    protected List<T> mList;
    protected int mlayoutId;
    protected Context mContext;
    protected LayoutInflater mInflater;


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHolder viewHolder = ViewHolder.get(mContext,parent,mlayoutId,-1);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.updatePosition(position);
        convert(holder, mList.get(position));
    }

    public abstract void convert(ViewHolder holder, T t);

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public BaseRecycleViewAdapter(Context context, List<T> lists, int layoutId){
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mList = lists;
        this.mlayoutId = layoutId;
    }
    public void setList(List<T> objectList){
        this.mList = objectList;
    }
}
