package dev.efnilite.gui.util.collections;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

// todo finish
public class ViList<T> extends ArrayList<T> {
    private List<T> list;
    public ViList(@NotNull Collection<? extends T> collection) {
        super(collection);

        this.list = new ArrayList<>(collection);
    }

    public ViList<T> sort() {
        list = list.stream()
                .sorted()
                .collect(Collectors.toList());
        return this;
    }

    public T first() {
        return list.get(0);
    }

    public T last() {
        return list.get(list.size() - 1);
    }

    public List<T> toList() {
        return list;
    }
}