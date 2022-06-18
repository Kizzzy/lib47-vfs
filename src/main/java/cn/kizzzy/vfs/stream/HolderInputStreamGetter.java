package cn.kizzzy.vfs.stream;

import cn.kizzzy.vfs.IHolderInputStreamGetter;
import cn.kizzzy.vfs.IInputStreamGetter;

public class HolderInputStreamGetter implements IHolderInputStreamGetter {
    
    private IInputStreamGetter source;
    
    @Override
    public IInputStreamGetter getSource() {
        return source;
    }
    
    @Override
    public void setSource(IInputStreamGetter source) {
        this.source = source;
    }
}
