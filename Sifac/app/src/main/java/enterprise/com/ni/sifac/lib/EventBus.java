package enterprise.com.ni.sifac.lib;

/**
 * Created by STARK on 15/06/2016.
 */
public interface EventBus {

    void register(Object subscriber);
    void unregister(Object subscriber);
    void post(Object event);
}
