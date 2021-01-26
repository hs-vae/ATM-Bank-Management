package com.atm.server;

import com.atm.entity.CommonAtmVO;
import com.atm.entity.CommonResult;
import com.atm.util.StatusEnumEntity;

import java.util.ArrayList;
import java.util.List;

/**
 *  策略模式中处理上下文
 */
public class ProcessContext {
    private String type;
    private IStrategy is;
    private static List<ProcessContext> strategyList = new ArrayList<ProcessContext>();
    static {
        strategyList.add(new ProcessContext(StatusEnumEntity.getValue("STATUS_SAVE"),new StrategyDepositImpl()));
        strategyList.add(new ProcessContext(StatusEnumEntity.getValue("STATUS_LOGIN"),new StrategyLoginImpl()));
        strategyList.add(new ProcessContext(StatusEnumEntity.getValue("STATUS_LOGINFAILED"),new StrategyLoginImpl()));
        strategyList.add(new ProcessContext(StatusEnumEntity.getValue("STATUS_CHANGEPWD"),new StrategyChangePwdImpl()));
        strategyList.add(new ProcessContext(StatusEnumEntity.getValue("STATUS_LOGOUT"),new StrategyLogoutImpl()));
        strategyList.add(new ProcessContext(StatusEnumEntity.getValue("STATUS_INTRANSFER"),new StrategyTransferInnerImpl()));
        strategyList.add(new ProcessContext(StatusEnumEntity.getValue("STATUS_OUTTRANSFER"),new StrategyTransferOuterImpl()));
        strategyList.add(new ProcessContext(StatusEnumEntity.getValue("STATUS_FETCH"),new StrategyWithdrawImpl()));
    }
    public CommonResult delStrategy(CommonAtmVO commonAtmVO) {
        IStrategy is = null;
        for (ProcessContext pc : strategyList) {
            if (pc.getType().equals(commonAtmVO.getType())) {
                is = pc.getIs();
            }
        }
        return is.process(commonAtmVO);
    }
    public ProcessContext() {
    }

    public ProcessContext(String type, IStrategy is) {
        this.type = type;
        this.is = is;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public IStrategy getIs() {
        return is;
    }

    public void setIs(IStrategy is) {
        this.is = is;
    }
}
