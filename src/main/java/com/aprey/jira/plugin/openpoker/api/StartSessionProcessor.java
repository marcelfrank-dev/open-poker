package com.aprey.jira.plugin.openpoker.api;

import com.aprey.jira.plugin.openpoker.persistence.PersistenceService;
import javax.servlet.http.HttpServletRequest;

public class StartSessionProcessor implements ActionProcessor {

    @Override
    public void process(PersistenceService persistenceService, HttpServletRequest request, String issueId) {
        final long userId = Long.parseLong(request.getParameter("userId"));

        persistenceService.startSession(issueId, userId);
    }
}
