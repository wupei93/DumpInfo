package wupei.dumpinfo.common.http;

@FunctionalInterface
public interface Callback<P, R> {

    public R call(P param);
}
