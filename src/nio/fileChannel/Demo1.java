package nio.fileChannel;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author zhiyu
 * @Date 2019-11-12
 */
public class Demo1 {
    /**java.io中的类都将相对路径解释为以用户工作目录开始，可通过user.dir来获取这个信息。*/
    @Test
    public void test1(){
        Assertions.assertEquals("E:\\IdeaWorkspace\\J2SETest",System.getProperty("user.dir"));
    }

    /**测试 FileChannel.write(ByteBuffer) 功能。
     * write()是从通道的当前位置开始写入，将Buffer中的remaining字节序列写入通道的当前位置。返回值代表写入的字节数，可能为0。
     * */
    @Test
    public void testFileChannelWrite() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(new File("TestFileChannel.txt"));
        FileChannel fileChannel = fileOutputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.wrap("abcde".getBytes());
        //返回此通道的文件位置
        System.out.println("fileChannel.position()=" + fileChannel.position());
        System.out.println("write()返回值:"+fileChannel.write(byteBuffer));
        System.out.println("fileChannel.position()=" + fileChannel.position()+"，buffer.position="+byteBuffer.position());

        //设置此通道的文件位置，再进行write()时就会从第2个字节往后写入
        fileChannel.position(2);
        byteBuffer.rewind();
        System.out.println("write()返回值:"+fileChannel.write(byteBuffer));
        System.out.println("fileChannel.position()=" + fileChannel.position()+"，buffer.position="+byteBuffer.position());

        fileChannel.close();
        fileOutputStream.close();
    }

    /**测试 FileChannel.read(ByteBuffer) 功能。
     * read()的作用是将字节序列从通道的当前位置读入给定的缓冲区的当前位置。返回值为读取的字节数，有3种值：
     * 1、正数：代表读到的字节数。
     * 2、0：代表没有读取任何数据，即0字节，有可能发生的情况是Buffer中没有remaining剩余空间了。
     * 3、-1：到达流的末端。
     * */
    @Test
    public void testFileChannelRead() throws IOException {
        //TestFileChannel.txt的文件内容为abcde。
        FileInputStream fileInputStream = new FileInputStream(new File("TestFileChannel.txt"));
        FileChannel fileChannel = fileInputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(5);

        int readLen = fileChannel.read(byteBuffer); //读到5个字节的数据
        Assertions.assertEquals(5, readLen);

        //此处read()的返回值为0，因为byteBuffer的remaining没有剩余空间
        readLen = fileChannel.read(byteBuffer);
        Assertions.assertEquals(0, readLen);

        byteBuffer.clear();
        readLen = fileChannel.read(byteBuffer);
        Assertions.assertEquals(-1, readLen);   //达到流的末尾，所以值为-1

        fileChannel.close();
        fileInputStream.close();
    }

    /**测试FileChannel.write(ByteBuffer, long position)方法。用于将Buffer中的数据写入通道的指定位置。
     * 参数position代表开始写入的文件位置，必须为非负数。如果给定的位置大于文件的当前大小，则文件将扩大以容纳新的字节，在以前文件末尾和新写入字节之间的字节值是未指定的。
     * 【注意】此方法不会修改通道的位置！
     * */
    @Test
    public void testWritePosition() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("TestFileChannel.txt");
        FileChannel fileChannel = fileOutputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.wrap("abcde".getBytes());

        fileChannel.write(byteBuffer);
        Assertions.assertEquals(5, fileChannel.position());
        byteBuffer.rewind();
        fileChannel.write(byteBuffer, 2);
        //此处通道的position仍为5，而不会是7。
        Assertions.assertEquals(5, fileChannel.position());

        fileChannel.close();
        fileOutputStream.close();
    }
}
