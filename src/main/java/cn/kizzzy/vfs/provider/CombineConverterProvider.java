package cn.kizzzy.vfs.provider;

import cn.kizzzy.vfs.IConverter;
import cn.kizzzy.vfs.IConverterProvider;

import java.util.LinkedList;
import java.util.List;

public class CombineConverterProvider extends ConverterProvider {
    
    private final List<IConverterProvider> providers
        = new LinkedList<>();
    
    public CombineConverterProvider(Iterable<IConverterProvider> iterator) {
        for (IConverterProvider provider : iterator) {
            this.providers.add(provider);
        }
    }
    
    @Override
    public <Source, Target> IConverter<Source, Target> getHandler(Class<Source> sourceClazz, Class<Target> targetClazz) {
        for (IConverterProvider provider : providers) {
            IConverter<Source, Target> handler = provider.getHandler(sourceClazz, targetClazz);
            if (handler != null) {
                return handler;
            }
        }
        return super.getHandler(sourceClazz, targetClazz);
    }
}
