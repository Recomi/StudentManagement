package site.recomi.studentmanagement.utils;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import site.recomi.studentmanagement.R;
import site.recomi.studentmanagement.gui.activities.WriteNoteActivity;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder>{
    //此处传入的List的作用只是是利用其get方法来获取每个项的位置的.
    private List<Notes> mNotesList;

    public NotesAdapter(List<Notes> fruitList){
        mNotesList = fruitList;
    }

    //ViewHolder视图持有者类,这里的视图指的就是每一项的视图,包括整体notesView还有其元素titleView,contentView,monthView,dayView.
    public static class ViewHolder extends RecyclerView.ViewHolder {
        View notesView;
        TextView titleView,contentView,monthView,dayView;

        public ViewHolder(View itemView) {
            super(itemView);
            notesView =itemView;
            titleView = (TextView) itemView.findViewById(R.id.titleView);
            contentView = (TextView) itemView.findViewById(R.id.contentView);
            monthView = (TextView) itemView.findViewById(R.id.monthView);
            dayView = (TextView) itemView.findViewById(R.id.dayView);
        }
    }

    //此方法用于创建ViewHolder实例
    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        //静态方法from的作用是从给定的上下文获得LayoutInflater,inflate方法返回了View.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_layout , parent , false);
        //此处new了一个我们自定义的内部类ViewHolder的对象,并把我们上面得到的view作为参数传进去
        final ViewHolder viewHolder = new ViewHolder(view);
        //viewHolder.fruitView得到的是R.layout.fruit_layout布局,之后就可以利用监控实现按能
        viewHolder.notesView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = viewHolder.getAdapterPosition();
                /*get()方法的作用是返回列表中指定位置的元素,由于在此前我们设置了List的泛型为Fruit,
                所以返回的对象可以直接赋给Fruit型引用,即fruit.这样我们就可以利用获取到相应位置的fruit的
                属性来获取相应的数据.
                */
                Notes notes = mNotesList.get(position);
                Intent intent = new Intent(parent.getContext() , WriteNoteActivity.class);
                intent.putExtra("id" , notes.getId());
                parent.getContext().startActivity(intent);
            }
        });
        return  viewHolder;
    }

    //此方法用于对RecyclerVeiw子项进行赋值,每个子项滚到屏幕就会执行
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Notes notes = mNotesList.get(position);
        holder.titleView.setText(notes.getTitle());
        holder.contentView.setText(notes.getContent());
        holder.monthView.setText(notes.getMonth());
        holder.dayView.setText(notes.getDay());
    }

    //getItemCount的意思是获取项的数目
    @Override
    public int getItemCount() {
        //用于告诉RecyclerView有多少个子项.
        return mNotesList.size();
    }
}
