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
                new News("W sklepach poawił się nowy typ kija bramkarskiego. Zapraszamy do sklepów na prezentację.Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Imperdiet proin fermentum leo vel. Faucibus in ornare quam viverra orci sagittis. Leo duis ut diam quam nulla porttitor massa id neque. Sem viverra aliquet eget sit amet tellus cras. Orci a scelerisque purus semper eget duis at. Morbi quis commodo odio aenean sed adipiscing diam donec. Tempor commodo ullamcorper a lacus. Nam libero justo laoreet sit amet. Non blandit massa enim nec dui nunc mattis enim ut. Risus ultricies tristique nulla aliquet enim tortor. Amet consectetur adipiscing elit duis tristique. Pellentesque nec nam aliquam sem et.\n" +
                        "\n" +
                        "Mauris rhoncus aenean vel elit scelerisque. Nunc sed blandit libero volutpat. Faucibus nisl tincidunt eget nullam non. Eleifend donec pretium vulputate sapien nec sagittis aliquam. Sapien nec sagittis aliquam malesuada bibendum arcu. Donec adipiscing tristique risus nec. Viverra tellus in hac habitasse. Vulputate sapien nec sagittis aliquam malesuada bibendum arcu vitae elementum. Auctor elit sed vulputate mi. Enim sit amet venenatis urna. Eget mi proin sed libero enim sed faucibus turpis in. Fusce ut placerat orci nulla pellentesque dignissim enim.\n" +
                        "\n" +
                        "Facilisis mauris sit amet massa vitae tortor condimentum lacinia quis. Quam quisque id diam vel quam elementum. Eleifend mi in nulla posuere sollicitudin aliquam ultrices sagittis. Adipiscing at in tellus integer feugiat. Posuere sollicitudin aliquam ultrices sagittis orci a. Euismod in pellentesque massa placerat duis. Tortor dignissim convallis aenean et tortor at. A diam maecenas sed enim. Ipsum a arcu cursus vitae congue mauris rhoncus aenean vel. Egestas diam in arcu cursus euismod. Nisi quis eleifend quam adipiscing vitae proin sagittis nisl. Fermentum posuere urna nec tincidunt praesent semper feugiat nibh.", "Tomasz Golob", LocalDate.of(2222,1,22),"Nowy kij bramkarski", getNewsImage("kij.jpg")),
                new News("Dnia 21.10.2022 byliśmy świadkiem wielkiej wygranej drużyny Wilków. Pokonali oni na wyjeździe druynę z Nowego Targu.Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Imperdiet proin fermentum leo vel. Faucibus in ornare quam viverra orci sagittis. Leo duis ut diam quam nulla porttitor massa id neque. Sem viverra aliquet eget sit amet tellus cras. Orci a scelerisque purus semper eget duis at. Morbi quis commodo odio aenean sed adipiscing diam donec. Tempor commodo ullamcorper a lacus. Nam libero justo laoreet sit amet. Non blandit massa enim nec dui nunc mattis enim ut. Risus ultricies tristique nulla aliquet enim tortor. Amet consectetur adipiscing elit duis tristique. Pellentesque nec nam aliquam sem et.\n" +
                        "\n" +
                        "Mauris rhoncus aenean vel elit scelerisque. Nunc sed blandit libero volutpat. Faucibus nisl tincidunt eget nullam non. Eleifend donec pretium vulputate sapien nec sagittis aliquam. Sapien nec sagittis aliquam malesuada bibendum arcu. Donec adipiscing tristique risus nec. Viverra tellus in hac habitasse. Vulputate sapien nec sagittis aliquam malesuada bibendum arcu vitae elementum. Auctor elit sed vulputate mi. Enim sit amet venenatis urna. Eget mi proin sed libero enim sed faucibus turpis in. Fusce ut placerat orci nulla pellentesque dignissim enim.\n" +
                        "\n" +
                        "Facilisis mauris sit amet massa vitae tortor condimentum lacinia quis. Quam quisque id diam vel quam elementum. Eleifend mi in nulla posuere sollicitudin aliquam ultrices sagittis. Adipiscing at in tellus integer feugiat. Posuere sollicitudin aliquam ultrices sagittis orci a. Euismod in pellentesque massa placerat duis. Tortor dignissim convallis aenean et tortor at. A diam maecenas sed enim. Ipsum a arcu cursus vitae congue mauris rhoncus aenean vel. Egestas diam in arcu cursus euismod. Nisi quis eleifend quam adipiscing vitae proin sagittis nisl. Fermentum posuere urna nec tincidunt praesent semper feugiat nibh.", "Artur Stonoga", LocalDate.of(2222,2,22),"Wielka wygrana Wilków", getNewsImage("wilk.jpg")),
                new News("Na lodowsiku w Gdańsku uległa awarii rolba dopielęgnacji lodu. Wszystkie mecze zostały odwołane.Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Imperdiet proin fermentum leo vel. Faucibus in ornare quam viverra orci sagittis. Leo duis ut diam quam nulla porttitor massa id neque. Sem viverra aliquet eget sit amet tellus cras. Orci a scelerisque purus semper eget duis at. Morbi quis commodo odio aenean sed adipiscing diam donec. Tempor commodo ullamcorper a lacus. Nam libero justo laoreet sit amet. Non blandit massa enim nec dui nunc mattis enim ut. Risus ultricies tristique nulla aliquet enim tortor. Amet consectetur adipiscing elit duis tristique. Pellentesque nec nam aliquam sem et.\n" +
                        "\n" +
                        "Mauris rhoncus aenean vel elit scelerisque. Nunc sed blandit libero volutpat. Faucibus nisl tincidunt eget nullam non. Eleifend donec pretium vulputate sapien nec sagittis aliquam. Sapien nec sagittis aliquam malesuada bibendum arcu. Donec adipiscing tristique risus nec. Viverra tellus in hac habitasse. Vulputate sapien nec sagittis aliquam malesuada bibendum arcu vitae elementum. Auctor elit sed vulputate mi. Enim sit amet venenatis urna. Eget mi proin sed libero enim sed faucibus turpis in. Fusce ut placerat orci nulla pellentesque dignissim enim.\n" +
                        "\n" +
                        "Facilisis mauris sit amet massa vitae tortor condimentum lacinia quis. Quam quisque id diam vel quam elementum. Eleifend mi in nulla posuere sollicitudin aliquam ultrices sagittis. Adipiscing at in tellus integer feugiat. Posuere sollicitudin aliquam ultrices sagittis orci a. Euismod in pellentesque massa placerat duis. Tortor dignissim convallis aenean et tortor at. A diam maecenas sed enim. Ipsum a arcu cursus vitae congue mauris rhoncus aenean vel. Egestas diam in arcu cursus euismod. Nisi quis eleifend quam adipiscing vitae proin sagittis nisl. Fermentum posuere urna nec tincidunt praesent semper feugiat nibh.", "Jan Rolbista", LocalDate.of(2222,3,22),"Awaria rolby na lodowisku", getNewsImage("rolba.jpg")),
                new News("Portal sponsorX.pl zostało głównym sponsorem lig amatorskich.Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Imperdiet proin fermentum leo vel. Faucibus in ornare quam viverra orci sagittis. Leo duis ut diam quam nulla porttitor massa id neque. Sem viverra aliquet eget sit amet tellus cras. Orci a scelerisque purus semper eget duis at. Morbi quis commodo odio aenean sed adipiscing diam donec. Tempor commodo ullamcorper a lacus. Nam libero justo laoreet sit amet. Non blandit massa enim nec dui nunc mattis enim ut. Risus ultricies tristique nulla aliquet enim tortor. Amet consectetur adipiscing elit duis tristique. Pellentesque nec nam aliquam sem et.\n" +
                        "\n" +
                        "Mauris rhoncus aenean vel elit scelerisque. Nunc sed blandit libero volutpat. Faucibus nisl tincidunt eget nullam non. Eleifend donec pretium vulputate sapien nec sagittis aliquam. Sapien nec sagittis aliquam malesuada bibendum arcu. Donec adipiscing tristique risus nec. Viverra tellus in hac habitasse. Vulputate sapien nec sagittis aliquam malesuada bibendum arcu vitae elementum. Auctor elit sed vulputate mi. Enim sit amet venenatis urna. Eget mi proin sed libero enim sed faucibus turpis in. Fusce ut placerat orci nulla pellentesque dignissim enim.\n" +
                        "\n" +
                        "Facilisis mauris sit amet massa vitae tortor condimentum lacinia quis. Quam quisque id diam vel quam elementum. Eleifend mi in nulla posuere sollicitudin aliquam ultrices sagittis. Adipiscing at in tellus integer feugiat. Posuere sollicitudin aliquam ultrices sagittis orci a. Euismod in pellentesque massa placerat duis. Tortor dignissim convallis aenean et tortor at. A diam maecenas sed enim. Ipsum a arcu cursus vitae congue mauris rhoncus aenean vel. Egestas diam in arcu cursus euismod. Nisi quis eleifend quam adipiscing vitae proin sagittis nisl. Fermentum posuere urna nec tincidunt praesent semper feugiat nibh.", "Kazimierz Sponsorski", LocalDate.of(2222,4,22),"sponsorX.pl nowym sponsorem", getNewsImage("sponsor.jpg"))
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
