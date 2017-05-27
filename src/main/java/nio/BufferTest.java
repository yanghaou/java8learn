package nio;

import org.junit.Test;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * author :yahao
 * time :2017/5/24
 * function:
 */
public class BufferTest {

    @Test
    public void t1(){
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        byteBuffer.put("222".getBytes());
        byteBuffer.flip();
        while (byteBuffer.hasRemaining()){
            System.out.println((char) byteBuffer.get());
        }
        byteBuffer.clear();
    }

    @Test
    public void t2() throws Exception{
        RandomAccessFile aFile = new RandomAccessFile("t2.txt", "rw");
        FileChannel inChannel = aFile.getChannel();

        //create buffer with capacity of 48 bytes
        ByteBuffer buf = ByteBuffer.allocate(10);

        int bytesRead = inChannel.read(buf); //read into buffer.
        while (bytesRead != -1) {

            buf.flip();  //make buffer ready for read

            while(buf.hasRemaining()){
                System.out.print((char) buf.get()); // read 1 byte at a time
            }

            buf.compact(); //make buffer ready for writing
            bytesRead = inChannel.read(buf);

        }
        aFile.close();
    }


    @Test
    public void t3(){
        CharBuffer buffer = CharBuffer.allocate(5);
        buffer.put('H');
        buffer.put('E');
        buffer.put('L');
        buffer.put('L');
        buffer.put('O');

        buffer.flip();
        while (buffer.hasRemaining()) {
            System.out.print(buffer.get());
        }
    }
}
