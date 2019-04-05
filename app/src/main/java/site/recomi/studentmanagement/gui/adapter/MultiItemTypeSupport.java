package site.recomi.studentmanagement.gui.adapter;

import java.util.List;

public interface MultiItemTypeSupport<T> {
    int getLayoutId(int itemType);

    int getItemViewType(int position, List<T> list);
}