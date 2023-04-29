package cn.kizzzy.vfs.context;

import cn.kizzzy.vfs.IConvertContext;

import java.util.function.Function;

public class NullConvertContext<Source> implements IConvertContext<Source> {
    
    @Override
    public <Target> IConvertContext<Target> to(Class<Target> clazz) {
        return new NullConvertContext<>();
    }
    
    @Override
    public <Target> IConvertContext<Target> to(Class<Target> clazz, Function<Source, Target> handler) {
        return new NullConvertContext<>();
    }
    
    @Override
    public Source get() {
        return null;
    }
}
