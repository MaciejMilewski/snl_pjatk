package pl.edu.pjwstk.snl.news;

import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NewsService {
    private final NewsRepository newsRepository;

    public Page<News> findAllWithPagination(Pageable paging) {
        return this.newsRepository.findAll(paging);
    }

    public List<News> findAll() {
        return this.newsRepository.findAll();
    }

    public Optional<News> findById(long id) {
        return this.newsRepository.findById(id);
    }

    public News save(News news) {
        return this.newsRepository.save(news);
    }

    public void deleteById(long id) {
        this.newsRepository.deleteById(id);
    }

    public List<News> saveAll(List<News> news) {
        return newsRepository.saveAll(news);
    }
}
