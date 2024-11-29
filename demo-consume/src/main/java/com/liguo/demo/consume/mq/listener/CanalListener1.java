//package com.liguo.demo.consume.mq.listener;
//
//import com.alibaba.fastjson.JSONObject;
//import com.alibaba.otter.canal.client.CanalConnector;
//import com.alibaba.otter.canal.client.CanalConnectors;
//import com.alibaba.otter.canal.protocol.CanalEntry;
//import com.alibaba.otter.canal.protocol.Message;
//import com.google.protobuf.ByteString;
//import com.google.protobuf.InvalidProtocolBufferException;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import java.net.InetSocketAddress;
//import java.util.List;
//
///**
// * dsc
// *
// * @author xialiguo0212@gmail.com
// * @version 0.0.1
// * @date 2024/9/12 15:52
// * @since 0.0.1
// */
//@Component
//public class CanalListener1 {
//    @Value("${canal.server.host}")
//    private String canalHost;
//
//    @Value("${canal.server.port}")
//    private int canalPort;
//
//    @Value("${canal.instance.destination}")
//    private String destination;
//
//    @Value("${canal.instance.username}")
//    private String username;
//
//    @Value("${canal.instance.password}")
//    private String password;
//
//    @PostConstruct
//    public void init() {
//        InetSocketAddress address = new InetSocketAddress(canalHost, canalPort);
//        CanalConnector connector = CanalConnectors.newSingleConnector(
//                address,
//                destination,
//                username,
//                password
//        );
//        new Thread(() -> {
//            connector.connect();
//            connector.subscribe(".*\\..*"); // 订阅所有数据库和表的变更
//            try {
//                while (true) {
//                    Message message = connector.getWithoutAck(100); // 获取指定数量的数据变更事件
//                    long batchId = message.getId();
//                    if (batchId != -1 && message.getEntries().size() > 0) {
//                        // 处理数据变更事件，这里可以根据实际需求进行处理
//                        System.out.println(message);
//                        this.processEntries(message.getEntries());
//                    }
//                    connector.ack(batchId); // 提交确认
//                }
//            } catch (InvalidProtocolBufferException e) {
//                throw new RuntimeException(e);
//            } finally {
//                connector.disconnect();
//            }
//        }).start();
//    }
//    /**
//     * 处理Canal Entry条目列表。
//     *
//     * @param entries Canal Entry条目列表。
//     */
//    public void processEntries(List<CanalEntry.Entry> entries) throws InvalidProtocolBufferException {
//        for (CanalEntry.Entry entry : entries) {
//            // 1. 获取表名
//            String tableName = entry.getHeader().getTableName();
//
//            // 2. 获取类型
//            CanalEntry.EntryType entryType = entry.getEntryType();
//
//            // 3. 获取序列化后的数据
//            ByteString storeValue = entry.getStoreValue();
//
//            // 4. 判断当前entryType类型是否为ROWDATA
//            if (CanalEntry.EntryType.ROWDATA.equals(entryType)) {
//                // 5. 反序列化数据
//                CanalEntry.RowChange rowChange = CanalEntry.RowChange.parseFrom(storeValue);
//
//                // 6. 获取当前事件的操作类型
//                CanalEntry.EventType eventType = rowChange.getEventType();
//
//                // 7. 获取数据集
//                List<CanalEntry.RowData> rowDataList = rowChange.getRowDatasList();
//
//                // 8. 遍历rowDataList，并打印数据集
//                for (CanalEntry.RowData rowData : rowDataList) {
//                    JSONObject beforeData = new JSONObject();
//                    List<CanalEntry.Column> beforeColumnsList = rowData.getBeforeColumnsList();
//                    for (CanalEntry.Column column : beforeColumnsList) {
//                        beforeData.put(column.getName(), column.getValue());
//                    }
//
//                    JSONObject afterData = new JSONObject();
//                    List<CanalEntry.Column> afterColumnsList = rowData.getAfterColumnsList();
//                    for (CanalEntry.Column column : afterColumnsList) {
//                        afterData.put(column.getName(), column.getValue());
//                    }
//
//                    // 数据打印
//                    System.out.println("Table:" + tableName +
//                            ",EventType:" + eventType +
//                            ",Before:" + beforeData +
//                            ",After:" + afterData);
//                }
//            } else {
//                System.out.println("当前操作类型为：" + entryType);
//            }
//        }
//    }
//}
