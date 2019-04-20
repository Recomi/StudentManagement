package site.recomi.studentmanagement.gui.fragments.main;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import site.recomi.studentmanagement.R;
import site.recomi.studentmanagement.entity.Message;
import site.recomi.studentmanagement.gui.activities.ChattingActivity;
import site.recomi.studentmanagement.gui.adapter.Base.BaseRecycleViewAdapter;
import site.recomi.studentmanagement.gui.adapter.ViewHolder;
import site.recomi.studentmanagement.gui.fragments.Base.BaseFragment;
import site.recomi.studentmanagement.gui.listenner.BaseRecyclerItemTouchListener;

public class MessageFragment extends BaseFragment {
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
        messages.add(new Message("温柔",R.drawable.headicon1,"今天有空吗","5/3 9:50"));
        messages.add(new Message("牵强的小草",R.drawable.headicon_default,"今天一起去图书馆学习","5/3 8:10"));
        messages.add(new Message("团长",R.drawable.test13,"明天来社团这边组织一下活动","5/2 1:00"));
        messages.add(new Message("古兄",R.drawable.testbook2,"你可真是个小机灵鬼","5/1 2:00"));
        BaseRecycleViewAdapter<Message> adapter = new BaseRecycleViewAdapter<Message>(mContext,messages,R.layout.list_message_list) {
            @Override
            public void convert(ViewHolder holder, Message message,int position) {
//                Calendar calendar = GregorianCalendar.getInstance();
//                calendar.setTime(message.getReceivingTime());

//                String time = calendar.get(Calendar.HOUR_OF_DAY) + ":"
//                        + calendar.get(Calendar.MINUTE);
                
                holder.setText(R.id.tv_sender,message.getSender());
                holder.setText(R.id.tv_content,message.getContent());
                holder.setImageResource(R.id.img_headicon,message.getHeadiconRes());
                holder.setText(R.id.tv_time,message.getReceivingTime());
            }
        };
        recycler.addOnItemTouchListener(new BaseRecyclerItemTouchListener(mContext,new BaseRecyclerItemTouchListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivityOnly(mContext, ChattingActivity.class);
            }

            @Override
            public void onLongClick(View view, int posotion) {

            }
        }));
        LinearLayoutManager manager = new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false);
        recycler.setLayoutManager(manager);
        recycler.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
