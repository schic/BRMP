<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="com.wondersgroup.base.login.model.AuthInfo"%>    
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";


AuthInfo authInfo = (AuthInfo) request.getSession().getAttribute(com.wondersgroup.base.login.model.AuthConstants.SESSION_USER_CURRENT_INFO);
%> 
<!DOCTYPE html>   
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<!-- <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3.org/TR/html4/frameset.dtd"> -->  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<base href="<%=basePath%>">
<title>基础资源管理平台</title>
<%@ include  file="./common/project_myhtml.jsp" %><!-- 页面的通用静态文件引用 -->
<link rel="stylesheet" href="static/common/css/myhtml_css/index.css" />

</head>
<body>

<section>
	
<div class="headBox">
    <img class="headBoxBg" src="static/common/img/myhtml_imgs/indexTopBg.png" alt="">
    <div class="headTop clearfix">
        <div class="topLeft">
            <img src="static/common/img/myhtml_imgs/indexHeadImg1.png" alt="">
            <label for="">卫生资源管理及服务平台<span id="errorMessage" style="font-size:25px"><p><font color="red">${ requestScope.errorMessage}</font></p></span></label>
        </div>
        <div class="topRight clearfix">
            <div class="login">
                <img src="static/common/img/myhtml_imgs/indexHeadImg7.png" alt="">
                <span>
                	<a onclick="longinUser('<%=authInfo.getLoginName()%>')" class="page-scroll">欢迎登录 <%=authInfo.getPersonName()%></a>
                </span>
            </div>
            <div class="det">
                <span class="itemDet active">
                    <img src="static/common/img/myhtml_imgs/indexHeadImg2.png" alt="">
                    <a href="#home">首页</a>
                </span>
                <span class="itemDet">
                    <img src="static/common/img/myhtml_imgs/indexHeadImg3.png" alt="">
                    <a href="resource_catalog/catalog">资源目录</a>
                </span>
                <span class="itemDet">
                    <img src="static/common/img/myhtml_imgs/indexHeadImg4.png" alt="">
                    <a href="model_admin/datatype">数据模型</a>
                </span>
                <span class="itemDet">
                    <img src="static/common/img/myhtml_imgs/indexHeadImg5.png" alt="">
                    <a href="statistics/statistics">开放统计</a>
                </span>
                <span class="itemDet">
                    <img src="static/common/img/myhtml_imgs/indexHeadImg6.png" alt="">
                    <a href="apply_management/apply">申请管理</a>
                </span>
            </div>
        </div>
    </div>
    <div class="headCon">
        <div class="conFont">
            <div class="conBg conBgOne">
                <p class="p1">现接入<label for="">8</label>个部门<i></i><label for="">28</label>个系统数据资源<i></i><label for="">68</label>个数据模型</p>
                <p class="p2">总计<label for="">1232,435</label>条数据记录</p>
            </div>
            <div class="conBg conBgTwo">
                <p class="p1">数据提供给<label for="">8</label>个部门使用<i></i>为<label for="">4</label>个系统数据资源服务   </p>
                <p class="p2"> 总计服务调用<label for="">435</label>次</p>
            </div>
        </div>
    </div>
    <div class="headBot">
        <div class="botSer">
            <span class="inStyle">
                <img src="static/common/img/myhtml_imgs/indexHeadImg8.png" alt="">
                <input type="text" placeholder="输入要查询的关键字">
            </span>
            <span class="inStyle serFont">搜索</span>
        </div>
    </div>
