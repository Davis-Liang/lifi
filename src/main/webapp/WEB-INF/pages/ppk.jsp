<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
<script>
function submitForm (type) {
	var aForm = document.forms['aForm'];
	if (type == 'upload') {
		aForm.action = 'submit.do';
	} else if (type == 'confirm') {
		aForm.action = 'confirm.do';
	} else if (type == 'template') {
		aForm.action = 'template.do';
	} else if (type == 'history') {
		aForm.action = 'history.do';
	} else {
		return ;
	}
	aForm.submit();
}

</script>
</head>
<body>
<h2>PPK Test Page</h2>
	<table width="100%" border=1>
		<tr>
			<td>Prod Code</td>
			<td>PPK</td>
		</tr>
		<c:forEach items="${data}" var="ppk">
			<tr>
				<td>${ppk.prodCd}</td>
				<td>${ppk.ppk}</td>
			</tr>
		</c:forEach>
	</table>

	<form name="aForm" action="" method="POST" enctype="multipart/form-data">
		<input name="file" type="file"/>
		<input name="upload" type="button" value="upload" onclick="javascript:submitForm('upload');return false;"/>
		<input name="confirm" type="button" value="confirm"  onclick="javascript:submitForm('confirm');return false;"/>
		<input name="template" type="button" value="template"  onclick="javascript:submitForm('template');return false;"/>
		<input name="history" type="button" value="history"  onclick="javascript:submitForm('history');return false;"/>
	</form>

</body>
</html>
