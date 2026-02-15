package ru.nabokovsg.referencebooks.search;

import java.util.List;

public interface SearchService {

    boolean search(String search, List<String> names);
}