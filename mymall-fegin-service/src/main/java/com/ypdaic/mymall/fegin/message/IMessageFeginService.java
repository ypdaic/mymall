package com.ypdaic.mymall.fegin.message;

import com.ypdaic.mymall.common.annotation.NeedAuth;
import com.ypdaic.mymall.common.enums.NeedAuthEnum;
import com.ypdaic.mymall.common.result.Result;
import com.ypdaic.mymall.common.to.RpTransactionMessageVo;
import com.ypdaic.mymall.common.util.R;
import com.ypdaic.mymall.fegin.config.FeginConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "mymall-message", configuration = {FeginConfig.class})
public interface IMessageFeginService {

    @PostMapping("/message/rp-transaction-message/add")
    R saveMessageWaitingConfirm(@RequestBody RpTransactionMessageVo rpTransactionMessageVo);

    @PostMapping("/confirmAndSendMessage")
    R confirmAndSendMessage(@RequestParam("messageId") String messageId);
}
