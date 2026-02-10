module.exports = app => {
    const users = require("../controllers/user.controller.js");
  
    // Create a new User
    app.post("/users", users.create);
  
    // Retrieve all Users
    app.get("/users", users.findAll);
  
    // Retrieve a single User with sso
    app.get("/users/:sso", users.findOne);
  
    // Update a User with sso
    app.put("/users/:sso", users.update);
  
    // Delete a User with sso
    app.delete("/users/:sso", users.delete);
  
    // Create a new User
    app.delete("/users", users.deleteAll);
  };