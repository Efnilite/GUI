package dev.efnilite.gui.util.collections;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class ViMap<K, V> extends HashMap<K, V> {

    public Map<K, V> sortKeys() {
        return entrySet()
                .stream()
                .sorted()
                .collect(Collectors.toMap(
                        Entry::getKey,
                        Entry::getValue,
                        (a, b) -> a,
                        LinkedHashMap::new
                ));
    }

    public Map<K, V> sortValues() {
        return entrySet()
                .stream()
                .sorted()
                .collect(Collectors.toMap(
                        Entry::getKey,
                        Entry::getValue,
                        (a, b) -> a,
                        LinkedHashMap::new
                ));
    }


    public K randomKey() {
        List<K> keys = new ArrayList<>(keySet());
        return keys.get(ThreadLocalRandom.current().nextInt(keys.size() - 1));
    }

    public V randomValue() {
        List<V> values = new ArrayList<>(values());
        return values.get(ThreadLocalRandom.current().nextInt(values.size() - 1));
    }
}
