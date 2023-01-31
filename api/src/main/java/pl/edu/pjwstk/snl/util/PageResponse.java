package pl.edu.pjwstk.snl.util;

import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.Map;

public class PageResponse<T> {

    public PageResponse() {
    }

    public Map<String, Object> getPageResponseMap(Page<T> pageableCollection) {
        Map<String, Object> response = new HashMap<>();
        response.put("data", pageableCollection.getContent());
        response.put("currentPageIndex",pageableCollection.getNumber());
        response.put("totalPages", pageableCollection.getTotalPages());
        response.put("isLastPage", pageableCollection.getTotalPages() - 1 == pageableCollection.getNumber());

        return response;
    }
}
