package com.atm.server;

import com.atm.entity.CommonAtmVO;
import com.atm.entity.CommonResult;
import com.atm.service.ChargeService;

/**
 * ����ģʽ�д��ʵ����
 */
public class StrategyDepositImpl implements IStrategy{
    ChargeService chargeService = new ChargeService();
    @Override
    public CommonResult process(CommonAtmVO commonAtmVO) {
        return chargeService.depositBank(commonAtmVO.getSourceCardNumber(),commonAtmVO.getOperatorMoney());
    }
}
