<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
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
                	<a onclick="longinUser('${sessionScope["wondersgroup.qyws.curuser"].loginName}')" class="page-scroll">欢迎登录 ${sessionScope["wondersgroup.qyws.curuser"].personName}</a>
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
    
</div>