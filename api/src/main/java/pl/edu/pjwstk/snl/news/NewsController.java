package pl.edu.pjwstk.snl.news;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.pjwstk.snl.util.PageResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class NewsController {
    private final NewsService newsService;

    @GetMapping("/news")
    public ResponseEntity<Map<String, Object>> getNewsWithPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ) {
        try {
            Pageable paging = PageRequest.of(page, size);
            List<News> news = new ArrayList<>();
            Page<News> pageNews;

            pageNews = newsService.findAllWithPagination(paging);
            news = pageNews.getContent();

            if (news.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            Map<String, Object> response = new PageResponse<News>().getPageResponseMap(pageNews);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/news/all")
    public ResponseEntity<Map<String, Object>> getAllNews() {
        try {
            List<News> news = newsService.findAll();

            if (news.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            Map<String, Object> response = new HashMap<>();
            response.put("news", news);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/news/{id}")
    public ResponseEntity<News> getNewsById(@PathVariable("id") long id) {
        Optional<News> news = newsService.findById(id);

        return news.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/news")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<News> createNews(
            @RequestParam String description,
            @RequestParam String author,
            @RequestParam String date,
            @RequestParam String title,
            @RequestParam MultipartFile image
    ) {
        try {
            newsService.save(new News(
                    description,
                    author,
                    LocalDate.parse(date),
                    title,
                    image.getBytes()
            ));
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/news/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<News> updateNews
            (@PathVariable("id") long id,
             @RequestParam String description,
             @RequestParam String author,
             @RequestParam String date,
             @RequestParam String title,
             @RequestParam MultipartFile image) throws IOException {
        Optional<News> newsData = newsService.findById(id);

        if (newsData.isPresent()) {
            News newsDao = newsData.get();

            newsDao.setDescription(description);
            newsDao.setAuthor(author);
            newsDao.setDate(LocalDate.parse(date));
            newsDao.setTitle(title);
            newsDao.setImage(image.getBytes());

            newsService.save(newsDao);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/news/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> deleteNews(@PathVariable("id") long id) {
        try {
            newsService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
