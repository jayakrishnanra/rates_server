'use strict';

describe('myApp.view3 module', function() {

  beforeEach(module('myApp.view3'));

  describe('view3 controller', function(){
    var scope, ctrl;
    it('should ....', inject(function($rootScope, $controller) {
      scope = $rootScope.$new();
      //spec body
      var view3Ctrl = $controller('View3Ctrl', {$scope: scope}, null, null);

    }));

  });
});