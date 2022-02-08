package com.marcosoft.jiraslamanagment.controller;

import com.marcosoft.jiraslamanagment.model.*;
import com.marcosoft.jiraslamanagment.repository.SlaResultsRepository;
import com.marcosoft.jiraslamanagment.service.JiraService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class Controller {

    @Autowired
    private SlaResultsRepository slaResultsRepository;

    @Autowired
    private JiraService jiraService;

    @GetMapping("apiStatus")
    public ResponseEntity apiStatus(){
        return ResponseEntity.ok("The API is Running");
    }


    @PostMapping("saveSla")
    public ResponseEntity<ResponseObject> saveSla(@RequestParam String project){

        ResponseObject responseObject = new ResponseObject();

        try{
            slaResultsRepository.deleteAll();

            IssuePickerResponse issuePickerResponse = jiraService.getIssueList(project);

            for (IssuePickerSectionIssue issue: issuePickerResponse.getSections().get(0).getIssues()) {

                JiraIssue jiraIssue = new JiraIssue(issue.getKey());

                List<SlaResult> slaResultList = jiraService.getSlaResult(jiraIssue);

                for (SlaResult slaResult: slaResultList) {
                    log.info("Saving this SLA into the database:" + slaResult.getSlaName());

                    slaResult.setIssueKey(jiraIssue.getIssueKey());
                    slaResult.setProject(jiraIssue.getIssueKey().split("-")[0]);

                    slaResultsRepository.save(slaResult);
                }

            }

            responseObject.setStatus(OperationStatus.SUCCESS);
            responseObject.setMessage("Operation Succeed");

        }catch (Exception e){
            responseObject.setStatus(OperationStatus.FAILURE);
            responseObject.setMessage(e.getMessage());
        }

        return ResponseEntity.ok(responseObject);
    }
}
