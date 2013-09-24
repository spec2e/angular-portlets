
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


