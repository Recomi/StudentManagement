package site.recomi.studentmanagement.gui.adapter.Delegetes;

import java.util.List;

import site.recomi.studentmanagement.R;
import site.recomi.studentmanagement.entity.ChattingMessage;
import site.recomi.studentmanagement.gui.adapter.MultiItemTypeSupport;

public class ChatMessageDelegete implements MultiItemTypeSupport<ChattingMessage> {

    @Override
    public int getLayoutId(int itemType) {
        switch (itemType) {
            default:
            case ChattingMessage.MINE_TEXT:
                return R.layout.item_chat_mine_text;
            case ChattingMessage.OTHER_TEXT:
                return R.layout.item_chat_other_text;
        }
    }

    @Override
    public int getItemViewType(int position, List<ChattingMessage> list) {
        //返回消息类型
        return list.get(position).getType();
    }
}
