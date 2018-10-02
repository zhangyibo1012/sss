package com.zyblogs.concurrency.juc.utils;

import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Title: CountDownLatchExample4.java
 * @Package com.zyblogs.concurrency.juc.utils
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class CountDownLatchExample4 {
    private static final Random random = new Random(System.currentTimeMillis());
    static class Event{

        int id = 0;
        public Event(int id) {
            this.id = id;
        }
    }
    interface Watcher{
        void done(Table table);
    }

    static class TaskBatch implements Watcher{

        private CountDownLatch countDownLatch;
        private TaskGroup taskGroup;
        public TaskBatch(TaskGroup taskGroup ,int size ){
            this.taskGroup = taskGroup;
            this.countDownLatch = new CountDownLatch(size);
        }

        @Override
        public void done(Table table) {
            countDownLatch.countDown();
            if (countDownLatch.getCount() == 0){
                System.out.println("The table " + table.tableName + " fininshed work [" + table.toString()+ "]");
                taskGroup.done(table);
            }
        }
    }

    static class TaskGroup implements Watcher{

        private CountDownLatch countDownLatch;
        private Event event;
        public TaskGroup(int size, Event event){
            this.event = event;
            this.countDownLatch = new CountDownLatch(size);

        }

        @Override
        public void done(Table table) {
            countDownLatch.countDown();
            if (countDownLatch.getCount() == 0){
                System.out.println("========== All of table done in event: " + event.id);
            }
        }
    }

    @ToString
    static class Table{
        String tableName;
        long sourceRecordCount = 10;
        long targetCount;
        String sourceColumnSchema = "<table name = 'a'><column name = 'coll' type = ‘varchar'/></table>";
        String targetColumnSchema = "";

        public Table(String tableName, long sourceRecordCount) {
            this.tableName = tableName;
            this.sourceRecordCount = sourceRecordCount;
        }
    }

    private static List<Table> capture(Event event){
        List<Table> list = new ArrayList<>();
        for (int i = 0 ; i < 10 ; i ++){
            list.add(new Table("table-" + event.id + "-" + i,i * 1000 ));
        }
        return list;
    }

    public static void main(String[] args) {
        Event[] events = {new Event(1) , new Event(2)};

        // 让两个runnable并行执行
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (Event event : events){
            List<Table> tables = capture(event);
            TaskGroup taskGroup = new TaskGroup(tables.size(), event);

            for (Table table : tables){
                TaskBatch taskBatch = new TaskBatch(taskGroup,2);
                TrustSourceColumns columnsRunnable = new TrustSourceColumns(table, taskBatch);
                TrustSourceRecordCount recordCountRunnable = new TrustSourceRecordCount(table, taskBatch);

                executorService.submit(columnsRunnable);
                executorService.submit(recordCountRunnable);
            }
        }
    }

    static class TrustSourceRecordCount implements Runnable{
        private final Table table;
        private final TaskBatch taskBatch;
        public TrustSourceRecordCount(Table table, TaskBatch taskBatch) {
            this.table = table;
            this.taskBatch = taskBatch;
        }
        @Override
        public void run() {
            try {
                Thread.sleep(random.nextInt(10_000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            table.targetCount = table.sourceRecordCount;
//            System.out.println("The table " + table.tableName + " target RecordCount capture donw and update the data.");
            taskBatch.done(table);
        }
    }

    static class TrustSourceColumns implements Runnable{
        private final Table table;
        private final TaskBatch taskBatch;
        public TrustSourceColumns(Table table, TaskBatch taskBatch) {
            this.table = table;
            this.taskBatch = taskBatch;
        }
        @Override
        public void run() {
            try {
                Thread.sleep(random.nextInt(10_000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            table.targetColumnSchema = table.sourceColumnSchema;
//            System.out.println("The table " + table.tableName + " target columns capture donw and update the data.");
            taskBatch.done(table);
        }
    }
}
