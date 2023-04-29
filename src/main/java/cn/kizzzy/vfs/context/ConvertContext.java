package cn.kizzzy.vfs.context;

import cn.kizzzy.vfs.IConvertContext;
import cn.kizzzy.vfs.IConverter;
import cn.kizzzy.vfs.IConverterProvider;

import java.util.function.Function;

public class ConvertContext<Source, Target> implements IConvertContext<Target> {
    
    private final IConverterProvider provider;
    
    private final IConvertContext<Source> prev;
    
    private final Function<Source, Target> handler;
    
    private final Class<Target> targetClass;
    
    public ConvertContext(IConverterProvider provider, IConvertContext<Source> prev, Function<Source, Target> handler, Class<Target> targetClass) {
        this.provider = provider;
        this.prev = prev;
        this.handler = handler;
        this.targetClass = targetClass;
    }
    
    @Override
    public <T> IConvertContext<T> to(Class<T> clazz) {
        if (provider != null) {
            IConverter<Target, T> handler = provider.getHandler(targetClass, clazz);
            if (handler != null) {
                return to(clazz, handler::to);
            }
            
            IConverter<T, Target> handler1 = provider.getHandler(clazz, targetClass);
            if (handler1 != null) {
                return to(clazz, handler1::from);
            }
        }
        
        return new NullConvertContext<>();
    }
    
    @Override
    public <T> IConvertContext<T> to(Class<T> clazz, Function<Target, T> handler) {
        return new ConvertContext<>(provider, this, handler, clazz);
    }
    
    @Override
    public Target get() {
        Source source = prev.get();
        if (source != null) {
            return handler.apply(source);
        }
        return null;
    }
}
