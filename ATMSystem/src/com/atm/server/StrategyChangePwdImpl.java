package com.atm.server;

import com.atm.entity.CommonAtmVO;
import com.atm.entity.CommonResult;
import com.atm.service.LoginLogoutService;

/**
 * ����ģʽ�޸��˻�����ʵ����
 */
public class StrategyChangePwdImpl implements IStrategy{
    LoginLogoutService loginLogoutService = new LoginLogoutService();
    @Override
    public CommonResult process(CommonAtmVO commonAtmVO) {
        return loginLogoutService.changePwdBank(commonAtmVO.getSourceCardNumber(),
                commonAtmVO.getCustomerInfo().getPassword());
    }
}
