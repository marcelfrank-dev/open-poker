AJS.$(document).ready(() => {
	function handleAllowedProjects() {
		let $allowedProjectsSelect = AJS.$("#allowedProjectsSelect");
		$allowedProjectsSelect.auiSelect2(
			{
				placeholder: "Open poker will be available for each project",
				allowClear:  true
			});

		$allowedProjectsSelect.on("change", () => {
			AJS.$("#allowedProjects").val(AJS.$("#allowedProjectsSelect").val());
		});
	}

	function handleVotesOverview() {
		const keepVotesOverviewToggle = document.getElementById('keepVotesOverviewToggle');
		keepVotesOverviewToggle.addEventListener('change', function () {
			const isChecked              = keepVotesOverviewToggle.checked;     // new value of the toggle
			keepVotesOverviewToggle.busy = true;
			AJS.$('#keepVotesOverview').val(isChecked);
			keepVotesOverviewToggle.busy = false;
		});
	}

	const initialize = () => {
		handleAllowedProjects();
		handleVotesOverview();
	};
	//	This line disables "Changes you made may not be saved" pop-up window because it doesn't work properly
	// TODO: that should be investigated
	window.onbeforeunload = null;
	initialize();
});
