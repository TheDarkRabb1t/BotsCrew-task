package tdr.task.botscrewtask.service;

import tdr.task.botscrewtask.model.entity.Lector;

import java.util.List;

public interface LectorService {
    List<Lector> globalSearchByValue(String value);
}