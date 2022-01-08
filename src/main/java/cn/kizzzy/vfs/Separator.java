package cn.kizzzy.vfs;

public class Separator {
    
    public static final char SLASH = '/';
    
    public static final char BACKSLASH = '\\';
    
    public static final String SLASH_SPLITTER = "/";
    
    public static final String BACKSLASH_SPLITTER = "\\\\";
    
    public static final Separator SLASH_SEPARATOR_UPPERCASE
        = new Separator(SLASH, false);
    
    public static final Separator SLASH_SEPARATOR_LOWERCASE
        = new Separator(SLASH, true);
    
    public static final Separator BACKSLASH_SEPARATOR_UPPERCASE
        = new Separator(BACKSLASH, false);
    
    public static final Separator BACKSLASH_SEPARATOR_LOWERCASE
        = new Separator(BACKSLASH, true);
    
    protected final char undesired;
    protected final String undesiredSplitter;
    
    protected final char desired;
    protected final String desiredSplitter;
    
    protected final boolean lowercase;
    
    public Separator(char desired, boolean lowercase) {
        this.desired = desired;
        this.desiredSplitter = SLASH == desired ? SLASH_SPLITTER : BACKSLASH_SPLITTER;
        this.undesired = SLASH == desired ? BACKSLASH : SLASH;
        this.undesiredSplitter = SLASH == desired ? BACKSLASH_SPLITTER : SLASH_SPLITTER;
        this.lowercase = lowercase;
    }
    
    public String replace(String origin) {
        String temp = origin.replace(undesired, desired);
        return lowercase ? temp.toLowerCase() : temp;
    }
    
    public String[] split(String origin) {
        return replace(origin).split(desiredSplitter);
    }
    
    public String combine(String parent, String child) {
        parent = replace(parent);
        child = replace(child);
        return String.format("%s%s%s", parent, desired, child);
    }
    
    public char getUndesired() {
        return undesired;
    }
    
    public String getUndesiredSplitter() {
        return undesiredSplitter;
    }
    
    public char getDesired() {
        return desired;
    }
    
    public String getDesiredSplitter() {
        return desiredSplitter;
    }
    
    public boolean isLowercase() {
        return lowercase;
    }
}
