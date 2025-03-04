package com.taein.comprehensive_practice.repository;

import java.util.List;

public interface Storage<T> {
    public void save(List<T> list);
    public List<T> load();
}
