(window["webpackJsonp"] = window["webpackJsonp"] || []).push([["main"],{

/***/ "./src/$$_lazy_route_resource lazy recursive":
/*!**********************************************************!*\
  !*** ./src/$$_lazy_route_resource lazy namespace object ***!
  \**********************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

function webpackEmptyAsyncContext(req) {
	// Here Promise.resolve().then() is used instead of new Promise() to prevent
	// uncaught exception popping up in devtools
	return Promise.resolve().then(function() {
		var e = new Error("Cannot find module '" + req + "'");
		e.code = 'MODULE_NOT_FOUND';
		throw e;
	});
}
webpackEmptyAsyncContext.keys = function() { return []; };
webpackEmptyAsyncContext.resolve = webpackEmptyAsyncContext;
module.exports = webpackEmptyAsyncContext;
webpackEmptyAsyncContext.id = "./src/$$_lazy_route_resource lazy recursive";

/***/ }),

/***/ "./src/app/app.component.css":
/*!***********************************!*\
  !*** ./src/app/app.component.css ***!
  \***********************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ""

/***/ }),

/***/ "./src/app/app.component.html":
/*!************************************!*\
  !*** ./src/app/app.component.html ***!
  \************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<app-class-select></app-class-select>\n\n\n"

/***/ }),

/***/ "./src/app/app.component.ts":
/*!**********************************!*\
  !*** ./src/app/app.component.ts ***!
  \**********************************/
/*! exports provided: AppComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AppComponent", function() { return AppComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};

var AppComponent = /** @class */ (function () {
    function AppComponent() {
        this.title = 'index';
    }
    AppComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-root',
            template: __webpack_require__(/*! ./app.component.html */ "./src/app/app.component.html"),
            styles: [__webpack_require__(/*! ./app.component.css */ "./src/app/app.component.css")]
        })
    ], AppComponent);
    return AppComponent;
}());



/***/ }),

/***/ "./src/app/app.module.ts":
/*!*******************************!*\
  !*** ./src/app/app.module.ts ***!
  \*******************************/
/*! exports provided: AppModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AppModule", function() { return AppModule; });
/* harmony import */ var _angular_platform_browser__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/platform-browser */ "./node_modules/@angular/platform-browser/fesm5/platform-browser.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/forms */ "./node_modules/@angular/forms/fesm5/forms.js");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/fesm5/http.js");
/* harmony import */ var _app_component__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ./app.component */ "./src/app/app.component.ts");
/* harmony import */ var _upload_upload_component__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ./upload/upload.component */ "./src/app/upload/upload.component.ts");
/* harmony import */ var _class_select_class_select_component__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ./class-select/class-select.component */ "./src/app/class-select/class-select.component.ts");
/* harmony import */ var _class_select_class_button_class_button_component__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! ./class-select/class-button/class-button.component */ "./src/app/class-select/class-button/class-button.component.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};








var AppModule = /** @class */ (function () {
    function AppModule() {
    }
    AppModule = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["NgModule"])({
            declarations: [
                _app_component__WEBPACK_IMPORTED_MODULE_4__["AppComponent"],
                _upload_upload_component__WEBPACK_IMPORTED_MODULE_5__["UploadComponent"],
                _class_select_class_select_component__WEBPACK_IMPORTED_MODULE_6__["ClassSelectComponent"],
                _class_select_class_button_class_button_component__WEBPACK_IMPORTED_MODULE_7__["ClassButtonComponent"],
            ],
            imports: [
                _angular_platform_browser__WEBPACK_IMPORTED_MODULE_0__["BrowserModule"],
                _angular_forms__WEBPACK_IMPORTED_MODULE_2__["FormsModule"],
                _angular_common_http__WEBPACK_IMPORTED_MODULE_3__["HttpClientModule"],
            ],
            providers: [],
            bootstrap: [_app_component__WEBPACK_IMPORTED_MODULE_4__["AppComponent"]]
        })
    ], AppModule);
    return AppModule;
}());



/***/ }),

/***/ "./src/app/class-select/class-button/class-button.component.css":
/*!**********************************************************************!*\
  !*** ./src/app/class-select/class-button/class-button.component.css ***!
  \**********************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ""

/***/ }),

/***/ "./src/app/class-select/class-button/class-button.component.html":
/*!***********************************************************************!*\
  !*** ./src/app/class-select/class-button/class-button.component.html ***!
  \***********************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<span>{{className}}</span>\r\n"

/***/ }),

/***/ "./src/app/class-select/class-button/class-button.component.ts":
/*!*********************************************************************!*\
  !*** ./src/app/class-select/class-button/class-button.component.ts ***!
  \*********************************************************************/
/*! exports provided: ClassButtonComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ClassButtonComponent", function() { return ClassButtonComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};

var ClassButtonComponent = /** @class */ (function () {
    function ClassButtonComponent(name) {
        this.className = "";
        this.className = name;
    }
    ClassButtonComponent.prototype.ngOnInit = function () {
    };
    ClassButtonComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-class-button',
            template: __webpack_require__(/*! ./class-button.component.html */ "./src/app/class-select/class-button/class-button.component.html"),
            styles: [__webpack_require__(/*! ./class-button.component.css */ "./src/app/class-select/class-button/class-button.component.css")]
        }),
        __metadata("design:paramtypes", [String])
    ], ClassButtonComponent);
    return ClassButtonComponent;
}());



