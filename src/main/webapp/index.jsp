<html>
<body>
	<h2>Hello World!</h2>


	<form action="rest/upload/file" enctype="multipart/form-data" method="post">
		<!-- 这里的名字一定要和 controller  @RequestParam("file") 中配置的一致-->
		<!-- 
			public void uploadFileToMongo(HttpServletRequest req, HttpServletResponse resp,
			 @RequestParam("file") MultipartFile mFile) throws IOException {
		 -->
		<input type="file" name="file" /> <input type="submit" value="submit" />
	</form>
</body>
</html>
