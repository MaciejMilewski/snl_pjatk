package pl.edu.pjwstk.snl.config.dataloader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import pl.edu.pjwstk.snl.news.News;
import pl.edu.pjwstk.snl.news.NewsRepository;
import pl.edu.pjwstk.snl.news.NewsService;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.core.io.ResourceLoader.CLASSPATH_URL_PREFIX;


@Component
public class NewsLoader implements ApplicationRunner {

    private final ResourceLoader resourceLoader;
    private final NewsService newsService;

    @Autowired
    public NewsLoader(ResourceLoader resourceLoader, NewsService newsService) {
        this.resourceLoader = resourceLoader;
        this.newsService = newsService;
    }


    public void run(ApplicationArguments args) throws IOException {

        List<News> news = List.of(
                new News("W sklepach poawił się nowy typ kija bramkarskiego. Zapraszamy do sklepów na prezentację", "Tomasz Golob", LocalDate.of(2222,1,22),"Nowy kij bramkarski", getNewsImage("kij.jpg")),
                new News("Dnia 21.10.2022 byliśmy świadkiem wielkiej wygranej drużyny Wilków. Pokonali oni na wyjeździe druynę z Nowego Targu", "Artur Stonoga", LocalDate.of(2222,2,22),"Wielka wygrana Wilków", getNewsImage("wilk.jpg")),
                new News("Na lodowsiku w Gdańsku uległa awarii rolba dopielęgnacji lodu. Wszystkie mecze zostały odwołane", "Jan Rolbista", LocalDate.of(2222,3,22),"Awaria rolby na lodowisku", getNewsImage("rolba.jpg")),
                new News("Portal trójmiasto.pl zpsyało gównym sponsorem ligi TLH", "Kazimierz Sponsorski", LocalDate.of(2222,4,22),"Trójmiasto.pl nowym sponsorem", getNewsImage("logo.jpg"))
        );

        newsService.saveAll(news);
    }

    private byte[] getNewsImage(String imageName) throws IOException {
        try {
            Path path = Paths.get("/maciek/lib/resources/pl/edu/pjwstk/snl/config/dataloader/news/" + imageName);
            return Files.readAllBytes(path);
        } catch (Exception e) {
            return null;
        }
    }

}
