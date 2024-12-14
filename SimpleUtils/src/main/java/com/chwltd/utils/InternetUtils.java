package com.chwltd.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import java.net.NetworkInterface;
import java.util.Collections;

public class InternetUtils {
  /*
  Mus类库
  */
  // 获取当前wifi下设备ipv4地址
  public static String getIpV4(Context activity) {
    int ipAddress =
        ((WifiManager) activity.getSystemService(Context.WIFI_SERVICE))
            .getConnectionInfo()
            .getIpAddress();
    return (ipAddress & 0xFF)
        + "."
        + ((ipAddress >> 8) & 0xFF)
        + "."
        + ((ipAddress >> 16) & 0xFF)
        + "."
        + (ipAddress >> 24 & 0xFF);
  }

  // 判断网络类型
  public static String getNetType(Context co) {
    NetworkInfo activeNetwork =
        ((ConnectivityManager) co.getSystemService(Context.CONNECTIVITY_SERVICE))
            .getActiveNetworkInfo();
    if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
      return "wifi";
    } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
      return "数据";
    } else {
      return "unknown";
    }
  }

  // 判断网络是否连接
  public static boolean isNetConnected(Context co) {
    NetworkInfo activeNetwork =
        ((ConnectivityManager) co.getSystemService(Context.CONNECTIVITY_SERVICE))
            .getActiveNetworkInfo();
    return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
  }

  // 判断是否连接vpn
  public static boolean isVpn() {
        boolean z = false;
        try {
            for (NetworkInterface networkInterface : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                if (networkInterface.isUp() && networkInterface.getInterfaceAddresses().size() != 0) {
                    String name = networkInterface.getName();
                    if (name.equals("tun0") || name.equals("ppp0")) {
                        z = true;
                        break;
                    }
                }
            }
        } catch (Exception e) {
        }
        return z;
    }
}