/***/ }),

/***/ "./src/app/class-select/class-select.component.css":
/*!*********************************************************!*\
  !*** ./src/app/class-select/class-select.component.css ***!
  \*********************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "ul {\r\n  list-style-type: none;\r\n  padding: 10pt;\r\n  text-align: center;\r\n}\r\nli {\r\n  padding: 10pt;\r\n}\r\ndiv {\r\n  text-align: center;\r\n}\r\nh1 {\r\n  font-size: 72px;\r\n}\r\n.button {\r\n  padding: 10pt;\r\n  margin: 10pt;\r\n}\r\nbutton {\r\n  margin: 20pt;\r\n}\r\n"

/***/ }),

/***/ "./src/app/class-select/class-select.component.html":
/*!**********************************************************!*\
  !*** ./src/app/class-select/class-select.component.html ***!
  \**********************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "\r\n<ul class=\"classes\" *ngIf=\"ifNoSelectedClass()\">\r\n  <li *ngFor=\"let cl of classButtons\">\r\n    <button class=\"button\" (click) =\"selectClass(cl.className)\">{{cl.className}}</button>\r\n  </li>\r\n</ul>\r\n<div *ngIf=\"ifSelectedClass()\">\r\n  <h1>{{selectedClass}}</h1>\r\n  <app-upload></app-upload>\r\n  <span>\r\n    <button (click)=\"unSelect()\">Back</button>\r\n  </span>\r\n</div>\r\n\r\n\r\n"

/***/ }),

/***/ "./src/app/class-select/class-select.component.ts":
/*!********************************************************!*\
  !*** ./src/app/class-select/class-select.component.ts ***!
  \********************************************************/
/*! exports provided: ClassSelectComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ClassSelectComponent", function() { return ClassSelectComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/fesm5/http.js");
/* harmony import */ var util__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! util */ "./node_modules/util/util.js");
/* harmony import */ var util__WEBPACK_IMPORTED_MODULE_2___default = /*#__PURE__*/__webpack_require__.n(util__WEBPACK_IMPORTED_MODULE_2__);
/* harmony import */ var _class_button_class_button_component__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./class-button/class-button.component */ "./src/app/class-select/class-button/class-button.component.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};




var ClassSelectComponent = /** @class */ (function () {
    function ClassSelectComponent(http) {
        var _this = this;
        this.http = http;
        this.selectedClass = "";
        this.classButtons = [];
        var req = new _angular_common_http__WEBPACK_IMPORTED_MODULE_1__["HttpRequest"]('GET', "http://localhost:4000/get_request", {
            headers: new _angular_common_http__WEBPACK_IMPORTED_MODULE_1__["HttpHeaders"]({
                "Content-Type": "application/json"
            }),
            reportProgress: true,
            withCredentials: true
        });
        this.http.request(req).subscribe(function (event) {
            if (event instanceof _angular_common_http__WEBPACK_IMPORTED_MODULE_1__["HttpResponse"]) {
                console.log("HEARD BACK!");
                var i = 0;
                _this.classButtons = [];
                while (!Object(util__WEBPACK_IMPORTED_MODULE_2__["isUndefined"])(event.body[i])) {
                    _this.classButtons.push(new _class_button_class_button_component__WEBPACK_IMPORTED_MODULE_3__["ClassButtonComponent"](event.body[i].className));
                    i++;
                }
                //classes = String[](event.body);
                //var items = event.body;
                //classes = items.map(function (item) {
                //  return item['className'];
                //});
                //if (isNullOrUndefined(classes)) {
                //  classes = [""]
                //}
            }
        });
    }
    ClassSelectComponent.prototype.ifNoSelectedClass = function () {
        if (this.selectedClass == "") {
            return true;
        }
        return false;
    };
    ClassSelectComponent.prototype.ifSelectedClass = function () {
        if (this.selectedClass == "") {
            return false;
        }
        return true;
    };
    ClassSelectComponent.prototype.selectClass = function (classToSelect) {
        this.selectedClass = classToSelect;
    };
    ClassSelectComponent.prototype.unSelect = function () {
        this.selectedClass = "";
    };
    ClassSelectComponent.prototype.ngOnInit = function () {
    };
    ClassSelectComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-class-select',
            template: __webpack_require__(/*! ./class-select.component.html */ "./src/app/class-select/class-select.component.html"),
            styles: [__webpack_require__(/*! ./class-select.component.css */ "./src/app/class-select/class-select.component.css")]
        }),
        __metadata("design:paramtypes", [_angular_common_http__WEBPACK_IMPORTED_MODULE_1__["HttpClient"]])
    ], ClassSelectComponent);
    return ClassSelectComponent;
}());



/***/ }),

