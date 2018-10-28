package site.recomi.studentmanagement.utils;


import android.Manifest;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.io.IOException;

import site.recomi.studentmanagement.entity.DownloadEntity;

public class DownloadFileUtil {
    public static final int REQUEST_CODE_DOWNLOAD_PERMISSION = 233;

    private static void checkPermissionBeforeDownload(Activity activity,final String versionName){
        //Android 6.0 以上检查是否有权限
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String permission = Manifest.permission.WRITE_EXTERNAL_STORAGE;
            int hasPermission = activity.checkSelfPermission(permission);
            // 判断权限是否开启
           if (hasPermission != PackageManager.PERMISSION_GRANTED) {
               //无权限，弹框请求开启权限
               activity.requestPermissions(new String[]{permission}, REQUEST_CODE_DOWNLOAD_PERMISSION);
           }else {
               //有权限，搞点事情
           }
        }
    }

    public void downloadApk(Context context,String versionName){
        registerDownloadCompleteReceiver(context);
        DownloadEntity entity = new DownloadEntity();
        entity.setUrl("https://raw.githubusercontent.com/JustinRoom/JSCKit/master/capture/JSCKitDemo.apk");
        entity.setSubPath("JSCKitDemo"+ versionName + ".apk");
        entity.setTitle("JSCKitDemo"+ versionName + ".apk");
        entity.setDesc("JSCKit Library");
        entity.setMimeType("application/vnd.android.package-archive");
        downloadFile(context,entity);
    }

    public final long downloadFile(Context context,DownloadEntity downloadEntity) {
        String url = downloadEntity.getUrl();
        if (TextUtils.isEmpty(url))
            return -1;

        Uri uri = Uri.parse(url);
        String subPath = downloadEntity.getSubPath();
        if (subPath == null || subPath.trim().length() == 0) {
            subPath = uri.getLastPathSegment();
        }

        File destinationDirectory = downloadEntity.getDestinationDirectory();
        if (destinationDirectory == null) {
            destinationDirectory = context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
        }

        File file = new File(destinationDirectory, subPath);
        File directory = file.getParentFile();
        if (!directory.exists()){//创建文件保存目录
            boolean result = directory.mkdirs();
            if (!result)
                Log.e("APermissionCheck", "Failed to make directories.");
        }

        if (file.exists()){
            boolean result = file.delete();
            if (!result)
                Log.e("APermissionCheck", "Failed to delete file.");
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        DownloadManager.Request request = new DownloadManager.Request(uri);
        //设置title
        request.setTitle(downloadEntity.getTitle());
        // 设置描述
        request.setDescription(downloadEntity.getDesc());
        // 完成后显示通知栏
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        //
        Uri destinationUri = Uri.withAppendedPath(Uri.fromFile(destinationDirectory), subPath);
//        Uri destinationUri = FileProviderCompat.getUriForFile(this, file);
        request.setDestinationUri(destinationUri);
//        request.setDestinationInExternalFilesDir(this, Environment.DIRECTORY_DOWNLOADS, subPath);
        request.setMimeType(downloadEntity.getMimeType());
        request.setVisibleInDownloadsUi(true);

        DownloadManager mDownloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        return mDownloadManager == null ? -1 : mDownloadManager.enqueue(request);
    }

    /**
     * 注册下载完成监听
     */
    private static void registerDownloadCompleteReceiver(Context context){
//        if (downloadReceiver == null)
//            downloadReceiver = new BroadcastReceiver() {
//                @Override
//                public void onReceive(Context context, Intent intent) {
//                    if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(intent.getAction())){
//                        unRegisterDownloadCompleteReceiver();
//                        long downloadId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
//                        findDownloadFileUri(context,downloadId);
//                    }
//                }
//            };
//        IntentFilter intentFilter = new IntentFilter();
//        intentFilter.addAction(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
//        registerReceiver(downloadReceiver, intentFilter);
    }

    /**
     * 注销下载完成监听
     */
    private static void unRegisterDownloadCompleteReceiver(){
//        if (downloadReceiver != null){
//            unregisterReceiver(downloadReceiver);
//            downloadReceiver = null;
//        }
    }

    public static Uri  findDownloadFileUri(Context context,long completeDownLoadId) {
        Uri uri = null;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            // 6.0以下
            DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
            assert  downloadManager != null;
            uri = downloadManager.getUriForDownloadedFile(completeDownLoadId);
        } else {
            File file = queryDownloadedFile(context,completeDownLoadId);
            uri = FileProviderCompat.getUriForFile(context, file);
        }
        return uri;
    }

    private static File queryDownloadedFile(Context context,long downloadId) {
        File targetFile = null;
        DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        if (downloadId != -1) {
            DownloadManager.Query query = new DownloadManager.Query();
            query.setFilterById(downloadId);
            query.setFilterByStatus(DownloadManager.STATUS_SUCCESSFUL);
            assert downloadManager != null;
            Cursor cur = downloadManager.query(query);
            if (cur != null) {
                if (cur.moveToFirst()) {
                    String uriString = cur.getString(cur.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI));
                    if (!TextUtils.isEmpty(uriString)) {
                        targetFile = new File(Uri.parse(uriString).getPath());
                    }
                }
                cur.close();
            }
        }
        return targetFile;
    }
}
