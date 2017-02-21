package cn.hhjjj.externalExtension;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;


import com.imcyz.imcyz.R;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * This class echoes a string called from JavaScript.
 */
public class externalExtension extends CordovaPlugin {
    
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("openMobileQQChat")) {
            String qqNumber = args.getString(0);
            this.openMobileQQChat(qqNumber, callbackContext);
            return true;
        } else if (action.equals("openURL")) {
            String url = args.getString(0);
            this.openURL(url, callbackContext);
            return true;
        }
        return false;
    }
    
    private void openMobileQQChat(String qqNumber, CallbackContext callbackContext) {
        
        if (!isQQInstall()) {
            Dialog alertDialog = new AlertDialog.Builder(this.cordova.getActivity()).
            setTitle("提示").
            setMessage("您还没有安装QQ!").
            setIcon(R.drawable.icon).
            create();
            alertDialog.show();
            return;
        }
        
        
        String url = "mqq://im/chat?chat_type=wpa&uin=" + qqNumber + "&version=1&src_type=web";
        openURL(url, callbackContext);
    }
    
    private void openURL(String url, CallbackContext callbackContext) {
        
        if (TextUtils.isEmpty(url)) {
            Dialog alertDialog = new AlertDialog.Builder(this.cordova.getActivity()).
            setTitle("提示").
            setMessage("URL为空！！！").
            setIcon(R.drawable.icon).
            create();
            alertDialog.show();
            return;
        }
        
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setAction(Intent.ACTION_VIEW);
        
        this.cordova.getActivity().startActivity(intent);
        callbackContext.success(1);
    }
    
    
    private boolean isQQInstall() {
        PackageManager mPm = this.cordova.getActivity().getPackageManager();
        
        List<PackageInfo> pinfo = mPm.getInstalledPackages(0);
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals("com.tencent.mobileqq")) {
                    return true;
                }
            }
        }
        return false;
    }
}
