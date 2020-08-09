package com.coohua.base.autoservice;

import java.util.ServiceLoader;

public class CoohuaServiceLoader {
    private CoohuaServiceLoader() {
    }

    public static <S> S load(Class<S> service) {
        try {
            return ServiceLoader.load(service).iterator().next();

        } catch (Exception e) {
            return null;
        }
    }
}
