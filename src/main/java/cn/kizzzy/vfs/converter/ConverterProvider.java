package cn.kizzzy.vfs.converter;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unchecked")
public class ConverterProvider implements IHandlerProvider {
    
    private final Map<Integer, Map<Integer, Object>> handlerKvs
        = new HashMap<>();
    
    @Override
    public <Source, Target> boolean addHandler(Class<Source> sourceClazz, Class<Target> targetClass, IConverter<Source, Target> handler) {
        IConverter<Source, Target> old = getHandler(sourceClazz, targetClass);
        if (old == null) {
            Map<Integer, Object> kvs = handlerKvs.computeIfAbsent(sourceClazz.hashCode(), k -> {
                return new HashMap<>();
            });
            kvs.put(targetClass.hashCode(), handler);
            return true;
        }
        return false;
    }
    
    @Override
    public <Source, Target> boolean removeHandler(Class<Source> sourceClazz, Class<Target> targetClass) {
        Map<Integer, Object> kvs = handlerKvs.get(sourceClazz.hashCode());
        if (kvs != null) {
            kvs.remove(targetClass.hashCode());
        }
        return true;
    }
    
    @Override
    public <Source, Target> IConverter<Source, Target> getHandler(Class<Source> sourceClazz, Class<Target> targetClass) {
        Map<Integer, Object> kvs = handlerKvs.get(sourceClazz.hashCode());
        if (kvs != null) {
            return (IConverter<Source, Target>) kvs.get(targetClass.hashCode());
        }
        return null;
    }
}
