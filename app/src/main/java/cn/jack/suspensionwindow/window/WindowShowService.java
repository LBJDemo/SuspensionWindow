package cn.jack.suspensionwindow.window;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import static cn.jack.suspensionwindow.WebViewActivity.BROAD_CAST_NAME;


/**
 * Created by manji
 * Date：2018/9/29 上午11:50
 * Desc：
 */
public class WindowShowService extends Service implements WindowUtil.OnPermissionListener {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        WindowUtil.getInstance().showPermissionWindow(this, this);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        WindowUtil.getInstance().dismissWindow();
    }

    @Override
    public void result(boolean isSuccess) {
        //获取权限成功，发送广播通知Activity
        Intent intent = new Intent(BROAD_CAST_NAME);
        intent.putExtra("permission", isSuccess);
        sendBroadcast(intent);
    }
}
