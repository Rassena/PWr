const Purchase = require("../models/purchase.model.js");

// Create and Save a new Purchase
exports.create = (req, res) => {
    // Validate request
    if (!req.body) {
      res.status(400).send({
        message: "Content can not be empty!"
      });
    }
  
    // Create a Purchase
    const purchase = new Purchase({
      Date: req.body.Date,
      AdId: req.body.AdId,
      UserSso: req.body.UserSso,
      Paid: req.body.Paid,
    });
  
    // Save Purchase in the database
    Purchase.create(purchase, (err, data) => {
      if (err)
        res.status(500).send({
          message:
            err.message || "Some error occurred while creating the Purchase."
        });
      else res.send(data);
    });
  };


// Retrieve all Purchases from the database.
exports.findAll = (req, res) => {
    Purchase.getAll((err, data) => {
      if (err)
        res.status(500).send({
          message:
            err.message || "Some error occurred while retrieving purchases."
        });
      else res.send(data);
    });
  };

// Find a single Purchase with a purchaseId
exports.findOne = (req, res) => {
    Purchase.findById(req.params.purchaseId, (err, data) => {
      if (err) {
        if (err.kind === "not_found") {
          res.status(404).send({
            message: `Not found Purchase with id ${req.params.purchaseId}.`
          });
        } else {
          res.status(500).send({
            message: "Error retrieving Purchase with id " + req.params.purchaseId
          });
        }
      } else res.send(data);
    });
  };

// Update a Purchase identified by the purchaseId in the request
exports.update = (req, res) => {
    // Validate Request
    if (!req.body) {
      res.status(400).send({
        message: "Content can not be empty!"
      });
    }
  
    Purchase.updateById(
      req.params.purchaseId,
      new Purchase(req.body),
      (err, data) => {
        if (err) {
          if (err.kind === "not_found") {
            res.status(404).send({
              message: `Not found Purchase with id ${req.params.purchaseId}.`
            });
          } else {
            res.status(500).send({
              message: "Error updating Purchase with id " + req.params.purchaseId
            });
          }
        } else res.send(data);
      }
    );
  };

// pay a Purchase identified by the purchaseId in the request
exports.pay = (req, res) => {
  Purchase.pay(req.params.purchaseId, (err, data) => {
    if (err) {
      if (err.kind === "not_found") {
        res.status(404).send({
          message: `Not found Purchase with id ${req.params.purchaseId}.`
        });
      } else {
        res.status(500).send({
          message: "Error retrieving Purchase with id " + req.params.purchaseId
        });
      }
    } else res.send({ message: `Purchase was paid successfully with id  `  + req.params.purchaseId});
  });
};

// Delete a Purchase with the specified purchaseId in the request
exports.delete = (req, res) => {
    Purchase.remove(req.params.purchaseId, (err, data) => {
      if (err) {
        if (err.kind === "not_found") {
          res.status(404).send({
            message: `Not found Purchase with id ${req.params.purchaseId}.`
          });
        } else {
          res.status(500).send({
            message: "Could not delete Purchase with id " + req.params.purchaseId
          });
        }
      } else res.send({ message: `Purchase was deleted successfully!` });
    });
  };

// Delete all Purchases from the database.
exports.deleteAll = (req, res) => {
    Purchase.removeAll((err, data) => {
      if (err)
        res.status(500).send({
          message:
            err.message || "Some error occurred while removing all purchases."
        });
      else res.send({ message: `All Purchases were deleted successfully!` });
    });
  };

// Retrieve user's all Purchases from the database.
exports.findByUserSso = (req, res) => {
  Purchase.findByUserSso(req.params.userSso, (err, data) => {
    if (err) {
      if (err.kind === "not_found") {
        res.status(404).send({
          message: `Not found Purchase with sso ${req.params.userSso}.`
        });
      } else {
        res.status(500).send({
          message: "Error retrieving Purchase with sso " + req.params.userSso
        });
      }
    } else res.send(data);
  });
};

