
// global scope
var x = "global"; // this variable will be accessible from everywhere

var simple = function(){
  var local = "local"; // local scope, available only within function
}
console.log("local: " + simple.local); // outputs 'local: undefined'


// closure allows us to return function reference as function return value
var closure = function(message) {
  var local =  "message: " + message;
  return function(){
    return local;
  }
}
// closure() returns function reference,we add another invocation operator '()' to 
// invoke returned function
console.log("closure: " + closure('test message')());

// by default, each property of object is public
var text = {
  words: "some words"
}
console.log("text: " + text.words);
// we can set new value without limitation
text.words = "new words";
console.log("text: " + text.words);

// we can create setter/getter methods to encapsulate 'words' property
text = {
  words: "some words",
  setWords: function(txt){
    this.words = "prefix: " + txt; 
  },
  getWords: function(){
    return this.words;
  }
}

text.setWords("word1");
console.log(text.getWords());
text.words = "another word"; // we can still set property directly
console.log(text.getWords());

var text = function(){
  var words = "some words";
  
  return {
    setWords: function(txt){
    words = "prefix: " + txt; 
    },
    getWords: function(){
      return words;
    }
  }
}(); // here, we invoke the function and assign returned value to variable 'text'

text.setWords("set words");
console.log("text: " + text.getWords()); // outputs 'prefix: set words'
text.words = "direct value"; // try to assign value to property directly
console.log("getter access: " + text.getWords()); // value of property is not changed

// this is new prooperty words implicitly created
console.log("direct access: " + text.words);
