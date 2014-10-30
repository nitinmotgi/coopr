angular.module(PKG.name)
  .config(function ($stateProvider, $urlRouterProvider, $locationProvider, MYAUTH_ROLE, myHelpers) {

    var crud = myHelpers.crud.mkState,
        abstractSubnav = myHelpers.crud.abstractSubnav;


    $locationProvider
      .html5Mode(true);

    /**
     * Redirects and Otherwise
     */
    $urlRouterProvider
      .when('/signin', '/login')
      .otherwise(function($injector, $location){
        $injector.get('$state').go($location.path() ? '404' : 'home');
      });


    /**
     * State Configurations
     */
    $stateProvider

      .state('home', {
        url: '/',
        templateUrl: '/assets/features/_home/home.html',
        controller: 'HomeCtrl'
      })

      .state('404', {
        templateUrl: '/assets/features/_home/404.html'
      })

      .state('login', {
        url: '/login',
        templateUrl: '/assets/features/login/login.html',
        controller: 'LoginCtrl'
      })


      /*
        /#/clusters/...
       */

      .state(abstractSubnav('Cluster', {
        authorizedRoles: MYAUTH_ROLE.all
      }))
        .state(crud('Cluster', 'list', 'ClusterListCtrl'))
        .state(crud('Cluster', 'edit', 'ClusterFormCtrl', { title: 'Reconfigure cluster' }))
        .state(crud('Cluster', 'create', 'ClusterFormCtrl', { title: 'Create a cluster' }))
        .state(crud('Cluster', 'detail', 'ClusterDetailCtrl'))
          .state('clusters.detail.node', {
            url: '/node/:nodeId'
          })



      /*
        /#/templates/...
       */

      .state(abstractSubnav('Template', {
        title: 'Catalog',
        ddLabel: 'Cluster Templates',
        authorizedRoles: MYAUTH_ROLE.admin
      }))
        .state(crud('Template', 'list', 'CrudListCtrl'))
        .state(crud('Template', 'edit', 'TemplateFormCtrl'))
          .state('templates.edit.tab', {
            url: '/tab/:tab'
          })
        .state(crud('Template', 'create', 'TemplateFormCtrl'))
          .state('templates.create.tab', {
            url: '/tab/:tab'
          })


      /*
        /#/providers/...
       */

      .state(abstractSubnav('Provider', {
        authorizedRoles: MYAUTH_ROLE.admin
      }))
        .state(crud('Provider', 'list', 'CrudListCtrl'))
        .state(crud('Provider', 'edit', 'ProviderFormCtrl'))
        .state(crud('Provider', 'create', 'ProviderFormCtrl'))


      /*
        /#/hardwaretypes/...
       */

      .state(abstractSubnav('HardwareType', {
        title: 'Hardware',
        ddLabel: 'Hardware Types',
        authorizedRoles: MYAUTH_ROLE.admin
      }))
        .state(crud('HardwareType', 'list', 'CrudListCtrl'))
        .state(crud('HardwareType', 'edit', 'HardwareFormCtrl'))
        .state(crud('HardwareType', 'create', 'HardwareFormCtrl'))



      /*
        /#/imagetypes/...
       */

      .state(abstractSubnav('ImageType', {
        title: 'Images',
        ddLabel: 'Image Types',
        authorizedRoles: MYAUTH_ROLE.admin
      }))
        .state(crud('ImageType', 'list', 'CrudListCtrl'))
        .state(crud('ImageType', 'edit', 'ImageFormCtrl'))
        .state(crud('ImageType', 'create', 'ImageFormCtrl')) 



      /*
        /#/services/...
       */
      .state(abstractSubnav('Service', {
        authorizedRoles: MYAUTH_ROLE.admin
      }))
        .state(crud('Service', 'list', 'CrudListCtrl'))
        .state(crud('Service', 'edit', 'ServiceFormCtrl'))
        .state(crud('Service', 'create', 'ServiceFormCtrl'))


      /*
        /#/tenants/...
       */
      .state(abstractSubnav('Tenant', {
        authorizedRoles: MYAUTH_ROLE.superadmin
      }))
        .state(crud('Tenant', 'list', 'TenantListCtrl'))
        .state(crud('Tenant', 'edit', 'TenantFormCtrl'))
        .state(crud('Tenant', 'create', 'TenantFormCtrl'))


      ;




  })
  .run(function ($rootScope, $state, $alert, myAuth, MYAUTH_EVENT, MYAUTH_ROLE) {

    $rootScope.$on(MYAUTH_EVENT.loginSuccess, function () {
      $alert({title:'Welcome!', content:'Your tenant is "'+myAuth.currentUser.tenant+'".', type:'success'});
      $state.go(myAuth.currentUser.hasRole(MYAUTH_ROLE.admin) ? 'home' : 'clusters.list');
    });

    $rootScope.$on(MYAUTH_EVENT.logoutSuccess, function () {
      $alert({title:'Bye!', content:'You are now logged out.', type:'info'});
      $state.go('home');
    });

    $rootScope.$on(MYAUTH_EVENT.notAuthorized, function () {
      $alert({title:'Authentication error!', content:'You are not allowed to access the requested page.', type:'warning'});
      $state.go('home');
    });

    angular.forEach([
        MYAUTH_EVENT.loginFailed,
        MYAUTH_EVENT.sessionTimeout,
        MYAUTH_EVENT.notAuthenticated
      ],
      function (v) {
        $rootScope.$on(v, function (event) {
          $alert({title:event.name, type:'danger'});
          if(!$state.is('login')) {
            $state.go('login');
          }
        });
      }
    );

  })

  ;
