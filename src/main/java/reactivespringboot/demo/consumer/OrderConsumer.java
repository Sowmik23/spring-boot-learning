package reactivespringboot.demo.consumer;

import java.util.function.Consumer;

public class OrderConsumer implements Consumer<String> {
    @Override
    public void accept(String data) {
        System.out.println(data);
    }

    @Override
    public Consumer<String> andThen(Consumer<? super String> after) {
        return null;
    }
}
