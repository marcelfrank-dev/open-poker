package com.aprey.jira.plugin.openpoker.config;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.project.Project;
import com.atlassian.jira.security.request.RequestMethod;
import com.atlassian.jira.security.request.SupportedMethods;
import com.atlassian.jira.web.action.JiraWebActionSupport;
import webwork.action.Action;
import webwork.action.ServletActionContext;

import static com.aprey.jira.plugin.openpoker.api.config.ConfigurationManager.*;

@SupportedMethods({ RequestMethod.GET })
public class PokerConfigPage extends JiraWebActionSupport {

	//List of allowed projects
	private List<String> allowedProjects = new ArrayList<>();
	//Setting for keepVotesOverview
	private boolean keepVotesOverview = false;

	/**
	 * Method is called when the page is first loaded
	 */
	@Override
	public String doDefault() throws Exception {
		allowedProjects.addAll(getStoredAllowedProjects());
		keepVotesOverview = getStoredVotesOverviewSetting();
		return Action.INPUT;
	}

	/**
	 * Method is called when the form is submitted
	 */
	@Override
	@SupportedMethods({ RequestMethod.POST })
	protected String doExecute() {
		String updatedAllowedProjects = getUpdatedAllowedProjects();
		storeAllowedProjects(updatedAllowedProjects);

		String updateKeepVotesOverview = getUpdatedKeepVotesOverview();
		storeVotesOverviewSetting(updateKeepVotesOverview);

		if ( !getHasErrorMessages() ) {
			return returnComplete("openPokerConfig!default.jspa");
		}
		return Action.INPUT;
	}

	/**
	 * Method to get the updated allowed projects from the form
	 *
	 * @return String of allowed projects
	 */
	private static String getUpdatedAllowedProjects() {
		HttpServletRequest request = ServletActionContext.getRequest();
		return request.getParameter("allowedProjects");
	}

	/**
	 * Method to get the updated keep overview setting from the form
	 *
	 * @return String of setting if votes overview should be kept after apply
	 */
	private static String getUpdatedKeepVotesOverview() {
		HttpServletRequest request = ServletActionContext.getRequest();
		return request.getParameter("keepVotesOverview");
	}

	public List<String> getAllowedProjects() {
		return allowedProjects;
	}

	public String getAllowedProjectsValue() {
		return String.join(",", allowedProjects);
	}

	public void setAllowedProjects(List<String> allowedProjects) {
		this.allowedProjects = allowedProjects;
	}

	public List<Project> getProjects() {
		return ComponentAccessor.getProjectManager().getProjects();
	}

	public String getURL() {
		return "openPokerConfig.jspa";
	}

	public boolean isKeepVotesOverview() {
		return keepVotesOverview;
	}

	public void setKeepVotesOverview(boolean keepVotesOverview) {
		this.keepVotesOverview = keepVotesOverview;
	}
}
