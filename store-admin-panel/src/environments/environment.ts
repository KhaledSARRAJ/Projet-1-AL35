// This file can be replaced during build by using the `fileReplacements` array.
// `ng build` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: false,
  apiUrlusers : 'ec2-3-140-192-195.us-east-2.compute.amazonaws.com:8085/administrateur/publications',
  serviceUrl : 'ec2-3-140-192-195.us-east-2.compute.amazonaws.com:8085/administrateur/',
  signalementUrl :  'http://18.224.51.251:8080/aec-api-rest/signalements/',
  annoncesUrl : 'ec2-3-140-192-195.us-east-2.compute.amazonaws.com:8085/administrateur/annonces'

};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/plugins/zone-error';  // Included with Angular CLI.
