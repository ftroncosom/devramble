
// base class of vehicles hierarchy
var Vehicle = function(manufacturer, model){
  this.make = manufacturer;
  this.model = model;
  
}
// here we create protoype method which will be inherited by children
Vehicle.prototype.describe = function(){
  console.log("Vehicle: " + this.make + " " + this.model);
}

var vehicle1 = new Vehicle("Opel", "Astra");
vehicle1.describe();

// create subclass for road vehicles
var RoadVehicle = function(manufacturer, model){
  // this will invoke superclass constructor
  Vehicle.call(this, manufacturer, model);
  this.engine = "V12";
  this.fuelType = "gasoline";
}
// make sure to use Object.create(). Simply assigning prototype will make it the same object, thus creating a lot of problems
RoadVehicle.prototype = Object.create(Vehicle.prototype);

var roadVehicle = new RoadVehicle("BMW", "M3");
roadVehicle.describe();

// to override describe method from Vehicle type, redefine it in Road Vehicle protoype
RoadVehicle.prototype.describe = function(){
  console.log("Road vehicle: " + this.engine + " " + this.fuelType);
}
roadVehicle.describe();

// we could also call supertype method from overriden method
RoadVehicle.prototype.describe = function(){
  console.log("==============================");
  Vehicle.prototype.describe.call(this);
  console.log("Road vehicle: " + this.engine + " " + this.fuelType);
  console.log("==============================");
}
roadVehicle.describe();

// add new method to subclass
RoadVehicle.prototype.start = function(){
  console.log("Starting road vehicle " + this.make + " " + this.model);
}
roadVehicle.start();
