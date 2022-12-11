package cn.kizzzy.vfs.context;

import cn.kizzzy.vfs.IConvertContextFactory;
import cn.kizzzy.vfs.IConverter;
import cn.kizzzy.vfs.IConverterProvider;

public class ConvertContextFactory implements IConvertContextFactory {
    
    private final IConverterProvider handlerProvider;
    
    public ConvertContextFactory(IConverterProvider handlerProvider) {
        this.handlerProvider = handlerProvider;
    }
    
    @Override
    public <Source, Target> boolean addHandler(Class<Source> sourceClazz, Class<Target> clazz, IConverter<Source, Target> handler) {
        return handlerProvider.addHandler(sourceClazz, clazz, handler);
    }
    
    @Override
    public <Source, Target> boolean removeHandler(Class<Source> sourceClazz, Class<Target> clazz) {
        return removeHandler(sourceClazz, clazz);
    }
    
    @Override
    public <Source, Target> IConverter<Source, Target> getHandler(Class<Source> sourceClazz, Class<Target> clazz) {
        return getHandler(sourceClazz, clazz);
    }
}
