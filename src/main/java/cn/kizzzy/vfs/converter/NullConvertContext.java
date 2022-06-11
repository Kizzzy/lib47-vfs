package cn.kizzzy.vfs.converter;

public class NullConvertContext<Source> implements IConvertContext<Source> {
    
    @Override
    public <Target> IConvertContext<Target> load(Class<Target> clazz) {
        return new NullConvertContext<>();
    }
    
    @Override
    public <Target> IConvertContext<Target> load(Class<Target> clazz, IConverter<Source, Target> handler) {
        return new NullConvertContext<>();
    }
    
    @Override
    public Source get() {
        return null;
    }
}
