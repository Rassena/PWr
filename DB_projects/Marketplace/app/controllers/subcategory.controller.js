const Subcategory = require("../models/subcategory.model.js");

// Create and Save a new Subcategory
exports.create = (req, res) => {
    // Validate request
    if (!req.body) {
      res.status(400).send({
        message: "Content can not be empty!"
      });
    }
  
    // Create a Subcategory
    const subcategory = new Subcategory({
      Name: req.body.Name,
      Color: req.body.Color,
      IconUrl: req.body.IconUrl,
      CategoryName:req.body.CategoryName
    });
  
    // Save Subcategory in the database
    Subcategory.create(subcategory, (err, data) => {
      if (err)
        res.status(500).send({
          message:
            err.message || "Some error occurred while creating the Subcategory."
        });
      else res.send(data);
    });
  };


// Retrieve all Subcategorys from the database.
exports.findAll = (req, res) => {
    Subcategory.getAll((err, data) => {
      if (err)
        res.status(500).send({
          message:
            err.message || "Some error occurred while retrieving subcategorys."
        });
      else res.send(data);
    });
  };

// Find a single Subcategory with a subcategoryName
exports.findOne = (req, res) => {
    Subcategory.findByName(req.params.categoryName, (err, data) => {
      if (err) {
        if (err.kind === "not_found") {
          res.status(404).send({
            message: `Not found Category with name ${req.params.subcategoryName}.`
          });
        } else {
          res.status(500).send({
            message: "Error retrieving Category with name " + req.params.subcategoryName
          });
        }
      } else res.send(data);
    });
  };

// Update a Subcategory identified by the subcategoryName in the request
exports.update = (req, res) => {
    // Validate Request
    if (!req.body) {
      res.status(400).send({
        message: "Content can not be empty!"
      });
    }
  
    Subcategory.updateByName(
      req.params.subcategoryName,
      new Subcategory(req.body),
      (err, data) => {
        if (err) {
          if (err.kind === "not_found") {
            res.status(404).send({
              message: `Not found Category with name ${req.params.subcategoryName}.`
            });
          } else {
            res.status(500).send({
              message: "Error updating Category with name " + req.params.subcategoryName
            });
          }
        } else res.send(data);
      }
    );
  };

// Delete a Subcategory with the specified subcategoryName in the request
exports.delete = (req, res) => {
    Subcategory.remove(req.params.subcategoryName, (err, data) => {
      if (err) {
        if (err.kind === "not_found") {
          res.status(404).send({
            message: `Not found Category with name ${req.params.subcategoryName}.`
          });
        } else {
          res.status(500).send({
            message: "Could not delete Category with name " + req.params.subcategoryName
          });
        }
      } else res.send({ message: `Category was deleted successfully!` });
    });
  };

// Delete all Subcategorys from the database.
exports.deleteAll = (req, res) => {
    Subcategory.removeAll((err, data) => {
      if (err)
        res.status(500).send({
          message:
            err.message || "Some error occurred while removing all subcategorys."
        });
      else res.send({ message: `All Subcategorys were deleted successfully!` });
    });
  };