package ru.nabokovsg.equipmentunit.search;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SearchServiceImpl implements SearchService {

    @Override
    public boolean search(String search, List<String> names) {
        final String searchName = search.toLowerCase();
        for (String name : names) {
            if (name.toLowerCase().contains(searchName)) {
                return true;
            }
        }
        return false;
    }
}