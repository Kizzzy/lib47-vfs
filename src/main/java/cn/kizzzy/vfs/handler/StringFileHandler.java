package cn.kizzzy.vfs.handler;

public class StringFileHandler extends TextFileHandler<String> {
    
    @Override
    protected String loadImpl(String str) {
        return str;
    }
    
    @Override
    protected String saveImpl(String data) {
        return data;
    }
}
