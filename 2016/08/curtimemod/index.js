
var colors = require('colors');

var themodule = {};

themodule.getCurrentTime = function(){
  var time = new Date();
  var out = time.getFullYear() + "-" + time.getMonth() + "-" + time.getDate() +
	    " " + time.getHours() + ":" + time.getMinutes() + ":" + time.getSeconds();
  console.log(out.underline.red);
}

module.exports = themodule;
