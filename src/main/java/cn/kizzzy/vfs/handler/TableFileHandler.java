package cn.kizzzy.vfs.handler;

import cn.kizzzy.data.TableFile;

import java.nio.charset.Charset;

public abstract class TableFileHandler<T> extends TextFileHandler<TableFile<T>> {
    
    protected final boolean skip;
    
    protected final boolean skipFirst;
    
    protected final String lineSeparator;
    
    protected final String fieldSeparator;
    
    public TableFileHandler() {
        this(Charset.forName("GB2312"), false, false, "\r\n", "\\s+");
    }
    
    public TableFileHandler(Charset charset, boolean skip, boolean skipFirst, String lineSeparator, String fieldSeparator) {
        super(charset);
        this.skip = skip;
        this.skipFirst = skipFirst;
        this.lineSeparator = lineSeparator;
        this.fieldSeparator = fieldSeparator;
    }
    
    protected TableFile<T> loadImpl(String text) {
        String[] var0 = text.split(lineSeparator);
        int i = 0;
        int count = var0.length;
        TableFile<T> file = new TableFile<T>();
        if (this.skip) {
            return file;
        } else {
            if (!this.skipFirst) {
                file.count = Integer.parseInt(var0[i++].trim());
                count = file.count;
            }
            
            for (int k = 0; k < count; ++k) {
                String[] var1 = var0[i++].trim().split(this.fieldSeparator);
                if (var1.length <= 0) {
                    continue;
                }
                
                T data = this.from(var1);
                file.dataList.add(data);
            }
            
            return file;
        }
    }
    
    protected abstract T from(String[] fields);
    
    protected String saveImpl(TableFile<T> ts) {
        if (skip) {
            return "";
        } else {
            StringBuilder builder = new StringBuilder();
            if (!skipFirst) {
                builder.append(ts.count).append(lineSeparator);
            }
            for (T data : ts.dataList) {
                String[] fields = to(data);
                for (String field : fields) {
                    builder.append(field).append(fieldSeparator);
                }
                builder.append(lineSeparator);
            }
            
            return builder.toString();
        }
    }
    
    protected abstract String[] to(T data);
}
