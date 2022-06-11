package cn.kizzzy.vfs.converter;

import cn.kizzzy.data.TableFile;

public abstract class TableFileToStringConverter<T, R extends TableFile<T>> implements IConverter<R, String> {
    
    protected final boolean skip;
    
    protected final boolean skipFirst;
    
    protected final String lineSeparator;
    
    protected final String fieldSeparator;
    
    protected final Class<R> clazz;
    
    public TableFileToStringConverter(Class<R> clazz) {
        this(clazz, false, false, "\r\n", "\\s+");
    }
    
    public TableFileToStringConverter(Class<R> clazz, boolean skip, boolean skipFirst, String lineSeparator, String fieldSeparator) {
        this.clazz = clazz;
        this.skip = skip;
        this.skipFirst = skipFirst;
        this.lineSeparator = lineSeparator;
        this.fieldSeparator = fieldSeparator;
    }
    
    public String convert(R file) {
        if (skip) {
            return "";
        } else {
            StringBuilder builder = new StringBuilder();
            if (!skipFirst) {
                builder.append(file.count).append(lineSeparator);
            }
            for (T data : file.dataList) {
                String[] fields = fromEntity(data);
                for (String field : fields) {
                    builder.append(field).append(fieldSeparator);
                }
                builder.append(lineSeparator);
            }
            
            return builder.toString();
        }
    }
    
    protected abstract String[] fromEntity(T entity);
}
