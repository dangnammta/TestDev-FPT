</head>
<body>
<h1>My marvellous blog</h1>


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