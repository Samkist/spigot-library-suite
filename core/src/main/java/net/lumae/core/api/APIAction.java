package net.lumae.core.api;

import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class APIAction<K, V>  implements APIResponse<K, V> {

    private final Consumer<K> action;
    private final BiConsumer<K, V> biAction;
    private final K applied;
    private final V effect;

    protected APIAction(Consumer<K> action, K applied, V effect) {
        this.action = action;
        this.biAction = null;
        this.applied = applied;
        this.effect = effect;

    }

    protected APIAction(BiConsumer<K, V> biAction, K applied, V effect) {
        this.action = null;
        this.biAction = biAction;
        this.applied = applied;
        this.effect = effect;
    }

    public V queue() {
        if(Objects.nonNull(action)) {
            action.accept(applied);
        } else {
            biAction.accept(applied, effect);
        }
        return effect;
    }

    public V queue(BiConsumer<K, V> success) {
        queue();
        success.accept(applied, effect);
        return effect;
    }

    public V queue(Consumer<V> success) {
        queue();
        success.accept(effect);
        return effect;
    }

}
