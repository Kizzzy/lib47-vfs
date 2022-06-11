package cn.kizzzy.vfs.converter;

public class BeginConvertContext<T> extends ConvertContext<Void, T> {
    
    private final T data;
    
    public BeginConvertContext(IHandlerProvider provider, T data, Class<T> clazz) {
        super(provider, null, null, clazz);
        this.data = data;
    }
    
    @Override
    public T get() {
        return data;
    }
}
