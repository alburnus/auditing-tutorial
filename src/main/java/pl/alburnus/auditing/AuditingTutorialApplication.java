package pl.alburnus.auditing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.alburnus.auditing.model.Team;
import pl.alburnus.auditing.repository.TeamRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@CrossOrigin(origins = "http://localhost:4200")
@SpringBootApplication
@RestController
@EnableJpaAuditing
public class AuditingTutorialApplication {

    @Autowired
    private TeamRepository teamRepository;

    public static void main(String[] args) {
        SpringApplication.run(AuditingTutorialApplication.class, args);
    }

    @GetMapping("/{name}")
    public String save(@PathVariable("name") String name) {
        teamRepository.save(new Team(name));
        return "Save";
    }

    @GetMapping("/{name}/update/{newName}")
    public String update(@PathVariable("name") String name, @PathVariable("newName") String newName) {
        Team found = teamRepository.findByName(name);
        found.setName(newName);
        teamRepository.save(found);
        return "Update";
    }

    @GetMapping("/{name}/delete")
    public String delete(@PathVariable("name") String name) {
        Team found = teamRepository.findByName(name);
        teamRepository.delete(found.getId());
        return "Delete";
    }

    private List<Team> teams = Arrays.asList(
            new Team("AA TEAM"),
            new Team("BB TEAM"),
            new Team("CC TEAM")
    );

    @GetMapping("/api/team")
    public ResponseEntity<List<Team>> getAllMock() {
        return ResponseEntity.ok(teams);
    }

    @PostMapping("/api/team")
    public ResponseEntity<Team> create(@RequestBody Team team) {
        if(Objects.isNull(team.getName())) {
            return ResponseEntity.badRequest().build();
        }
        List<Team> copyTeams = new ArrayList<>(teams);
        copyTeams.add(new Team(team.getName()));
        teams = copyTeams;
        return ResponseEntity.ok(team);
    }

    // This Bean is required in case when on our Entity we use annotations: @LastModifiedBy and @CreatedBy
    // Documentation: https://docs.spring.io/spring-data/jpa/docs/1.7.0.DATAJPA-580-SNAPSHOT/reference/html/auditing.html
    @Bean
    public AuditorAware<String> createAuditorProvider() {
        return () -> "alburnus";
    }


}
