package cn.kizzzy.vfs.tree;

import java.util.concurrent.atomic.AtomicInteger;

public class IdGenerator {
    
    private final AtomicInteger id;
    
    public IdGenerator() {
        this(0);
    }
    
    public IdGenerator(int id) {
        this.id = new AtomicInteger(id);
    }
    
    public int getId() {
        return id.incrementAndGet();
    }
}
