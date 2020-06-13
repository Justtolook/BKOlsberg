function conditionNumberChanged($value) {


    var conditionString = "Condition_%count%";
    var educationCondition = "condition_%count%_education";
    var qualificationCondition = "condition_%count%_qualification";


    if (document.getElementById("numberConditions_Update") != null)
        document.getElementById("numberConditions_Update").value=$value;
    if (document.getElementById("numberConditions_Insert") != null)
    document.getElementById("numberConditions_Insert").value=$value;

    for (var i = 0; i < 5; i++)
    {
        console.log("[LOOP] i : " +i+ " >> "+conditionString.replace("%count%", i+1));
        var x = document.getElementsByClassName(conditionString.replace("%count%", i+1))
        for (var l = 0; l < x.length; l++) { x[l].style.display = "none"; }

        var x = document.getElementsByClassName(educationCondition.replace("%count%", i+1))
        for (var l = 0; l < x.length; l++) { x[l].required = false; }

        var x = document.getElementsByClassName(qualificationCondition.replace("%count%", i+1))
        for (var l = 0; l < x.length; l++) { x[l].required = false; }
    }

    for (var i = 0; i < 5; i++)
    {
        if (i < $value)
        {

            var x = document.getElementsByClassName(conditionString.replace("%count%", i+1))
            for (var l = 0; l < x.length; l++) { x[l].style.display = null; }

            var x = document.getElementsByClassName(educationCondition.replace("%count%", i+1))
            for (var l = 0; l < x.length; l++) { x[l].required = true; }

            var x = document.getElementsByClassName(qualificationCondition.replace("%count%", i+1))
            for (var l = 0; l < x.length; l++) { x[l].required = true; }
        }
    }
}


function createMainUpdateModal($id)
{
    console.log($id);
    if ($id == -1)
        return;
    xmlhttp = new XMLHttpRequest();

    xmlhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200){
            document.getElementById("updateModal").innerHTML = this.responseText;
            $(".update-modal").modal();
        }
    };

    xmlhttp.open("GET", "./mainUpdateModal.php?dbid="+$id, true);
    xmlhttp.send();
}



function allowEdit()
{
    var x = document.getElementsByClassName("input-editable");

    for (var i = 0; i < x.length; i++)
    {
        x[i].disabled= false;
    }

    document.getElementById("update-save").style.display = null;
    document.getElementById("button-edit").style.display = "none";
    document.getElementById("button-delete").style.display = null;
}

function deleteBildungsgang($id)
{
    window.location="./utils/Bildungsgang/deleteEducation.php?dbid=" +$id;
}
