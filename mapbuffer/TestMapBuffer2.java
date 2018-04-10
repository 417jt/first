package first.mapbuffer;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;

/**
 * Created by zfh on 16-4-19.
 */
public class TestMapBuffer2 {
    private FileInputStream fileIn;
    private MappedByteBuffer mappedBuf;
    private long fileLength;
    private int arraySize;
    private byte[] array;
    int i = 0;
    public TestMapBuffer2(String fileName, int arraySize) throws IOException {
        this.fileIn = new FileInputStream(fileName);
        FileChannel fileChannel = fileIn.getChannel();
        this.fileLength = fileChannel.size();
        this.mappedBuf = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, fileLength);
        this.arraySize = arraySize;
    }

    public int read() throws IOException {
        int limit = mappedBuf.limit();
        int position = mappedBuf.position();
        System.out.println(position);
        if (position == limit) {
            return -1;
        }
        if (limit - position > arraySize) {
            array = new byte[arraySize];
            mappedBuf.get(array);
            System.out.println(Arrays.toString(array));
            try {
                System.out.println(new String(array,"UTF-8"));
                } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                }  
            i = i + arraySize;
            if (i > 600000 ) {
                return -1;
            }
            return arraySize;
        } else {// 最后一次读取数据
            array = new byte[limit - position];
            mappedBuf.get(array);
            return limit - position;
        }
    }

    public void close() throws IOException {
        fileIn.close();
        array = null;
    }

    public byte[] getArray() {
        return array;
    }

    public long getFileLength() {
        return fileLength;
    }

    public static void main(String[] args) throws IOException {
        TestMapBuffer2 reader = new TestMapBuffer2("F:\\work\\中恒华瑞\\pdump", 100);
        long start = System.nanoTime();
        
        while (reader.read() != -1);
        long end = System.nanoTime();
        reader.close();
        System.out.println("MappedFileReader: " + (end - start));
    }
}