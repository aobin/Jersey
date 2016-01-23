<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <%-- <script src="jscript/jquery.js"/>--%>
    <script src="http://libs.baidu.com/jquery/1.9.1/jquery.min.js"></script>
    <title>查询商品列表888</title>
    <script type="text/javascript">
        function test() {
            /*$("#aobin").val("asfafafs");*/
            console.log($("#aobin").val());
            $("#aobin").css("background-color", "blue");
            $("input.aobin1").attr("value", "qweqwe");
            $("#aobin").show();

        }

        function testSelect() {
            /*var checkedRadio = $("input:radio").filter(":checked");
             console.log(checkedRadio.val());*/
            var checkedRadio1 = $("input:radio:checked");
            console.log(checkedRadio1.attr("name") + " : " + checkedRadio1.val());
            var checkedRadio2 = $("input:radio:checked[name=aobin_name]");
            console.log(checkedRadio2.attr("name") + " : " + checkedRadio2.val());
            var checkedRadio3 = $("input:radio[name=sex][checked!=checked]");
            console.log(checkedRadio3.attr("name") + " : " + checkedRadio3.val());

            //修改 input text aobin 里面的值
            var inputTextAobin = $("input:text[name=aobin]");
            inputTextAobin.val("modified");
        }

        $(document).ready(function () {
            $("button.buttonHide").click(function () {
                $("#aobin").toggle(5000, test());
                /*$("#aobin").fadeOut();*/
                testSelect();
            });
        });

        $(document).ready(function () {
                    $("input:button[value=test]").click(function () {
                        var inputTextAobin = $("input:text[name=aobin]");
                        inputTextAobin.val(inputTextAobin.val()+"aobin");
                    });
                }
        );

        window.onload = function () {
            var ajaxBtn = document.getElementById("ajaxBtn");
            ajaxBtn.onclick = function () {
                var htpRequest = new XMLHttpRequest();
                /*htpRequest.open("get", "http://www.baidu.com", true);*/
                htpRequest.open("get", "http://localhost:8080/Jersey/items/getAllItems", true);
                htpRequest.send();
                htpRequest.onreadystatechange = function () {
                    if (htpRequest.readyState == 4) {
                        $("#ajaxText").val(htpRequest.response);

                    }
                }
            };
        }

        /* window.addEventListener("load",hello,false);*/
        function hello() {
            console.log("hello");
        }

        /* $("#testHello").click(hello());*/
        /*document.getElementById("testHello").onclick=hello();*/

    </script>
    <style type="text/css">
        .myStyle {
            color: #ff12f0;
        }
    </style>
</head>

<input id="aobin" type="text" value="${memcacheName}" class="myStyle"/>
<input type="button"  value="test" />
<br>
<input class="aobin1" type="text" value="123"/><br>
<button class="buttonHide">hide</button>
<br>

<div id="divInfo">111</div>
<input type="text" id="ajaxText"/><br>
<button id="ajaxBtn">ajaxBtn</button>
<br>
<button id="testHello">testHello</button>
<br>

<input type="radio" value="aobin" name="aobin_name" checked="checked"/><br>

<form
        action="${pageContext.request.contextPath}/items/getItemsTotalNumber"
        method="post">
    查询条件66：
    <table width="100%" border=1>
        <tr>
            <td><input type="submit" value="查询总数"/></td>
            <td><input type="radio" value="male" name="sex" checked="checked"/>male
                <input type="radio" value="female" name="sex"/>female
            </td>
            <td><input type="text" value="abc" name="aobin"></td>
        </tr>
    </table>
    商品列表：
    <table width="100%" border=1>
        <tr>
            <td>商品名称</td>
            <td>商品价格</td>
            <td>生产日期</td>
            <td>商品描述</td>
            <td>操作</td>
        </tr>
        <c:forEach items="${itemsList}" var="item">
            <tr>
                <td>${item.name }</td>
                <td>${item.price }</td>
                <td><fmt:formatDate value="${item.createtime}"
                                    pattern="yyyy-MM-dd HH:mm:ss"/></td>
                <td>${item.detail }</td>

                <td><a
                        href="${pageContext.request.contextPath }/items/editItem?id=${item.id}">修改</a></td>

            </tr>
        </c:forEach>

    </table>

</form>
</body>

</html>