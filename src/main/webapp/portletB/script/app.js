/*jslint browser : true, continue : true,
 devel : true, indent : 2, maxerr : 50,
 newcap : true, nomen : true, plusplus : true,
 regexp : true, sloppy : true, vars : false,
 white : false
 */
/*global namespace, log, window, angular, kata */

namespace('portletB');

var portletB = angular.module('portletB', ['ngResource', 'ui.router']);

portletB.config(['$stateProvider', function ($stateProvider) {
    log('portletB.config');

    $stateProvider.state('portletB-begin', {
        views:{
            'portletB':{
                templateUrl:"portletB/begin.html"
            }
        }
    });

    $stateProvider.state('portletB-next', {
        views:{
            'portletB':{
                templateUrl:"portletB/next.html"
            }
        }
    });
}]);

portletB.run(function($state) {
    log('portletB-begin');
    $state.go('portletB-begin');
});