package cn.kizzzy.vfs;

import cn.kizzzy.io.FullyReader;
import cn.kizzzy.io.SliceFullReader;

public interface IStreamable {
    
    IStreamable getSource();
    
    void setSource(IStreamable source);
    
    default FullyReader OpenStream() throws Exception {
        if (getSource() == null) {
            throw new NullPointerException("source is null");
        }
        return new SliceFullReader(getSource().OpenStream());
    }
}
