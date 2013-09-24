
namespace('portletB.controllers');

portletB.controllers.BeginCtrl = function($scope, $state) {
    log('portletB-BeginCtrl');
    $scope.whoAmI = 'portletB-begin';
    $scope.next = function() {
        log('portletB-next pressed');
        $state.go('portletB-next');
    };
};

portletB.controllers.NextCtrl = function($scope) {
    log('portletB-next');
    $scope.whoAmI = 'portletB-next';
};


