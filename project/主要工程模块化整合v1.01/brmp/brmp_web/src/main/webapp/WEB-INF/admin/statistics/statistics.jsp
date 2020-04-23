<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%> 
<!DOCTYPE html>   
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<!-- <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3.org/TR/html4/frameset.dtd"> -->  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<base href="<%=basePath%>">
<title>开放统计</title>
<%@ include  file="../common/project_myhtml.jsp" %><!-- 页面的通用静态文件引用 -->

<link rel="stylesheet" href="static/common/css/myhtml_css/statistics.css" />


</head>
<body>
	<!-- 页面通用头部引用 -->
	<%@ include file="../common/head_myhtml.jsp" %>

	<div class="main">
        <div class="top clearfix">
            <div class="same">
                <img class="styOne" src="static/common/img/myhtml_imgs/TjTopImg1.png" alt="">
                <span class="styTwo">
                    <label for="">19,582,847</label><br>
                    <span>平台访问次数</span>
                </span>
                <i class="styThree"></i>
            </div>
            <div class="same">
                <img class="styOne" src="static/common/img/myhtml_imgs/TjTopImg2.png" alt="">
                <span class="styTwo">
                    <label for="">19,582,847</label><br>
                    <span>平台访问次数</span>
                </span>
                <i class="styThree"></i>
            </div>
            <div class="same">
                <img class="styOne" src="static/common/img/myhtml_imgs/TjTopImg3.png" alt="">
                <span class="styTwo">
                    <label for="">19,582,847</label><br>
                    <span>平台访问次数</span>
                </span>
                <i class="styThree"></i>
            </div>
            <div class="same">
                <img class="styOne" src="static/common/img/myhtml_imgs/TjTopImg4.png" alt="">
                <span class="styTwo">
                    <label for="">19,582,847</label><br>
                    <span>平台访问次数</span>
                </span>
            </div>
        </div>
        <div class="center clearfix">
            <div class="item itemOne">
                <div class="itemBg">
                    <div class="title">
                        <i></i>
                        <label for="">注册类型分析</label>
                    </div>
                    <div class="echartBox">
                        <div class="chart01" id="chart01"></div>
                    </div>
                </div>
            </div>
            <div class="item itemTwo">
                <div class="itemBg">
                    <div class="title">
                        <i></i>
                        <label for="">平台访问历史</label>
                    </div>
                    <div class="echartBox">
                        <div class="chart02" id="chart02"></div>
                    </div> 
                </div>
            </div>
            <div class="item itemThree">
                <div class="itemBg">
                    <div class="title">
                        <i></i>
                        <label for="">关注热度词云</label>
                    </div>
                    <div class="echartBox">
                        <div class="chart03" id="chart03"></div>
                    </div> 
                </div>
            </div>
        </div>
        <div class="bot clearfix">
            <div class="item itemOne">
                <div class="itemBg">
                    <div class="title">
                        <i></i>
                        <label for="">数据提供TOP10</label>
                    </div>
                    <div class="echartBox">
                        <div class="rank">
                            <ul>
                                <li class="number1">1</li>
                                <li class="number2">2</li>
                                <li class="number3">3</li>
                                <li>4</li>
                                <li>5</li>
                                <li>6</li>
                                <li>7</li>
                                <li>8</li>
                                <li>9</li>
                                <li>10</li>
                            </ul>
                        </div>
                        <div class="chart04" id="chart04"></div>
                    </div>
                </div>
            </div>
            <div class="item itemTwo">
                <div class="itemBg">
                    <div class="title">
                        <i></i>
                        <label for="">KPI调用TOP10</label>
                    </div>
                    <div class="echartBox clearfix">
                        <div class="rank">
                            <ul>
                                <li class="number1">1</li>
                                <li class="number2">2</li>
                                <li class="number3">3</li>
                                <li>4</li>
                                <li>5</li>
                                <li>6</li>
                                <li>7</li>
                                <li>8</li>
                                <li>9</li>
                                <li>10</li>
                            </ul>
                        </div>
                        <div class="chart05" id="chart05"></div>
                    </div> 
                </div>
            </div>
        </div>
    </div>
    
	<!-- 页面通用底部引用 -->
	<%@ include file="../common/bottom_myhtml.jsp" %>    
	
<script src="static/common/js/echarts/echarts.min.js"></script>
<script src="static/common/js/echarts/echarts-wordcloud.min.js"></script>
<script src="static/statistics/statistics_view.js"></script>	
</body>
</html>