/***/ "./src/app/upload/upload.component.css":
/*!*********************************************!*\
  !*** ./src/app/upload/upload.component.css ***!
  \*********************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ""

/***/ }),

/***/ "./src/app/upload/upload.component.html":
/*!**********************************************!*\
  !*** ./src/app/upload/upload.component.html ***!
  \**********************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<div>\r\n  <!-- <input [(ngModel)]=\"file\" accept=\".zip\" type=\"file\" id=\"file\" name=\"file\" />\r\n  <button (click)=\"uploadFile()\">Add</button> -->\r\n\r\n  <input  [(ngModel)]=\"file\" accept=\".zip\" type=\"file\" id=\"file\" name=\"file\" (change)=\"fileChangeEvent($event)\" placeholder=\"Upload file...\" />\r\n<button type=\"button\" (click)=\"upload()\">Upload</button>\r\n</div>\r\n"

/***/ }),

/***/ "./src/app/upload/upload.component.ts":
/*!********************************************!*\
  !*** ./src/app/upload/upload.component.ts ***!
  \********************************************/
/*! exports provided: UploadComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "UploadComponent", function() { return UploadComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};

var UploadComponent = /** @class */ (function () {
    function UploadComponent() {
        this.filesToUpload = [];
    }
    UploadComponent.prototype.upload = function () {
        this.makeFileRequest("http://localhost:4000/upload", [], this.filesToUpload).then(function (result) {
            console.log(result);
        }, function (error) {
            console.error(error);
        });
    };
    UploadComponent.prototype.fileChangeEvent = function (fileInput) {
        this.filesToUpload = fileInput.target.files;
    };
    UploadComponent.prototype.makeFileRequest = function (url, params, files) {
        return new Promise(function (resolve, reject) {
            var formData = new FormData();
            var xhr = new XMLHttpRequest();
            for (var i = 0; i < files.length; i++) {
                formData.append("uploads[]", files[i], files[i].name);
            }
            xhr.onreadystatechange = function () {
                if (xhr.readyState == 4) {
                    if (xhr.status == 200) {
                        resolve(JSON.parse(xhr.response));
                    }
                    else {
                        reject(xhr.response);
                    }
                }
            };
            xhr.open("POST", url, true);
            xhr.send(formData);
        });
    };
    // constructor(private http: HttpClient) { }
    // uploadFile() {
    //   if (!isNullOrUndefined(this.file)) {
    //     console.log(this.file.size);
    //     console.log(this.file.type);
    //     console.log(this.file);
    //     const formData: FormData = new FormData();
    //     formData.append('file', this.file);
    //     const req = new HttpRequest('POST', "http://localhost:4000/post_request", formData, {
    //       headers: new HttpHeaders({
    //         "Content-Type": "application/json"
    //       }),
    //       reportProgress: true,
    //       withCredentials: true
    //     });
    //     this.http.request(req).subscribe(event => {
    //       if (event instanceof HttpResponse) {
    //         return;
    //       }
    //     });
    //   } else {
    //     console.log("BAD!");
    //   }
    // }
    UploadComponent.prototype.ngOnInit = function () {
    };
    UploadComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-upload',
            template: __webpack_require__(/*! ./upload.component.html */ "./src/app/upload/upload.component.html"),
            styles: [__webpack_require__(/*! ./upload.component.css */ "./src/app/upload/upload.component.css")]
        }),
        __metadata("design:paramtypes", [])
    ], UploadComponent);
    return UploadComponent;
}());



/***/ }),

/***/ "./src/environments/environment.ts":
/*!*****************************************!*\
  !*** ./src/environments/environment.ts ***!
  \*****************************************/
/*! exports provided: environment */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "environment", function() { return environment; });
// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.
var environment = {
    production: false
};
/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.


/***/ }),

/***/ "./src/main.ts":
/*!*********************!*\
  !*** ./src/main.ts ***!
  \*********************/
/*! no exports provided */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_platform_browser_dynamic__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/platform-browser-dynamic */ "./node_modules/@angular/platform-browser-dynamic/fesm5/platform-browser-dynamic.js");
/* harmony import */ var _app_app_module__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./app/app.module */ "./src/app/app.module.ts");
/* harmony import */ var _environments_environment__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./environments/environment */ "./src/environments/environment.ts");




if (_environments_environment__WEBPACK_IMPORTED_MODULE_3__["environment"].production) {
    Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["enableProdMode"])();
}
Object(_angular_platform_browser_dynamic__WEBPACK_IMPORTED_MODULE_1__["platformBrowserDynamic"])().bootstrapModule(_app_app_module__WEBPACK_IMPORTED_MODULE_2__["AppModule"])
    .catch(function (err) { return console.error(err); });


/***/ }),

/***/ 0:
/*!***************************!*\
  !*** multi ./src/main.ts ***!
  \***************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__(/*! C:\Users\footezo\Desktop\Git\Senior Project\compile.io\Frontend\index\src\main.ts */"./src/main.ts");


/***/ })

},[[0,"runtime","vendor"]]]);
//# sourceMappingURL=main.js.map