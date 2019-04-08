package site.recomi.studentmanagement.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import site.recomi.studentmanagement.R;

public class CountWordsUtil {
    /**
     * 用于统计字数
     * */
    public static void displayCountWords(final Context context, int title, final int content){
        AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(context);
        // String
        String title_str = context.getString(R.string.title_count);
        String content_str = context.getString(R.string.content_count);
        String all_str = context.getString(R.string.all_count);
        final String display = title_str + title + '\n' + content_str + content + '\n' + all_str + (content + title);
        //
        normalDialog.setTitle(R.string.count);
        normalDialog.setMessage(display);
        normalDialog.setNegativeButton(R.string.copy,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //获取剪贴板管理器：
                        ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                        // 创建普通字符型ClipData
                        ClipData mClipData = ClipData.newPlainText("Label", display);
                        // 将ClipData内容放到系统剪贴板里。
                        cm.setPrimaryClip(mClipData);
                        Toast.makeText(context,R.string.copy_successful,Toast.LENGTH_SHORT).show();
                    }
                });
        normalDialog.setPositiveButton(R.string.back,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        // 显示
        normalDialog.show();
    }
}
