var exec = require('cordova/exec');

module.exports = {
    openMobileQQChat: function (qqNumber, success, error) {
        exec(success, error, "externalExtension", "openMobileQQChat", [qqNumber]);
    },
    openURL: function (url, success, error) {
        exec(success, error, "externalExtension", "openURL", [url]);
    }
};

