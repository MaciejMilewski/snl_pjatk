package pl.edu.pjwstk.snl.sponsors;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SponsorController {
    private final SponsorService sponsorService;

    @GetMapping("/sponsors")
    public ResponseEntity<Map<String, Object>> getAllSponsors() {
        try {
            List<Sponsor> sponsors = sponsorService.findAll();

            if (sponsors.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            Map<String, Object> response = new HashMap<>();
            response.put("sponsors", sponsors);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/sponsors/{id}")
    public ResponseEntity<Sponsor> getSponsorById(@PathVariable("id") long id) {
        Optional<Sponsor> sponsors = sponsorService.findById(id);

        return sponsors.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/sponsors")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Sponsor> createSponsor(
            @RequestParam String name,
            @RequestParam MultipartFile image
    ) {
        try {
            sponsorService.save(new Sponsor(
                    name,
                    image.getBytes()
            ));
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/sponsors/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Sponsor> updateSponsor
            (@PathVariable("id") long id,
             @RequestParam String name,
             @RequestParam MultipartFile image) throws IOException {
        Optional<Sponsor> sponsorData = sponsorService.findById(id);

        if (sponsorData.isPresent()) {
            Sponsor sponsorDao = sponsorData.get();

            sponsorDao.setName(name);
            sponsorDao.setImage(image.getBytes());

            sponsorService.save(sponsorDao);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/sponsors/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> deleteSponsor(@PathVariable("id") long id) {
        try {
            sponsorService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
