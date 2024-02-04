package cn.kizzzy.vfs.handler;

import cn.kizzzy.data.TableFile;

import java.nio.charset.Charset;

public abstract class TableFileHandler<T> extends TextFileHandler<TableFile<T>> {
    
    protected final boolean skip;
    
    public TableFileHandler() {
        this(Charset.forName("GB2312"), false);
    }
    
    public TableFileHandler(Charset charset, boolean skip) {
        super(charset);
        this.skip = skip;
    }
    
    protected TableFile<T> loadImpl(String text) {
        TableFile<T> file = new TableFile<T>();
        if (skip) {
            return file;
        }
        
        String[] lines = text.split("\r\n");
        int count = lines.length;
        if (file.count == 1) {
            lines = text.split("\n");
            count = lines.length;
        }
        
        for (int i = 0; i < count; ++i) {
            String line = lines[i].trim();
            String[] values = line.split("\\s+");
            if (values.length == 0) {
                continue;
            }
            if (values.length == 1) {
                file.count = Integer.parseInt(line);
            } else {
                T data = this.from(values);
                file.dataList.add(data);
            }
        }
        
        return file;
    }
    
    protected abstract T from(String[] fields);
    
    protected String saveImpl(TableFile<T> ts) {
        if (skip) {
            return "";
        }
        
        StringBuilder builder = new StringBuilder();
        builder.append(ts.count).append("\r\n");
        for (T data : ts.dataList) {
            String[] fields = to(data);
            for (String field : fields) {
                builder.append(field).append(" ");
            }
            builder.append("\r\n");
        }
        
        return builder.toString();
    }
    
    protected abstract String[] to(T data);
}
