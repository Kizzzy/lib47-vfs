package cn.kizzzy.vfs;

import cn.kizzzy.io.IFullyReader;
import cn.kizzzy.io.SliceFullReader;

public interface IInputStreamGetter {
    
    IInputStreamGetter getSource();
    
    void setSource(IInputStreamGetter source);
    
    default IFullyReader getInput() throws Exception {
        if (getSource() == null) {
            throw new NullPointerException("source is null");
        }
        return new SliceFullReader(getSource().getInput());
    }
}
