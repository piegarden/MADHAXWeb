package com.madhax.website.bootstrap;

import com.madhax.website.domain.*;
import com.madhax.website.service.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final Logger log = LoggerFactory.getLogger(DevBootstrap.class);
    private final ProjectService projectService;

    public DevBootstrap(ProjectService projectService) {
        this.projectService = projectService;
        log.debug("Running constructor...");
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        List<Project> projects = initData();
        log.debug("Saving data...");
        projectService.saveAll(projects);
        log.debug("Bootstrap complete.");
    }

    private List<Project> initData() {
        log.debug("Initializing data...");
        List<Project> projects = new ArrayList<>();

        Project project1 = new Project();
        project1.setName("MADHAX Website");
        project1.setDescription("A website for project project.");
        project1.setVersion("In development");
        project1.setRepositoryURL("https://github.com/Mad-Hax-Code/MADHAXWeb");

        project1.addFeature(new Feature(
                "Dynamically Add Projects",
                "The application allows you to add and manage multiple project."
        ));

        project1.addFeature(new Feature(
                "List Application Features",
                "Add a list of features so that users are aware of the capabilities of the application"
        ));

        project1.addFeature(new Feature(
                "Keep The Community Informed",
                "Use the log feature to keep a history of development and upcoming improvements"
        ));

        project1.addNote(new Note(
                "In Development",
                "This project is currently in development. No releases have been published as of yet."
        ));

        projects.add(project1);

        Project project2 = new Project();
        project2.setName("MADPorts");
        project2.setDescription("A mutli-threaded tcp scanning desktop application.");
        project2.setVersion("In development");
        project2.setRepositoryURL("https://github.com/Mad-Hax-Code/MADPortsCLI");

        project2.addFeature(new Feature(
                "Scan Port Ranges",
                "The application scans the tcp ports for a given range."
        ));

        project2.addFeature(new Feature(
                "Multi-threaded Scans",
                "Divide the port range between a given number of threads to increase the speed."
        ));

        project2.addFeature(new Feature(
                "Choose Timeout Value",
                "Set the timeout value for each port in order to customize your scan."
        ));

        project2.addNote(new Note(
                "In Development",
                "This project is currently in development. No releases have been published as of yet."
        ));

        Issue issue1 = new Issue();
        issue1.setIssueType(IssueType.BUG);
        issue1.setTitle("Wrong Port Count");
        issue1.setBody("ScanResult port count is returning incorrect value.");

        project2.addIssue(issue1);

        projects.add(project2);

        return projects;
    }
}
