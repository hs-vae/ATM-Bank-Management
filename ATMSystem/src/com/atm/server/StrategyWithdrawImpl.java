package com.atm.server;

import com.atm.entity.CommonAtmVO;
import com.atm.entity.CommonResult;
import com.atm.service.ChargeService;

/**
 * 策略模式中取款实现类
 */
public class StrategyWithdrawImpl implements IStrategy{
    ChargeService chargeService = new ChargeService();
    @Override
    public CommonResult process(CommonAtmVO commonAtmVO) {
        return chargeService.withdrawBank(commonAtmVO.getSourceCardNumber(),
                commonAtmVO.getOperatorMoney(),
                commonAtmVO.getCustomerInfo().getPassword());
    }
}
