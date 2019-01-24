package com.mvc.cryptovault.console.service;

import com.mvc.cryptovault.common.bean.AppFinancial;
import com.mvc.cryptovault.common.bean.AppUserFinancialIncome;
import com.mvc.cryptovault.common.bean.AppUserFinancialPartake;
import com.mvc.cryptovault.common.constant.RedisConstant;
import com.mvc.cryptovault.console.common.AbstractService;
import com.mvc.cryptovault.console.common.BaseService;
import com.mvc.cryptovault.console.dao.AppUserFinancialIncomeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.List;

@Service
@Transactional(rollbackFor = RuntimeException.class)
public class AppUserFinancialIncomeService extends AbstractService<AppUserFinancialIncome> implements BaseService<AppUserFinancialIncome> {

    @Autowired
    private AppUserFinancialIncomeMapper appUserFinancialIncomeMapper;
    @Autowired
    private CommonTokenPriceService commonTokenPriceService;

    public BigDecimal getLastDay(BigInteger userId, BigInteger id, BigInteger tokenId) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        Long stopAt = calendar.getTime().getTime() + RedisConstant.ONE_DAY;
        Long startAt = stopAt - RedisConstant.ONE_DAY;
        List<AppUserFinancialIncome> list = appUserFinancialIncomeMapper.getLastDay(userId, id, startAt, stopAt);
        BigDecimal balance = list.stream().map(obj -> obj.getValue()).reduce(BigDecimal.ZERO, BigDecimal::add);
        return balance;
    }

    public BigDecimal getLast(BigInteger userId) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        Long stopAt = calendar.getTime().getTime();
        Long startAt = stopAt - RedisConstant.ONE_DAY;
        List<AppUserFinancialIncome> list = appUserFinancialIncomeMapper.getLast(userId, startAt, stopAt);
        BigDecimal balance = list.stream().map(obj -> obj.getValue().multiply(commonTokenPriceService.findById(obj.getTokenId()).getTokenPrice())).reduce(BigDecimal.ZERO, BigDecimal::add);
        return balance;
    }

    public void insert(AppUserFinancialPartake partake, AppFinancial appFinancial, BigDecimal value) {
        Long time = System.currentTimeMillis();
        AppUserFinancialIncome income = new AppUserFinancialIncome();
        income.setCreatedAt(time);
        income.setUpdatedAt(time);
        income.setFinancialId(partake.getFinancialId());
        income.setPartakeId(partake.getId());
        income.setTokenId(appFinancial.getTokenId());
        income.setTokenName(appFinancial.getTokenName());
        income.setUserId(partake.getUserId());
        income.setValue(value);
        save(income);
    }
}