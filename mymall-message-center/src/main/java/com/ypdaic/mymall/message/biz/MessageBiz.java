package com.ypdaic.mymall.message.biz;

import com.alibaba.fastjson.JSONObject;
import com.ypdaic.mymall.message.entity.RpTransactionMessage;
import com.ypdaic.mymall.message.service.IRpTransactionMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 和业务相关了
 */
@Component
@Slf4j
public class MessageBiz {

//    @Autowired
//    private RpTradePaymentQueryService rpTradePaymentQueryService;
    @Autowired
    private IRpTransactionMessageService rpTransactionMessageService;

    @Autowired
    RestTemplate restTemplate;

    @Value("${message.max.send.times}")
    private Integer maxSendTimes;

    @Value("${message.send.1.time}")
    private Integer maxSendTimes1;

    @Value("${message.send.2.time}")
    private Integer maxSendTimes2;

    @Value("${message.send.3.time}")
    private Integer maxSendTimes3;

    @Value("${message.send.4.time}")
    private Integer maxSendTimes4;

    @Value("${message.send.5.time}")
    private Integer maxSendTimes5;

    /**
     * 处理[waiting_confirm]状态的消息
     *
     * @param messages
     */
    public void handleWaitingConfirmTimeOutMessages(Map<String, RpTransactionMessage> messageMap) {
        log.debug("开始处理[waiting_confirm]状态的消息,总条数[" + messageMap.size() + "]");
        // 单条消息处理（目前该状态的消息，消费队列全部是accounting，如果后期有业务扩充，需做队列判断，做对应的业务处理。）
        for (Map.Entry<String, RpTransactionMessage> entry : messageMap.entrySet()) {
            RpTransactionMessage message = entry.getValue();
            try {

                log.debug("开始处理[waiting_confirm]消息ID为[" + message.getMessageId() + "]的消息");
                String url = message.getUrl();
                JSONObject forObject = restTemplate.getForObject(url, JSONObject.class);
                String result = forObject.getString("result");
                if ("ok".equals(result)) {
                    // 确认并发送消息
                    rpTransactionMessageService.confirmAndSendMessage(message.getMessageId());
                }
                if ("fail".equals(result)) {
                    // 订单状态是等到支付，可以直接删除数据
                    log.debug("订单没有支付成功,删除[waiting_confirm]消息id[" + message.getMessageId() + "]的消息");
                    rpTransactionMessageService.deleteMessageByMessageId(message.getMessageId());
                }


                log.debug("结束处理[waiting_confirm]消息ID为[" + message.getMessageId() + "]的消息");
            } catch (Exception e) {
                log.error("处理[waiting_confirm]消息ID为[" + message.getMessageId() + "]的消息异常：", e);
            }
        }
    }

    /**
     * 处理[SENDING]状态的消息
     *
     * @param messages
     */
    public void handleSendingTimeOutMessage(Map<String, RpTransactionMessage> messageMap) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        log.debug("开始处理[SENDING]状态的消息,总条数[" + messageMap.size() + "]");

        // 根据配置获取通知间隔时间
        Map<Integer, Integer> notifyParam = getSendTime();

        // 单条消息处理
        for (Map.Entry<String, RpTransactionMessage> entry : messageMap.entrySet()) {
            RpTransactionMessage message = entry.getValue();
            try {
                log.debug("开始处理[SENDING]消息ID为[" + message.getMessageId() + "]的消息");
                // 判断发送次数
                int maxTimes = maxSendTimes;
                log.debug("[SENDING]消息ID为[" + message.getMessageId() + "]的消息,已经重新发送的次数[" + message.getMessageSendTimes() + "]");

                // 如果超过最大发送次数直接退出
                if (maxTimes < message.getMessageSendTimes()) {
                    // 标记为死亡
                    rpTransactionMessageService.setMessageToAreadlyDead(message.getMessageId());
                    continue;
                }
                // 判断是否达到发送消息的时间间隔条件
                int reSendTimes = message.getMessageSendTimes();
                int times = notifyParam.get(reSendTimes == 0 ? 1 : reSendTimes);
                long currentTimeInMillis = Calendar.getInstance().getTimeInMillis();
                long needTime = currentTimeInMillis - times * 60 * 1000;
                long hasTime = message.getEditTime().getTime();
                // 判断是否达到了可以再次发送的时间条件
                if (hasTime > needTime) {
                    log.debug("currentTime[" + sdf.format(new Date()) + "],[SENDING]消息上次发送时间[" + sdf.format(message.getEditTime()) + "],必须过了[" + times + "]分钟才可以再发送。");
                    continue;
                }

                // 重新发送消息
                rpTransactionMessageService.reSendMessage(message);

                log.debug("结束处理[SENDING]消息ID为[" + message.getMessageId() + "]的消息");
            } catch (Exception e) {
                log.error("处理[SENDING]消息ID为[" + message.getMessageId() + "]的消息异常：", e);
            }
        }

    }

    /**
     * 根据配置获取通知间隔时间
     *
     * @return
     */
    private Map<Integer, Integer> getSendTime() {
        Map<Integer, Integer> notifyParam = new HashMap<Integer, Integer>();
        notifyParam.put(1, maxSendTimes1);
        notifyParam.put(2, maxSendTimes2);
        notifyParam.put(3, maxSendTimes3);
        notifyParam.put(4, maxSendTimes4);
        notifyParam.put(5, maxSendTimes5);
        return notifyParam;
    }


}
