package first.fileio;

import java.util.BitSet;

public class BloomFilter {
    /*BitSet默认大小*/
    private static final int DEFAULT_SIZE = 1 << 25;
    private static final int[] keys = {11, 13, 17, 23, 29, 31, 37, 41};
    private static BitSet bitSet = new BitSet(DEFAULT_SIZE);
    private static HashUtil[] hashGroup = new HashUtil[keys.length];
    
    static {
        for(int i = 0;i < keys.length;i++) {
            hashGroup[i] = new HashUtil(DEFAULT_SIZE, keys[i]);
        }
    }
    
    public static void add(String url) {
        for(HashUtil hashUtil : hashGroup) {
            bitSet.set(hashUtil.hash(url), true);
        }
    }
    
    public static boolean contains(String url) {
        if(url == null) {
            return false;
        }
        boolean res = true;
        for(HashUtil hashUtil : hashGroup) {
            res = res && bitSet.get(hashUtil.hash(url));
        }
        return res;
    }
    
}
