import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;


import static java.util.Objects.isNull;

@Slf4j
public class Hippodrome {

    // Logger log = LogManager.getLogger(Hippodrome.class);
    private final List<Horse> horses;

    public Hippodrome(List<Horse> horses) {
        if (isNull(horses)) {
            log.error("Horses list is null");
            throw new IllegalArgumentException("Horses cannot be null.");
        } else if (horses.isEmpty()) {
            log.error("Horses list is empty");
            throw new IllegalArgumentException("Horses cannot be empty.");
        }
        log.debug("Создание Hippodrome, лошадей [" + horses.size() +"]");
        this.horses = horses;
    }

    public List<Horse> getHorses() {
        return Collections.unmodifiableList(horses);
    }

    public void move() {
        horses.forEach(Horse::move);
    }

    public Horse getWinner() {
        return horses.stream()
                .max(Comparator.comparing(Horse::getDistance))
                .get();
    }
}
