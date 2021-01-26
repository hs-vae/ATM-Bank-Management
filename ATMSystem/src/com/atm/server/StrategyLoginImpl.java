package com.atm.server;

import com.atm.entity.CommonAtmVO;
import com.atm.entity.CommonResult;
import com.atm.service.LoginLogoutService;

/**
 * ����ģʽ�е�½ʵ����
 */
public class StrategyLoginImpl implements IStrategy{
    LoginLogoutService loginLogoutService = new LoginLogoutService();
    @Override
    public CommonResult process(CommonAtmVO commonAtmVO) {
        return loginLogoutService.loginBank(commonAtmVO.getSourceCardNumber(),
                commonAtmVO.getCustomerInfo().getPassword());
    }
}
