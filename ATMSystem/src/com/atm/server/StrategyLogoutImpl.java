package com.atm.server;

import com.atm.entity.CommonAtmVO;
import com.atm.entity.CommonResult;
import com.atm.service.LoginLogoutService;

/**
 * ����ģʽ�еǳ�ʵ����
 */
public class StrategyLogoutImpl implements IStrategy{
    LoginLogoutService loginLogoutService = new LoginLogoutService();
    @Override
    public CommonResult process(CommonAtmVO commonAtmVO) {
        return loginLogoutService.logoutBank(commonAtmVO.getSourceCardNumber());
    }
}
