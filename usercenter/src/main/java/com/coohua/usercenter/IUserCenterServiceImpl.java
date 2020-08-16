package com.coohua.usercenter;

import android.content.Intent;

import com.coohua.base.BaseApplication;
import com.coohua.base.autoservice.IUserCenterService;
import com.google.auto.service.AutoService;

@AutoService({IUserCenterService.class})
public class IUserCenterServiceImpl implements IUserCenterService {
    @Override
    public void login() {
        Intent intent = new Intent(BaseApplication.sApplication, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        BaseApplication.sApplication.startActivity(intent);
    }
}
