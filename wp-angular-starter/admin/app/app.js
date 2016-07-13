var WPAngularStarter = angular.module('admin-angular-starter', [
  'ui.router',
  'ngResource',
  'pascalprecht.translate',
  'smart-table',
  'mgcrea.ngStrap',
  'toastr',
  'angular-loading-bar',
  'ui.select',
  'ngQuickDate',
  'angularUtils.directives.dirPagination']);

  WPAngularStarter.constant("serverURL", "http://localhost:8080/servlet-showcase");
  WPAngularStarter.constant("apiURL", "http://localhost:8080/servlet-showcase/api");
  WPAngularStarter.constant("siteURL", "http://localhost:8000/#");

  WPAngularStarter.filter('to_trusted', ['$sce', function($sce) {
    return function (text) {
      return $sce.trustAsHtml(text);
    };
  }]);

  WPAngularStarter.directive('fileModel', ['$parse', function ($parse) {
    return {
      restrict: 'A',
      link: function(scope, element, attrs) {
        var model = $parse(attrs.fileModel);
        var modelSetter = model.assign;

        element.bind('change', function(){
          scope.$apply(function(){
            modelSetter(scope, element[0].files[0]);
          });
        });
      }
    };
  }]);

  WPAngularStarter.config(function (paginationTemplateProvider){
    paginationTemplateProvider.setPath('../../bower_components/angularUtils-pagination/dirPagination.tpl.html');
  });

  WPAngularStarter.config(["$stateProvider", "$urlRouterProvider", function ($stateProvider, $urlRouterProvider){
    //for undefined state, redirect to home
    $urlRouterProvider.otherwise("/");

    //here we defined the state
    $stateProvider.state("app", {
      url: "/",
      views: {
        "header": {
          templateUrl: "/admin/views/header.html",
          controller: "HeaderController"
        },
        "content": {
          templateUrl: "/admin/views/home.html",
          controller: "HomeController"
        }
      }
    }).state("app.users", {
      url: "users",
      views: {
        "content@": {
          templateUrl: "/admin/views/user/users.html",
          controller: "UserController"
        }
      }
    }).state("app.userDetails", {
      url: "users/:id",
      views: {
        "content@": {
          templateUrl: "/admin/views/user/userDetails.html",
          controller: "UserDetailsController"
        },
        "userDetails@app.userDetails": {
          templateUrl: "/admin/views/user/viewUserDetails.html",
          controller: "ViewUserDetailsController"
        }
      }
    }).state("app.userDetails.edit", {
      url: "/edit",
      views: {
        "userDetails@app.userDetails": {
          templateUrl: "/admin/views/user/editUserDetails.html",
          controller: "EditUserDetailsController"
        }
      }
    }).state("app.listings", {
      url: "listings",
      views: {
        "content@": {
          templateUrl: "/admin/views/listing/listings.html",
          controller: "ListingController"
        }
      }
    }).state("app.reports", {
      url: "reports",
      views: {
        "content@": {
          templateUrl: "/admin/views/listing/reports.html",
          controller: "ReportController"
        }
      }
    }).state("app.reportDetails", {
      url: "reports/:id",
      views: {
        "content@": {
          templateUrl: "/admin/views/listing/reportDetails.html",
          controller: "ReportDetailsController"
        }
      }
    });
  }]);
