var WPAngularStarter = angular.module('wp-angular-starter', [
    'ui.router',
    'ngResource',
    'pascalprecht.translate',
    'smart-table',
    'mgcrea.ngStrap',
    'toastr',
    'angular-loading-bar',
    'ui.select',
    'ngQuickDate',
    'uiRouterStyles',
    'ngMessages',
    'angularUtils.directives.dirPagination',
    'ui.tinymce',
    'google.places',
    'ngMap',
    'ngNotificationsBar']);

WPAngularStarter.constant("serverURL", "http://localhost:8080");
WPAngularStarter.constant("apiURL", "http://localhost:8080/api");
WPAngularStarter.constant("siteURL", "http://localhost:8000/#");
WPAngularStarter.constant("adminURL", "http://localhost:8000/admin");

WPAngularStarter.config(function (paginationTemplateProvider, notificationsConfigProvider) {
    tinyMCE.baseURL = '/bower_components/tinymce-dist';

    // for paginationTemplateProvider
    paginationTemplateProvider.setPath('../bower_components/angular-utils-pagination/dirPagination.tpl.html');
    //for notificationsConfigProvider
    notificationsConfigProvider.setAutoHide(true);
    notificationsConfigProvider.setHideDelay(3000);

    notificationsConfigProvider.setAcceptHTML(false);
    notificationsConfigProvider.setAutoHideAnimation('fadeOutNotifications');
    notificationsConfigProvider.setAutoHideAnimationDelay(1200);
});

WPAngularStarter.run(function ($rootScope, $state, adminURL) {
    $rootScope.isLoggedIn = false;
    $rootScope.isAdmin = false;
    $rootScope.userId = -1;

    $rootScope.createCookie = function (name, value, days) {
        var expires;

        if (days) {
            var date = new Date();
            date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
            expires = "; expires=" + date.toGMTString();
        } else {
            expires = "";
        }
        document.cookie = encodeURIComponent(name) + "=" + encodeURIComponent(value) + expires + "; path=/";
    };

    $rootScope.readCookie = function (name) {
        var nameEQ = encodeURIComponent(name) + "=";
        var ca = document.cookie.split(';');
        for (var i = 0; i < ca.length; i++) {
            var c = ca[i];
            while (c.charAt(0) === ' ') c = c.substring(1, c.length);
            if (c.indexOf(nameEQ) === 0) return decodeURIComponent(c.substring(nameEQ.length, c.length));
        }
        return null;
    };

    $rootScope.eraseCookie = function (name) {
        $rootScope.createCookie(name, "", -1);
    };


    $rootScope.logout = function () {
        $rootScope.eraseCookie("userId");
        $rootScope.eraseCookie("userIsAdmin");
        $rootScope.eraseCookie("userName");
        $rootScope.isLoggedIn = false;
        $rootScope.isAdmin = false;
        $rootScope.userId = null;
        $state.go("home");
    };

    $rootScope.adminPanel = function () {
        window.location.href = adminURL;
    };

    $(document).ready(function () {
        if ($rootScope.userId = $rootScope.readCookie("userId")) {
            $rootScope.isLoggedIn = true;
            $rootScope.isAdmin = $rootScope.readCookie("userIsAdmin");
            $rootScope.userName = $rootScope.readCookie("userName");
        }

    });

    $rootScope.tinymceOptions = {
        onChange: function (e) {
            // put logic here for keypress and cut/paste changes
        }
        ,
        inline: false,
        plugins: 'advlist autolink link lists charmap print preview',
        skin: 'lightgray',
        theme: 'modern'
    };
});

