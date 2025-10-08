package cn.kizzzy.vfs.stream;

import cn.kizzzy.helper.FileHelper;
import cn.kizzzy.io.BufferedInputStreamReader;
import cn.kizzzy.io.IFullyReader;
import cn.kizzzy.io.IFullyWriter;
import cn.kizzzy.io.RandomAccessFileReader;
import cn.kizzzy.io.RandomAccessFileWriter;
import cn.kizzzy.vfs.IInputStreamGetter;
import cn.kizzzy.vfs.IOutputStreamGetter;

import java.io.File;

public class FileStreamGetter implements IInputStreamGetter, IOutputStreamGetter {
    
    private final String file;
    
    public FileStreamGetter(String file) {
        this.file = file;
    }
    
    public IFullyReader getInput() throws Exception {
        return new BufferedInputStreamReader(new RandomAccessFileReader(file));
        // return new RandomAccessFileReader(file);
    }
    
    @Override
    public IFullyWriter getOutput() throws Exception {
        if (!FileHelper.createFolderIfAbsent(new File(file).getParent())) {
            throw new RuntimeException("create folder failed: " + file);
        }
        return new RandomAccessFileWriter(file);
    }
}
