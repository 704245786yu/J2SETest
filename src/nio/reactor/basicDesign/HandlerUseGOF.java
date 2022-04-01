package nio.reactor.basicDesign;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**基于GoF状态对象模式对Handler类的一个优化实现，不需要再进行状态的判断*/
public class HandlerUseGOF implements Runnable{
    final SocketChannel socketChannel;
    final SelectionKey selectionKey;
    ByteBuffer input = ByteBuffer.allocate(1024);
    ByteBuffer output = ByteBuffer.allocate(1024);

    public HandlerUseGOF(Selector selector, SocketChannel socketChannel) throws IOException {
        this.socketChannel = socketChannel;
        socketChannel.configureBlocking(false);
        selectionKey = socketChannel.register(selector, 0);
        selectionKey.attach(this);
        selectionKey.interestOps(SelectionKey.OP_READ);
        selector.wakeup();
    }

    boolean inputIsComplete(){/*...*/ return true;}
    boolean outputIsComplete(){/*...*/ return true;}
    void process(){/*...*/}

    @Override
    public void run() { //inital state is reader
        try {
            socketChannel.read(input);
            if(inputIsComplete()){
                process();
                selectionKey.attach(new Sender());
                selectionKey.interestOps(SelectionKey.OP_WRITE);
                selectionKey.selector().wakeup();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class Sender implements Runnable{
        @Override
        public void run() {
            try {
                socketChannel.write(output);
                if(outputIsComplete())
                    selectionKey.cancel();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
