package cn.kizzzy.vfs.converter;

public class ConvertContext<Source, Target> implements IConvertContext<Target> {
    
    private final IHandlerProvider provider;
    
    private final IConvertContext<Source> prev;
    
    private final IConverter<Source, Target> handler;
    
    private final Class<Target> targetClass;
    
    public ConvertContext(IHandlerProvider provider, IConvertContext<Source> prev, IConverter<Source, Target> handler, Class<Target> targetClass) {
        this.provider = provider;
        this.prev = prev;
        this.handler = handler;
        this.targetClass = targetClass;
    }
    
    @Override
    public <T> IConvertContext<T> load(Class<T> clazz) {
        if (provider != null) {
            IConverter<Target, T> handler = provider.getHandler(targetClass, clazz);
            if (handler != null) {
                return load(clazz, handler);
            }
        }
        
        return new NullConvertContext<>();
    }
    
    @Override
    public <T> IConvertContext<T> load(Class<T> clazz, IConverter<Target, T> handler) {
        return new ConvertContext<>(provider, this, handler, clazz);
    }
    
    @Override
    public Target get() {
        Source source = prev.get();
        if (source != null) {
            return handler.convert(source);
        }
        return null;
    }
}
