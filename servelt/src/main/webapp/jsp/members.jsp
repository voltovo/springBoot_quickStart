<%@ page import="hello.servelt.domain.member.MemberRepository" %>
<%@ page import="hello.servelt.domain.member.Member" %>
<%@ page import="java.util.List" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  // request, response 자동으로 사용 가
  MemberRepository memberRepository = MemberRepository.getInstance();
  List<Member> allMembers = memberRepository.findByAll();
%>
<html>
<head>
  <title>Title</title>
</head>
<body>
<a href="/index.html">메인</a>
<table>
  <thead>
  <th>id</th>
  <th>username</th>
  <th>age</th>
  </thead>
  <tbody>
  <%
    for (Member member : allMembers) {
      out.write("    <tr>");
      out.write("<td>" + member.getId() + "</td>");
      out.write("<td>" + member.getUsername() + "</td>");
      out.write("<td>" + member.getAge() + "</td>");
      out.write("</tr>");
    } %>
  </tbody>
</table>

</body>
</html>
