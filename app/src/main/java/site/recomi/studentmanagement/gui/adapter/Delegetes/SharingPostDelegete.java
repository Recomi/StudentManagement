package site.recomi.studentmanagement.gui.adapter.Delegetes;

import java.util.List;

import site.recomi.studentmanagement.R;
import site.recomi.studentmanagement.entity.UserSharingPost;
import site.recomi.studentmanagement.gui.adapter.MultiItemTypeSupport;

public class SharingPostDelegete implements MultiItemTypeSupport<UserSharingPost> {
    private final int SHARING_POST = 0;
    private final int LOAD_MORE = 1;

    @Override
    public int getLayoutId(int itemType) {
        switch (itemType) {
            default:
            case SHARING_POST:
                return R.layout.item_user_sharing_post;
            case LOAD_MORE:
                return R.layout.item_loadmore;
        }
    }

    @Override
    public int getItemViewType(int position, List<UserSharingPost> list) {
        if (position == list.size() -1 ) {
            return LOAD_MORE;
        }else {
            return SHARING_POST;
        }
    }
}
