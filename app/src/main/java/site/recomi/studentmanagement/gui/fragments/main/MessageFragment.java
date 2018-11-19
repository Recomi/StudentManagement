package site.recomi.studentmanagement.gui.fragments.main;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import site.recomi.studentmanagement.R;
import site.recomi.studentmanagement.entity.Message;
import site.recomi.studentmanagement.gui.adapter.BaseRecycleViewAdapter;
import site.recomi.studentmanagement.gui.adapter.ViewHolder;

public class MessageFragment extends Fragment {
    //
    Context mContext;
    @BindView(R.id.recy_messageList)
    RecyclerView recycler;
//    @BindView(R.id.tv_sender)
//    TextView sender;
//    @BindView(R.id.img_headicon)
//    ImageView headicon;
//    @BindView(R.id.tv_content)
//    TextView content;
//    @BindView(R.id.tv_time)
//    TextView time;
    //
    private ArrayList<Message> messages;
    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, container , false);
        mContext = view.getContext();
        unbinder = ButterKnife.bind(this,view);
        initView();
        return view;

    }

    private void initView() {
        messages = new ArrayList<>();
        messages.add(new Message("林海志",R.drawable.headicon1,"今天有空吗？",new Date()));
        messages.add(new Message("林海志",R.drawable.headicon1,"今天有空吗？",new Date()));
        messages.add(new Message("林海志",R.drawable.headicon1,"今天有空吗？",new Date()));
        messages.add(new Message("林海志",R.drawable.headicon1,"今天有空吗？",new Date()));
        messages.add(new Message("林海志",R.drawable.headicon1,"今天有空吗？",new Date()));
        messages.add(new Message("林海志",R.drawable.headicon1,"今天有空吗？",new Date()));
        messages.add(new Message("林海志",R.drawable.headicon1,"今天有空吗？",new Date()));
        messages.add(new Message("林海志",R.drawable.headicon1,"今天有空吗？",new Date()));
        messages.add(new Message("林海志",R.drawable.headicon1,"今天有空吗？",new Date()));
        messages.add(new Message("林海志",R.drawable.headicon1,"今天有空吗？",new Date()));
        messages.add(new Message("林海志",R.drawable.headicon1,"今天有空吗？",new Date()));
        BaseRecycleViewAdapter<Message> adapter = new BaseRecycleViewAdapter<Message>(mContext,messages,R.layout.list_message_list) {
            @Override
            public void convert(ViewHolder holder, Message message) {
                Calendar calendar = GregorianCalendar.getInstance();
                calendar.setTime(message.getReceivingTime());
                String time = calendar.get(Calendar.HOUR_OF_DAY) + ":"
                        + calendar.get(Calendar.MINUTE);
                holder.setText(R.id.tv_sender,message.getSender());
                holder.setText(R.id.tv_content,message.getContent());
                holder.setImageResource(R.id.img_headicon,message.getHeadiconRes());
                holder.setText(R.id.tv_time,time);
            }
        };
        LinearLayoutManager manager = new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false);
        recycler.setLayoutManager(manager);
        recycler.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
