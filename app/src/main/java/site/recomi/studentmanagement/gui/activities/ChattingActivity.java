package site.recomi.studentmanagement.gui.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import site.recomi.studentmanagement.R;
import site.recomi.studentmanagement.entity.ChattingMessage;
import site.recomi.studentmanagement.gui.activities.base.MySwipeBackActivity;
import site.recomi.studentmanagement.gui.adapter.Base.BaseMultiItemTypeRecyclerViewAdapter;
import site.recomi.studentmanagement.gui.adapter.Delegetes.ChatMessageDelegete;
import site.recomi.studentmanagement.gui.adapter.ViewHolder;

public class ChattingActivity extends MySwipeBackActivity {
    @BindView(R.id.recy_chatting)
    RecyclerView recy_chatting;
    ChatMessageDelegete chatMessageDelegete ;
    BaseMultiItemTypeRecyclerViewAdapter<ChattingMessage> adapter_chatting;
    List<ChattingMessage> list_message = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting);
        ButterKnife.bind(this);
        mContext = ChattingActivity.this;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_base);
        initToolbarDefaultStyle(toolbar);
        setTitle("Chartting");
        setSupportActionBar(toolbar);
        initView();
    }

    private void initView() {
        list_message.add(new ChattingMessage("我要上天","你好，请问你现在有空吗？","233",new Date(),ChattingMessage.MINE_TEXT));
        list_message.add(new ChattingMessage("我要入地","嗯，有空，有什么事吗？","233",new Date(),ChattingMessage.OTHER_TEXT));
        list_message.add(new ChattingMessage("我要上天","我们明天7点去图书馆学习吧，怎么样？","233",new Date(),ChattingMessage.MINE_TEXT));
        list_message.add(new ChattingMessage("我要入地","好的，没问题","233",new Date(),ChattingMessage.OTHER_TEXT));
        chatMessageDelegete = new ChatMessageDelegete();
        adapter_chatting = new BaseMultiItemTypeRecyclerViewAdapter<ChattingMessage>(mContext,list_message,chatMessageDelegete) {
            @Override
            public void convert(ViewHolder holder, ChattingMessage chattingMessage, int position) {
                int type = getItemViewType(position);
                switch (type) {
                    case ChattingMessage.MINE_TEXT:
                        holder.setText(R.id.tv_chat_content,chattingMessage.getContent());
                        holder.setImageResource(R.id.img_chat_headicon,R.drawable.headicon_default);
                        break;
                    case ChattingMessage.OTHER_TEXT:
                        holder.setText(R.id.tv_chat_content,chattingMessage.getContent());
                        holder.setImageResource(R.id.img_chat_headicon,R.drawable.headicon1);
                        break;
                }
            }
        };
        LinearLayoutManager manager = new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false);
        recy_chatting.setAdapter(adapter_chatting);
        recy_chatting.setLayoutManager(manager);
    }
}
