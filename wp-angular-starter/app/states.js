/**
 * Created by Mile on 03/09/2016.
 */
WPAngularStarter.config(function ($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise('/');
    $stateProvider
        .state('create-listing', {
            url: '/listing',
            templateUrl: '/views/listing/listing.html',
            controller: 'ListingController',
            data: {
                css: 'css/jquery.fileupload.css'
            }
        })
        .state('view-listings', {
            url: '/listings/{keyword}',
            templateUrl: '/views/listing/view-listings.html',
            controller: 'ViewListingsController',
            data: {
                css: 'css/listing.css'
            }
        })
        .state('search-listings', {
            url: '/search/{keyword}',
            templateUrl: '/views/listing/view-listings.html',
            controller: 'SearchListingsController',
            data: {
                css: 'css/listing.css'
            }
        })
        .state('view-listing', {
            url: '/listing/{id}',
            templateUrl: 'views/listing/view-listing.html',
            controller: 'ViewListingController',
            data: {
                css: 'css/view-listing.css'
            }
        })
        .state('edit-listing', {
            url: '/edit',
            templateUrl: 'views/listing/edit-listing.html',
            controller: 'EditListingController',
            params: {
                listing: 'null'
            },
            data: {
                css: 'css/view-listing.css'
            }
        })
        .state('sign-up', {
            url: '/signup',
            templateUrl: 'views/user/signup.html',
            controller: 'signupController',
            data: {
                css: 'css/signup.css'
            }
        })
        .state('home', {
            url: '/',
            templateUrl: 'views/home.html',
            controller: 'homeController',
            data: {
                css: 'css/home.css'
            }
        })
        .state('log-in', {
            url: '/login',
            templateUrl: 'views/user/login.html',
            controller: 'loginController',
            data: {
                css: 'css/login.css'
            }
        })
        .state('profile', {
            url: '/profile',
            templateUrl: 'views/user/user-profile.html',
            controller: 'userProfileController',
            data: {
                css: 'css/listing.css'
            }
        })
        .state("inbox", {
            url: "/messages/inbox",
            templateUrl: "views/user/messages.html",
            controller: "ReceivedMessageController"
        })
        .state("outbox", {
            url: "/messages/outbox",
            templateUrl: "views/user/messages.html",
            controller: "SentMessageController"
        })
        .state("readMessage", {
            url: "/messages/read/{id}",
            templateUrl: "views/user/messageDetails.html",
            controller: "ViewMessageController"
        });
});
