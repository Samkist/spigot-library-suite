package net.lumae.core.api;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

public class APIResult<K, V> implements APIResponse<K, V> {
    private Function<K, V> resultFunction;
    private K input;
    private V result;

    protected APIResult(Function<K, V> resultFunction, K input) {
        this.resultFunction = resultFunction;
        this.input = input;
    }


    @Override
    public V queue() {
        this.result = resultFunction.apply(input);
        return result;
    }

    @Override
    public V queue(BiConsumer<K, V> success) {
        queue();
        success.accept(input, result);
        return result;
    }

    @Override
    public V queue(Consumer<V> success) {
        queue();
        success.accept(result);
        return result;
    }
}
