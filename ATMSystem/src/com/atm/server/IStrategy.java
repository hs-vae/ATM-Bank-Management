package com.atm.server;

import com.atm.entity.CommonAtmVO;
import com.atm.entity.CommonResult;

/**
 *  ����ģʽ�ܽӿ�
 */
public interface IStrategy {
    CommonResult process(CommonAtmVO commonAtmVO);
}
