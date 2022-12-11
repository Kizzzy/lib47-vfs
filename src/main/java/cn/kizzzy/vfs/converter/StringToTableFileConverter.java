package cn.kizzzy.vfs.converter;

import cn.kizzzy.data.TableFile;
import cn.kizzzy.helper.LogHelper;
import cn.kizzzy.vfs.IConverter;

public abstract class StringToTableFileConverter<T, R extends TableFile<T>> implements IConverter<String, R> {
    
    protected final boolean skip;
    
    protected final boolean skipFirst;
    
    protected final String lineSeparator;
    
    protected final String fieldSeparator;
    
    protected final Class<R> clazz;
    
    public StringToTableFileConverter(Class<R> clazz) {
        this(clazz, false, false, "\r\n", "\\s+");
    }
    
    public StringToTableFileConverter(Class<R> clazz, boolean skip, boolean skipFirst, String lineSeparator, String fieldSeparator) {
        this.clazz = clazz;
        this.skip = skip;
        this.skipFirst = skipFirst;
        this.lineSeparator = lineSeparator;
        this.fieldSeparator = fieldSeparator;
    }
    
    public R to(String text) {
        try {
            String[] var0 = text.split(lineSeparator);
            int i = 0;
            int count = var0.length;
            R file = clazz.newInstance();
            if (!this.skip) {
                if (!this.skipFirst) {
                    file.count = Integer.parseInt(var0[i++].trim());
                    count = file.count;
                }
                
                for (int k = 0; k < count; ++k) {
                    String[] var1 = var0[i++].trim().split(this.fieldSeparator);
                    if (var1.length <= 0) {
                        continue;
                    }
                    
                    T data = this.toEntity(var1);
                    file.dataList.add(data);
                }
                
            }
            return file;
        } catch (Exception e) {
            LogHelper.error("load table file error", e);
            return null;
        }
    }
    
    protected abstract T toEntity(String[] fields);
    
    @Override
    public String from(R file) {
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
