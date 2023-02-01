package pl.edu.pjwstk.snl.sponsors;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode= DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class SponsorServiceTest {

    @Mock
    private SponsorRepository sponsorRepository;

    @Autowired
    private SponsorService sponsorService;

    @Test
    void findAll() {
        List<Sponsor> sponsors = sponsorService.findAll();

        Assertions.assertTrue(sponsors.size() == 6);
    }

    @Test
    void findById() {
        Assertions.assertTrue(sponsorService.findById(1L).get().getName().equals("Sponsor lig amatorskich"));
    }

    @Test
    void save() {
        sponsorService.save(Sponsor.builder()
                .name("Mostostal")
                .build()
        );

        Assertions.assertTrue(sponsorService.existsByName("Mostostal"));
    }

    @Test
    void deleteById() {
        sponsorService.save(Sponsor.builder()
                .name("Mostostal")
                .build()
        );

        sponsorService.deleteByName("Mostostal");

        Assertions.assertFalse(sponsorService.existsByName("Mostostal"));
    }

    @Test
    void saveAll() {
        List<Sponsor> sponsors = new ArrayList<>();

        sponsors.add(
                Sponsor.builder()
                .name("Mostostal")
                .build()
        );

        sponsors.add(
                Sponsor.builder()
                        .name("SkiLife2000")
                        .build()
        );

        sponsorService.saveAll(sponsors);

        Assertions.assertTrue(sponsorService.existsByName("Mostostal"));
        Assertions.assertTrue(sponsorService.existsByName("SkiLife2000"));
    }
}