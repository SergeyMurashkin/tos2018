package net.thumbtack.school.backend.threads.exercise_12;

import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class  MyAnalogConcurrentHashMap<K,V> {

    private Map<K,V> map;
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    MyAnalogConcurrentHashMap(Map<K,V> map){
        this.map = map;
    }

    public V get(K k) {
        lock.readLock().lock();
        V v = map.get(k);
        lock.readLock().unlock();
        return v;
    }

    public void put(K key, V value){
        lock.writeLock().lock();
        map.put(key, value);
        lock.writeLock().unlock();
    }


}
