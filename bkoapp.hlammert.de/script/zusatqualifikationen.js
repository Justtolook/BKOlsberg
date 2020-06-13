function createZusatzqualifikationUpdateModal($id)
{
	console.log($id);
	if ($id == -1) //Error Number
		return;
	
	xmlhttp = new XMLHttpRequest();
	
	xmlhttp.onreadystatechange = function () {
		if (this.readyState == 4 && this.status == 200)
		{
			document.getElementById("updateModal").innerHTML = this.responseText;
			$(".update-modal").modal();
		}
	};

    xmlhttp.open("GET", "./zusatzqualifikationenUpdateModal.php?dbid="+$id, true);
    xmlhttp.send();
}

function deleteZusatzqualifikation($id)
{
    window.location="./utils/Zusatzqualifikationen/deleteZusatzqualifikationen.php?dbid=" +$id;
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