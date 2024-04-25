<!DOCTYPE html>
<html lang="en">
<head>
    <title>Students Table Chart</title>
</head>
<body>
<table border="1">
    <tr>
        <th>Group</th>
        <th>Name</th>
        <th>Task</th>
        <th>Soft Deadline</th>
        <th>Hard Deadline</th>
        <th>Build</th>
        <th>Documentation</th>
        <th>Tests total</th>
        <th>Tests passed</th>
        <th>Points</th>
    </tr>
    <#list groups as group>
        <#if group.students?size != 0>
            <#list group.students as student>
                <tr>
                <#if student?is_first>
                    <td rowspan="${group.tasks}">${group.name}</td>
                </#if>
                <#if student.assignments?size != 0>
                    <#list student.assignments as assignment>
                        <#if assignment?is_first>
                            <td rowspan="${student.assignments?size}">${student.name}</td>
                        </#if>
                        <td>${assignment.info.title}</td>
                        <td><input type="checkbox" class="soft-deadline-checkbox" data-points="${assignment.points}" data-student="${student.name}">${assignment.softDeadline} </td>
                        <td><input type="checkbox" class="hard-deadline-checkbox" data-points="${assignment.points}" data-student="${student.name}">${assignment.hardDeadline} </td>

                        <td>${assignment.build}</td>
                        <td>${assignment.docs}</td>
                        <td>${assignment.testsTotal}</td>
                        <td>${assignment.testsPassed}</td>
                        <td class="student-points">0</td>
                        </tr>
                    </#list>
                <#else>
                    <td>${student.name}</td>
                    <td colspan="5">Student isn't assigned any tasks</td>
                    </tr>
                </#if>
            </#list>
        <#else>
            <tr>
                <td>${group.name}</td>
                <td colspan="6">The group is empty</td>
            </tr>
        </#if>
    </#list>
</table>

<table border="1">
    <tr>
        <th>Group Name</th>
        <th>Student Name</th>
        <th>Student Activity Percentage</th>
    </tr>
    <#list groups as group>
        <#if group.students?size != 0>
            <#list group.students as student>
                <tr class="student-row-${student.name}">
                    <#if student?is_first>
                        <td rowspan="${group.students?size}">${group.name}</td>
                    </#if>
                    <td>${student.name}</td>
                    <td>${student.activityPercentage}%</td>
                </tr>
            </#list>
        <#else>
            <tr>
                <td>${group.name}</td>
                <td colspan="6">The group is empty</td>
            </tr>
        </#if>
    </#list>
</table>

<script>

    // Get all soft deadline checkboxes
    const softDeadlineCheckboxes = document.querySelectorAll('.soft-deadline-checkbox');
    // Get all hard deadline checkboxes
    const hardDeadlineCheckboxes = document.querySelectorAll('.hard-deadline-checkbox');

    // Function to handle checkbox change event
    function handleCheckboxSoftChange(checkboxes) {
        checkboxes.forEach((checkbox) => {
            checkbox.addEventListener('change', () => {
                // Get the corresponding points and student name
                const points = parseInt(checkbox.getAttribute('data-points'));
                const studentName = checkbox.getAttribute('data-student');
                const pointsCell = checkbox.parentNode.nextElementSibling.nextElementSibling.nextElementSibling.nextElementSibling.nextElementSibling.nextElementSibling;
                const buildCell = checkbox.parentNode.nextElementSibling.nextElementSibling;
                const buildStatus = buildCell.textContent.trim()

                if (buildStatus === "Failed to build") {
                    return;
                }

                // Increment points if checkbox is checked, else decrement
                if (checkbox.checked) {
                    pointsCell.textContent = parseInt(pointsCell.textContent) + 1;
                } else {
                    pointsCell.textContent = parseInt(pointsCell.textContent) - 1;
                }

            });
        });
    }

    // Function to handle checkbox change event
    function handleCheckboxHardChange(checkboxes) {
        checkboxes.forEach((checkbox) => {
            checkbox.addEventListener('change', () => {
                // Get the corresponding points and student name
                const points = parseInt(checkbox.getAttribute('data-points'));
                const studentName = checkbox.getAttribute('data-student');
                const pointsCell = checkbox.parentNode.nextElementSibling.nextElementSibling.nextElementSibling.nextElementSibling.nextElementSibling;
                const buildCell = checkbox.parentNode.nextElementSibling;
                const buildStatus = buildCell.textContent.trim()

                if (buildStatus === "Failed to build") {
                    return;
                }

                // Increment points if checkbox is checked, else decrement
                if (checkbox.checked) {
                    pointsCell.textContent = parseInt(pointsCell.textContent) + 1;
                } else {
                    pointsCell.textContent = parseInt(pointsCell.textContent) - 1;
                }

            });
        });
    }

    // Call the function for soft deadline checkboxes
    handleCheckboxSoftChange(softDeadlineCheckboxes);
    // Call the function for hard deadline checkboxes
    handleCheckboxHardChange(hardDeadlineCheckboxes);
</script>


</body>
</html>
