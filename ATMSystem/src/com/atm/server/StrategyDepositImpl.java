package com.atm.server;

import com.atm.entity.CommonAtmVO;
import com.atm.entity.CommonResult;
import com.atm.service.ChargeService;

/**
 * 策略模式中存款实现类
 */
public class StrategyDepositImpl implements IStrategy{
    ChargeService chargeService = new ChargeService();
    @Override
    public CommonResult process(CommonAtmVO commonAtmVO) {
        return chargeService.depositBank(commonAtmVO.getSourceCardNumber(),commonAtmVO.getOperatorMoney());
    }
}
