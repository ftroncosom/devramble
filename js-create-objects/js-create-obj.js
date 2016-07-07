
var myObject = {name: 'Name', size: 10};
console.log('name: ' + myObject.name);
console.log('size: ' + myObject.size);

myObject.city = "New York";
console.log("city: " + myObject.city);

myObject.sayHello = function(){
  console.log("saying hello");
}

myObject.sayHello();

var object2 = {tool: 'hammer', 2:3}; // we can use any valid identifier for property name
console.log("tool: " + object2.tool);
//console.log("2: " + object2.2);// this will not work
console.log("2: " + object2[2]);// this will
console.log("tool: " + object2['tool']); // this too

for(var propName in myObject){
  console.log("property name: " + propName);
  console.log("property value: " + myObject[propName]);
}

// define constructor for object
function Car(make, model){
  this.make = make;
  this.model = model;
  
  this.carName = function(){
    console.log("Car name: " + make + " " + model);
  }
}

var car1 = new Car("Honda", "Civic");
var car2 = new Car("Mercedes", "S300");

car1.carName();
car2.carName();

var car3 = new Car("Opel");
car3.carName();

// create objects using Object.create()
var Truck = {
  make: "Scania",
  power: 1400,
  ignite: function(){
    console.log(this.make + " ignite");
  }
}

var scania = Object.create(Truck);
scania.ignite();

var renault = Object.create(Truck);
renault.make = "Renault";
renault.ignite();


