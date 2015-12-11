package cn.guolf.cordova.plugins;

import android.app.ProgressDialog;

import org.apache.cordova.api.CallbackContext;
import org.apache.cordova.api.CordovaPlugin;
import org.apache.cordova.api.PluginResult;
import org.json.*;

public class PgProgressDialog extends CordovaPlugin {

    private ProgressDialog progressDialog = null;

    public PgProgressDialog() {
    }

    public synchronized void show(final String message, final boolean cancelable) {
        Runnable runnable = new Runnable() {
            public void run() {
                if (progressDialog == null) {
                    progressDialog = new ProgressDialog(cordova.getActivity());
                }
                progressDialog.setMessage(message);
                progressDialog.setCancelable(cancelable);
                progressDialog.show();
            }
        };
        this.cordova.getActivity().runOnUiThread(runnable);
    }

    public synchronized void hide() {
        if (progressDialog != null) {
            progressDialog.cancel();
        }
    }

    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) {
        if (action.equals("show")) {
            try {
                JSONObject params = args.getJSONObject(0);

                String message = params.has("message") ? params
                        .getString("message") : "数据加载中...";
                Boolean cancelable = params.has("cancelable") ? params
                        .getBoolean("cancelable") : false;
                this.show(message, cancelable);
                callbackContext.success();
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
                return false;
            }
        }else if (action.equals("hide")) {
            this.hide();
            return true;
        } 
        return false;
    }
}
