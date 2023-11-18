package top.arhi.handler;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import top.arhi.domain.DelayMessage;
import top.arhi.domain.DelayQueue;

import java.util.Date;
import java.util.List;


@Component
public class DelayMessageHandler {

    @Autowired
    private DelayQueue delayQueue;

    /**
     * 处理已到期的消息(轮询)
     */
    @Scheduled(fixedDelay = 1000)
    public void handleExpiredMessages() {
        //获取当前系统时间
        String dateTime = DateUtil.format(new Date(), DatePattern.NORM_DATETIME_PATTERN );

        //扫描任务，并将需要执行的任务加入到任务队列中
        List<DelayMessage> messages = delayQueue.getExpiredMessages();
        if(messages.size()==0){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(dateTime + " 待处理消息：" + messages);
        //处理消息
        if (!messages.isEmpty()) {
            for (DelayMessage message : messages) {
                System.out.println(dateTime + " 处理消息：" + message.getContent());
                //成功处理消息后，便将消息进行移除。
                delayQueue.remove(message);
            }
        }
    }

}