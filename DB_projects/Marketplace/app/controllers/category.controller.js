const Category = require("../models/category.model.js");

// Create and Save a new Category
exports.create = (req, res) => {
    // Validate request
    if (!req.body) {
      res.status(400).send({
        message: "Content can not be empty!"
      });
    }
  
    // Create a Category
    const category = new Category({
      Name: req.body.Name,
      IconUrl: req.body.IconUrl,
      Color: req.body.Color,

    });
  
    // Save Category in the database
    Category.create(category, (err, data) => {
      if (err)
        res.status(500).send({
          message:
            err.message || "Some error occurred while creating the Category."
        });
      else res.send(data);
    });
  };


// Retrieve all Categorys from the database.
exports.findAll = (req, res) => {
    Category.getAll((err, data) => {
      if (err)
        res.status(500).send({
          message:
            err.message || "Some error occurred while retrieving categorys."
        });
      else res.send(data);
    });
  };


// Retrieve Categorys from the database by pages.
exports.getPage = (req, res) => {
  Category.getPage(req.params.page,req.params.inPage, (err, data) => {
    if (err) {
      if (err.kind === "not_found") {
        res.status(404).send({
          message: `Not found Category with page ${req.params.page}.`
        });
      } else {
        res.status(500).send({
          message: "Error retrieving Category with page " + req.params.page
        });
      }
    } else res.send(data);
  });
};

// Find a single Category with a categoryName
exports.findOne = (req, res) => {
    Category.findByName(req.params.categoryName, (err, data) => {
      if (err) {
        if (err.kind === "not_found") {
          res.status(404).send({
            message: `Not found Category with name ${req.params.categoryName}.`
          });
        } else {
          res.status(500).send({
            message: "Error retrieving Category with name " + req.params.categoryName
          });
        }
      } else res.send(data);
    });
  };


// Retrieve Categorys by categoryColor
exports.findColor = (req, res) => {
  Category.findByColor(req.params.categoryColor, (err, data) => {
    if (err) {
      if (err.kind === "not_found") {
        res.status(404).send({
          message: `Not found Categorys with Color ${req.params.categoryColor}.`
        });
      } else {
        res.status(500).send({
          message: "Error retrieving Categorys with Color " + req.params.categoryColor
        });
      }
    } else res.send(data);
  });
};


// Update a Category identified by the categoryName in the request
exports.update = (req, res) => {
    // Validate Request
    if (!req.body) {
      res.status(400).send({
        message: "Content can not be empty!"
      });
    }
  
    Category.updateByName(
      req.params.categoryName,
      new Category(req.body),
      (err, data) => {
        if (err) {
          if (err.kind === "not_found") {
            res.status(404).send({
              message: `Not found Category with name ${req.params.categoryName}.`
            });
          } else {
            res.status(500).send({
              message: "Error updating Category with name " + req.params.categoryName
            });
          }
        } else res.send(data);
      }
    );
  };

// Delete a Category with the specified categoryName in the request
exports.delete = (req, res) => {
    Category.remove(req.params.categoryName, (err, data) => {
      if (err) {
        if (err.kind === "not_found") {
          res.status(404).send({
            message: `Not found Category with name ${req.params.categoryName}.`
          });
        } else {
          res.status(500).send({
            message: "Could not delete Category with name " + req.params.categoryName
          });
        }
      } else res.send({ message: `Category was deleted successfully!` });
    });
  };

// Delete all Categorys from the database.
exports.deleteAll = (req, res) => {
    Category.removeAll((err, data) => {
      if (err)
        res.status(500).send({
          message:
            err.message || "Some error occurred while removing all categorys."
        });
      else res.send({ message: `All Categorys were deleted successfully!` });
    });
  };