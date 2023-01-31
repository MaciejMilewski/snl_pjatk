package pl.edu.pjwstk.snl.config.dataloader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import pl.edu.pjwstk.snl.player.Player;
import pl.edu.pjwstk.snl.player.PlayerRepository;
import pl.edu.pjwstk.snl.player.PlayerService;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

import static org.springframework.core.io.ResourceLoader.CLASSPATH_URL_PREFIX;

@Component
@Order(97)
public class PlayerLoader implements ApplicationRunner {

    private final PlayerService playerService;

    private final ResourceLoader resourceLoader;

    @Autowired
    public PlayerLoader(PlayerService playerService, ResourceLoader resourceLoader) {
        this.playerService = playerService;
        this.resourceLoader = resourceLoader;
    }

    @Transactional
    public void run(ApplicationArguments args) throws IOException {

        List<Player> players = List.of(
                new Player("Tomasz", "Nowak", 1, Player.Position.BR, LocalDate.of(1991,1,22), "Gdańsk", 76, Player.Stick.Prawy, getPlayersImage("player1.jpg")),
                new Player("Jan", "Kowalski", 2, Player.Position.BR, LocalDate.of(1976,2,2), "Kraków", 89, Player.Stick.Lewy, getPlayersImage("player2.jpg")),
                new Player("Stefan", "Krzyworyj", 3, Player.Position.PO, LocalDate.of(1990,3,13), "Chojnice", 83, Player.Stick.Prawy, getPlayersImage("player3.jpg")),
                new Player("Kazimierz", "Zawadiaka", 4, Player.Position.PS, LocalDate.of(1993,4,18), "Kościerzyna", 123, Player.Stick.Lewy, getPlayersImage("player4.jpg")),
                new Player("Maciej", "Blizna", 5, Player.Position.SR, LocalDate.of(2000,5,29), "Wejherowo", 90, Player.Stick.Lewy, getPlayersImage("player5.jpg")),
                new Player("Stefan", "Pierwszy", 6, Player.Position.PS, LocalDate.of(1991,11,22), "Gdańsk", 76, Player.Stick.Prawy, getPlayersImage("player5.jpg")),
                new Player("Kazimierz", "Drugi", 7, Player.Position.LO, LocalDate.of(1976,11,2), "Kraków", 89, Player.Stick.Lewy, getPlayersImage("player5.jpg")),
                new Player("Konstaty", "Trzeci", 8, Player.Position.PO, LocalDate.of(1990,11,13), "Chojnice", 83, Player.Stick.Prawy, getPlayersImage("player5.jpg")),
                new Player("Marek", "Dziekan", 9, Player.Position.PS, LocalDate.of(1993,11,18), "Kościerzyna", 123, Player.Stick.Lewy, getPlayersImage("player5.jpg")),
                new Player("Maciej", "Kierownik", 10, Player.Position.SR, LocalDate.of(2000,11,29), "Wejherowo", 90, Player.Stick.Lewy, getPlayersImage("player5.jpg")),

                new Player("Grzegorz", "Wielki", 21, Player.Position.LO, LocalDate.of(1991,2,22), "Gdańsk", 76, Player.Stick.Prawy, getPlayersImage("player5.jpg")),
                new Player("Grzegorz", "Mały", 22, Player.Position.LO, LocalDate.of(1976,2,2), "Kraków", 89, Player.Stick.Lewy, getPlayersImage("player5.jpg")),
                new Player("Tymoteusz", "Pietrzak", 1, Player.Position.PO, LocalDate.of(1990,2,13), "Chojnice", 83, Player.Stick.Prawy, getPlayersImage("player5.jpg")),
                new Player("Kazimiera", "Kowalska", 2, Player.Position.PS, LocalDate.of(1993,2,18), "Kościerzyna", 123, Player.Stick.Lewy, getPlayersImage("player5.jpg")),
                new Player("Joanna", "Kobita", 3, Player.Position.SR, LocalDate.of(2000,2,29), "Wejherowo", 90, Player.Stick.Lewy, getPlayersImage("player5.jpg")),

                new Player("Ildefons", "Gauczyński", 4, Player.Position.LO, LocalDate.of(1991,3,22), "Gdańsk", 76, Player.Stick.Prawy, getPlayersImage("player5.jpg")),
                new Player("Stefan", "Pazura", 5, Player.Position.LO, LocalDate.of(1976,3,2), "Kraków", 89, Player.Stick.Lewy, getPlayersImage("player5.jpg")),
                new Player("Karol", "Daje", 6, Player.Position.PO, LocalDate.of(1990,3,13), "Chojnice", 83, Player.Stick.Prawy, getPlayersImage("player5.jpg")),
                new Player("Marek", "Komina", 7, Player.Position.PS, LocalDate.of(1993,3,18), "Kościerzyna", 123, Player.Stick.Lewy, getPlayersImage("player5.jpg")),
                new Player("Maciej", "Pijany", 8, Player.Position.SR, LocalDate.of(2000,3,29), "Wejherowo", 90, Player.Stick.Lewy, getPlayersImage("player5.jpg")),

                new Player("Wiktor", "Przykładowy", 9, Player.Position.PS, LocalDate.of(1991,4,22), "Gdańsk", 76, Player.Stick.Prawy, getPlayersImage("player5.jpg")),
                new Player("Julian", "Miazga", 10, Player.Position.LO, LocalDate.of(1976,4,2), "Kraków", 89, Player.Stick.Lewy, getPlayersImage("player5.jpg")),
                new Player("Bartosz", "Kwiecień", 13, Player.Position.BR, LocalDate.of(1990,4,13), "Chojnice", 83, Player.Stick.Prawy, getPlayersImage("player5.jpg")),
                new Player("Bartłomiej", "Maj", 14, Player.Position.BR, LocalDate.of(1993,4,18), "Kościerzyna", 123, Player.Stick.Lewy, getPlayersImage("player5.jpg")),
                new Player("Szymon", "Konieczny", 15, Player.Position.SR, LocalDate.of(2000,4,29), "Wejherowo", 90, Player.Stick.Lewy, getPlayersImage("player5.jpg")),

                new Player("Jan", "Papierz", 16, Player.Position.LS, LocalDate.of(1991,5,22), "Gdańsk", 76, Player.Stick.Prawy, getPlayersImage("player5.jpg")),
                new Player("Kamil", "Mocarz", 17, Player.Position.LO, LocalDate.of(1976,5,2), "Kraków", 89, Player.Stick.Lewy, getPlayersImage("player5.jpg")),
                new Player("Kamil", "Poziomka", 19, Player.Position.PS, LocalDate.of(1993,5,18), "Kościerzyna", 123, Player.Stick.Lewy, getPlayersImage("player5.jpg")),
                new Player("Maciej", "Truskawka", 20, Player.Position.SR, LocalDate.of(2000,5,29), "Wejherowo", 90, Player.Stick.Lewy, getPlayersImage("player5.jpg")),

                new Player("Tomasz", "Skobry", 31, Player.Position.SR, LocalDate.of(1991,6,22), "Gdańsk", 76, Player.Stick.Prawy, getPlayersImage("player5.jpg")),
                new Player("Jan", "Psikutabezs", 34, Player.Position.LO, LocalDate.of(1976,6,2), "Kraków", 89, Player.Stick.Lewy, getPlayersImage("player5.jpg")),
                new Player("Stefan", "Kolejarz", 33, Player.Position.PO, LocalDate.of(1990,6,13), "Chojnice", 83, Player.Stick.Prawy, getPlayersImage("player5.jpg")),
                new Player("Andrzej", "Kowalski", 32, Player.Position.PS, LocalDate.of(1993,6,18), "Kościerzyna", 123, Player.Stick.Lewy, getPlayersImage("player5.jpg")),
                new Player("Maciej", "Kowal", 35, Player.Position.SR, LocalDate.of(2000,6,29), "Wejherowo", 90, Player.Stick.Lewy, getPlayersImage("player5.jpg")),

                new Player("Wiktor", "Skobry", 37, Player.Position.LS, LocalDate.of(1991,7,22), "Gdańsk", 76, Player.Stick.Prawy, getPlayersImage("player5.jpg")),
                new Player("Wiktorian", "Psikutabezs", 39, Player.Position.LO, LocalDate.of(1976,7,2), "Kraków", 89, Player.Stick.Lewy, getPlayersImage("player5.jpg")),
                new Player("Stefania", "Andrzejewski", 38, Player.Position.PO, LocalDate.of(1990,7,13), "Chojnice", 83, Player.Stick.Prawy, getPlayersImage("player5.jpg")),
                new Player("Jeremi", "Karolak", 44, Player.Position.PS, LocalDate.of(1993,7,18), "Kościerzyna", 123, Player.Stick.Lewy, getPlayersImage("player5.jpg")),
                new Player("Joan", "Migiel", 41, Player.Position.SR, LocalDate.of(2000,7,29), "Wejherowo", 90, Player.Stick.Lewy, getPlayersImage("player5.jpg")),

                new Player("Tomasz", "Koncita", 42, Player.Position.LS, LocalDate.of(1991,8,22), "Gdańsk", 76, Player.Stick.Prawy, getPlayersImage("player5.jpg")),
                new Player("Jan", "Wurst", 46, Player.Position.LO, LocalDate.of(1976,8,2), "Kraków", 89, Player.Stick.Lewy, getPlayersImage("player5.jpg")),
                new Player("Stefan", "Kiełbasa", 43, Player.Position.PO, LocalDate.of(1990,8,13), "Chojnice", 83, Player.Stick.Prawy, getPlayersImage("player5.jpg")),
                new Player("Andrzej", "Parówka", 45, Player.Position.PS, LocalDate.of(1993,8,18), "Kościerzyna", 123, Player.Stick.Lewy, getPlayersImage("player5.jpg")),
                new Player("Maciej", "Burak", 48, Player.Position.SR, LocalDate.of(2000,8,29), "Wejherowo", 90, Player.Stick.Lewy, getPlayersImage("player5.jpg")),


                new Player("Tomasz", "Koncita", 1, Player.Position.LS, LocalDate.of(1991,8,22), "Gdańsk", 76, Player.Stick.Prawy, getPlayersImage("player5.jpg")),
                new Player("Jan", "Wurst", 2, Player.Position.LO, LocalDate.of(1976,8,2), "Kraków", 89, Player.Stick.Lewy, getPlayersImage("player5.jpg")),
                new Player("Stefan", "Kiełbasa", 4, Player.Position.PO, LocalDate.of(1990,8,13), "Chojnice", 83, Player.Stick.Prawy, getPlayersImage("player5.jpg")),
                new Player("Andrzej", "Parówka", 6, Player.Position.PS, LocalDate.of(1993,8,18), "Kościerzyna", 123, Player.Stick.Lewy, getPlayersImage("player5.jpg")),
                new Player("Maciej", "Burak", 8, Player.Position.SR, LocalDate.of(2000,8,29), "Wejherowo", 90, Player.Stick.Lewy, getPlayersImage("player5.jpg")),

                new Player("Jan", "Zdatny", 21, Player.Position.LS, LocalDate.of(1991,8,22), "Gdańsk", 76, Player.Stick.Prawy, getPlayersImage("player5.jpg")),
                new Player("Jan", "Niezdatny", 55, Player.Position.LO, LocalDate.of(1976,8,2), "Kraków", 89, Player.Stick.Lewy, getPlayersImage("player5.jpg")),
                new Player("Stefan", "Kulisty", 33, Player.Position.PO, LocalDate.of(1990,8,13), "Chojnice", 83, Player.Stick.Prawy, getPlayersImage("player5.jpg")),
                new Player("Andrzej", "Zderzak", 66, Player.Position.PS, LocalDate.of(1993,8,18), "Kościerzyna", 123, Player.Stick.Lewy, getPlayersImage("player5.jpg")),
                new Player("Maciej", "Kalik", 88, Player.Position.SR, LocalDate.of(2000,8,29), "Wejherowo", 90, Player.Stick.Lewy, getPlayersImage("player5.jpg")),

                new Player("Tomasz", "Rozkwitalskiss", 86, Player.Position.LS, LocalDate.of(1991,8,22), "Gdańsk", 76, Player.Stick.Prawy, getPlayersImage("player5.jpg")),
                new Player("Jan", "Korneliusz", 45, Player.Position.LO, LocalDate.of(1976,8,2), "Kraków", 89, Player.Stick.Lewy, getPlayersImage("player5.jpg")),
                new Player("Stefan", "Wikariuszek", 79, Player.Position.PO, LocalDate.of(1990,8,13), "Chojnice", 83, Player.Stick.Prawy, getPlayersImage("player5.jpg")),
                new Player("Andrzej", "Politeryk", 43, Player.Position.PS, LocalDate.of(1993,8,18), "Kościerzyna", 123, Player.Stick.Lewy, getPlayersImage("player5.jpg")),
                new Player("Maciej", "Kurolt", 68, Player.Position.SR, LocalDate.of(2000,8,29), "Wejherowo", 90, Player.Stick.Lewy, getPlayersImage("player5.jpg")),

                new Player("Tomasz", "Stefański", 12, Player.Position.LS, LocalDate.of(1991,8,22), "Gdańsk", 76, Player.Stick.Prawy, getPlayersImage("player5.jpg")),
                new Player("Jan", "Nietali", 17, Player.Position.LO, LocalDate.of(1976,8,2), "Kraków", 89, Player.Stick.Lewy, getPlayersImage("player5.jpg")),
                new Player("Stefan", "Kierowkca", 19, Player.Position.PO, LocalDate.of(1990,8,13), "Chojnice", 83, Player.Stick.Prawy, getPlayersImage("player5.jpg")),
                new Player("Andrzej", "Pikoroal", 43, Player.Position.PS, LocalDate.of(1993,8,18), "Kościerzyna", 123, Player.Stick.Lewy, getPlayersImage("player5.jpg")),
                new Player("Maciej", "Wistuza", 23, Player.Position.SR, LocalDate.of(2000,8,29), "Wejherowo", 90, Player.Stick.Lewy, getPlayersImage("player5.jpg")),

                new Player("Tomasz", "Karila", 75, Player.Position.LS, LocalDate.of(1991,8,22), "Gdańsk", 76, Player.Stick.Prawy, getPlayersImage("player5.jpg")),
                new Player("Jan", "Witarla", 86, Player.Position.LO, LocalDate.of(1976,8,2), "Kraków", 89, Player.Stick.Lewy, getPlayersImage("player5.jpg")),
                new Player("Stefan", "Polako", 45, Player.Position.PO, LocalDate.of(1990,8,13), "Chojnice", 83, Player.Stick.Prawy, getPlayersImage("player5.jpg")),
                new Player("Andrzej", "Burako", 26, Player.Position.PS, LocalDate.of(1993,8,18), "Kościerzyna", 123, Player.Stick.Lewy, getPlayersImage("player5.jpg")),
                new Player("Maciej", "Slązako", 99, Player.Position.SR, LocalDate.of(2000,8,29), "Wejherowo", 90, Player.Stick.Lewy, getPlayersImage("player5.jpg")),

                new Player("Tomasz", "Koliteka", 87, Player.Position.LS, LocalDate.of(1991,8,22), "Gdańsk", 76, Player.Stick.Prawy, getPlayersImage("player5.jpg")),
                new Player("Jan", "Politekajs", 95, Player.Position.LO, LocalDate.of(1976,8,2), "Kraków", 89, Player.Stick.Lewy, getPlayersImage("player5.jpg")),
                new Player("Stefan", "Woalida", 57, Player.Position.PO, LocalDate.of(1990,8,13), "Chojnice", 83, Player.Stick.Prawy, getPlayersImage("player5.jpg")),
                new Player("Andrzej", "Pilotoka", 36, Player.Position.PS, LocalDate.of(1993,8,18), "Kościerzyna", 123, Player.Stick.Lewy, getPlayersImage("player5.jpg")),
                new Player("Maciej", "Suwerniksaa", 85, Player.Position.SR, LocalDate.of(2000,8,29), "Wejherowo", 90, Player.Stick.Lewy, getPlayersImage("player5.jpg")),

                new Player("Tomasz", "Palikata", 34, Player.Position.LS, LocalDate.of(1991,8,22), "Gdańsk", 76, Player.Stick.Prawy, getPlayersImage("player5.jpg")),
                new Player("Jan", "Kopalina", 43, Player.Position.LO, LocalDate.of(1976,8,2), "Kraków", 89, Player.Stick.Lewy, getPlayersImage("player5.jpg")),
                new Player("Stefan", "Wiktals", 26, Player.Position.PO, LocalDate.of(1990,8,13), "Chojnice", 83, Player.Stick.Prawy, getPlayersImage("player5.jpg")),
                new Player("Andrzej", "Wolista", 75, Player.Position.PS, LocalDate.of(1993,8,18), "Kościerzyna", 123, Player.Stick.Lewy, getPlayersImage("player5.jpg")),
                new Player("Maciej", "Krabina", 83, Player.Position.SR, LocalDate.of(2000,8,29), "Wejherowo", 90, Player.Stick.Lewy, getPlayersImage("player5.jpg")),

                new Player("Tomasz", "Kostaryka", 42, Player.Position.LS, LocalDate.of(1991,8,22), "Gdańsk", 76, Player.Stick.Prawy, getPlayersImage("player5.jpg")),
                new Player("Jan", "Nawalia", 46, Player.Position.LO, LocalDate.of(1976,8,2), "Kraków", 89, Player.Stick.Lewy, getPlayersImage("player5.jpg")),
                new Player("Stefan", "Politaka", 43, Player.Position.PO, LocalDate.of(1990,8,13), "Chojnice", 83, Player.Stick.Prawy, getPlayersImage("player5.jpg")),
                new Player("Andrzej", "Witalianka", 45, Player.Position.PS, LocalDate.of(1993,8,18), "Kościerzyna", 123, Player.Stick.Lewy, getPlayersImage("player5.jpg")),
                new Player("Maciej", "Poliwarea", 48, Player.Position.SR, LocalDate.of(2000,8,29), "Wejherowo", 90, Player.Stick.Lewy, getPlayersImage("player5.jpg")),

                new Player("Tomasz", "Stolarczyksa", 73, Player.Position.LS, LocalDate.of(1991,8,22), "Gdańsk", 76, Player.Stick.Prawy, getPlayersImage("player5.jpg")),
                new Player("Jan", "Sporny", 59, Player.Position.LO, LocalDate.of(1976,8,2), "Kraków", 89, Player.Stick.Lewy, getPlayersImage("player5.jpg")),
                new Player("Stefan", "Wiltu", 37, Player.Position.PO, LocalDate.of(1990,8,13), "Chojnice", 83, Player.Stick.Prawy, getPlayersImage("player5.jpg")),
                new Player("Andrzej", "Pilwad", 50, Player.Position.PS, LocalDate.of(1993,8,18), "Kościerzyna", 123, Player.Stick.Lewy, getPlayersImage("player5.jpg")),
                new Player("Maciej", "Porawski", 22, Player.Position.SR, LocalDate.of(2000,8,29), "Wejherowo", 90, Player.Stick.Lewy, getPlayersImage("player5.jpg"))
        );

        playerService.saveAll(players);

    }

    private byte[] getPlayersImage(String imageName) throws IOException {
        try {
            Path path = Paths.get("/maciek/lib/resources/pl/edu/pjwstk/snl/config/dataloader/players/" + imageName);
            return Files.readAllBytes(path);
        } catch (Exception e) {
            return null;
        }
    }

}
