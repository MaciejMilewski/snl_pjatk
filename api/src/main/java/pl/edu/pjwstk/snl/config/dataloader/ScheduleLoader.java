package pl.edu.pjwstk.snl.config.dataloader;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import pl.edu.pjwstk.snl.schedule.Schedule;
import pl.edu.pjwstk.snl.schedule.ScheduleService;
import pl.edu.pjwstk.snl.team.TeamService;
import static org.springframework.core.io.ResourceLoader.CLASSPATH_URL_PREFIX;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

@Component
@Order(101)
@RequiredArgsConstructor
public class ScheduleLoader implements ApplicationRunner {
    private final ScheduleService scheduleService;
    private final TeamService teamService;
    private final ResourceLoader resourceLoader;

    @Transactional
    public void run(ApplicationArguments args) throws IOException {
        Schedule goldwellSchedule = new Schedule(
                "Plan spotkań na 2023",
                getScheduleImage("planGoldwell.jpg"),
                teamService.findByName("Goldwell").orElse(null)
        );

        Schedule dragonsSchedule = new Schedule(
                "Plan spotkań na 2023",
                getScheduleImage("planDragons.jpg"),
                teamService.findByName("Dragons").orElse(null)
        );

        scheduleService.save(goldwellSchedule);
        scheduleService.save(dragonsSchedule);
    }

    private byte[] getScheduleImage(String imageName) throws IOException {
        try {
            Path path = Paths.get("/maciek/lib/resources/pl/edu/pjwstk/snl/config/dataloader/schedules/" + imageName);
            return Files.readAllBytes(path);
        } catch (Exception e) {
            return null;
        }
    }
}
