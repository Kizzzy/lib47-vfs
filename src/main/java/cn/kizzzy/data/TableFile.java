package cn.kizzzy.data;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class TableFile<T> implements Iterable<T> {
    
    public int count;
    public List<T> dataList = new LinkedList<>();
    
    public TableFile() {
    }
    
    public Iterator<T> iterator() {
        return this.dataList.iterator();
    }
}
