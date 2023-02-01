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
import pl.edu.pjwstk.snl.sponsors.Sponsor;
import pl.edu.pjwstk.snl.sponsors.SponsorRepository;
import pl.edu.pjwstk.snl.sponsors.SponsorService;


import java.util.List;

import static org.springframework.core.io.ResourceLoader.CLASSPATH_URL_PREFIX;

@Component
public class SponsorsLoader implements ApplicationRunner {

    private final SponsorService sponsorService;
    private final ResourceLoader resourceLoader;


    @Autowired
    public SponsorsLoader( SponsorService sponsorService, ResourceLoader resourceLoader) {
        this.sponsorService = sponsorService;
        this.resourceLoader = resourceLoader;

    }


    public void run(ApplicationArguments args) throws IOException {



        List<Sponsor> sponsors = List.of(
                new Sponsor("Sponsor lig amatorskich", getSponsorImage("sponsorX.jpg")),
                new Sponsor("Sponsor zawodnikow", getSponsorImage("sponsor.png")),
                new Sponsor("Sponsor ligi", getSponsorImage("sponsor2.jpg")),
                new Sponsor("Sponsor sędziów", getSponsorImage("sponsor3.jpg")),
                new Sponsor("Sponsor upominków", getSponsorImage("sponsor4.jpg")),
                new Sponsor("Sponsor krązków", getSponsorImage("sponsor5.jpg"))
        );

        sponsorService.saveAll(sponsors);
    }
    private byte[] getSponsorImage(String imageName) throws IOException {
        try {
            Path path = Paths.get("/maciek/lib/resources/pl/edu/pjwstk/snl/config/dataloader/sponsor/" + imageName);
            return Files.readAllBytes(path);
        } catch (Exception e) {
            return null;
        }
    }
}
