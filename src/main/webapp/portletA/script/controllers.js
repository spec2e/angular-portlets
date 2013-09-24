/*jslint browser : true, continue : true,
 devel : true, indent : 2, maxerr : 50,
 newcap : true, nomen : true, plusplus : true,
 regexp : true, sloppy : true, vars : false,
 white : false
 */
/*global namespace, log, window, angular, portletA */

namespace('portletA.controllers');

portletA.controllers.BeginCtrl = function($scope, $state) {
    log('portletA.BeginCtrl');
    $scope.whoAmI = 'portletA.begin';

    $scope.next = function() {
        log('portletA-next pressed');
        $state.go('portletA-next');
    };
};

portletA.controllers.NextCtrl = function($scope) {
    log('portletA.NextCtrl');
    $scope.whoAmI = 'portletA.next';
};


