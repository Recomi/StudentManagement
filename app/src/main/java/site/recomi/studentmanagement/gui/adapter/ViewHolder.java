package site.recomi.studentmanagement.gui.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewHolder extends RecyclerView.ViewHolder {
    private Context mContext;
    private View mConvertView;
    private SparseArray<View> mViews;
    private int mPosition;

    /**
     * 判断该position对应的位置是要固定
     *
     * @param position adapter position
     * @return true or false
     */

    /**
     * */
    public ViewHolder(Context context, View itemView
                        , ViewGroup parent, int position) {
        super(itemView);
        mContext = context;
        mConvertView = itemView;
        mViews = new SparseArray<View>();
        mPosition = position;
    }
    /**
     * */
    public static ViewHolder get(Context context, ViewGroup parent
                                    , int layoutId, int position) {
        View itemView = LayoutInflater
                .from(context)
                .inflate(layoutId, parent,false);
        return new ViewHolder(context,itemView,parent,position);
    }
    /**
     * */
    public ViewHolder setText(int viewId,String text){
        TextView textView = getView(viewId);
        textView.setText(text);
        return this;
    }
    /**
     * */
    public ViewHolder setTextSize(int viewId,Float size){
        TextView textView = getView(viewId);
        textView.setTextSize(size);
        return this;
    }
    /**
     * */
    public ViewHolder setImageResource(int viewId,int resId){
        ImageView imageView = getView(viewId);
        imageView.setImageResource(resId);
        return this;
    }
    /**
     * */
    public ViewHolder setImageHeight(int viewId,int height){
        ImageView imageView = getView(viewId);
        imageView.setMaxHeight(height);
        return this;
    }
    /**
     * */
    public ViewHolder setOnClickListener(int viewId,View.OnClickListener listener){
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }
    /**
     * 通过viewId获取控件
     *
     * @param viewId
     * @return
     */
    public <T extends View> T getView(int viewId){
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }
    /**
     * */
    public void updatePosition(int position){
        this.mPosition = position;
    }
}
