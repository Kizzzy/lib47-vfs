package cn.kizzzy.vfs;

import cn.kizzzy.io.SubStream;

import java.io.InputStream;

public interface IStreamable {
    
    IStreamable getSource();
    
    void setSource(IStreamable source);
    
    default InputStream OpenStream() throws Exception {
        if (getSource() == null) {
            throw new NullPointerException("source is null");
        }
        InputStream temp = getSource().OpenStream();
        return new SubStream(temp, 0, temp.available());
    }
}
