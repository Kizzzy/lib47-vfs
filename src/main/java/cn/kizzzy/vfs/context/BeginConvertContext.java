package cn.kizzzy.vfs.context;

import cn.kizzzy.vfs.IConverterProvider;

public class BeginConvertContext<T> extends ConvertContext<Void, T> {
    
    private final T data;
    
    public BeginConvertContext(IConverterProvider provider, T data, Class<T> clazz) {
        super(provider, null, null, clazz);
        this.data = data;
    }
    
    @Override
    public T get() {
        return data;
    }
}
