package test.fileio;

public class HashUtil {
    private int size;
    private int key;
    
    public HashUtil(int size, int key) {
        this.size = size;
        this.key  = key;
    }
    
    public int hash(String str) {
        int result = 0;
        int len = str.length();
        for(int i = 0; i < len; i++) {
            result = key * result + str.charAt(i);
        }
        return (size - len) & result;
    }
    
}
