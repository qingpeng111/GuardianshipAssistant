package com.pandas.guardianshipassistant.ui.methods;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.pandas.guardianshipassistant.ui.bean.AppInfo;

import java.util.ArrayList;
import java.util.List;

/**
 *类功能  ：  扫描本地安装的 APP
 *@创建者 ： 青鹏
 * @QQ   ： 260498491
 *@date  ： 2020/11/18 20:28
 */
public class AppTool {
    static  String TAG = "ApkTool";
    public static List<AppInfo> mLocalInstallApps = null;

    public static List<AppInfo> scanLocalInstallAppList(PackageManager packageManager) {
        List<AppInfo> myAppInfos = new ArrayList<AppInfo>();
        try {
            List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
            for (int i = 0; i < packageInfos.size(); i++) {
                PackageInfo packageInfo = packageInfos.get(i);
                //过滤掉系统app
                if ((ApplicationInfo.FLAG_SYSTEM & packageInfo.applicationInfo.flags) != 0) {
                    continue;
                }
                AppInfo myAppInfo = new AppInfo();
//                myAppInfo.setName(packageInfo.packageName);
                myAppInfo.setName(packageInfo.applicationInfo.loadLabel(packageManager).toString());
                if (packageInfo.applicationInfo.loadIcon(packageManager) == null) {
                    continue;
                }
                myAppInfo.setImage(packageInfo.applicationInfo.loadIcon(packageManager));
                myAppInfos.add(myAppInfo);

            }
        }catch (Exception e){
            Log.e(TAG,"===============获取应用包信息失败");
        }
        return myAppInfos;
    }

}