<%--
  Created by IntelliJ IDEA.
  User: dex
  Date: 14.06.2021
  Time: 9:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
  <head>
    <title>Aquarium</title>

  </head>
  <style>
    .cell
    {
      background-color: dodgerblue;
      width: 100px;
      height: 100px;
    }
    .img
    {
      position: absolute;
      width: 100px;
      height: 100px;
    }



  </style>
  <body>
  <form method="post">
      Пересоздать аквариум: <input name="redraw" type="checkbox" value="unchecked">
      Количесво итераций: <input name="iterations" type="number" value="1" min="0" max="1000">
      <input type="submit">
  </form>
  <table>


      <c:forEach var="cellrow" items="${cells}" varStatus="i">
    <tr>
        <c:forEach var="Cell" items="${cellrow}" varStatus="j">
            <jsp:useBean id="Cell" type="com.Dex.Structure.Cell"/>
<%--            <jsp:useBean id="cell.herbFish" type="com.Dex.Fishes.HerbFish"/>--%>
            <c:set value="${null}" scope="page" var="hunger" />
        <td>
          <div class="cell">
                <c:if test="${Cell.herb != null}">
                  <img class="img" src="${basepath}Resources/Images/seaweed.png">
                </c:if>
                <c:if test="${Cell.stone != null}">
                  <img class="img" src="${basepath}Resources/Images/stones.png">
                </c:if>

                <c:if test="${Cell.herbFish != null}">
                  <img class="img" src="${basepath}Resources/Images/HerbFish.gif">
                    <c:set value="${Cell.herbFish.getHungry()}" scope="page" var="hunger" />
                    <c:if test="${!Cell.herbFish.isMale}">
                        <c:choose>
                            <c:when test="<%= Cell.getHerbFish().getisPregnant()%>">
                                <img class="img" src="${basepath}Resources/Images/Pregnant.png"/>
                            </c:when>
                            <c:otherwise>
<%--                                <img class="img" src="${basepath}Resources/Images/NotPregnant.png"/>--%>
                            </c:otherwise>
                        </c:choose>
                    </c:if>
                    <c:choose>
                        <c:when test="<%=Cell.getHerbFish().getAge() == 0%>">
                            <img class="img" src="${basepath}Resources/Images/Baby.png"/>
                        </c:when>
                        <c:when test="<%=Cell.getHerbFish().getAge() == 1%>">
                            <img class="img" src="${basepath}Resources/Images/Middle.png"/>
                        </c:when>
                        <c:when test="<%=Cell.getHerbFish().getAge() == 2%>">
                            <img class="img" src="${basepath}Resources/Images/Old.png"/>
                        </c:when>
                    </c:choose>
                    <c:choose>
                        <c:when test="<%=Cell.getHerbFish().getisMale()%>">
                            <img class="img" src="${basepath}Resources/Images/Male.png"/>
                        </c:when>
                        <c:otherwise>
                            <img class="img" src="${basepath}Resources/Images/Female.png"/>
                        </c:otherwise>
                    </c:choose>
<%--                    <p>${cell.herbFish}</p>--%>
                </c:if>
                <c:if test="${Cell.predatorFish != null}">
                  <img  class="img" src="${basepath}Resources/Images/PredatorFish.png">
                    <c:set value="${Cell.predatorFish.getHungry()}" scope="page" var="hunger" />
                    <c:if test="${!Cell.predatorFish.isMale}">
                        <c:choose>
                            <c:when test="<%= Cell.getPredatorFish().getisPregnant()%>">
                                <img class="img" src="${basepath}Resources/Images/Pregnant.png"/>
                            </c:when>
                            <c:otherwise>
<%--                                <img class="img" src="${basepath}Resources/Images/NotPregnant.png"/>--%>
                            </c:otherwise>
                        </c:choose>
                    </c:if>
                    <c:choose>
                        <c:when test="<%=Cell.getPredatorFish().getAge() == 0%>">
                            <img class="img" src="${basepath}Resources/Images/Baby.png"/>
                        </c:when>
                        <c:when test="<%=Cell.getPredatorFish().getAge() == 1%>">
                            <img class="img" src="${basepath}Resources/Images/Middle.png"/>
                        </c:when>
                        <c:when test="<%=Cell.getPredatorFish().getAge() == 2%>">
                            <img class="img" src="${basepath}Resources/Images/Old.png"/>
                        </c:when>
                    </c:choose>
                    <c:choose>
                        <c:when test="<%=Cell.getPredatorFish().getisMale()%>">
                            <img class="img" src="${basepath}Resources/Images/Male.png"/>
                        </c:when>
                        <c:otherwise>
                            <img class="img" src="${basepath}Resources/Images/Female.png"/>
                        </c:otherwise>
                    </c:choose>
                </c:if>

<%--              <p>${hunger}</p>--%>
              <c:if test="${hunger != null}">
                  <c:if test="${hunger == 0}">
                      <img class="img" src="${basepath}Resources/Images/Hungry.png"
                  </c:if>
                  <c:if test="${hunger == 1}">
                      <img class="img" src="${basepath}Resources/Images/SemiHungry.png"
                  </c:if>
                  <c:if test="${hunger == 2}">
                      <img class="img" src="${basepath}Resources/Images/NotHungry.png"
                  </c:if>
              </c:if>
          </div>
        </td>
        </c:forEach>

    </tr>
      </c:forEach>


  </table>
<%--  $END$--%>
  </body>
</html>
