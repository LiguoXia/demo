package com.liguo.demo.core.study.设计模式.观察者;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2023/5/26 23:25
 * @since 0.0.1
 */
public class EventManager {
    Map<Enum<EventType>, List<EventListener>> listeners = new HashMap<>();
    public EventManager(Enum<EventType>... operations) {
        for (Enum<EventType> operation : operations) {
            this.listeners.put(operation, new ArrayList<>());
        }
    }
    public enum EventType {
        MQ, Message
    }
    /**
     * 订阅
     * @param eventType 事件类型
     * @param listener 监听
     */
    public void subscribe(Enum<EventType> eventType, EventListener
            listener) {
        List<EventListener> users = listeners.get(eventType);
        users.add(listener);
    }
    /**
     * 取消订阅
     * @param eventType 事件类型
     * @param listener 监听
     */
    public void unsubscribe(Enum<EventType> eventType, EventListener
            listener) {
        List<EventListener> users = listeners.get(eventType);
        users.remove(listener);
    }
    /**
     * 通知
     * @param eventType 事件类型
     * @param result 结果
     */
    public void notify(Enum<EventType> eventType, LotteryResult result) {
        List<EventListener> users = listeners.get(eventType);
        for (EventListener listener : users) {
            listener.doEvent(result);
        }
    }
}
