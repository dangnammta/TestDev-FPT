</head>
<body>
<h1>My marvellous blog</h1>
<#list his as his1>
	${his1.host}<br/>
</#list>

Field1: <input type="text" id="field1" value="Hello World!"><br>
Field2: <input type="text" id="field2"><br><br>

<button onclick="myFunction()">Copy Text</button>



<script>
function myFunction() {
    document.getElementById("field2").value = document.getElementById("field1").value;
}
</script>


</body>
</html>