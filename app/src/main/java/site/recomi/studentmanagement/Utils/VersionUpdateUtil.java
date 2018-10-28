package site.recomi.studentmanagement.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class VersionUpdateUtil {

    public static final String APP_UPDATE_DOWNLOAD_LINK = "";
    public static final String APP_UPDATE_JSON_LINK = "";

    public boolean if_have_update(Context context) throws IOException, PackageManager.NameNotFoundException, JSONException {
        String json = get_update_json();
        int curVersionCode = get_current_versionCode(context);
        int newVersionCode = get_new_verionCode(json);
        if (newVersionCode > curVersionCode) {
            //有更新
            return false;
        }else {
            //无更新
            return false;
        }
    }

    public void show_update_info(Context context) {

    }

    public void download_update() {
//        registerDownloadCompleteReceiver();
//        DownloadEntity entity = new DownloadEntity();
//        entity.setUrl(APP_UPDATE_DOWNLOAD_LINK);
//        entity.setSubPath("JSCKitDemo"+ versionName + ".apk");
//        entity.setTitle("JSCKitDemo"+ versionName + ".apk");
//        entity.setDesc("JSCKit Library");
//        entity.setMimeType("application/vnd.android.package-archive");
//        downloadFile(entity);
    }

    public int get_current_versionCode(Context context) throws PackageManager.NameNotFoundException {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo info = packageManager.getPackageInfo(context.getPackageName(),0);
        return info.versionCode;
    }

    public int get_new_verionCode(String json) throws JSONException {
        JSONArray array = new JSONArray(json);
        JSONObject apkInfo = array.getJSONObject(0).getJSONObject("apkInfo");
        return apkInfo.getInt("versionCode");
    }

    public String get_update_json() throws IOException {
        URL url = new URL(APP_UPDATE_JSON_LINK);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5000);
        conn.setReadTimeout(5000);
        conn.setRequestMethod("GET");
        String json_str = null;
        if (conn.getResponseCode() == 200) {
            json_str = new String(NetworkUtil.read(conn.getInputStream()),"UTF-8");
        }
        return json_str;
    }
}
