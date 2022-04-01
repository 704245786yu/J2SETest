package nio.reactor.basicDesign;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class Reactor implements Runnable{

    final Selector selector;
    final ServerSocketChannel serverSocketChannel;

    public Reactor(int port) throws IOException {
        selector = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(port));
        serverSocketChannel.configureBlocking(false);
        SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        selectionKey.attach(new Acceptor());
    }

    /**Dispatch Loop 分发循环*/
    @Override
    public void run() {
        try{
            while( !Thread.interrupted() ){
                selector.select();
                Set<SelectionKey> selected = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selected.iterator();
                while( iterator.hasNext() ){
                    dispatch(iterator.next());
                }
                selected.clear();
            }
        }catch (IOException ex){
            /*...*/
        }
    }

    void dispatch(SelectionKey selectionKey){
        Runnable r = (Runnable) selectionKey.attachment();
        if (r != null)
            r.run();
    }

    /**注意此处的设计方式：通过将Acceptor添加到SelectionKey，省去了SelectionKey.isAcceptable()这样的判断*/
    class Acceptor implements Runnable{
        @Override
        public void run() {
            try{
                SocketChannel socketChannel = serverSocketChannel.accept();
                if(socketChannel != null)
                    new Handler(selector, socketChannel);
            }catch (IOException ex){
                /*...*/
            }
        }
    }

    Selector[] selectors;   //Selector集合，每个Selector对应一个subReactor线程
    int next = 0;
    class MainReactorAcceptor implements Runnable{

        @Override
        public synchronized void run() {
            try {
                SocketChannel socketChannel = serverSocketChannel.accept();
                if (socketChannel != null)
                    new Handler(selectors[next], socketChannel);
                if(++next == selectors.length)
                    next = 0;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
