<%--
  Created by IntelliJ IDEA.
  User: ZHAN545
  Date: 2016/12/6
  Time: 14:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>订单</title>
    <script src="../js/jquery-3.1.1.min.js"></script>
    <script src="../js/angular.min.js"></script>
    <script>
        var myApp=angular.module("myApp",[]);
        myApp.controller("myCtrl",function ($scope,$http) {
            $scope.pageNo=1;
            $scope.pageSize=5;
            $scope.pageTotal=1;
            $scope.orders;
            $scope.queryCondition;
            $scope.queryMode=0;
            $scope.queryOrder=function () {
                $http({
                    url:"queryOrders",
                    method:"post",
                    headers:{"Content-Type":"application/json;charset:UTF-8"},
                    params:{pageNo:$scope.pageNo,pageSize:$scope.pageSize}
                }).success(function(data){
                    $scope.pageTotal=data.pageTotal;
                    $scope.orders=data.orderModels;
                })
            }
            $scope.confirmMode=function () {
                if($scope.queryMode==0){
                    $scope.queryOrder();
                }
                if($scope.queryMode==1){
                    $scope.queryByOid();
                }
            }
            $scope.pre=function(){
                $scope.pageNo-=1;
                $scope.confirmMode();
            }
            $scope.next=function () {
                $scope.pageNo+=1;
                $scope.confirmMode();
            }
            $scope.queryByOid=function () {
                $http({
                    url:"queryByOid",
                    method:"post",
                    headers:{"Content-Type":"application/json;charset:UTF-8"},
                    params:{pageNo:$scope.pageNo,pageSize:$scope.pageSize,data:$scope.queryCondition}
                }).success(function (data) {
                    $scope.pageTotal=data.pageTotal;
                    $scope.orders=data.orderModels;
                })
            }
            $scope.query=function () {
                $scope.pageNo=1;
                $scope.confirmMode();
            }
            $scope.queryOrder();
        })
    </script>
</head>
<body>
    <div ng-app="myApp" ng-controller="myCtrl">
        <select ng-model="queryMode">
            <option value="1">订单号</option>
            <option value="2">渠道订单号</option>
            <option value="3">订单状态</option>
            <option value="4">支付方式</option>
            <option value="5">物流公司</option>
            <option value="6">省</option>
            <option value="7">市</option>
            <option value="8">区</option>
            <option value="9">收货人手机号</option>
        </select>
        <input type="text" ng-model="queryCondition">{{queryMode}}
        <input type="button" value="查询" ng-click="query()">
        <table>
            <tr ng-repeat="x in orders">
                <td>{{x.id}}</td>
                <td><a href="modify?oId={{x.oid}}">{{x.oid}}</a></td>
                <td>{{x.channeloid}}</td>
                <td>{{x.orderstatus}}</td>
                <td>{{x.orderform}}</td>
                <td>{{x.ordertime}}</td>
            </tr>
        </table>
        <input type="button" value="上一页" ng-click="pre()" ng-disabled="pageNo==1">
        第{{pageNo}}页 共{{pageTotal}}页
        <input type="button" value="下一页" ng-click="next()" ng-disabled="pageNo==pageTotal">
    </div>
</body>
</html>
