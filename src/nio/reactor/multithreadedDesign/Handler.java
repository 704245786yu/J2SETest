package nio.reactor.multithreadedDesign;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Handler implements Runnable{
    ExecutorService executorService = Executors.newCachedThreadPool();
    final SocketChannel socketChannel;
    final SelectionKey selectionKey;
    ByteBuffer input = ByteBuffer.allocate(1024);
    ByteBuffer output = ByteBuffer.allocate(1024);
    static final int READING = 0, SENDING = 1;
    static final int PROCESSING = 3;
    int state = READING;

    public Handler(Selector selector, SocketChannel socketChannel) throws IOException {
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
    public void run() {
        try {
            if(state == READING)
                read();
            else if(state == SENDING)
                send();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*这里为何要用synchronized？*/
    synchronized void read() throws IOException {
        socketChannel.read(input);
        if(inputIsComplete()){
            state = PROCESSING;
            executorService.execute(new Processer()); //非IO操作放到Worker线程池
        }
    }

    void send() throws IOException {
        socketChannel.write(output);
        if(outputIsComplete())
            selectionKey.cancel();
    }

    synchronized void processAndHandoff(){
        process();
        state = SENDING; // or rebind attachment
        selectionKey.interestOps(SelectionKey.OP_WRITE);
    }

    class Processer implements Runnable{
        @Override
        public void run() {
            processAndHandoff();
        }
    }
}
