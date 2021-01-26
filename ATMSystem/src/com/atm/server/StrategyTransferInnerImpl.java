package com.atm.server;

import com.atm.entity.CommonAtmVO;
import com.atm.entity.CommonResult;
import com.atm.service.ChargeService;

/**
 * 策略模式中行内转账实现类
 */
public class StrategyTransferInnerImpl implements IStrategy{
    ChargeService chargeService = new ChargeService();
    @Override
    public CommonResult process(CommonAtmVO commonAtmVO) {
    return chargeService.transferInnerBank(commonAtmVO.getSourceCardNumber(),
            commonAtmVO.getTargetCardNumber(),
            commonAtmVO.getOperatorMoney(),
            commonAtmVO.getCustomerInfo().getPassword());
    }
}
