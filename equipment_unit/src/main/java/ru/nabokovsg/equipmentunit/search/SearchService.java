package ru.nabokovsg.equipmentunit.search;

import java.util.List;

public interface SearchService {

    boolean search(String search, List<String> names);
}