</div>
	
    <div class="main">
        <div class="oneCloude">
            <div class="one clearfix">
                <div class="same sameOne">
                    <div class="sameBg">
                        <div class="topImg">
                            <img src="static/common/img/myhtml_imgs/indexMainImg1.png" alt="">
                            <div>
                                <span>最新数据</span>
                                <p></p>
                            </div>
                        </div>
                        <div class="botCon">
                            <ul>
                                <li class="liOne">
                                    <span class="sp1">
                                        <img src="static/common/img/myhtml_imgs/indexMainImg4.png" alt="">
                                        <label for="">医疗卫生机构信息</label>
                                    </span>
                                    <span class="sp2">2019-8-23</span>
                                </li>
                                <li>
                                    <span class="sp1">
                                        <img src="static/common/img/myhtml_imgs/indexMainImg4.png" alt="">
                                        <label for="">医疗卫生标准科室信息</label>
                                    </span>
                                    <span class="sp2">2019-8-23</span>
                                </li>
                                <li>
                                    <span class="sp1">
                                        <img src="static/common/img/myhtml_imgs/indexMainImg4.png" alt="">
                                        <label for="">医疗卫生医护人员信息</label>
                                    </span>
                                    <span class="sp2">2019-8-23</span>
                                </li>
                                <li>
                                    <span class="sp1">
                                        <img src="static/common/img/myhtml_imgs/indexMainImg4.png" alt="">
                                        <label for="">全员人口信息</label>
                                    </span>
                                    <span class="sp2">2019-8-23</span>
                                </li>
                                <li>
                                    <span class="sp1">
                                        <img src="static/common/img/myhtml_imgs/indexMainImg4.png" alt="">
                                        <label for="">医疗设备信息</label>
                                    </span>
                                    <span class="sp2">2019-8-23</span>
                                </li>
                            </ul>
                            <div class="more">
                                <a href="">
                                    <img src="static/common/img/myhtml_imgs/indexMainImg7.png" alt="">
                                    <span>更多</span>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="same sameTwo">
                    <div class="sameBg">
                        <div class="topImg">
                            <img src="static/common/img/myhtml_imgs/indexMainImg2.png" alt="">
                            <div>
                                <span>数据接口调用最多</span>
                                <p></p>
                            </div>
                        </div>
                        <div class="botCon">
                            <ul>
                                <li class="liOne">
                                    <span class="sp1">
                                        <img src="static/common/img/myhtml_imgs/indexMainImg5.png" alt="">
                                        <label for="">药品编码信息</label>
                                    </span>
                                    <span class="sp2"><label for="">598,678,678</label>次</span>
                                </li>
                                <li>
                                    <span class="sp1">
                                        <img src="static/common/img/myhtml_imgs/indexMainImg5.png" alt="">
                                        <label for="">行政区划代码信息</label>
                                    </span>
                                    <span class="sp2"><label for="">598,678,678</label>次</span>
                                </li>
                                <li>
                                    <span class="sp1">
                                        <img src="static/common/img/myhtml_imgs/indexMainImg5.png" alt="">
                                        <label for="">疾病诊断编码信息</label>
                                    </span>
                                    <span class="sp2"><label for="">678,678</label>次</span>
                                </li>
                                <li>
                                    <span class="sp1">
                                        <img src="static/common/img/myhtml_imgs/indexMainImg5.png" alt="">
                                        <label for="">手术编码信息</label>
                                    </span>
                                    <span class="sp2"><label for="">8,678</label>次</span>
                                </li>
                                <li>
                                    <span class="sp1">
                                        <img src="static/common/img/myhtml_imgs/indexMainImg5.png" alt="">
                                        <label for="">乡镇街道编码信息</label>
                                    </span>
                                    <span class="sp2"><label for="">678,678</label>次</span>
                                </li>
                            </ul>
                            <div class="more">
                                <a href="">
                                    <img src="static/common/img/myhtml_imgs/indexMainImg7.png" alt="">
                                    <span>更多</span>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="same sameThree">
                    <div class="sameBg">
                        <div class="topImg">
                            <img src="static/common/img/myhtml_imgs/indexMainImg3.png" alt="">
                            <div>
                                <span>最新数据</span>
                                <p></p>
                            </div>
                        </div>
                        <div class="botCon">
                            <ul>
                                <li class="liOne">
                                    <span class="sp1">
                                        <img src="static/common/img/myhtml_imgs/indexMainImg6.png" alt="">
                                        <label for="">四川省全民健康信息平台</label>
                                    </span>
                                </li>
                                <li>
                                    <span class="sp1">
                                        <img src="static/common/img/myhtml_imgs/indexMainImg6.png" alt="">
                                        <label for="">卫生健康统计数据综合采集与决策支持系统</label>
                                    </span>
                                </li>
                                <li>
                                    <span class="sp1">
                                        <img src="static/common/img/myhtml_imgs/indexMainImg6.png" alt="">
                                        <label for="">四川省卫生标准网</label>
                                    </span>
                                </li>
                            </ul>
                            <div class="more">
                                <a href="">
                                    <img src="static/common/img/myhtml_imgs/indexMainImg7.png" alt="">
                                    <span>更多</span>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="two">
            <img class="twoBg" src="static/common/img/myhtml_imgs/mainTwoBg.png" alt="">
            <div class="top">
                <img src="static/common/img/myhtml_imgs/indexMainLeft.png" alt="">
                <span>数据提供部门</span>
                <img src="static/common/img/myhtml_imgs/indexMainRight.png" alt="">
            </div>
            <div class="con">
                <div class="conLi clearfix">
                    <div class="item">
                        <div class="itemBg">
                            <img src="static/common/img/myhtml_imgs/indexMainTwo1.png" alt="">
                            <span>四川省卫生信息中心</span>
                        </div>
                    </div>
                    <div class="item">
                        <div class="itemBg">
                            <img src="static/common/img/myhtml_imgs/indexMainTwo1.png" alt="">
                            <span>四川省药具管理中心</span>
                        </div>
                    </div>
                    <div class="item">
                        <div class="itemBg">
                            <img src="static/common/img/myhtml_imgs/indexMainTwo1.png" alt="">
                            <span>四川省政策和医学情报研究所</span>
                        </div>
                    </div>
                    <div class="item">
                        <div class="itemBg">
                            <img src="static/common/img/myhtml_imgs/indexMainTwo1.png" alt="">
                            <span>四川省医疗卫生服务指导中心</span>
                        </div>
                    </div>
                </div>
                <div class="conLi clearfix conLiTwo">
                    <div class="item">
                        <div class="itemBg">
                            <img src="static/common/img/myhtml_imgs/indexMainTwo1.png" alt="">
                            <span>四川省食品安全中心</span>
                        </div>
                    </div>
                    <div class="item itemDiff">
                        <div class="itemBg">
                            <img src="static/common/img/myhtml_imgs/indexMainTwo2.png" alt="">
                            <span><a href="">更多</a></span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <!-- 页面通用底部引用 -->
	<%@ include file="./common/bottom_myhtml.jsp" %>
</section>

</body>
</html>