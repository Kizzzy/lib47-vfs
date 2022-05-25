package cn.kizzzy.vfs;

import cn.kizzzy.io.IFullyReader;
import cn.kizzzy.io.SliceFullReader;

public interface IStreamable {
    
    IStreamable getSource();
    
    void setSource(IStreamable source);
    
    default IFullyReader OpenStream() throws Exception {
        if (getSource() == null) {
            throw new NullPointerException("source is null");
        }
        return new SliceFullReader(getSource().OpenStream());
    }
}
