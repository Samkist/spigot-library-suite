package net.lumae.database.wrapper;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

public class DatabaseOperation<Scope, R>  {

    private Function<Scope, R> resultFunction;
    private Scope scope;
    private R result;

    public DatabaseOperation(Function<Scope, R> resultFunction, Scope scope) {
        this.resultFunction = resultFunction;
        this.scope = scope;
    }

    public R queue() {
        this.result = resultFunction.apply(scope);
        return result;
    }

    public R queue(BiConsumer<Scope, R> success) {
        queue();
        success.accept(scope, result);
        return result;
    }

    public R queue(Consumer<R> success) {
        queue();
        success.accept(result);
        return result;
    }
}
