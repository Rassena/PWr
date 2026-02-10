const User = require("../models/user.model.js");

// Create and Save a new User
exports.create = (req, res) => {
    // Validate request
    if (!req.body) {
      res.status(400).send({
        message: "Content can not be empty!"
      });
    }
  
    // Create a User
    const user = new User({
      Name: req.body.Name,
      LastName: req.body.LastName,
      BirthDate: req.body.BirthDate,
      Email: req.body.Email,
      Password: req.body.Password,
      SSO: req.body.SSO,
    });
  
    // Save User in the database
    User.create(user, (err, data) => {
      if (err)
        res.status(500).send({
          message:
            err.message || "Some error occurred while creating the User."
        });
      else res.send(data);
    });
  };


// Retrieve all Users from the database.
exports.findAll = (req, res) => {
    User.getAll((err, data) => {
      if (err)
        res.status(500).send({
          message:
            err.message || "Some error occurred while retrieving users."
        });
      else res.send(data);
    });
  };

// Find a single User with a sso
exports.findOne = (req, res) => {
    User.findBySso(req.params.sso, (err, data) => {
      if (err) {
        if (err.kind === "not_found") {
          res.status(404).send({
            message: `Not found User with sso ${req.params.sso}.`
          });
        } else {
          res.status(500).send({
            message: "Error retrieving User with sso " + req.params.sso
          });
        }
      } else res.send(data);
    });
  };

// Update a User identified by the sso in the request
exports.update = (req, res) => {
    // Validate Request
    if (!req.body) {
      res.status(400).send({
        message: "Content can not be empty!"
      });
    }
  
    User.updateBysso(
      req.params.sso,
      new User(req.body),
      (err, data) => {
        if (err) {
          if (err.kind === "not_found") {
            res.status(404).send({
              message: `Not found User with sso ${req.params.sso}.`
            });
          } else {
            res.status(500).send({
              message: "Error updating User with sso " + req.params.sso
            });
          }
        } else res.send(data);
      }
    );
  };

// Delete a User with the specified sso in the request
exports.delete = (req, res) => {
    User.remove(req.params.sso, (err, data) => {
      if (err) {
        if (err.kind === "not_found") {
          res.status(404).send({
            message: `Not found User with sso ${req.params.sso}.`
          });
        } else {
          res.status(500).send({
            message: "Could not delete User with sso " + req.params.sso
          });
        }
      } else res.send({ message: `User was deleted successfully!` });
    });
  };

// Delete all Users from the database.
exports.deleteAll = (req, res) => {
    User.removeAll((err, data) => {
      if (err)
        res.status(500).send({
          message:
            err.message || "Some error occurred while removing all users."
        });
      else res.send({ message: `All Users were deleted successfully!` });
    });
  };