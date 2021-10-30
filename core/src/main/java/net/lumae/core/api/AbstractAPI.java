package net.lumae.core.api;

import net.lumae.core.Core;

public abstract class AbstractAPI {
    private final Core core;

    public AbstractAPI(Core core) {
        this.core = core;
    }
}
