package pl.alburnus.auditing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.alburnus.auditing.model.Team;
import pl.alburnus.auditing.repository.TeamRepository;

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

    // Documentation: https://docs.spring.io/spring-data/jpa/docs/1.7.0.DATAJPA-580-SNAPSHOT/reference/html/auditing.html
//    @Bean
//    public AuditorAware<String> createAuditorProvider() {
//        return new SecurityAuditor();
//    }

//    @Bean
//    public AuditingEntityListener createAuditingListener() {
//        return new AuditingEntityListener();
//    }
//
//    public static class SecurityAuditor implements AuditorAware<String> {
//        @Override
//        public String getCurrentAuditor() {
//            return "";
//        }
//    }

}
