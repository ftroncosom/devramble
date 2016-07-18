
// inheritance-based polymorphism
// base class

var Base = function(){
}
Base.prototype.greet = function(){
  console.log("Hello from Base class");
}
// create subclasses
var Sub1 = function(){
  Base.call(this);
}
Sub1.prototype = Object.create(Base.prototype);
// override 'greet' function
Sub1.prototype.greet = function(){
  console.log("Hello from Sub1 class");
}

var Sub2 = function(){
  Base.call(this);
}
Sub2.prototype = Object.create(Base.prototype);
// override 'greet' function
Sub2.prototype.greet = function(){
  console.log("Hello from Sub2 class");
}

// test function will use array of Base objects and invoke greet
// function on each one
var test = function(data){
  for(var i = 0;i < data.length;i++){
    data[i].greet();
  }
}
// create obejcts and call test function
var o1 = new Base();
var o2 = new Sub1();
var o3 = new Sub2();
var objects = [o1,o2,o3];

test(objects);

// another kind of polymorphism is called 'duck typing'
// object need not be related, but must contain same method signatures

var O1 = function(){
}
O1.prototype.sum = function(x,y){
  return x + y;
}

var O2 = function(){
}
O2.prototype.sum = function(x,y){
  return x * y;
}

var op1 = new O1();
var op2 = new O2();
// expects each operand to have method 'sum'
var duckTest = function(a,b){
  console.log("a: " + a.sum(2,3));
  console.log("b: " + b.sum(2,3));
}

duckTest(op1, op2);