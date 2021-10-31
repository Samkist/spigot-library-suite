package net.lumae.core.api;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public interface APIResponse<K, V> {
    V queue();

    V queue(BiConsumer<K, V> success);

    V queue(Consumer<V> success);
}
