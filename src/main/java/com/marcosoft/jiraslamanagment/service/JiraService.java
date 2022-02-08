package com.marcosoft.jiraslamanagment.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.marcosoft.jiraslamanagment.client.JiraClient;
import com.marcosoft.jiraslamanagment.model.IssuePickerResponse;
import com.marcosoft.jiraslamanagment.model.JiraIssue;
import com.marcosoft.jiraslamanagment.model.SlaResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.http.HttpResponse;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
@Slf4j
public class JiraService {

    @Autowired
    private JiraClient jiraClient;

    ObjectMapper jsonMapper = new ObjectMapper();

    public IssuePickerResponse getIssueList(String project){

        HttpResponse jiraResponse =
                jiraClient.sendJiraRequest(
                        jiraClient.buildJiraRequest("GET","api/2/issue/picker?currentProjectId=" + project, "")
                );

        try {
            return jsonMapper.readValue(jiraResponse.body().toString(), IssuePickerResponse.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<SlaResult> getSlaResult(JiraIssue jiraIssue){

        HttpResponse jiraResponse =
        jiraClient.sendJiraRequest(
            jiraClient.buildJiraRequest("GET","tts-api/1.0/sla/overview/" + jiraIssue.getIssueKey(), "")
        );

        try {
            return jsonMapper.readValue(jiraResponse.body().toString(), new TypeReference<List<SlaResult>>(){});

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }
}
