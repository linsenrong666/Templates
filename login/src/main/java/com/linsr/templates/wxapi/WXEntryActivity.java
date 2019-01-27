package com.linsr.templates.wxapi;

import com.linsr.common.utils.JLog;
import com.linsr.common.utils.ToastUtils;
import com.linsr.common.utils.contents.ContentsManager;
import com.linsr.login.base.LoginConstants;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import static com.linsr.login.base.LoginConstants.LOGIN_EVENT_WX_LOGIN_CODE;

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
    private IWXAPI wxAPI;
    private static final String TAG = "WXEntryActivity";
    private static final int RETURN_MSG_TYPE_LOGIN = 1; //登录
    private static final int RETURN_MSG_TYPE_SHARE = 2; //分享

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        wxAPI = WXAPIFactory.createWXAPI(this, LoginConstants.WECHAT_APP_ID, true);
        wxAPI.registerApp(LoginConstants.WECHAT_APP_ID);
        wxAPI.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        wxAPI.handleIntent(getIntent(), this);
    }

    @Override
    public void onReq(BaseReq baseReq) {
        JLog.i(TAG, "WXEntryActivity onReq:" + baseReq);
    }

    @Override
    public void onResp(BaseResp baseResp) {
        int type = baseResp.getType(); //类型：分享还是登录
        switch (baseResp.errCode) {
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                //用户拒绝授权
                ToastUtils.show("拒绝授权微信登录");
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                //用户取消
                String message = "";
                if (type == RETURN_MSG_TYPE_LOGIN) {
                    message = "取消了微信登录";
                } else if (type == RETURN_MSG_TYPE_SHARE) {
                    message = "取消了微信分享";
                }
                ToastUtils.show(message);
                break;
            case BaseResp.ErrCode.ERR_OK:
                //用户同意
                if (type == RETURN_MSG_TYPE_LOGIN) {
                    //用户换取access_token的code，仅在ErrCode为0时有效
                    String code = ((SendAuth.Resp) baseResp).code;
                    JLog.i(TAG, "微信登录操作 code:------>" + code);
                    ContentsManager.getInstance().notifyContentUpdateSuccess(LOGIN_EVENT_WX_LOGIN_CODE, code);
                } else if (type == RETURN_MSG_TYPE_SHARE) {
//                    ToastUtils.showToast(mContext, "微信分享成功");
                }
                break;
        }
        finish();
    }
}