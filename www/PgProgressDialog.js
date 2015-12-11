cordova.define("cordova-plugin-progressdialog.ProgressDialog", function(require, exports, module) { var exec = require('cordova/exec');

module.exports = {
    show : function(msg,cancelable) {
        exec(null, null, 'ProgressDialog', 'show',  [{message:msg, cancelable:cancelable?cancelable:true}]);
    },
    hide:function(msg,cancelable){
    	exec(null, null, 'ProgressDialog', 'hide',  [{message:msg, cancelable:cancelable?cancelable:true}]);
    }
};

});
