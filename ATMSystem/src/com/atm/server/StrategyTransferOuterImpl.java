package com.atm.server;

import com.atm.entity.CommonAtmVO;
import com.atm.entity.CommonResult;
import com.atm.service.ChargeService;
/**
 * ����ģʽ�п���ת��ʵ����
 */
public class StrategyTransferOuterImpl implements IStrategy{
    ChargeService chargeService = new ChargeService();
    @Override
    public CommonResult process(CommonAtmVO commonAtmVO) {
        return chargeService.transferOuterBank(commonAtmVO.getSourceCardNumber(),
                commonAtmVO.getOperatorMoney(),
                commonAtmVO.getCustomerInfo().getPassword());
    }
}
