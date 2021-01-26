package com.atm.server;

import com.atm.entity.CommonAtmVO;
import com.atm.entity.CommonResult;

/**
 *  策略模式总接口
 */
public interface IStrategy {
    CommonResult process(CommonAtmVO commonAtmVO);
}
