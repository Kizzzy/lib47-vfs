package cn.kizzzy.vfs.provider;

import cn.kizzzy.vfs.IConverter;
import cn.kizzzy.vfs.IConverterProvider;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unchecked")
public class ConverterProvider implements IConverterProvider {
    
    private final Map<Integer, Map<Integer, Object>> handlerKvs
        = new HashMap<>();
    
    @Override
    public <Source, Target> boolean addHandler(Class<Source> sourceClazz, Class<Target> targetClazz, IConverter<Source, Target> handler) {
        IConverter<Source, Target> old = getHandler(sourceClazz, targetClazz);
        if (old == null) {
            Map<Integer, Object> kvs = handlerKvs.computeIfAbsent(sourceClazz.hashCode(), k -> {
                return new HashMap<>();
            });
            kvs.put(targetClazz.hashCode(), handler);
            return true;
        }
        return false;
    }
    
    @Override
    public <Source, Target> boolean removeHandler(Class<Source> sourceClazz, Class<Target> targetClazz) {
        Map<Integer, Object> kvs = handlerKvs.get(sourceClazz.hashCode());
        if (kvs != null) {
            kvs.remove(targetClazz.hashCode());
        }
        return true;
    }
    
    @Override
    public <Source, Target> IConverter<Source, Target> getHandler(Class<Source> sourceClazz, Class<Target> targetClazz) {
        Map<Integer, Object> kvs = handlerKvs.get(sourceClazz.hashCode());
        if (kvs != null) {
            return (IConverter<Source, Target>) kvs.get(targetClazz.hashCode());
        }
        return null;
    }
}
