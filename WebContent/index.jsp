<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>

<script language="Javascript">
function abrePopUp() {
	window.open('upload.jsp','popup','width=650,height=350,scrolling=auto,top=0,left=0');
}
function atualiza(div_id) {
	document.getElementById(div_id).innerHTML = 'Arquivos atualizados com sucesso';
}
</script>
<body>
	<form name="form10">
		<input type="button" onclick="abrePopUp();" value="Upload">
		<input type="button" id="atualizarTabela" onclick="atualiza('subs');" value="Atualiza" style="display:none;">
		<div id="subs">
		</div>
	</form>
</body>
</html>