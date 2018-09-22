package com.zyblogs.concurrency.pattern.chapter12;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * @Title: BalkingData.java
 * @Package com.zyblogs.concurrency.pattern.chapter12
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class BalkingData {
    private final String fileName;

    private String content;

    private boolean changed;

    public BalkingData(String fileName, String content) {
        this.fileName = fileName;
        this.content = content;
        this.changed = true;
    }

    public synchronized void change(String newContent) {
        this.content = newContent;
        this.changed = true;
    }

    public synchronized void save() throws IOException {
        if (!changed) {
            return;
        }

        doSave();
        this.changed = false;
    }

    private void doSave() throws IOException {
        System.out.println(Thread.currentThread().getName() + " calls do save,content=" + content);
        try (Writer writer = new FileWriter(fileName, true)) {
            writer.write(content);
            writer.write("\n");
            writer.flush();
        }
    }
}