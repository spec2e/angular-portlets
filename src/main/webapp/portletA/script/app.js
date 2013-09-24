/*jslint browser : true, continue : true,
 devel : true, indent : 2, maxerr : 50,
 newcap : true, nomen : true, plusplus : true,
 regexp : true, sloppy : true, vars : false,
 white : false
 */
/*global namespace, log, window, angular */

namespace('portletA');

var portletA = angular.module('portletA', ['ngResource', 'ui.router']);


portletA.config(['$stateProvider', function ($stateProvider) {
    log('portlet-A config');

    $stateProvider.state('portletA-begin', {
        views:{
            'portletA':{
                templateUrl:"portletA/begin.html"
            }
        }
    });

    $stateProvider.state('portletA-next', {
        views:{
            'portletA':{
                templateUrl:"portletA/next.html"
            }
        }
    });

}]);

portletA.run(function ($state) {
    log('portletA-begin');
    $state.go('portletA-begin');
});

