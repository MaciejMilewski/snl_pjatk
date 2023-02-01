package pl.edu.pjwstk.snl.news;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import pl.edu.pjwstk.snl.player.Player;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode= DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class NewsServiceTest {

    @Autowired
    private NewsService newsService;

    @Test
    void findAll() {
        List<News> news = newsService.findAll();

        Assertions.assertTrue(news.size() == 4);
    }

    @Test
    void findById() {
        Assertions.assertTrue(newsService.findById(1L).get().getTitle().equals("Nowy kij bramkarski"));
    }

    @Test
    void save() {
        newsService.save(News.builder().title("Nowy sezon 2024").build());

        Assertions.assertTrue(newsService.findById(5L).get().getTitle().equals("Nowy sezon 2024"));
    }

    @Test
    void deleteById() {
        newsService.deleteById(1L);

        Assertions.assertFalse(newsService.existsById(1L));
    }
}