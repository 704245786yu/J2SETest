package nio.buffer;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.InvalidMarkException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**演示Buffer类的基本使用方法
 * @author zhiyu
 * @Date 2019-11-09
 */
public class BasicUse {
    /**通过XXXBuffer.wrap()来创建7种Buffer。
     * 通过7个Buffer子类各自的静态wrap()将对应数据类型的array包装（wrap）进缓冲区中。
     * wrap()相当于创建这些缓冲区的工厂方法。
     * */
    @Test
    public void wrap(){
        byte[] byteAry = new byte[]{1,2,3};
        char[] charAry = new char[]{'a','b','c','d'};

        ByteBuffer byteBuffer = ByteBuffer.wrap(byteAry);
        CharBuffer charBuffer = CharBuffer.wrap(charAry);
        Assertions.assertEquals("java.nio.HeapByteBuffer", byteBuffer.getClass().getName());
        Assertions.assertEquals("java.nio.HeapCharBuffer", charBuffer.getClass().getName());
        Assertions.assertEquals(3, byteBuffer.capacity(), "byteBuffer.capacity");
        Assertions.assertEquals(4, charBuffer.capacity(), "charBuffer.capacity");
    }

    /**Buffer的limit属性代表Buffer中第一个不应该读取或写入元素的index。只有在limit之前的数组元素才能读取或写入。*/
    @Test
    public void limit(){
        char[] charAry = new char[]{'a','b','c','d','e'};
        CharBuffer buffer = CharBuffer.wrap(charAry);
        Assertions.assertEquals(5, buffer.capacity());
        Assertions.assertEquals(5, buffer.limit());

        buffer.limit(3);
        Assertions.assertEquals(5, buffer.capacity());
        Assertions.assertEquals(3, buffer.limit());
        buffer.put(0,'o');
        buffer.put(1,'p');
        buffer.put(2,'q');
        //从index=3开始不可读和写，所以会从这里抛IndexOutOfBoundsException。
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {buffer.put(3,'r');});
    }

    /**Buffer的position属性代表“下一个”要读取或写入元素的index*/
    @Test
    public void position(){
        char[] charAry = new char[]{'a','b','c','d','e'};
        CharBuffer buffer = CharBuffer.wrap(charAry);
        Assertions.assertEquals(5, buffer.capacity());
        Assertions.assertEquals(5, buffer.limit());
        Assertions.assertEquals(0, buffer.position());

        buffer.position(2);
        Assertions.assertEquals(5, buffer.capacity());
        Assertions.assertEquals(5, buffer.limit());
        Assertions.assertEquals(2, buffer.position());
        buffer.put('z');
        Assertions.assertArrayEquals(buffer.array(), new char[]{'a','b','z','d','e'});
    }

    /**remaining()返回当前位置position与limit之间的元素数，即剩余的可读/可写元素数*/
    @Test
    public void remaining(){
        char[] charAry = new char[]{'a','b','c','d','e'};
        CharBuffer buffer = CharBuffer.wrap(charAry);
        buffer.position(2);
        Assertions.assertEquals(2, buffer.position());
        Assertions.assertEquals(3, buffer.remaining());
    }

    /**mark()用于在此缓冲区的当前位置position设置标记。
     * 用于之后调用reset()时，将Buffer的position重置为mark的值。*/
    @Test
    public void mark_reset(){
        byte[] byteAry = new byte[]{1,2,3};
        ByteBuffer buffer = ByteBuffer.wrap(byteAry);
        buffer.position(1);
        buffer.mark();  //在位置1设置mark
        Assertions.assertEquals(1, buffer.position());
        buffer.position(2); //改变位置
        Assertions.assertEquals(2, buffer.position());
        buffer.reset(); //设置position = mark
        Assertions.assertEquals(1, buffer.position());
    }

    /**clear()用于还原缓冲区到初始状态。设置position=0，limit=capacity，mark=-1。
     * 使用场景：在对buffer存储数据之前调用此方法。
     * 注意：clear()并不真正清除buffer中的数据。
     * */
    @Test
    public void clear(){
        byte[] byteAry = new byte[]{1,2,3};
        ByteBuffer buffer = ByteBuffer.wrap(byteAry);
        buffer.position(2);
        buffer.limit(1);
        buffer.mark();

        buffer.clear();
        Assertions.assertEquals(0, buffer.position());
        Assertions.assertEquals(3, buffer.limit());
        //clear()会设置mark=-1，reset()碰到mark=-1会抛异常
        Assertions.assertThrows(InvalidMarkException.class, () -> {buffer.reset();}, "buffer的mark=-1");
    }

    /**flip()用于对缓冲区进行反转，设置limit=position, position=0, mark=-1。
     * flip()的通俗解释是“缩小limit的范围”。常用于切换读写模式。
     * 使用场景：当向buffer存储数据，再从buffer中读取这些数据时，就需使用flip()。
     * */
    @Test
    public void flip(){
        byte[] byteAry = new byte[]{1,2,3,4,5};
        ByteBuffer buffer = ByteBuffer.wrap(byteAry);
        buffer.position(2);
        buffer.mark();
        Assertions.assertEquals(2, buffer.position());
        buffer.flip();
        Assertions.assertEquals(0, buffer.position());
        Assertions.assertEquals(2, buffer.limit());
        //由于mark被flip()设置为-1，所以此处会抛异常
        Assertions.assertThrows(InvalidMarkException.class, () -> {buffer.reset();}, "buffer的mark=-1");
    }

    /**重绕缓冲区，是buffer为“重新读取”已包含的数据做好准备。
     * rewind()做了两个操作：position=0，mark=-1。<u>该方法常在重新读取缓冲区中数据时使用</u>。<br></br>
     * 使用场景：在“重新写入或获取”的操作之前调用此方法（假定已经适当设置了limit）。<br></br>
     * 场景例子：将buffer的ramaining剩余空间的数据输出到一个地方，执行rewind()，再将buffer中数据保存到另一个地方。
     * */
    @Test
    public void rewind(){
        byte[] byteAry = new byte[]{1,2,3,4,5};
        ByteBuffer buffer = ByteBuffer.wrap(byteAry);
        buffer.position(1);
        buffer.limit(3);
        buffer.mark();
        buffer.rewind();
        Assertions.assertEquals(0, buffer.position());
        Assertions.assertEquals(3, buffer.limit());
        //由于mark被rewind()设置为-1，所以此处会抛异常
        Assertions.assertThrows(InvalidMarkException.class, () -> {buffer.reset();}, "buffer的mark=-1");
    }

    /**直接缓冲区*/
    @Test
    public void allocateDirect(){
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(100);
        Assertions.assertTrue(byteBuffer.isDirect());
    }
}